package com.emezon.cart.infra.outbound.mysql.jpa.mappers;

import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.infra.outbound.mysql.jpa.entities.CartEntity;

public class CartEntityMapper {

    private CartEntityMapper() { }

    public static Cart toModel(CartEntity entity, boolean includeItems) {
        Cart model = new Cart();
        model.setId(entity.getId());
        model.setClientId(entity.getClientId());
        model.setTotal(entity.getTotal());
        model.setStatus(entity.getStatus());
        if (includeItems) {
            model.setItems(entity.getItems()
                    .stream().map(item -> CartItemEntityMapper.toModel(item, false))
                    .toList());
        }
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        return model;
    }

    public static CartEntity toEntity(Cart model, boolean includeItems) {
        CartEntity entity = new CartEntity();
        entity.setId(model.getId());
        entity.setClientId(model.getClientId());
        entity.setTotal(model.getTotal());
        entity.setStatus(model.getStatus());
        if (includeItems) {
            entity.setItems(model.getItems()
                    .stream().map(item -> CartItemEntityMapper.toEntity(item, false))
                    .toList());
        }
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());
        return entity;
    }

}
