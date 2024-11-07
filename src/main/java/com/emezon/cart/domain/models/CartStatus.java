package com.emezon.cart.domain.models;

public enum CartStatus {

    ACTIVE(1),
    COMPLETED(2),
    CANCELED(3);

    private final int status;

    CartStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return String.valueOf(status);
    }

}
