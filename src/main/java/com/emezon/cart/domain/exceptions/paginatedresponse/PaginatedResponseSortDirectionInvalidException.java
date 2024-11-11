package com.emezon.cart.domain.exceptions.paginatedresponse;

import com.emezon.cart.domain.constants.PaginatedResponseErrorMessages;

public class PaginatedResponseSortDirectionInvalidException extends RuntimeException {

    public PaginatedResponseSortDirectionInvalidException() {
        super(PaginatedResponseErrorMessages.SORT_DIRECTION_INVALID);
    }

}
