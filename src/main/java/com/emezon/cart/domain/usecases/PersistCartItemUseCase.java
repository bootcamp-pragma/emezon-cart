package com.emezon.cart.domain.usecases;

import com.emezon.cart.domain.api.IPersistCartInPort;
import com.emezon.cart.domain.api.IPersistCartItemInPort;
import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.api.IRetrieveCartItemInPort;
import com.emezon.cart.domain.constants.CartConstrains;
import com.emezon.cart.domain.constants.CartItemConstrains;
import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.models.CartItem;
import com.emezon.cart.domain.models.CartStatus;
import com.emezon.cart.domain.models.external.*;
import com.emezon.cart.domain.spi.ICartItemRepositoryOutPort;
import com.emezon.cart.domain.spi.IJwtService;
import com.emezon.cart.domain.spi.external.IArticleExternalOutPort;

import java.time.LocalDateTime;
import java.util.*;

public class PersistCartItemUseCase implements IPersistCartItemInPort {

    private final ICartItemRepositoryOutPort cartItemRepository;
    private final IRetrieveCartItemInPort retrieveCartItem;
    private final IRetrieveCartInPort retrieveCart;
    private final IPersistCartInPort persistCart;
    private final IJwtService jwtService;
    private final IArticleExternalOutPort articleExternalOutPort;

    public PersistCartItemUseCase(
            ICartItemRepositoryOutPort cartItemRepository,
            IRetrieveCartItemInPort retrieveCartItem,
            IRetrieveCartInPort retrieveCart,
            IPersistCartInPort persistCart,
            IJwtService jwtService,
            IArticleExternalOutPort articleExternalOutPort) {
        this.cartItemRepository = cartItemRepository;
        this.retrieveCartItem = retrieveCartItem;
        this.retrieveCart = retrieveCart;
        this.persistCart = persistCart;
        this.jwtService = jwtService;
        this.articleExternalOutPort = articleExternalOutPort;
    }


    @Override
    public CartItem createCartItem(CartItem cartItem) {
        String userId = validateUserAndGetId();
        if (cartItem.getQuantity() < CartItemConstrains.MIN_QUANTITY) {
            throw new RuntimeException("Quantity must be greater than 0");
        }
        Cart cart = cartItem.getCart();
        if (cart == null) {
            cart = retrieveCart.getCartByUserIdAndStatus(userId, CartStatus.ACTIVE.value()).orElse(null);
        } else {
            if (cart.getId() == null) {
                throw new RuntimeException("Cart must have an id");
            }
            cart = retrieveCart.getCartById(cart.getId()).orElse(null);
        }
        if (cartItem.getArticleId() == null) {
            throw new RuntimeException("Article id is required");
        }
        Article article = articleExternalOutPort.getArticleById(cartItem.getArticleId());
        if (article == null) {
            throw new RuntimeException("Article not found");
        }
        if (cartItem.getQuantity() > article.getStock()) {
            throw new RuntimeException("Not enough stock");
        }
        if (cart == null) {
            cart = new Cart();
            cart.setClientId(userId);
            cart.setStatus(CartStatus.ACTIVE.value());
            cartItem.setCart(cart);
            cart.setItems(List.of(cartItem));
            cart = persistCart.createCart(cart);
            return cart.getItems().get(0);
        }
        cartItem.setCart(cart);
        CartItem existingCartItem = cart.getItems().stream()
                .filter(item -> item.getArticleId().equals(cartItem.getArticleId()))
                .findFirst()
                .orElse(null);
        if (existingCartItem != null) {
            if (existingCartItem.getQuantity() + cartItem.getQuantity() > article.getStock()) {
                throw new RuntimeException("Not enough stock");
            }
            existingCartItem.setCart(cart);
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
//            return cartItemRepository.save(existingCartItem);
            cart.setUpdatedAt(LocalDateTime.now());
            persistCart.updateCart(cart);
            return existingCartItem;
        }
        List<String> articleIds = cart.getItems().stream().map(CartItem::getArticleId).toList();
        Set<String> distinctCategories = new HashSet<>();
        for (String articleId : articleIds) {
            Article existingArticle = articleExternalOutPort.getArticleById(articleId);
            if (existingArticle != null && existingArticle.getCategories() != null) {
                List<Category> categories = existingArticle.getCategories();
                for (Category category : categories) {
                    distinctCategories.add(category.getName());
                }
                if (distinctCategories.size() >= CartConstrains.MAX_CATEGORIES_IN_CART) {
                    throw new RuntimeException("Cart must have at most 3 different categories");
                }
            }
        }
        return cartItemRepository.save(cartItem);
    }

    private String validateUserAndGetId() {
        User user = jwtService.getAuthenticatedUser();
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Role role = user.getRole();
        if (role == null) {
            throw new RuntimeException("User must have a role");
        }
        if (!role.getName().equals(UserRoles.CLIENT.toString())) {
            throw new RuntimeException("Only clients can add items to cart");
        }
        return user.getId();
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        String userId = validateUserAndGetId();
        if (cartItem.getId() == null) {
            throw new RuntimeException("Cart item must have an id");
        }
        Optional<CartItem> existingCartItem = retrieveCartItem.getCartItemById(cartItem.getId());
        if (existingCartItem.isEmpty()) {
            throw new RuntimeException("Cart item not found");
        }
        CartItem cartItemToUpdate = existingCartItem.get();
        Cart cart = cartItemToUpdate.getCart();
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }
        if (!Objects.equals(cart.getClientId(), userId)) {
            throw new RuntimeException("Cart item does not belong to user");
        }
        Article article = articleExternalOutPort.getArticleById(cartItemToUpdate.getArticleId());
        if (article == null) {
            throw new RuntimeException("Article not found");
        }
        if (!Objects.equals(article.getId(), cartItem.getArticleId())) {
            throw new RuntimeException("Article cannot be changed");
        }
        if (cartItem.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }
        if (cartItem.getQuantity() > article.getStock()) {
            throw new RuntimeException("Not enough stock");
        }
        cartItemToUpdate.setQuantity(cartItem.getQuantity());
        return cartItemRepository.save(cartItemToUpdate);
    }

    @Override
    public CartItem createCartItemForUser(String userId, CartItem cartItem) {
        return null;
    }

    @Override
    public void deleteCartItem(String id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItem removeQuantityFromCartItem(String id, int quantity) {
        Optional<CartItem> existingCartItem = retrieveCartItem.getCartItemById(id);
        if (existingCartItem.isEmpty()) {
            throw new RuntimeException("Cart item not found");
        }
        CartItem cartItem = existingCartItem.get();
        if (cartItem.getQuantity() - quantity <= 0) {
            String cartId = cartItem.getCart().getId();
            cartItemRepository.deleteById(id);
            Optional<Cart> cart = retrieveCart.getCartById(cartId);
            if (cart.isPresent() && cart.get().getItems().isEmpty()) {
                persistCart.deleteCart(cartId);
            }
            return null;
        }
        cartItem.setQuantity(cartItem.getQuantity() - quantity);
        return cartItemRepository.save(cartItem);
    }
}
