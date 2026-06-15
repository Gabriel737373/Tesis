package com.__01.APP.Tesis.Empresa.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "EmpresaGeneralResponse",
    description = "Respuesta con datos de la empresa"
)
public class EmpresaGeneralResponse {
    
    @Schema(description = "ID único de la empresa", example = "1")
    private Long id; // Cambiado de idEmpresa a id
    
    @Schema(description = "Nombre de la empresa", example = "Tech Solutions S.A.")
    private String nombreEmpresa;
    
    @Schema(description = "Email de contacto de la empresa", example = "contacto@techsolutions.com")
    private String emailEmpresa; // Cambiado de emailContacto a emailEmpresa

    @Schema(description = "Número de teléfono de la empresa", example = "+1234567890")
    private String telefonoEmpresa; // Cambiado de telefonoContacto a telefonoEmpresa

    @Schema(description = "Dirección de la empresa", example = "Calle Principal 123, Ciudad")
    private String direccionEmpresa;

    public EmpresaGeneralResponse() {}

    public EmpresaGeneralResponse(Long id, String nombreEmpresa, String emailEmpresa,
                                  String telefonoEmpresa, String direccionEmpresa) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.emailEmpresa = emailEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.direccionEmpresa = direccionEmpresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }
}