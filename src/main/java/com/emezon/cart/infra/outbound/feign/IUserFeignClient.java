package com.emezon.cart.infra.outbound.feign;

import com.emezon.cart.infra.inbound.rest.constants.RestApiConstants;
import com.emezon.cart.infra.outbound.feign.config.FeignConfig;
import com.emezon.cart.infra.outbound.feign.dtos.UserFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ms-user-user",
        url = "http://localhost:8074",
        path = RestApiConstants.API_FEIGN_USER,
        configuration = FeignConfig.class
)
public interface IUserFeignClient {

    @GetMapping("/email/{email}")
    UserFeign findUserByEmail(@PathVariable("email") String email);

}
