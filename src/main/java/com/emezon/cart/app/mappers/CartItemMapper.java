package com.emezon.cart.app.mappers;

import com.emezon.cart.app.dtos.CartItemDTO;
import com.emezon.cart.app.dtos.CartItemWithoutCartDTO;
import com.emezon.cart.app.dtos.CreateCartItemDTO;
import com.emezon.cart.domain.models.CartItem;

public class CartItemMapper {

    private CartItemMapper() { }

    public static CartItemDTO toDTO(CartItem model) {
        if (model == null) {
            return null;
        }
        CartItemDTO dto = new CartItemDTO();
        dto.setId(model.getId());
        dto.setCart(CartMapper.toDTOWithoutItems(model.getCart()));
        dto.setArticleId(model.getArticleId());
        dto.setQuantity(model.getQuantity());
        return dto;
    }

    public static CartItem toModel(CartItemDTO dto) {
        if (dto == null) {
            return null;
        }
        CartItem model = new CartItem();
        model.setId(dto.getId());
        model.setCart(CartMapper.toModel(dto.getCart()));
        model.setArticleId(dto.getArticleId());
        model.setQuantity(dto.getQuantity());
        return model;
    }

    public static CartItem toModel(CreateCartItemDTO dto) {
        if (dto == null) {
            return null;
        }
        CartItem model = new CartItem();
        model.setArticleId(dto.getArticleId());
        model.setQuantity(dto.getQuantity());
        return model;
    }

    public static CartItemWithoutCartDTO toDTOWithoutCart(CartItem model) {
        if (model == null) {
            return null;
        }
        CartItemWithoutCartDTO dto = new CartItemWithoutCartDTO();
        dto.setId(model.getId());
        dto.setArticleId(model.getArticleId());
        dto.setQuantity(model.getQuantity());
        return dto;
    }

    public static CartItem toModel(CartItemWithoutCartDTO dto) {
        if (dto == null) {
            return null;
        }
        CartItem model = new CartItem();
        model.setId(dto.getId());
        model.setArticleId(dto.getArticleId());
        model.setQuantity(dto.getQuantity());
        return model;
    }

}
