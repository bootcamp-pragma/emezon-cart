package com.emezon.cart.domain.api;

import com.emezon.cart.domain.models.Cart;

public interface IPersistCartInPort {

    Cart createCart(Cart cart);

    Cart updateCart(Cart cart);

    void deleteCart(String id);

    Cart removeItemFromCart(String cartId, String itemId);

}
