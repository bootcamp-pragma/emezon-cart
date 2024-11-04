package com.emezon.cart.infra.security;

import com.emezon.cart.domain.spi.IJwtHolder;
import com.emezon.cart.domain.spi.IJwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final IJwtService jwtService;
    private final IJwtHolder jwtHolder;
    private final UserDetailsService userDetailsService;

    private String getTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
        if (authHeader != null && authHeader.startsWith(SecurityConstants.AUTHENTICATION_SCHEME)) {
            return authHeader.substring(SecurityConstants.AUTHENTICATION_SCHEME.length());
        }
        return null;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException
    {
        if (shouldNotFilter(request)){
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = getTokenFromRequest(request);
        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtHolder.setToken(jwt);

        final String email = jwtService.extractUsername(jwt);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            Map<String, Object> data = new HashMap<>();
            data.put("username", userDetails.getUsername());
            if (jwtService.isTokenValid(jwt, data)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return Arrays.stream(SecurityConstants.WHITE_LIST_URL)
                .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }

}
