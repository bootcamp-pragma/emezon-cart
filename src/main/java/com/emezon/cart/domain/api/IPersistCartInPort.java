package com.emezon.cart.domain.api;

import com.emezon.cart.domain.models.Cart;

public interface IPersistCartInPort {

    Cart createCart(Cart cart);

}
