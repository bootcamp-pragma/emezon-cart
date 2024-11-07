package com.emezon.cart.domain.spi;

public interface IJwtHolder {

    String getToken();

    void setToken(String token);

    void clearToken();
    
}
