package com.emezon.cart.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${app-title}") String appTitle,
            @Value("${app-description}") String appDescription,
            @Value("${app-version}") String appVersion,
            @Value("${springdoc.jwt.key}") String key,
            @Value("${springdoc.jwt.scheme}") String scheme,
            @Value("${springdoc.jwt.format}") String format,
            @Value("${springdoc.jwt.name}") String name
    ) {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes(
                        key,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(scheme)
                                .bearerFormat(format)
                                .in(SecurityScheme.In.HEADER)
                                .name(name)
                ))
                .info(new Info()
                        .title(appTitle)
                        .description(appDescription)
                        .version(appVersion))
                .addSecurityItem(new SecurityRequirement().addList(key));
    }

}
