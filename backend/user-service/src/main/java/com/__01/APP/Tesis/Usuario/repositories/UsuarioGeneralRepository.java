package com.__01.APP.Tesis.Usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.__01.APP.Tesis.Usuario.models.entities.UsuarioGeneral;
=======
import com.__01.APP.Tesis.Usuario.models.UsuarioGeneral;
>>>>>>> a671c442f8d4471ebfe5e9368e528f90db5463bc

import java.util.Optional;

@Repository
public interface UsuarioGeneralRepository extends JpaRepository<UsuarioGeneral, Long> {
    Optional<UsuarioGeneral> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
    Optional<UsuarioGeneral> findByEmail(String email);
    boolean existsByEmail(String email);
}
