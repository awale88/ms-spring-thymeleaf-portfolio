package com.spring.portfolio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${SWAGGER_URL:http://localhost:8080}")
    private String swaggerUrl;

    @Value("${SWAGGER_CORS:http://localhost:3000}")
    private String swaggerCors;

    @Autowired
    Environment environment;

    @Bean
    public OpenAPI customOpenAPI() {
        String appName = environment.getProperty("spring.application.name", "Portfolio Contact API");
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .version("1.0.0")
                        .description("API for portfolio contact forms and email sending")
                        .contact(new Contact()
                                .name("Nayan Awale")
                                .email("awalenayan11@gmail.com")
                                .url("https://ms-thymeleaf-portfolio.onrender.com/contact"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .servers(List.of(
                        new Server().url(swaggerUrl).description("Production Server"),
                        new Server().url("http://localhost:8080").description("Local Server")
                ));
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                swaggerCors,
                "http://localhost:8080",
                "http://localhost:3000"
        ));

        configuration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L); // cache preflight response for 1h

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/v3/api-docs/**", configuration);
        source.registerCorsConfiguration("/swagger-ui/**", configuration);
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}
