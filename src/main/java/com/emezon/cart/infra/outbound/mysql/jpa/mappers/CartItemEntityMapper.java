package com.emezon.cart.infra.outbound.mysql.jpa.mappers;

import com.emezon.cart.domain.models.CartItem;
import com.emezon.cart.infra.outbound.mysql.jpa.entities.CartItemEntity;

public class CartItemEntityMapper {

    private CartItemEntityMapper() { }

    public static CartItem toModel(CartItemEntity entity, boolean includeCart) {
        CartItem model = new CartItem();
        model.setId(entity.getId());
        if (includeCart) {
            model.setCart(CartEntityMapper.toModel(entity.getCart(), false));
        }
        model.setArticleId(entity.getArticleId());
        model.setQuantity(entity.getQuantity());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        return model;
    }

    public static CartItemEntity toEntity(CartItem model, boolean includeCart) {
        CartItemEntity entity = new CartItemEntity();
        entity.setId(model.getId());
        if (includeCart) {
            entity.setCart(CartEntityMapper.toEntity(model.getCart(), false));
        }
        entity.setArticleId(model.getArticleId());
        entity.setQuantity(model.getQuantity());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());
        return entity;
    }

}
