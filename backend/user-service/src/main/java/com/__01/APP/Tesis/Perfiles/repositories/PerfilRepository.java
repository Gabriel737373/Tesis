package com.__01.APP.Tesis.Perfiles.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.__01.APP.Tesis.Perfiles.models.Perfil; 

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<Perfil> findByUserId(String userId);
    
}