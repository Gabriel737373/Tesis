package com.__01.APP.Tesis.repositories;

import com.__01.APP.Tesis.models.UsuarioGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioGeneralRepository extends JpaRepository<UsuarioGeneral, Long> {
    Optional<UsuarioGeneral> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
    Optional<UsuarioGeneral> findByEmail(String email);
    boolean existsByEmail(String email);
}
