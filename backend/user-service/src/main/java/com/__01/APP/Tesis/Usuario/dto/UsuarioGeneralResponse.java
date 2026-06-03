package com.__01.APP.Tesis.Usuario.dto;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "UsuarioGeneralResponse",
    description = "Respuesta con datos del usuario"
)
public class UsuarioGeneralResponse {
    @Schema(description = "ID único del usuario", example = "1")
    private Long id;
    
    @Schema(description = "Nombre de usuario", example = "juan_perez")
    private String nombreUsuario;
    
    @Schema(description = "Email del usuario", example = "juan@example.com")
    private String email;
    
    @Schema(description = "Estado del usuario (activo/inactivo)", example = "true")
    private Boolean activo;
    
    @Schema(description = "Fecha de creación del usuario", example = "2026-01-15T10:30:00")
    private LocalDateTime creadoEn;
    
    @Schema(description = "Fecha de última actualización", example = "2026-01-15T10:30:00")
    private LocalDateTime actualizadoEn;

    public UsuarioGeneralResponse() {}

    public UsuarioGeneralResponse(Long id, String nombreUsuario, String email, Boolean activo,
                                  LocalDateTime creadoEn, LocalDateTime actualizadoEn) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.activo = activo;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}
