package com.__01.APP.Tesis.Usuario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
<<<<<<< HEAD
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
=======
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/webjars/**").permitAll()
                .requestMatchers("/api/usuarios/registro", "/api/usuarios/login").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> {});

        return http.build();
    }
}

>>>>>>> a671c442f8d4471ebfe5e9368e528f90db5463bc
