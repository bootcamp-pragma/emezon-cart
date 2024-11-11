package com.emezon.cart.domain.constants;

public class PaginatedResponseErrorMessages {

    public static final String PAGE_SIZE_INVALID = "Page size must be greater than 0";
    public static final String PAGE_NUMBER_INVALID = "Page number must be greater than or equal to 0";
    public static final String SORT_DIRECTION_INVALID = "Sort direction must be 'none', 'asc' or 'desc'";
    public static final String SORT_PARAM_INVALID = "Sort by must be in the format: property,(asc|desc) (e.g. sort=name,asc)";

    private PaginatedResponseErrorMessages() {
        throw new IllegalStateException("Utility class");
    }

}
