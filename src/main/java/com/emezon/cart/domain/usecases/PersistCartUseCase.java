package com.emezon.cart.domain.usecases;

import com.emezon.cart.domain.api.IPersistCartInPort;
import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.models.CartStatus;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PersistCartUseCase implements IPersistCartInPort {

    private final IPersistCartInPort persistCartInPort;
    private final IRetrieveCartInPort retrieveCartInPort;

    @Override
    public Cart createCart(Cart cart) {
        if (retrieveCartInPort.userHasActiveCart(cart.getClientId())) {
            throw new RuntimeException("User already has an active cart");
        }
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart must have at least one item");
        }
        cart.setStatus(CartStatus.ACTIVE.value());
        return persistCartInPort.createCart(cart);
    }

    @Override
    public Cart updateCart(Cart cart) {
        Optional<Cart> cartOptional = retrieveCartInPort.getCartById(cart.getId());
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        return persistCartInPort.updateCart(cart);
    }

    @Override
    public void deleteCart(String id) {
        Optional<Cart> cartOptional = retrieveCartInPort.getCartById(id);
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        persistCartInPort.deleteCart(id);
    }

    @Override
    public Cart removeItemFromCart(String cartId, String itemId) {
        Optional<Cart> cartOptional = retrieveCartInPort.getCartById(cartId);
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        Cart cart = cartOptional.get();
        cart.setItems(cart.getItems().stream().filter(item -> !item.getId().equals(itemId)).toList());
        if (cart.getItems().isEmpty()) {
            cart.setStatus(CartStatus.CANCELED.value());
            persistCartInPort.updateCart(cart);
            return null;
        }
        return persistCartInPort.updateCart(cart);
    }
}
