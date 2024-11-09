package com.emezon.cart.app.dtos;

import com.emezon.cart.domain.models.Cart;
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
    private CartDTO cart;
    private String articleId;
    private int quantity;

}
