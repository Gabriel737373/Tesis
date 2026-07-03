package com.__01.APP.Tesis.ControllerGenerico;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.__01.APP.Tesis.Usuario.dto.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users") // Ruta en inglés que busca el frontend
@Tag(name = "Gestión de Usuarios (Frontend)", description = "Endpoints en inglés requeridos por Next.js")
public class UserController {

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
      
        return ResponseEntity.ok(new ApiResponse<>(true, "Cuenta eliminada correctamente", null));
    }
}