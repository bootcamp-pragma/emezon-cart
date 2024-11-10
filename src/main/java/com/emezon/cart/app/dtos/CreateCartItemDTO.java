package com.emezon.cart.app.dtos;

import com.emezon.cart.domain.constants.CartItemErrorMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import com.emezon.cart.domain.constants.CartIemConstrains;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartItemDTO {

    @NotNull(message = CartItemErrorMessages.ARTICLE_ID_NOT_NULL)
    @NotBlank(message = CartItemErrorMessages.ARTICLE_ID_NOT_BLANK)
    String articleId;

    @NotNull(message = CartItemErrorMessages.QUANTITY_NOT_NULL)
    @Min(value = CartIemConstrains.MIN_QUANTITY, message = CartItemErrorMessages.INVALID_QUANTITY)
    int quantity;

}
