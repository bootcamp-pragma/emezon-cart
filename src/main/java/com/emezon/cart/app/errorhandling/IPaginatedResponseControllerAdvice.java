package com.emezon.cart.app.errorhandling;

import com.emezon.cart.domain.exceptions.paginatedresponse.PaginatedResponsePageNumberInvalidException;
import com.emezon.cart.domain.exceptions.paginatedresponse.PaginatedResponsePageSizeInvalidException;
import com.emezon.cart.domain.exceptions.paginatedresponse.PaginatedResponseSortDirectionInvalidException;

public interface IPaginatedResponseControllerAdvice<T> {

    Object handleCategoryPageNumberInvalidException(PaginatedResponsePageNumberInvalidException ex, T request);

    Object handleCategoryPageSizeInvalidException(PaginatedResponsePageSizeInvalidException ex, T request);

    Object handleCategorySortDirectionInvalidException(PaginatedResponseSortDirectionInvalidException ex, T request);

    Object handlePropertyReferenceException(Exception ex, T request);

}
