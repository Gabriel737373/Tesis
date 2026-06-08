package com.__01.APP.Tesis.Empresa.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "LoginRequest",
    description = "Solicitud de login para empresas con credenciales"
)
public class LoginRequest {
    @Schema(
        description = "Nombre de usuario de la empresa",
        example = "empresa_xyz",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String nombreUsuario;
    
    @Schema(
        description = "Contraseña de la empresa",
        example = "password123",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String contrasena;

    public LoginRequest() {}

    public LoginRequest(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
