package com.emezon.cart.infra.outbound.feign.adapters;

import com.emezon.cart.domain.models.external.Article;
import com.emezon.cart.domain.spi.external.IArticleExternalOutPort;
import com.emezon.cart.infra.outbound.feign.IArticleFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleFeignExternalAdapter implements IArticleExternalOutPort {

    private final IArticleFeignClient articleFeignClient;

    @Override
    public Article getArticleById(String id) {
        return articleFeignClient.getArticleById(id);
    }

}
