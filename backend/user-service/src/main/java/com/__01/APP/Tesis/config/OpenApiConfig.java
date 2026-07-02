package com.__01.APP.Tesis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Usuario Service API")
                .version("1.0.0")
                .description("API de gestión de usuarios con autenticación y registro")
                .contact(new Contact()
                    .name("Gabriel")
                    .email("gabriel@example.com")))
            .addServersItem(new Server()
                .url("/")
                .description("Servidor Actual"));
    }
}