package com.__01.APP.Tesis.Empresa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.__01.APP.Tesis.Empresa.models.entities.EmpresaGeneral;

@Repository
public interface EmpresaGeneralRepository extends JpaRepository<EmpresaGeneral, Long> {
    Optional<EmpresaGeneral> findByNombreEmpresa(String nombreEmpresa);
    boolean existsByNombreEmpresa(String nombreEmpresa);
    Optional<EmpresaGeneral> findByEmail(String email);
    boolean existsByEmail(String email);

}
