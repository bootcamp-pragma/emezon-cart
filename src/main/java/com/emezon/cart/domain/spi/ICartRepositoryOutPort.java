package com.emezon.cart.domain.spi;

import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;

import java.util.Optional;

public interface ICartRepositoryOutPort {

    Cart save(Cart cart);

    Optional<Cart> findById(String id);

    Optional<Cart> findByUserId(String userId);

    PaginatedResponse<Cart> findAll(PaginatedResponseParams params);

    void deleteById(String id);

}
