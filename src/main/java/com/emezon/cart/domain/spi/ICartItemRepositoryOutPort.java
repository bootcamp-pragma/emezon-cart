package com.emezon.cart.domain.spi;

import com.emezon.cart.domain.models.CartItem;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.domain.utils.PaginatedResponseParams;

import java.util.Optional;

public interface ICartItemRepositoryOutPort {

    CartItem save(CartItem cartItem);

    Optional<CartItem> findById(String id);

    Optional<CartItem> findByCartIdAndArticleId(String cartId, String articleId);

    Optional<CartItem> findByUserIdAndArticleId(String userId, String articleId);

    PaginatedResponse<CartItem> findAll(PaginatedResponseParams params);

    PaginatedResponse<CartItem> findAllByCartId(String cartId, PaginatedResponseParams params);

    void deleteById(String id);

}
