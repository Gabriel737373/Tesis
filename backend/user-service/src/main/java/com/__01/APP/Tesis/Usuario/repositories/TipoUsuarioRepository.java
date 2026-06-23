package com.__01.APP.Tesis.Usuario.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.__01.APP.Tesis.Usuario.models.entities.TipoUsuario;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
    
    Optional<TipoUsuario> findByCategoriaUsuario(String categoriaUsuario);

    // AÑADIDO: Busca por categoría ignorando mayúsculas/minúsculas
    Optional<TipoUsuario> findByCategoriaUsuarioIgnoreCase(String categoriaUsuario);
    
    boolean existsByCategoriaUsuario(String categoriaUsuario);
}