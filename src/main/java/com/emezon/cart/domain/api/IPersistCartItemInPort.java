package com.emezon.cart.domain.api;

import com.emezon.cart.domain.models.CartItem;

public interface IPersistCartItemInPort {

    CartItem createCartItem(CartItem cartItem);

    CartItem updateCartItem(CartItem cartItem);

    CartItem createCartItemForUser(String userId, CartItem cartItem);

    void deleteCartItem(String id);

    CartItem removeQuantityFromCartItem(String id, int quantity);

}
