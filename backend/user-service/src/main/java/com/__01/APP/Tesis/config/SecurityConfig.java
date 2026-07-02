package com.__01.APP.Tesis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) // Habilita el filtro CORS global de abajo
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/swagger-ui/**", 
                    "/v3/api-docs/**", 
                    "/swagger-ui.html", 
                    "/api/usuarios/**",
                    "/api/empresas/**",
                    "/api/publicaciones/**",
                    
                    // --- ¡RUTAS DEL NUEVO FRONTEND PERMITIDAS! ---
                    "/api/auth/**",
                    "/api/servicios/**",
                    "/api/eventos/**",
                    "/api/categorias/**",
                    "/api/regiones/**",
                    "/api/ubicaciones/**",
                    "/api/profiles/**",
                    "/api/contactos/**",
                    "/api/users/**"
                ).permitAll()
                // El resto sigue exigiendo autenticación (para el futuro JWT)
                .anyRequest().authenticated() 
            );
            
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); 
        config.addAllowedHeader("*"); 
        config.addAllowedMethod("*"); 
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}