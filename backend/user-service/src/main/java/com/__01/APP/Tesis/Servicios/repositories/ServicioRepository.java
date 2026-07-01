package com.__01.APP.Tesis.Servicios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.__01.APP.Tesis.Servicios.models.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    // Si en el futuro necesitas buscar por slug o estado, lo agregas aquí
    // Optional<Servicio> findBySlug(String slug);
}