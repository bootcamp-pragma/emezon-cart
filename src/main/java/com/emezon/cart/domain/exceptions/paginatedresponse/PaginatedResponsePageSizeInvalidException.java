package com.emezon.cart.domain.exceptions.paginatedresponse;

import com.emezon.cart.domain.constants.PaginatedResponseErrorMessages;

public class PaginatedResponsePageSizeInvalidException extends RuntimeException {
    public PaginatedResponsePageSizeInvalidException() {
        super(PaginatedResponseErrorMessages.PAGE_SIZE_INVALID);
    }
}
