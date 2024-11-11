package com.emezon.cart.domain.api;

import com.emezon.cart.domain.models.CartItem;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;

import java.util.Optional;

public interface IRetrieveCartItemInPort {

    Optional<CartItem> getCartItemById(String id);

    PaginatedResponse<CartItem> getAllCartItems(PaginatedResponseParams params);

    PaginatedResponse<CartItem> getCartItemsByCartId(String cartId, PaginatedResponseParams params);

}
