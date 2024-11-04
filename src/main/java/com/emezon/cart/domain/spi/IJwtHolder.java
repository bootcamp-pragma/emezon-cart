package com.emezon.cart.domain.spi;

public interface IJwtHolder {

    public String getToken();

    public void setToken(String token);

    public void clearToken();
    
}
