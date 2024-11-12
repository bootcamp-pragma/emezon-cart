package com.emezon.cart.app.dtos;

import com.emezon.cart.domain.models.Cart;
import com.emezon.cart.domain.models.external.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {

    private String id;
    private CartWithoutItemsDTO cart;
    private String articleId;
    private Article article;
    private int quantity;

}
