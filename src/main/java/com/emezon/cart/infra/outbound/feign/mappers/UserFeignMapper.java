package com.emezon.cart.infra.outbound.feign.mappers;

import com.emezon.cart.domain.models.external.User;
import com.emezon.cart.infra.outbound.feign.dtos.UserFeign;

public class UserFeignMapper {

    private UserFeignMapper() { }

    public static UserFeign toUserFeign(User user) {
        UserFeign userFeign = new UserFeign();
        userFeign.setId(user.getId());
        userFeign.setName(user.getName());
        userFeign.setLastName(user.getLastName());
        userFeign.setDocNumber(user.getDocNumber());
        userFeign.setCellphone(user.getCellphone());
        userFeign.setBirthdate(user.getBirthdate());
        userFeign.setEmail(user.getEmail());
        userFeign.setRole(RoleFeignMapper.toRoleFeign(user.getRole()));
        return userFeign;
    }

    public static User toUser(UserFeign userFeign) {
        User user = new User();
        user.setId(userFeign.getId());
        user.setName(userFeign.getName());
        user.setLastName(userFeign.getLastName());
        user.setDocNumber(userFeign.getDocNumber());
        user.setCellphone(userFeign.getCellphone());
        user.setBirthdate(userFeign.getBirthdate());
        user.setEmail(userFeign.getEmail());
        user.setRole(RoleFeignMapper.toRole(userFeign.getRole()));
        return user;
    }

}
