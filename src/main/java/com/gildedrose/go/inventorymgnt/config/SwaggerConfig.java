package com.gildedrose.go.inventorymgnt.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Swagger.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customConfiguration() {
        return new OpenAPI().info(new Info()
                .title("Gilded Rose API")
                .version("1.0")
                .description("Open API documentation for Gilded Rose Inventory management system"));
    }

}
