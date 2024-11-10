package com.emezon.cart.infra.inbound.rest.constants;

public class PaginatedConstants {

    public static final String PARAM_PAGE = "page";
    public static final String PARAM_PAGE_DESC = "Número de la página";
    public static final String PARAM_PAGE_EXAMPLE = "0";

    public static final String PARAM_SIZE = "size";
    public static final String PARAM_SIZE_DESC = "Tamaño de la página";
    public static final String PARAM_SIZE_EXAMPLE = "10";

    public static final String PARAM_SORT = "sort";
    public static final String PARAM_SORT_DESC = "Criterios de ordenamiento (puede incluir múltiples valores)";
    public static final String PARAM_SORT_EXAMPLE = "name,asc";

    private PaginatedConstants() { }

}
