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

    // --- CAMPO ACTUALIZADO: Ahora es String y pide el nombre ---
    @Schema(description = "Nombre o categoría del tipo de usuario", example = "Estudiante", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tipoUsuarioNombre;

    public RegistroRequest() {}

    public RegistroRequest(String nombreUsuario, String email, String contrasena, String tipoUsuarioNombre) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasena = contrasena;
        this.tipoUsuarioNombre = tipoUsuarioNombre;
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getTipoUsuarioNombre() { return tipoUsuarioNombre; }
    public void setTipoUsuarioNombre(String tipoUsuarioNombre) { this.tipoUsuarioNombre = tipoUsuarioNombre; }
}