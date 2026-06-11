package com.__01.APP.Tesis.Empresa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.__01.APP.Tesis.Empresa.models.entities.TipoEmpresa;

@Repository
public interface TipoEmpresaRepository extends JpaRepository<TipoEmpresa, Long> {
    
    Optional<TipoEmpresa> findByCategoriaEmpresa(String categoriaEmpresa);
    
    boolean existsByCategoriaEmpresa(String categoriaEmpresa);
}