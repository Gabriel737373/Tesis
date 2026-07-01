package com.__01.APP.Tesis.Catalogos.services;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import com.__01.APP.Tesis.Catalogos.models.*;
import com.__01.APP.Tesis.Catalogos.repositories.*;
import com.__01.APP.Tesis.Catalogos.dto.CatalogoResponse.*;

@Service
public class CatalogoService {

    private final CategoriaRepository categoriaRepo;
    private final RegionRepository regionRepo;
    private final UbicacionRepository ubicacionRepo;

    public CatalogoService(CategoriaRepository catRepo, RegionRepository regRepo, UbicacionRepository ubiRepo) {
        this.categoriaRepo = catRepo;
        this.regionRepo = regRepo;
        this.ubicacionRepo = ubiRepo;
    }

    // ¡TRUCO DE ORO! Llena la base de datos con datos de prueba si está vacía
    @PostConstruct
    public void inicializarDatos() {
        if (categoriaRepo.count() == 0) {
            Categoria c1 = new Categoria(); c1.setName("Producción"); c1.setType("service"); categoriaRepo.save(c1);
            Categoria c2 = new Categoria(); c2.setName("Audiovisual"); c2.setType("service"); categoriaRepo.save(c2);
            Categoria c3 = new Categoria(); c3.setName("Catering"); c3.setType("service"); categoriaRepo.save(c3);
            Categoria c4 = new Categoria(); c4.setName("Decoración"); c4.setType("service"); categoriaRepo.save(c4);
        }
        if (regionRepo.count() == 0) {
            Region r1 = new Region(); r1.setName("Región de Valparaíso"); r1.setSlug("valparaiso");
            r1 = regionRepo.save(r1);

            Ubicacion u1 = new Ubicacion(); u1.setName("Viña del Mar"); u1.setRegion(r1); ubicacionRepo.save(u1);
            Ubicacion u2 = new Ubicacion(); u2.setName("Valparaíso"); u2.setRegion(r1); ubicacionRepo.save(u2);
            Ubicacion u3 = new Ubicacion(); u3.setName("Concón"); u3.setRegion(r1); ubicacionRepo.save(u3);
        }
    }

    public List<CategoriaDTO> obtenerCategorias(String type) {
        List<Categoria> categorias = (type != null && !type.isEmpty()) 
            ? categoriaRepo.findByType(type) 
            : categoriaRepo.findAll();
            
        return categorias.stream()
                .map(c -> new CategoriaDTO(c.getId().toString(), c.getName(), c.getType()))
                .collect(Collectors.toList());
    }

    public List<RegionDTO> obtenerRegiones() {
        return regionRepo.findAll().stream().map(r -> {
            List<LocationMiniDTO> locs = r.getLocations().stream()
                .map(u -> new LocationMiniDTO(u.getId().toString(), u.getName()))
                .collect(Collectors.toList());
            return new RegionDTO(r.getId().toString(), r.getName(), r.getSlug(), locs);
        }).collect(Collectors.toList());
    }

    public List<UbicacionDTO> obtenerUbicaciones() {
        return ubicacionRepo.findAll().stream()
                .map(u -> new UbicacionDTO(
                    u.getId().toString(), 
                    u.getName(), 
                    new RegionMiniDTO(u.getRegion().getId().toString(), u.getRegion().getName(), u.getRegion().getSlug())
                )).collect(Collectors.toList());
    }
}