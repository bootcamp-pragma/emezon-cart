package com.emezon.cart.domain.utils;

import java.util.List;

public class PaginatedResponseParams {
    public int page;
    public int size;
    public List<String> sorting;

    public PaginatedResponseParams() {}

    public PaginatedResponseParams(int page, int size, List<String> sorting) {
        this.page = page;
        this.size = size;
        this.sorting = sorting;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getSorting() {
        return sorting;
    }

    public void setSorting(List<String> sorting) {
        this.sorting = sorting;
    }
}
