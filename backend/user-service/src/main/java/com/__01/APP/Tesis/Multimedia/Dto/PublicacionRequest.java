package com.__01.APP.Tesis.Multimedia.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear una publicación multimedia")
public class PublicacionRequest {
    
    @Schema(description = "Título o descripción de la publicación", example = "Mi nuevo proyecto", requiredMode = Schema.RequiredMode.REQUIRED)
    private String titulo;

    @Schema(description = "Nombre del usuario (Completar este O nombreEmpresa)", example = "maria_gonzalez", nullable = true)
    private String nombreUsuario;

    @Schema(description = "Nombre de la empresa (Completar este O nombreUsuario)", example = "TechCorp", nullable = true)
    private String nombreEmpresa;

    // --- ¡AQUÍ ESTÁ EL CAMPO QUE FALTABA! ---
    @Schema(description = "Tipo de archivo que se está subiendo (ej: IMAGEN, VIDEO)", example = "IMAGEN", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tipoMultimedia;

    @Schema(hidden = true) 
    private String archivoUrl;

    public PublicacionRequest() {}

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getTipoMultimedia() { return tipoMultimedia; }
    public void setTipoMultimedia(String tipoMultimedia) { this.tipoMultimedia = tipoMultimedia; }

    public String getArchivoUrl() { return archivoUrl; }
    public void setArchivoUrl(String archivoUrl) { this.archivoUrl = archivoUrl; }
}