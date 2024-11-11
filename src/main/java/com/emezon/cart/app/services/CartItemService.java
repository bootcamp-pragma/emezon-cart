package com.emezon.cart.app.services;

import com.emezon.cart.app.dtos.CartItemDTO;
import com.emezon.cart.app.dtos.CreateCartItemDTO;
import com.emezon.cart.app.handlers.ICartItemHandler;
import com.emezon.cart.app.mappers.CartItemMapper;
import com.emezon.cart.domain.api.IPersistCartItemInPort;
import com.emezon.cart.domain.models.CartItem;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartItemService implements ICartItemHandler {

    private final IPersistCartItemInPort persistCartItemInPort;

    @Override
    public CartItemDTO createCartItem(CreateCartItemDTO createCartItemDTO) {
        CartItem cartItem = CartItemMapper.toModel(createCartItemDTO);
        CartItem savedCartItem = persistCartItemInPort.createCartItem(cartItem);
        return CartItemMapper.toDTO(savedCartItem, true);
    }

    @Override
    public CartItemDTO removeQuantity(String id, int quantity) {
        CartItem savedCartItem = persistCartItemInPort.removeQuantityFromCartItem(id, quantity);
        return CartItemMapper.toDTO(savedCartItem, true);
    }

    @Override
    public void deleteCartItem(String id) {
        persistCartItemInPort.deleteCartItem(id);
    }

}
