package com.emezon.cart.infra.outbound.mysql.jpa.utils;

import com.emezon.cart.domain.utils.PaginatedResponseParams;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PageableUtils {

    public static Pageable getFromPaginatedResponseParams(PaginatedResponseParams params) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String sort : params.getSorting()) {
            String[] sortArr = sort.split(",");
            if (sortArr.length == 2) {
                Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortArr[1]), sortArr[0]);
                orders.add(order);
            }
        }
        return PageRequest.of(params.getPage(), params.getSize(), Sort.by(orders));
    }

    private PageableUtils() {}

}
