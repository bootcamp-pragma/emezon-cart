package com.emezon.cart.domain.usecases;

import com.emezon.cart.domain.api.IPersistCartInPort;
import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.models.CartStatus;
import com.emezon.cart.domain.spi.ICartRepositoryOutPort;

import java.util.Optional;

public class PersistCartUseCase implements IPersistCartInPort {

    private final ICartRepositoryOutPort cartRepository;
    private final IRetrieveCartInPort retrieveCartInPort;

    public PersistCartUseCase(ICartRepositoryOutPort cartRepository, IRetrieveCartInPort retrieveCartInPort) {
        this.cartRepository = cartRepository;
        this.retrieveCartInPort = retrieveCartInPort;
    }

    @Override
    public Cart createCart(Cart cart) {
        if (retrieveCartInPort.userHasActiveCart(cart.getClientId())) {
            throw new RuntimeException("User already has an active cart");
        }
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart must have at least one item");
        }
        cart.setStatus(CartStatus.ACTIVE.value());
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Cart cart) {
        Optional<Cart> cartOptional = retrieveCartInPort.getCartById(cart.getId());
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(String id) {
        Optional<Cart> cartOptional = retrieveCartInPort.getCartById(id);
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        cartRepository.deleteById(id);
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
            cartRepository.save(cart);
            return null;
        }
        return cartRepository.save(cart);
    }
}
