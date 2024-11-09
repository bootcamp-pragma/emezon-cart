package com.emezon.cart.domain.api;

import com.emezon.cart.domain.models.CartItem;

public interface IPersistCartItem {

    CartItem createCartItem(CartItem cartItem);

    CartItem updateCartItem(CartItem cartItem);

    void deleteCartItem(String id);

    CartItem removeQuantityFromCartItem(String id, int quantity);

}
