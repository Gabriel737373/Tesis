package com.__01.APP.Tesis.Usuario.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.__01.APP.Tesis.Usuario.dto.ApiResponse;
import com.__01.APP.Tesis.Usuario.dto.LoginRequest;
import com.__01.APP.Tesis.Usuario.dto.RegistroRequest;
import com.__01.APP.Tesis.Usuario.dto.UsuarioGeneralResponse;
import com.__01.APP.Tesis.Usuario.services.UsuarioGeneralService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = {"*"})
@Tag(name = "Gestión de Usuarios", description = "Endpoints para registro, login y gestión de usuarios")
public class UsuarioGeneralController {

    private final UsuarioGeneralService usuarioGeneralService;

    public UsuarioGeneralController(UsuarioGeneralService usuarioGeneralService) {
        this.usuarioGeneralService = usuarioGeneralService;
    }

    @PostMapping("/registro")
    @Operation(summary = "Registrar nuevo usuario", description = "Crea un nuevo usuario en el sistema con validaciones de email y contraseña")
    public ResponseEntity<ApiResponse<UsuarioGeneralResponse>> registro(@RequestBody RegistroRequest request) {
        try {
            UsuarioGeneralResponse response = usuarioGeneralService.registrar(request);
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
    @Operation(summary = "Iniciar sesión de usuario", description = "Autentica al usuario y retorna sus datos si las credenciales son válidas")
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

    @GetMapping("/obtener/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Obtiene un usuario por su identificador único")
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
    @Operation(summary = "Obtener usuario por email", description = "Busca un usuario por su dirección de correo electrónico")
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

    @GetMapping("/listar")
    @Operation(summary = "Listar todos los usuarios", description = "Obtiene una lista de todos los usuarios registrados en el sistema")
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

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina permanentemente un usuario del sistema por su ID")
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
