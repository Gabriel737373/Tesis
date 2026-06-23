package com.__01.APP.Tesis.Multimedia.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.__01.APP.Tesis.Multimedia.Models.Entities.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    
    // Trae las publicaciones de un usuario específico ordenadas de la más nueva a la más antigua
    List<Publicacion> findByUsuarioIdOrderByFechaPublicacionDesc(Long usuarioId);
    
    // Trae las publicaciones de una empresa específica ordenadas de la más nueva a la más antigua
    List<Publicacion> findByEmpresaIdOrderByFechaPublicacionDesc(Long empresaId);
    
    // Ideal para el Muro Global de la app: trae absolutamente todo ordenado por fecha descendente
    List<Publicacion> findAllByOrderByFechaPublicacionDesc();
}