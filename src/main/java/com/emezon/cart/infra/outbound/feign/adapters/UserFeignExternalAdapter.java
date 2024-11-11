package com.emezon.cart.infra.outbound.feign.adapters;

import com.emezon.cart.domain.models.external.User;
import com.emezon.cart.domain.spi.external.IUserExternalOutPort;
import com.emezon.cart.infra.outbound.feign.IUserFeignClient;
import com.emezon.cart.infra.outbound.feign.dtos.UserFeign;
import com.emezon.cart.infra.outbound.feign.mappers.UserFeignMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserFeignExternalAdapter implements IUserExternalOutPort {

    private final IUserFeignClient userFeignClient;

    @Override
    public User findByEmail(String email) {
        UserFeign userFeign = userFeignClient.findUserByEmail(email);
        return UserFeignMapper.toUser(userFeign);
    }
}
