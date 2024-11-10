package com.emezon.cart.domain.usecases;

import com.emezon.cart.domain.api.IPersistCartInPort;
import com.emezon.cart.domain.api.IPersistCartItemInPort;
import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.api.IRetrieveCartItemInPort;
import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.models.CartItem;
import com.emezon.cart.domain.models.CartStatus;
import com.emezon.cart.domain.models.external.Article;
import com.emezon.cart.domain.spi.ICartItemRepositoryOutPort;
import com.emezon.cart.domain.spi.IJwtService;
import com.emezon.cart.domain.spi.external.IArticleExternalOutPort;

import java.util.List;
import java.util.Optional;

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
        if (cartItem.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }
        String userId = jwtService.getAuthenticatedUserId();
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
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            return cartItemRepository.save(existingCartItem);
        }
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        if (cartItem.getId() == null) {
            throw new RuntimeException("Cart item must have an id");
        }
        Optional<CartItem> existingCartItem = retrieveCartItem.getCartItemById(cartItem.getId());
        if (existingCartItem.isEmpty()) {
            throw new RuntimeException("Cart item not found");
        }
        CartItem cartItemToUpdate = existingCartItem.get();
        Article article = articleExternalOutPort.getArticleById(cartItemToUpdate.getArticleId());
        if (article == null) {
            throw new RuntimeException("Article not found");
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
