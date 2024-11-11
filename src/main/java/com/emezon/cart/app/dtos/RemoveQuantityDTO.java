package com.emezon.cart.app.dtos;

import com.emezon.cart.domain.constants.CartItemConstrains;
import com.emezon.cart.domain.constants.CartItemErrorMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemoveQuantityDTO {

    @NotNull(message = CartItemErrorMessages.QUANTITY_NOT_NULL)
    @Min(value = CartItemConstrains.MIN_QUANTITY, message = CartItemErrorMessages.INVALID_QUANTITY)
    private int quantity;

}
