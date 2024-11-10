package com.emezon.cart.app.handlers;

import com.emezon.cart.app.dtos.CartItemDTO;
import com.emezon.cart.app.dtos.CreateCartItemDTO;

public interface CartItemHandler {

    CartItemDTO createCartItem(CreateCartItemDTO createCartItemDTO);

}
