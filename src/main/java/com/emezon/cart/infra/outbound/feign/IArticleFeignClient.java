package com.emezon.cart.infra.outbound.feign;

import com.emezon.cart.domain.models.external.Article;
import com.emezon.cart.infra.inbound.rest.constants.RestApiConstants;
import com.emezon.cart.infra.outbound.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ms-stock-article",
        url = "http://localhost:8073",
        path = RestApiConstants.API_FEIGN_ARTICLE,
        configuration = FeignConfig.class
)
public interface IArticleFeignClient {

    @GetMapping("/{id}")
    Article getArticleById(@PathVariable String id);

}
