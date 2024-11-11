package com.emezon.cart.domain.usecases;

import com.emezon.cart.domain.api.IPersistCartInPort;
import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.models.external.Role;
import com.emezon.cart.domain.models.external.User;
import com.emezon.cart.domain.models.external.UserRoles;
import com.emezon.cart.domain.spi.ICartRepositoryOutPort;
import com.emezon.cart.domain.spi.IJwtService;

import java.util.Optional;

public class PersistCartUseCase implements IPersistCartInPort {

    private final ICartRepositoryOutPort cartRepository;
    private final IRetrieveCartInPort retrieveCartInPort;
    private final IJwtService jwtService;

    public PersistCartUseCase(
            ICartRepositoryOutPort cartRepository,
            IRetrieveCartInPort retrieveCartInPort,
            IJwtService jwtService) {
        this.cartRepository = cartRepository;
        this.retrieveCartInPort = retrieveCartInPort;
        this.jwtService = jwtService;
    }

    @Override
    public Cart createCart(Cart cart) {
        if (retrieveCartInPort.userHasCart(cart.getClientId())) {
            throw new RuntimeException("User already has an active cart");
        }
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart must have at least one item");
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Cart cart) {
        String clientId = validateUserAndGetId();
        Optional<Cart> cartOptional = retrieveCartInPort.getCartById(cart.getId());
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        Cart existingCart = cartOptional.get();
        if (!existingCart.getClientId().equals(clientId)) {
            throw new RuntimeException("Cart does not belong to the authenticated user");
        }
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(String id) {
        String clientId = validateUserAndGetId();
        Optional<Cart> cartOptional = retrieveCartInPort.getCartById(id);
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        Cart cart = cartOptional.get();
        if (!cart.getClientId().equals(clientId)) {
            throw new RuntimeException("Cart does not belong to the authenticated user");
        }
        cartRepository.deleteById(id);
    }

    @Override
    public Cart removeItemFromCart(String cartId, String itemId) {
        String clientId = validateUserAndGetId();
        Optional<Cart> cartOptional = retrieveCartInPort.getCartById(cartId);
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        Cart cart = cartOptional.get();
        if (!cart.getClientId().equals(clientId)) {
            throw new RuntimeException("Cart does not belong to the authenticated user");
        }
        int itemsCount = cart.getItems().size();
        cart.setItems(cart.getItems().stream().filter(item -> !item.getId().equals(itemId)).peek(
                item -> item.setCart(cart)).toList());
        if (itemsCount == cart.getItems().size()) {
            throw new RuntimeException("Item not found in cart");
        }
        if (cart.getItems().isEmpty()) {
            cartRepository.deleteById(cartId);
            return null;
        }
        return cartRepository.save(cart);
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

}
