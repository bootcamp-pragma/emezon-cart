package com.emezon.cart.domain.spi;

import java.util.Map;
import java.util.function.Function;

public interface IJwtService {

    public String extractUsername(String token);

    public <T> T extractClaim(String token, Function<Map<String, Object>, T> claimsResolver);

    public boolean isTokenValid(String token, Map<String, Object> data);

    public String getRoleName(String token);


}
