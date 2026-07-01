package com.teachain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    private static final String AUTH_SCHEME = "Authorization";

    @Bean
    public OpenAPI teaChainOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("茶饮甜品连锁加盟 SaaS 管理系统 API")
                        .description("多租户茶饮甜品连锁加盟 SaaS 平台后端接口文档")
                        .version("1.0.0")
                        .contact(new Contact().name("tea-chain").email("dev@teachain.com"))
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
                .components(new Components()
                        .addSecuritySchemes(AUTH_SCHEME,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name(AUTH_SCHEME)
                                        .description("请输入 Bearer Token（JWT）")))
                .addSecurityItem(new SecurityRequirement().addList(AUTH_SCHEME));
    }
}
