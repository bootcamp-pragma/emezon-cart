package com.emezon.cart.domain.api;

import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;

import java.util.Optional;

public interface IRetrieveCartInPort {

    Optional<Cart> getCartById(String id);

    Optional<Cart> getCartByUserIdAndStatus(String userId, int status);

    boolean userHasActiveCart(String userId);

    PaginatedResponse<Cart> getAllCarts(PaginatedResponseParams params);

}
