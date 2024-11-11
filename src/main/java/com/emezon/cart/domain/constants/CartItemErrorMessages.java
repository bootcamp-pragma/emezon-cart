package com.emezon.cart.domain.constants;

public class CartItemErrorMessages {

    public static final String ARTICLE_ID_NOT_NULL = "Article ID cannot be null";
    public static final String ARTICLE_ID_NOT_BLANK = "Article ID cannot be blank";
    public static final String QUANTITY_NOT_NULL = "Quantity cannot be null";
    public static final String INVALID_QUANTITY = "Quantity must be greater than or equal to "
            + CartItemConstrains.MIN_QUANTITY;

    private CartItemErrorMessages() { }

}
