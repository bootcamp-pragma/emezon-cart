package com.emezon.cart.domain.api;

import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;

import java.util.Optional;

public interface IRetrieveCartInPort {

    Optional<Cart> getCartById(String id);

    Optional<Cart> getCartByUserId(String userId);

    Optional<Cart> getCartOfAuthenticatedUser();

    boolean userHasCart(String userId);

    PaginatedResponse<Cart> getAllCarts(PaginatedResponseParams params);

}
