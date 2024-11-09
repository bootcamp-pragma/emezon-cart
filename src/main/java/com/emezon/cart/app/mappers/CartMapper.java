package com.emezon.cart.app.mappers;

import com.emezon.cart.app.dtos.CartDTO;
import com.emezon.cart.domain.models.Cart;

public class CartMapper {

    private CartMapper() { }

    public static CartDTO toDTO(Cart model, boolean includeItems) {
        CartDTO dto = new CartDTO();
        dto.setId(model.getId());
        dto.setClientId(model.getClientId());
        dto.setTotal(model.getTotal());
        dto.setStatus(model.getStatus());
        if (includeItems && model.getItems() != null) {
            dto.setItems(model.getItems()
                    .stream().map(item -> CartItemMapper.toDTO(item, false))
                    .toList());
        }
        dto.setCreatedAt(model.getCreatedAt());
        dto.setUpdatedAt(model.getUpdatedAt());
        return dto;
    }

    public static Cart toModel(CartDTO dto, boolean includeItems) {
        Cart model = new Cart();
        model.setId(dto.getId());
        model.setClientId(dto.getClientId());
        model.setTotal(dto.getTotal());
        model.setStatus(dto.getStatus());
        if (includeItems && dto.getItems() != null) {
            model.setItems(dto.getItems()
                    .stream().map(item -> CartItemMapper.toModel(item, false))
                    .toList());
        }
        model.setCreatedAt(dto.getCreatedAt());
        model.setUpdatedAt(dto.getUpdatedAt());
        return model;
    }

}
