package com.__01.APP.Tesis.controllers;

import com.__01.APP.Tesis.dto.ApiResponse;
import com.__01.APP.Tesis.dto.LoginRequest;
import com.__01.APP.Tesis.dto.RegistroRequest;
import com.__01.APP.Tesis.dto.UsuarioGeneralResponse;
import com.__01.APP.Tesis.services.UsuarioGeneralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = {"*"})
public class UsuarioGeneralController {

    private final UsuarioGeneralService usuarioGeneralService;

    public UsuarioGeneralController(UsuarioGeneralService usuarioGeneralService) {
        this.usuarioGeneralService = usuarioGeneralService;
    }

    @PostMapping("/registro")
    public ResponseEntity<ApiResponse<UsuarioGeneralResponse>> registro(@RequestBody RegistroRequest request) {
        try {
            UsuarioGeneralResponse response = usuarioGeneralService.registrar(
                request.getNombreUsuario(),
                request.getEmail(),
                request.getContrasena()
            );
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Usuario registrado exitosamente", response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UsuarioGeneralResponse>> login(@RequestBody LoginRequest request) {
        try {
            UsuarioGeneralResponse usuario = usuarioGeneralService.verificarCredenciales(
                request.getNombreUsuario(),
                request.getContrasena()
            );
            if (usuario != null) {
                return ResponseEntity.ok(
                    new ApiResponse<>(true, "Login exitoso", usuario)
                );
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Nombre de usuario o contraseña incorrectos"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioGeneralResponse>> obtenerPorId(@PathVariable Long id) {
        try {
            UsuarioGeneralResponse usuario = usuarioGeneralService.obtenerPorId(id);
            if (usuario != null) {
                return ResponseEntity.ok(
                    new ApiResponse<>(true, "Usuario obtenido", usuario)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Usuario no encontrado"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<UsuarioGeneralResponse>> obtenerPorEmail(@PathVariable String email) {
        try {
            UsuarioGeneralResponse usuario = usuarioGeneralService.obtenerPorEmail(email);
            if (usuario != null) {
                return ResponseEntity.ok(
                    new ApiResponse<>(true, "Usuario obtenido", usuario)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Usuario no encontrado"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioGeneralResponse>>> listarTodos() {
        try {
            List<UsuarioGeneralResponse> usuarios = usuarioGeneralService.listar();
            return ResponseEntity.ok(
                new ApiResponse<>(true, "Usuarios obtenidos", usuarios)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        try {
            boolean eliminado = usuarioGeneralService.eliminar(id);
            if (eliminado) {
                return ResponseEntity.ok(
                    new ApiResponse<>(true, "Usuario eliminado exitosamente")
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Usuario no encontrado"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }
}
