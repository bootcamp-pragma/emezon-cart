package com.emezon.cart.domain.constants;

import com.emezon.cart.domain.exceptions.paginatedresponse.PaginatedResponsePageNumberInvalidException;
import com.emezon.cart.domain.exceptions.paginatedresponse.PaginatedResponsePageSizeInvalidException;
import com.emezon.cart.domain.exceptions.paginatedresponse.PaginatedResponseSortDirectionInvalidException;
import com.emezon.cart.domain.utils.PaginatedResponseParams;

import java.util.List;

public class PaginatedResponseConstraints {

    public static final int PAGE_SIZE_MIN = 1;
    public static final int PAGE_NUMBER_MIN = 0;
    public static final List<String> SORT_DIRECTIONS = List.of("asc", "desc");
    public static final List<String> ALLOWED_PARAMS = List.of("page", "size", "sort");
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final List<String> DEFAULT_SORT = List.of();
    public static final String VALID_SORT_FORMAT = "^((?!.*\\.\\..*)[a-zA-Z]+(\\.[a-zA-Z]{1,20}){0,3}),(" + String.join("|", SORT_DIRECTIONS) + ")$";

    public static void validateParameters(int page, int size, List<String> sorting) {
        if (page < PAGE_NUMBER_MIN) {
            throw new PaginatedResponsePageNumberInvalidException();
        }
        if (size < PAGE_SIZE_MIN) {
            throw new PaginatedResponsePageSizeInvalidException();
        }
        for (String sort : sorting) {
            if (!sort.matches(VALID_SORT_FORMAT)) {
                throw new PaginatedResponseSortDirectionInvalidException();
            }
        }
    }

    public static void validateParameters(PaginatedResponseParams params) {
        validateParameters(params.page, params.size, params.sorting);
    }

    private PaginatedResponseConstraints() {
        throw new IllegalStateException("Utility class");
    }

}
