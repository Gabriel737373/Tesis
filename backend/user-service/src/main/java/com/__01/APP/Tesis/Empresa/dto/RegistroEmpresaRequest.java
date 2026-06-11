package com.__01.APP.Tesis.Empresa.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "RegistroEmpresaRequest",
    description = "Solicitud para registrar una nueva empresa"
)
public class RegistroEmpresaRequest {
    @Schema(description = "Nombre de la empresa", example = "Tech Solutions S.A.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreEmpresa;
    
    @Schema(description = "Email de contacto de la empresa", example = "contacto@techsolutions.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    
    @Schema(description = "Contraseña para el acceso", example = "contrasenaSegura123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contrasena;
    
    @Schema(description = "ID del tipo de empresa", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long tipoEmpresaId; // ¡AGREGADO!

    public RegistroEmpresaRequest() {}

    public RegistroEmpresaRequest(String nombreEmpresa, String email, String contrasena, Long tipoEmpresaId) {
        this.nombreEmpresa = nombreEmpresa;
        this.email = email;
        this.contrasena = contrasena;
        this.tipoEmpresaId = tipoEmpresaId;
    }

    // Getters y Setters
    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Long getTipoEmpresaId() { return tipoEmpresaId; }
    public void setTipoEmpresaId(Long tipoEmpresaId) { this.tipoEmpresaId = tipoEmpresaId; }
}