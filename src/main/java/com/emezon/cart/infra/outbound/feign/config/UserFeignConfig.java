package com.emezon.cart.infra.outbound.feign.config;

import com.emezon.cart.domain.spi.external.IUserExternalOutPort;
import com.emezon.cart.infra.outbound.feign.IUserFeignClient;
import com.emezon.cart.infra.outbound.feign.adapters.UserFeignExternalAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserFeignConfig {

    private final IUserFeignClient userFeignClient;

    @Bean
    public IUserExternalOutPort userExternalOutPort() {
        return new UserFeignExternalAdapter(userFeignClient);
    }

}
