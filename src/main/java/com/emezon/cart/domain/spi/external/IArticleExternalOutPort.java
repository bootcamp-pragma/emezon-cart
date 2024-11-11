package com.emezon.cart.domain.spi.external;

import com.emezon.cart.domain.models.external.Article;

public interface IArticleExternalOutPort {

    Article getArticleById(String id);

}
