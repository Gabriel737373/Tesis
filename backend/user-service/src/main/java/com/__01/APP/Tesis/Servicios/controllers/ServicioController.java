package com.__01.APP.Tesis.Servicios.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.__01.APP.Tesis.Servicios.dto.CreateServicioRequest;
import com.__01.APP.Tesis.Servicios.dto.ServicioResponse;
import com.__01.APP.Tesis.Servicios.services.ServicioService;
import com.__01.APP.Tesis.Usuario.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/servicios")
@Tag(name = "Gestión de Servicios", description = "Endpoints para el directorio de servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @PostMapping
    @Operation(summary = "Crear servicio", description = "Registra un nuevo servicio desde el formulario del frontend")
    public ResponseEntity<ApiResponse<ServicioResponse>> crearServicio(@RequestBody CreateServicioRequest request) {
        try {
            ServicioResponse response = servicioService.crearServicio(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Servicio creado con éxito", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error al crear el servicio: " + e.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Listar servicios", description = "Obtiene todos los servicios para la página de descubrir")
    public ResponseEntity<ApiResponse<List<ServicioResponse>>> listarServicios() {
        try {
            List<ServicioResponse> servicios = servicioService.obtenerTodos();
            return ResponseEntity.ok(new ApiResponse<>(true, "Servicios obtenidos", servicios));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error al obtener servicios"));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalle del servicio", description = "Obtiene la información de un servicio específico por su ID")
    public ResponseEntity<ApiResponse<ServicioResponse>> obtenerServicio(@PathVariable Long id) {
        try {
            ServicioResponse response = servicioService.obtenerPorId(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Servicio encontrado", response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error al buscar el servicio"));
        }
    }
}