package com.__01.APP.Tesis.Empresa.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "RegistroEmpresaRequest",
    description = "Solicitud para registrar una nueva empresa"
)
public class RegistroEmpresaRequest {
    @Schema(
        description = "Nombre de la empresa",
        example = "Tech Solutions S.A.",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String nombreEmpresa;
    
    @Schema(
        description = "Email de contacto de la empresa",
        example = "contacto@techsolutions.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;
    
    @Schema(
        description = "Contraseña para el acceso a la cuenta de la empresa",
        example = "contrasenaSegura123",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String contrasena;

    public RegistroEmpresaRequest() {}

    public RegistroEmpresaRequest(String nombreEmpresa, String email, String contrasena) {
        this.nombreEmpresa = nombreEmpresa;
        this.email = email;
        this.contrasena = contrasena;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}