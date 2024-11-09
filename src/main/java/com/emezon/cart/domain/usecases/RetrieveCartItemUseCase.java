package com.emezon.cart.domain.usecases;

import com.emezon.cart.domain.api.IRetrieveCartItemInPort;
import com.emezon.cart.domain.constants.PaginatedResponseConstraints;
import com.emezon.cart.domain.models.CartItem;
import com.emezon.cart.domain.spi.ICartItemRepositoryOutPort;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;

import java.util.Optional;


public class RetrieveCartItemUseCase implements IRetrieveCartItemInPort {

    private final ICartItemRepositoryOutPort cartItemRepository;

    public RetrieveCartItemUseCase(ICartItemRepositoryOutPort cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Optional<CartItem> getCartItemById(String id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public PaginatedResponse<CartItem> getAllCartItems(PaginatedResponseParams params) {
        PaginatedResponseConstraints.validateParameters(params);
        return cartItemRepository.findAll(params);
    }

    @Override
    public PaginatedResponse<CartItem> getCartItemsByCartId(String cartId, PaginatedResponseParams params) {
        PaginatedResponseConstraints.validateParameters(params);
        return cartItemRepository.findAllByCartId(cartId, params);
    }
}
