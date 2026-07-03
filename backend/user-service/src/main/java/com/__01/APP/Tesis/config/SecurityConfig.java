package com.__01.APP.Tesis.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
            .cors(Customizer.withDefaults()) // <-- CORS Global encendido
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                // --- ¡RUTAS DEL FRONTEND PERMITIDAS! ---
                .requestMatchers(
                    "/api/auth/**", 
                    "/api/servicios/**",  // <-- Faltaba esto
                    "/api/eventos/**",    // <-- Faltaba esto
                    "/api/categorias/**", // <-- Faltaba esto
                    "/api/regiones/**",   // <-- Faltaba esto
                    "/api/ubicaciones/**",// <-- Faltaba esto
                    "/api/profiles/**",   // <-- Faltaba esto
                    "/api/users/**",      // <-- Faltaba esto para poder borrar cuenta
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                // Todas las demás rutas exigen que el usuario envíe su Token
                .anyRequest().authenticated() 
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // --- 2. EL MOTOR DE CORS DINÁMICO QUE ARREGLA EL ERROR ---
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        configuration.setAllowCredentials(true); // Obligatorio para Next.js
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // ¡Truco! Permite todo pero devuelve la IP exacta en vez de "*"
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}