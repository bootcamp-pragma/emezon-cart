package com.emezon.cart.domain.usecases;

import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.constants.PaginatedResponseConstraints;
import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.models.CartStatus;
import com.emezon.cart.domain.spi.ICartRepositoryOutPort;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RetrieveCartUseCase implements IRetrieveCartInPort {

    private final ICartRepositoryOutPort cartRepository;

    @Override
    public Optional<Cart> getCartById(String id) {
        return cartRepository.findById(id);
    }

    @Override
    public Optional<Cart> getCartByUserIdAndStatus(String userId, int status) {
        return cartRepository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public boolean userHasActiveCart(String userId) {
        Optional<Cart> cart = this.getCartByUserIdAndStatus(userId, CartStatus.ACTIVE.value());
        return cart.isPresent();
    }

    @Override
    public PaginatedResponse<Cart> getAllCarts(PaginatedResponseParams params) {
        PaginatedResponseConstraints.validateParameters(params);
        return cartRepository.findAll(params);
    }
}
