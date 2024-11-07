package com.emezon.cart.domain.spi;

import java.util.Map;
import java.util.function.Function;

public interface IJwtService {

    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Map<String, Object>, T> claimsResolver);

    boolean isTokenValid(String token, Map<String, Object> data);

    String getRoleName(String token);

    String getAuthenticatedUserId();

}
