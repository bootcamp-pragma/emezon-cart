package com.emezon.cart.infra.outbound.feign.utils;

import com.emezon.cart.domain.spi.IJwtHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeignClientInterceptor implements RequestInterceptor {

    private final IJwtHolder jwtHolder;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String jwtToken = jwtHolder.getToken();
        if (jwtToken != null) {
            requestTemplate.header("Authorization", "Bearer " + jwtToken);
        }
    }

}