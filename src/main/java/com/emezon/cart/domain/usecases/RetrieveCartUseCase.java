package com.emezon.cart.domain.usecases;

import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.constants.PaginatedResponseConstraints;
import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.models.external.User;
import com.emezon.cart.domain.spi.ICartRepositoryOutPort;
import com.emezon.cart.domain.spi.IJwtService;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;

import java.util.Optional;

public class RetrieveCartUseCase implements IRetrieveCartInPort {

    private final ICartRepositoryOutPort cartRepository;
    private final IJwtService jwtService;

    public RetrieveCartUseCase(ICartRepositoryOutPort cartRepository, IJwtService jwtService) {
        this.cartRepository = cartRepository;
        this.jwtService = jwtService;
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
    public Optional<Cart> getCartOfAuthenticatedUser() {
        String authenticatedUserId = this.getAuthenticatedUserId();
        if (authenticatedUserId == null) {
            throw new RuntimeException("User not authenticated");
        }
        return this.getCartByUserId(authenticatedUserId);
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

    private String getAuthenticatedUserId() {
        User user = jwtService.getAuthenticatedUser();
        if (user == null) {
            return null;
        }
        return user.getId();
    }

}
