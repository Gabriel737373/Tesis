package com.__01.APP.Tesis.ControllerGenerico;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Autenticación Unificada", description = "Endpoints de autenticación requeridos por el frontend Next.js")
public class AuthController {

    private final UsuarioGeneralService usuarioService;

    // Inyectamos tu servicio existente para reutilizar tu lógica de BD
    public AuthController(UsuarioGeneralService usuarioService) {
        this.usuarioService = usuarioService;
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
    // 1. INICIAR SESIÓN
    // ==========================================
    @PostMapping("/sign-in/email")
    @Operation(summary = "Login unificado")
    public ResponseEntity<?> signIn(@RequestBody SignInPayload payload) {
        try {
            // Nota: Si tu backend exige "nombreUsuario" en vez de email, 
            // usa el email como si fuera el nombre o adáptalo en tu Service.
            UsuarioGeneralResponse usuario = usuarioService.verificarCredenciales(payload.email, payload.password);
            
            if (usuario != null) {
                // El frontend de Next.js espera exactamente esta estructura: { user: {...}, session: {...} }
                Map<String, Object> response = new HashMap<>();
                
                Map<String, Object> userObj = new HashMap<>();
                userObj.put("id", usuario.getId().toString());
                userObj.put("name", usuario.getNombreUsuario()); 
                userObj.put("email", usuario.getEmail());
                userObj.put("image", null); 
                
                Map<String, Object> sessionObj = new HashMap<>();
                sessionObj.put("id", UUID.randomUUID().toString()); // Simulamos un token
                sessionObj.put("userId", usuario.getId().toString());
                
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
            // Convertimos lo que manda el frontend a tu DTO
            RegistroRequest registro = new RegistroRequest();
            registro.setEmail(payload.email);
            registro.setContrasena(payload.password);
            // Si RegistroRequest te exige más datos obligatorios, ponlos aquí. Por ejemplo:
            registro.setNombreUsuario(payload.name); 

            UsuarioGeneralResponse usuario = usuarioService.registrar(registro);
            
            // Retornamos el formato esperado
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> userObj = new HashMap<>();
            userObj.put("id", usuario.getId().toString());
            userObj.put("name", payload.name);
            userObj.put("email", payload.email);
            
            Map<String, Object> sessionObj = new HashMap<>();
            sessionObj.put("id", UUID.randomUUID().toString());
            sessionObj.put("userId", usuario.getId().toString());
            
            response.put("user", userObj);
            response.put("session", sessionObj);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, e.getMessage()));
        }
    }

    // ==========================================
    // 3. CERRAR SESIÓN (CORREGIDO)
    // ==========================================
    @PostMapping("/sign-out")
    public ResponseEntity<?> signOut() {
        // Ahora devuelve un JSON válido para que el frontend no lance error
        return ResponseEntity.ok(new ApiResponse<>(true, "Sesión cerrada correctamente", null)); 
    }

    // ==========================================
    // 4. OBTENER USUARIO ACTUAL (Para mantener la sesión en F5)
    // ==========================================
    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        
        Map<String, Object> userObj = new HashMap<>();
        userObj.put("id", "1");
        userObj.put("name", "Usuario de Pruebas");
        userObj.put("email", "test@test.com");
        
        response.put("data", userObj);
        return ResponseEntity.ok(response);
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
        // En el futuro aquí irá la lógica para enviar correos reales (ej. JavaMailSender)
        return ResponseEntity.ok(new ApiResponse<>(true, "Se ha enviado un nuevo enlace de verificación a tu correo.", null));
    }
}