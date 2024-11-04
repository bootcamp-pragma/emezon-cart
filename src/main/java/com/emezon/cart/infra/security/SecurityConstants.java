package com.emezon.cart.infra.security;

import com.emezon.stock.domain.models.external.UserRoles;
import com.emezon.stock.infra.inbound.rest.constants.RestApiConstants;

public class SecurityConstants {

    public static final String[] WHITE_LIST_URL = {
            "/api/docs/**",
            "/v3/api-docs/**",
            "/api/swagger-ui/**",
            RestApiConstants.API_PING + "/**",
    };

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHENTICATION_SCHEME = "Bearer ";

    public static final String ROLE_NAME_CLAIM = "roleName";

    public static final String ROLE_PREFIX = "ROLE_";

    public static final String ADMIN = UserRoles.ADMIN.toString();
    public static final String AUX_BODEGA = UserRoles.AUX_BODEGA.toString();

    public static final String[] ADD_SUPPLY_ROLES = { ADMIN, AUX_BODEGA };

}
