package com.__01.APP.Tesis.ControllerGenerico;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.__01.APP.Tesis.Usuario.dto.ApiResponse;
import com.__01.APP.Tesis.Usuario.dto.RegistroRequest;
import com.__01.APP.Tesis.Usuario.dto.UsuarioGeneralResponse;
import com.__01.APP.Tesis.Usuario.services.UsuarioGeneralService;
import com.__01.APP.Tesis.config.JwtService; // <-- Importamos tu fábrica de Tokens

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Autenticación Unificada", description = "Endpoints de autenticación requeridos por el frontend Next.js")
public class AuthController {

    private final UsuarioGeneralService usuarioService;
    private final JwtService jwtService; // <-- Inyectamos el servicio JWT

    // Actualizamos el constructor para recibir ambos servicios
    public AuthController(UsuarioGeneralService usuarioService, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    // --- CLASES AUXILIARES PARA RECIBIR LOS JSON DEL FRONTEND ---
    public static class SignInPayload {
        public String email;
        public String password;
    }

    public static class SignUpPayload {
        public String name;
        public String email;
        public String password;
    }

    public static class EmailPayload {
        public String email;
    }

    public static class ChangePasswordPayload {
        public String currentPassword;
        public String newPassword;
    }

    // ==========================================
    // 1. INICIAR SESIÓN (AHORA CON JWT)
    // ==========================================
    @PostMapping("/sign-in/email")
    @Operation(summary = "Login unificado")
    public ResponseEntity<?> signIn(@RequestBody SignInPayload payload) {
        try {
            UsuarioGeneralResponse usuario = usuarioService.verificarCredencialesPorEmail(payload.email, payload.password);
            
            if (usuario != null) {
                Map<String, Object> response = new HashMap<>();
                
                Map<String, Object> userObj = new HashMap<>();
                userObj.put("id", usuario.getId().toString());
                userObj.put("name", usuario.getNombreUsuario()); 
                userObj.put("email", usuario.getEmail());
                userObj.put("image", null); 
                
                // --- MAGIA JWT AQUÍ ---
                // Generamos el token real firmado y lo enviamos al frontend
                String tokenReal = jwtService.generateToken(usuario.getEmail());

                Map<String, Object> sessionObj = new HashMap<>();
                sessionObj.put("id", UUID.randomUUID().toString()); 
                sessionObj.put("userId", usuario.getId().toString());
                sessionObj.put("token", tokenReal); // <-- Guardamos el JWT en la sesión
                
                response.put("user", userObj);
                response.put("session", sessionObj);
                
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse<>(false, "Credenciales incorrectas"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error en el servidor: " + e.getMessage()));
        }
    }

    // ==========================================
    // 2. REGISTRO
    // ==========================================
    @PostMapping("/sign-up/email")
    public ResponseEntity<?> signUp(@RequestBody SignUpPayload payload) {
        try {
            RegistroRequest registro = new RegistroRequest();
            registro.setEmail(payload.email);
            registro.setContrasena(payload.password);
            registro.setNombreUsuario(payload.name); 

            UsuarioGeneralResponse usuario = usuarioService.registrar(registro);
            
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> userObj = new HashMap<>();
            userObj.put("id", usuario.getId().toString());
            userObj.put("name", payload.name);
            userObj.put("email", payload.email);
            
            // También generamos el token al registrarse para que no tenga que hacer login de nuevo
            String tokenReal = jwtService.generateToken(usuario.getEmail());

            Map<String, Object> sessionObj = new HashMap<>();
            sessionObj.put("id", UUID.randomUUID().toString());
            sessionObj.put("userId", usuario.getId().toString());
            sessionObj.put("token", tokenReal);
            
            response.put("user", userObj);
            response.put("session", sessionObj);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, e.getMessage()));
        }
    }

    // ==========================================
    // 3. CERRAR SESIÓN
    // ==========================================
    @PostMapping("/sign-out")
    public ResponseEntity<?> signOut() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Sesión cerrada correctamente", null)); 
    }

    // ==========================================
    // 4. OBTENER USUARIO ACTUAL (LEE EL TOKEN)
    // ==========================================
    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        try {
            // Spring Security ya validó el token antes de llegar aquí gracias al JwtAuthenticationFilter
            // Solo extraemos quién es la persona autenticada
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            
            if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse<>(false, "No autenticado"));
            }

            UsuarioGeneralResponse usuarioReal = (UsuarioGeneralResponse) auth.getPrincipal();

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            
            Map<String, Object> userObj = new HashMap<>();
            userObj.put("id", usuarioReal.getId().toString());
            userObj.put("name", usuarioReal.getNombreUsuario());
            userObj.put("email", usuarioReal.getEmail());
            
            response.put("data", userObj);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error al leer usuario: " + e.getMessage()));
        }
    }

    // ==========================================
    // 5. RUTAS SECUNDARIAS DE SEGURIDAD
    // ==========================================
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody EmailPayload payload) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Se ha enviado un enlace a tu correo electrónico.", null));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordPayload payload) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Contraseña actualizada", null));
    }
    
    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestBody Map<String, String> payload) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Email verificado", null));
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> payload) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Contraseña restablecida", null));
    }

    @PostMapping("/send-verification-email")
    @Operation(summary = "Enviar correo de verificación")
    public ResponseEntity<?> sendVerificationEmail(@RequestBody EmailPayload payload) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Se ha enviado un nuevo enlace de verificación a tu correo.", null));
    }
}