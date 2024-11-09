package com.emezon.cart.app.mappers;

import com.emezon.cart.app.dtos.CartItemDTO;
import com.emezon.cart.domain.models.CartItem;

public class CartItemMapper {

    private CartItemMapper() { }

    public static CartItemDTO toDTO(CartItem model, boolean includeCart) {
        if (model == null) {
            return null;
        }
        CartItemDTO dto = new CartItemDTO();
        dto.setId(model.getId());
        if (includeCart) {
            dto.setCart(CartMapper.toDTO(model.getCart(), false));
        }
        dto.setArticleId(model.getArticleId());
        dto.setQuantity(model.getQuantity());
        return dto;
    }

    public static CartItem toModel(CartItemDTO dto, boolean includeCart) {
        if (dto == null) {
            return null;
        }
        CartItem model = new CartItem();
        model.setId(dto.getId());
        if (includeCart) {
            model.setCart(CartMapper.toModel(dto.getCart(), false));
        }
        model.setArticleId(dto.getArticleId());
        model.setQuantity(dto.getQuantity());
        return model;
    }

}
