package com.__01.APP.Tesis.Catalogos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.__01.APP.Tesis.Usuario.dto.ApiResponse;
import com.__01.APP.Tesis.Catalogos.services.CatalogoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Tag(name = "Catálogos", description = "Endpoints para obtener regiones, ubicaciones y categorías")
public class CatalogoController {

    private final CatalogoService catalogoService;

    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GetMapping("/categorias")
    public ResponseEntity<?> getCategorias(@RequestParam(required = false) String type) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Categorías obtenidas", catalogoService.obtenerCategorias(type)));
    }

    @GetMapping("/regiones")
    public ResponseEntity<?> getRegiones() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Regiones obtenidas", catalogoService.obtenerRegiones()));
    }

    @GetMapping("/ubicaciones")
    public ResponseEntity<?> getUbicaciones() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Ubicaciones obtenidas", catalogoService.obtenerUbicaciones()));
    }
}