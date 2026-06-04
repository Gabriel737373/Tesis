package com.__01.APP.Tesis.Empresa.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "EmpresaGeneralResponse",
    description = "Respuesta con datos de la empresa"
)
public class EmpresaGeneralResponse {
    @Schema(description = "ID único de la empresa", example = "1")
    private Long idEmpresa;
    
    @Schema(description = "Nombre de la empresa", example = "Tech Solutions S.A.")
    private String nombreEmpresa;
    
    @Schema(description = "Email de contacto de la empresa", example = "contacto@techsolutions.com")
    private String emailContacto;

    @Schema(description = "Número de teléfono de la empresa", example = "+1234567890")
    private String telefonoContacto;

    @Schema(description = "Dirección de la empresa", example = "Calle Principal 123, Ciudad")
    private String direccionEmpresa;

    public EmpresaGeneralResponse() {}

    public EmpresaGeneralResponse(Long idEmpresa, String nombreEmpresa, String emailContacto,
                                  String telefonoContacto, String direccionEmpresa) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.emailContacto = emailContacto;
        this.telefonoContacto = telefonoContacto;
        this.direccionEmpresa = direccionEmpresa;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }
}
