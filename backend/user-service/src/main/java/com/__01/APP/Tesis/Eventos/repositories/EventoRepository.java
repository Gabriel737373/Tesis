package com.__01.APP.Tesis.Eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.__01.APP.Tesis.Eventos.models.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}