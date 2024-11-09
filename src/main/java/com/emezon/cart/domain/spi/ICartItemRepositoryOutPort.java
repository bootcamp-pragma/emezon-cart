package com.emezon.cart.domain.spi;

import com.emezon.cart.domain.models.CartItem;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;

import java.util.Optional;

public interface ICartItemRepositoryOutPort {

    CartItem save(CartItem cartItem);

    Optional<CartItem> findById(String id);

    PaginatedResponse<CartItem> findAll(PaginatedResponseParams params);

    PaginatedResponse<CartItem> findByCartId(String cartId, PaginatedResponseParams params);

    void deleteById(String id);

}
