package com.emezon.cart.domain.usecases;

import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.constants.PaginatedResponseConstraints;
import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.spi.ICartRepositoryOutPort;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;

import java.util.Optional;

public class RetrieveCartUseCase implements IRetrieveCartInPort {

    private final ICartRepositoryOutPort cartRepository;

    public RetrieveCartUseCase(ICartRepositoryOutPort cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> getCartById(String id) {
        return cartRepository.findById(id);
    }

    @Override
    public Optional<Cart> getCartByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public boolean userHasCart(String userId) {
        Optional<Cart> cart = this.getCartByUserId(userId);
        return cart.isPresent();
    }

    @Override
    public PaginatedResponse<Cart> getAllCarts(PaginatedResponseParams params) {
        PaginatedResponseConstraints.validateParameters(params);
        return cartRepository.findAll(params);
    }
}
