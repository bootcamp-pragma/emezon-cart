package com.emezon.cart.app.services;

import com.emezon.cart.app.dtos.CartItemDTO;
import com.emezon.cart.app.dtos.CreateCartItemDTO;
import com.emezon.cart.app.handlers.CartItemHandler;
import com.emezon.cart.app.mappers.CartItemMapper;
import com.emezon.cart.domain.api.IPersistCartItemInPort;
import com.emezon.cart.domain.models.CartItem;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartItemService implements CartItemHandler {

    private final IPersistCartItemInPort persistCartItemInPort;

    @Override
    public CartItemDTO createCartItem(CreateCartItemDTO createCartItemDTO) {
        CartItem cartItem = CartItemMapper.toModel(createCartItemDTO);
        CartItem savedCartItem = persistCartItemInPort.createCartItem(cartItem);
        return CartItemMapper.toDTO(savedCartItem, true);
    }

}
