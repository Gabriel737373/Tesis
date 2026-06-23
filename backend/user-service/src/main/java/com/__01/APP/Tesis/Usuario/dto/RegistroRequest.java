package com.__01.APP.Tesis.Usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "RegistroRequest",
    description = "Solicitud para registrar un nuevo usuario"
)
public class RegistroRequest {
    @Schema(description = "Nombre de usuario único", example = "maria_gonzalez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreUsuario;
    
    @Schema(description = "Email del usuario", example = "maria@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    
    @Schema(description = "Contraseña del usuario (mínimo 6 caracteres)", example = "SecurePassword123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contrasena;

    // --- NUEVO CAMPO ---
    @Schema(description = "ID del tipo de usuario", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long tipoUsuarioId;

    public RegistroRequest() {}

    public RegistroRequest(String nombreUsuario, String email, String contrasena, Long tipoUsuarioId) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasena = contrasena;
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    // Nuevos Getter y Setter
    public Long getTipoUsuarioId() { return tipoUsuarioId; }
    public void setTipoUsuarioId(Long tipoUsuarioId) { this.tipoUsuarioId = tipoUsuarioId; }
}