package com.furniturewala.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI furnitureWalaOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("FurnitureWala Backend API")
                .version("v1")
                .description("Local REST API documentation for customers, projects, materials, and furniture types.")
                .contact(new Contact().name("FurnitureWala API Team")));
    }
}
