package com.emezon.cart.app.services;

import com.emezon.cart.app.dtos.CartDTO;
import com.emezon.cart.app.dtos.CartItemWithoutCartDTO;
import com.emezon.cart.app.handlers.ICartHandler;
import com.emezon.cart.app.mappers.CartMapper;
import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.models.external.Article;
import com.emezon.cart.infra.outbound.feign.IArticleFeignClient;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CartService implements ICartHandler {

    private final IRetrieveCartInPort retrieveCartInPort;
    private final IArticleFeignClient articleFeignClient;

    @Override
    public CartDTO getCartOfAuthenticatedUser() {
        Optional<Cart> cart = retrieveCartInPort.getCartOfAuthenticatedUser();
        CartDTO cartDTO = CartMapper.toDTO(cart.orElse(null));
        return loadArticles(cartDTO);
    }

    private CartDTO loadArticles(CartDTO cart) {
        if (cart == null) {
            return null;
        }
        if (cart.getItems() == null) {
            return cart;
        }
        for (CartItemWithoutCartDTO cartItem : cart.getItems()) {
            try {
                Article article = articleFeignClient.getArticleById(cartItem.getArticleId());
                cartItem.setArticle(article);
            } catch (Exception e) {
                // do nothing
            }
        }
        return cart;
    }

}
