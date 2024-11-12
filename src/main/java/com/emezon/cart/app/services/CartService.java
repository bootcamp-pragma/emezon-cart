package com.emezon.cart.app.services;

import com.emezon.cart.app.dtos.CartDTO;
import com.emezon.cart.app.handlers.ICartHandler;
import com.emezon.cart.app.mappers.CartMapper;
import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.models.Cart;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CartService implements ICartHandler {

    private final IRetrieveCartInPort retrieveCartInPort;

    @Override
    public CartDTO getCartOfAuthenticatedUser() {
        Optional<Cart> cart = retrieveCartInPort.getCartOfAuthenticatedUser();
        return cart.map(value -> CartMapper.toDTO(value, true)).orElse(null);
    }
}
