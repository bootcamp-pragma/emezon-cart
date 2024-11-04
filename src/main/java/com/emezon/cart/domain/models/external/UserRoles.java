package com.emezon.cart.domain.models.external;

public enum UserRoles {

    AUX_BODEGA("aux_bodega"),
    ADMIN("admin"),
    CLIENT("client");

    private final String name;

    UserRoles(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
