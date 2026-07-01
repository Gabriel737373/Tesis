package com.__01.APP.Tesis.Catalogos.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.__01.APP.Tesis.Catalogos.models.Categoria;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByType(String type);
}