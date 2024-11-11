package com.emezon.cart.infra.config;

import com.emezon.cart.domain.api.IPersistCartInPort;
import com.emezon.cart.domain.api.IRetrieveCartInPort;
import com.emezon.cart.domain.spi.ICartRepositoryOutPort;
import com.emezon.cart.domain.spi.IJwtService;
import com.emezon.cart.domain.usecases.PersistCartUseCase;
import com.emezon.cart.domain.usecases.RetrieveCartUseCase;
import com.emezon.cart.infra.outbound.mysql.jpa.adapters.MySQLJpaCartAdapter;
import com.emezon.cart.infra.outbound.mysql.jpa.repositories.IMySQLJpaCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CartConfig {

    private final IMySQLJpaCartRepository repository;
    private final IJwtService jwtService;

    @Bean
    public ICartRepositoryOutPort cartRepositoryOutPort() {
        return new MySQLJpaCartAdapter(repository);
    }

    @Bean
    public IRetrieveCartInPort retrieveCartInPort() {
        return new RetrieveCartUseCase(cartRepositoryOutPort());
    }

    @Bean
    public IPersistCartInPort persistCartInPort() {
        return new PersistCartUseCase(cartRepositoryOutPort(), retrieveCartInPort(), jwtService);
    }

}
