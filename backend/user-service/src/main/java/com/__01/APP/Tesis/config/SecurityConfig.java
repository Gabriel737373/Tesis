package com.__01.APP.Tesis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF para facilitar pruebas en Postman/Swagger
            .authorizeHttpRequests(auth -> auth
                // Liberar los endpoints de Swagger y OpenAPI
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/api/usuarios/**").permitAll()
                // Cualquier otra ruta requerirá autenticación (puedes cambiarlo a permitAll() si quieres probar)
                .anyRequest().authenticated() 
            );
        return http.build();    
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
