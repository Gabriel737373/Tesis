package com.__01.APP.Tesis.Contactos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.__01.APP.Tesis.Contactos.models.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
    
    // Busca los mensajes de un usuario y los ordena del más nuevo al más viejo
    List<Contacto> findByReceiverIdOrderByCreatedAtDesc(String receiverId);
}