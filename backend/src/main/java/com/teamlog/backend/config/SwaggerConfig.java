package com.teamlog.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI teamlogOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TeamLog API 문서")
                        .description("JWT 인증 기반 API 문서입니다.")
                        .version("v1.0"));
    }
}
