package com.__01.APP.Tesis.Usuario.config;

import java.time.LocalDateTime;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.__01.APP.Tesis.Usuario.models.entities.UsuarioGeneral;
import com.__01.APP.Tesis.Usuario.repositories.UsuarioGeneralRepository;

@Configuration
public class InitialDataLoader {

    @Bean
    ApplicationRunner applicationRunner(UsuarioGeneralRepository usuarioGeneralRepository,
                                       BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            // Crear usuario admin si no existe
            if (!usuarioGeneralRepository.existsByNombreUsuario("admin")) {
                UsuarioGeneral admin = new UsuarioGeneral(
                    "admin",
                    "admin@example.com",
                    passwordEncoder.encode("123456")
                );
                admin.setActivo(true);
                admin.setActualizadoEn(LocalDateTime.now());
                usuarioGeneralRepository.save(admin);
                System.out.println("Usuario admin creado exitosamente");
            } else {
                System.out.println("Usuario admin ya existe");
            }
        };
    }
}
