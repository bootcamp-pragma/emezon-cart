package com.emezon.cart.app.mappers;

import com.emezon.cart.app.dtos.CartDTO;
import com.emezon.cart.app.dtos.CartWithoutItemsDTO;
import com.emezon.cart.domain.models.Cart;

public class CartMapper {

    private CartMapper() { }

    public static CartDTO toDTO(Cart model) {
        if (model == null) {
            return null;
        }
        CartDTO dto = new CartDTO();
        dto.setId(model.getId());
        dto.setClientId(model.getClientId());
        dto.setTotal(model.getTotal());
        dto.setStatus(model.getStatus());
        dto.setItems(model.getItems()
                .stream().map(CartItemMapper::toDTOWithoutCart)
                .toList());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setUpdatedAt(model.getUpdatedAt());
        return dto;
    }

    public static Cart toModel(CartDTO dto) {
        if (dto == null) {
            return null;
        }
        Cart model = new Cart();
        model.setId(dto.getId());
        model.setClientId(dto.getClientId());
        model.setTotal(dto.getTotal());
        model.setStatus(dto.getStatus());
        model.setItems(dto.getItems()
                .stream().map(CartItemMapper::toModel)
                .toList());
        model.setCreatedAt(dto.getCreatedAt());
        model.setUpdatedAt(dto.getUpdatedAt());
        return model;
    }

    public static CartWithoutItemsDTO toDTOWithoutItems(Cart model) {
        if (model == null) {
            return null;
        }
        CartWithoutItemsDTO dto = new CartWithoutItemsDTO();
        dto.setId(model.getId());
        dto.setClientId(model.getClientId());
        dto.setTotal(model.getTotal());
        dto.setStatus(model.getStatus());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setUpdatedAt(model.getUpdatedAt());
        return dto;
    }

    public static Cart toModel(CartWithoutItemsDTO dto) {
        if (dto == null) {
            return null;
        }
        Cart model = new Cart();
        model.setId(dto.getId());
        model.setClientId(dto.getClientId());
        model.setTotal(dto.getTotal());
        model.setStatus(dto.getStatus());
        model.setCreatedAt(dto.getCreatedAt());
        model.setUpdatedAt(dto.getUpdatedAt());
        return model;
    }

}
