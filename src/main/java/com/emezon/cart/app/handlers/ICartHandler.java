package com.emezon.cart.app.handlers;

import com.emezon.cart.app.dtos.CartDTO;

public interface ICartHandler {

    CartDTO getCartOfAuthenticatedUser();

}
