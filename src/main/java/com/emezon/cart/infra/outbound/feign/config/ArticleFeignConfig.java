package com.emezon.cart.infra.outbound.feign.config;

import com.emezon.cart.domain.spi.external.IArticleExternalOutPort;
import com.emezon.cart.infra.outbound.feign.IArticleFeignClient;
import com.emezon.cart.infra.outbound.feign.adapters.ArticleFeignExternalAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ArticleFeignConfig {

    private final IArticleFeignClient articleFeignClient;

    @Bean
    public IArticleExternalOutPort articleExternalOutPort() {
        return new ArticleFeignExternalAdapter(articleFeignClient);
    }

}
