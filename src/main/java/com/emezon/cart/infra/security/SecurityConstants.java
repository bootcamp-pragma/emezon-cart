package com.emezon.cart.infra.security;

import com.emezon.cart.domain.models.external.UserRoles;

public class SecurityConstants {

    public static final String[] WHITE_LIST_URL = {
            "/api/docs/**",
            "/v3/api-docs/**",
            "/api/swagger-ui/**",
    };

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHENTICATION_SCHEME = "Bearer ";

    public static final String ROLE_NAME_CLAIM = "roleName";

    public static final String ROLE_PREFIX = "ROLE_";

    public static final String ADMIN = UserRoles.ADMIN.toString();
    public static final String AUX_BODEGA = UserRoles.AUX_BODEGA.toString();

}
