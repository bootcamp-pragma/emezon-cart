package com.emezon.cart.domain.spi;

import com.emezon.cart.domain.models.external.User;

import java.util.Map;
import java.util.function.Function;

public interface IJwtService {

    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Map<String, Object>, T> claimsResolver);

    boolean isTokenValid(String token, Map<String, Object> data);

    User getAuthenticatedUser();

}
