package com.emezon.cart.infra.config;

import com.emezon.cart.app.handlers.ICartItemHandler;
import com.emezon.cart.app.services.CartItemService;
import com.emezon.cart.domain.api.IPersistCartInPort;
import com.emezon.cart.domain.api.IPersistCartItemInPort;
import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.api.IRetrieveCartItemInPort;
import com.emezon.cart.domain.spi.ICartItemRepositoryOutPort;
import com.emezon.cart.domain.spi.IJwtService;
import com.emezon.cart.domain.spi.external.IArticleExternalOutPort;
import com.emezon.cart.domain.usecases.PersistCartItemUseCase;
import com.emezon.cart.domain.usecases.RetrieveCartItemUseCase;
import com.emezon.cart.infra.outbound.mysql.jpa.adapters.MySQLJpaCartItemAdapter;
import com.emezon.cart.infra.outbound.mysql.jpa.repositories.IMySQLJpaCartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CartItemConfig {

    private final IMySQLJpaCartItemRepository repository;
    private final IRetrieveCartInPort retrieveCartInPort;
    private final IPersistCartInPort persistCartInPort;
    private final IJwtService jwtService;
    private final IArticleExternalOutPort articleExternalOutPort;

    @Bean
    public ICartItemRepositoryOutPort cartItemRepositoryOutPort() {
        return new MySQLJpaCartItemAdapter(repository);
    }

    @Bean
    public IRetrieveCartItemInPort retrieveCartItemInPort() {
        return new RetrieveCartItemUseCase(cartItemRepositoryOutPort());
    }

    @Bean
    public IPersistCartItemInPort persistCartItemInPort() {
        return new PersistCartItemUseCase(
                cartItemRepositoryOutPort(),
                retrieveCartItemInPort(),
                retrieveCartInPort,
                persistCartInPort,
                jwtService,
                articleExternalOutPort
        );
    }

    @Bean
    public ICartItemHandler cartItemHandler() {
        return new CartItemService(persistCartItemInPort());
    }

}
