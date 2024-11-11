package com.emezon.cart.infra.outbound.feign.mappers;

import com.emezon.cart.domain.models.external.Role;
import com.emezon.cart.infra.outbound.feign.dtos.RoleFeign;

public class RoleFeignMapper {

    private RoleFeignMapper() { }

    public static RoleFeign toRoleFeign(Role role) {
        return new RoleFeign(role.getId(), role.getName(), role.getDescription());
    }

    public static Role toRole(RoleFeign roleFeign) {
        return new Role(roleFeign.getId(), roleFeign.getName(), roleFeign.getDescription());
    }

}
