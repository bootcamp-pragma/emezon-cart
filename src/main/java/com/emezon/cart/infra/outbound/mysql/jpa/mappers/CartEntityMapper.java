package com.emezon.cart.infra.outbound.mysql.jpa.mappers;

import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.infra.outbound.mysql.jpa.entities.CartEntity;
import com.emezon.cart.infra.outbound.mysql.jpa.entities.CartItemEntity;

public class CartEntityMapper {

    private CartEntityMapper() { }

    public static Cart toModel(CartEntity entity, boolean includeItems) {
        if (entity == null) {
            return null;
        }
        Cart model = new Cart();
        model.setId(entity.getId());
        model.setClientId(entity.getClientId());
        model.setTotal(entity.getTotal());
        model.setStatus(entity.getStatus());
        if (includeItems && entity.getItems() != null) {
            model.setItems(entity.getItems()
                    .stream().map(item -> CartItemEntityMapper.toModel(item, false))
                    .toList());
        }
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        return model;
    }

    public static CartEntity toEntity(Cart model, boolean includeItems) {
        if (model == null) {
            return null;
        }
        CartEntity entity = new CartEntity();
        entity.setId(model.getId());
        entity.setClientId(model.getClientId());
        entity.setTotal(model.getTotal());
        entity.setStatus(model.getStatus());
        if (includeItems && model.getItems() != null) {
            entity.setItems(model.getItems()
                    .stream().map(item -> {
                        CartItemEntity aux = CartItemEntityMapper.toEntity(item, false);
                        aux.setCart(entity);
                        return aux;
                    })
                    .toList());
        }
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());
        return entity;
    }

}
