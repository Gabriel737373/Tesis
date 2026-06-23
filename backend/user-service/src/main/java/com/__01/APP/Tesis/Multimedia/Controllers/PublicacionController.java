package com.__01.APP.Tesis.Multimedia.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType; // IMPORTANTE
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping; // IMPORTANTE
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.__01.APP.Tesis.Multimedia.Dto.PublicacionRequest;
import com.__01.APP.Tesis.Multimedia.Dto.PublicacionResponse;
import com.__01.APP.Tesis.Multimedia.Services.PublicacionService;
import com.__01.APP.Tesis.Usuario.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
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

    // AÑADIDO: consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    @PostMapping(value = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Crear nueva publicación", description = "Crea un post asignado a un usuario o empresa subiendo un archivo multimedia")
    public ResponseEntity<ApiResponse<PublicacionResponse>> crear(
            @Parameter(description = "Archivo multimedia (imagen o video)", required = true) 
            @RequestPart("archivo") MultipartFile archivo,
            
            @Parameter(description = "Datos de la publicación en formato JSON", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) 
            @RequestPart("datos") PublicacionRequest request) {
        
        try {
            // TODO: Aquí integraremos la subida real (Cloudinary o carpeta local).
            // Por ahora, extraemos el nombre original del archivo para simular que generó una URL.
            String nombreArchivo = archivo.getOriginalFilename();
            String urlSimulada = "https://servidor.com/archivos/" + nombreArchivo;
            
            // Le asignamos esa URL simulada al DTO antes de guardarlo en la base de datos
            request.setArchivoUrl(urlSimulada);
            
            PublicacionResponse response = publicacionService.crear(request);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Publicación creada con éxito (Archivo recibido: " + nombreArchivo + ")", response));
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