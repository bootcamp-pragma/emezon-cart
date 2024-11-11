package com.emezon.cart.infra.security;

import com.emezon.cart.domain.models.external.Role;
import com.emezon.cart.domain.models.external.User;
import com.emezon.cart.domain.spi.IJwtService;
import com.emezon.cart.infra.outbound.feign.dtos.UserFeign;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtService implements IJwtService {
    @Value("${spring.security.jwt.secret}")
    private String secret;

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, claims -> (String) claims.get("sub"));
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public <T> T extractClaim(String token, Function<Map<String, Object>, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        Map<String, Object> claimsMap = new HashMap<>(claims);
        return claimsResolver.apply(claimsMap);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, claim -> {
            Number exp = (Number) claim.get("exp");
            long expLong = exp.longValue();
            return new Date(expLong * 1000);
        });
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public boolean isTokenValid(String token, Map<String, Object> data) {
        final String username = extractUsername(token);
        if (isTokenExpired(token)) {
            throw new ExpiredJwtException(null, null, "Token has expired");
        }
        return username.equals(data.get("username"));
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserFeign user) {
                User userDomain = new User();
                userDomain.setId(user.getId());
                userDomain.setEmail(user.getEmail());
                Role role = new Role();
                role.setId(user.getRole().getId());
                role.setName(user.getRole().getName());
                role.setDescription(user.getRole().getDescription());
                userDomain.setRole(role);
                return userDomain;
            }
        }
        return null;
    }


}
