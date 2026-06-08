package com.__01.APP.Tesis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                // ¡AQUÍ ESTÁ EL CAMBIO! Agregamos la ruta de las empresas
                .requestMatchers(
                    "/swagger-ui/**", 
                    "/v3/api-docs/**", 
                    "/swagger-ui.html", 
                    "/api/usuarios/**",
                    "/api/empresas/**" // <-- Añade esta línea (verifica que sea la ruta real de tu controlador)
                ).permitAll()
                // El resto sigue exigiendo autenticación
                .anyRequest().authenticated() 
            );
            
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
