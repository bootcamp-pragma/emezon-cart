package com.emezon.cart.domain.exceptions.paginatedresponse;

import com.emezon.cart.domain.constants.PaginatedResponseErrorMessages;

public class PaginatedResponsePageNumberInvalidException extends RuntimeException {
    public PaginatedResponsePageNumberInvalidException() {
        super(PaginatedResponseErrorMessages.PAGE_NUMBER_INVALID);
    }
}
