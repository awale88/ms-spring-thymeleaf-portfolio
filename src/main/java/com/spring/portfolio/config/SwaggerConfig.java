package com.spring.portfolio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Portfolio Contact API")
                        .version("1.0")
                        .description("API for contact forms and sending emails"))
                .servers(List.of(
                        new Server()
                                .url("https://swagger-ui-production.up.railway.app/")
                                .description("Production Server"),
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Server")));
    }
}
