package com.emezon.cart.app.handlers;

import com.emezon.cart.app.dtos.CartItemDTO;
import com.emezon.cart.app.dtos.CreateCartItemDTO;
import com.emezon.cart.app.dtos.RemoveQuantityDTO;

public interface ICartItemHandler {

    CartItemDTO createCartItem(CreateCartItemDTO createCartItemDTO);

    CartItemDTO removeQuantity(String id, int quantity);

    void deleteCartItem(String id);

}
