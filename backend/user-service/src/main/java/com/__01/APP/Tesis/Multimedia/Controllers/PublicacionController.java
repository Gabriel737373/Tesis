package com.__01.APP.Tesis.Multimedia.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.__01.APP.Tesis.Multimedia.Dto.PublicacionRequest;
import com.__01.APP.Tesis.Multimedia.Dto.PublicacionResponse;
import com.__01.APP.Tesis.Multimedia.Services.PublicacionService;
import com.__01.APP.Tesis.Usuario.dto.ApiResponse; // Usamos tu respuesta genérica

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin(origins = {"*"})
@Tag(name = "Gestión de Muro/Publicaciones", description = "Endpoints para el feed multimedia de usuarios y empresas")
public class PublicacionController {

    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @PostMapping("/crear")
    @Operation(summary = "Crear nueva publicación", description = "Crea un post asignado a un usuario o empresa")
    public ResponseEntity<ApiResponse<PublicacionResponse>> crear(@RequestBody PublicacionRequest request) {
        try {
            PublicacionResponse response = publicacionService.crear(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Publicación creada con éxito", response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }

    @GetMapping("/muro")
    @Operation(summary = "Obtener el feed global", description = "Retorna todas las publicaciones ordenadas cronológicamente")
    public ResponseEntity<ApiResponse<List<PublicacionResponse>>> obtenerMuroGlobal() {
        List<PublicacionResponse> responses = publicacionService.obtenerMuroGlobal();
        return ResponseEntity.ok(new ApiResponse<>(true, "Muro cargado", responses));
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Publicaciones por usuario", description = "Retorna el historial de posts de un usuario específico")
    public ResponseEntity<ApiResponse<List<PublicacionResponse>>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        List<PublicacionResponse> responses = publicacionService.obtenerPorUsuario(usuarioId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Publicaciones del usuario", responses));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar publicación", description = "Elimina un post específico por su ID")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        try {
            publicacionService.eliminar(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Publicación eliminada exitosamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }
}