package com.project.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        String securitySchemeName = "bearerAuth";

        SecurityScheme securityScheme = new SecurityScheme().name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT");

        Components securityComponent = new Components().addSecuritySchemes(securitySchemeName, securityScheme);

        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securitySchemeName);

        return new OpenAPI().info(new Info().title("LibraryAPI").description("Reference documentation of API")
                .version("v0.0.1")).components(securityComponent).addSecurityItem(securityRequirement);
    }
}