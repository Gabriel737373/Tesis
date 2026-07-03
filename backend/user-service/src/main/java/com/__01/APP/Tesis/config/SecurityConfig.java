package com.__01.APP.Tesis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(cors -> cors.disable()) // Usa tu CorsFilter existente aquí si lo prefieres
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas (No piden Token)
                .requestMatchers(
                    "/api/auth/sign-in/**", 
                    "/api/auth/sign-up/**", 
                    "/api/usuarios/registro", 
                    "/api/usuarios/login",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                // Todas las demás rutas exigen que el usuario envíe su Token
                .anyRequest().authenticated() 
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Añadimos nuestro filtro JWT ANTES del filtro tradicional de contraseñas
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}