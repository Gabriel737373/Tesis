package com.__01.APP.Tesis.Multimedia.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PublicacionRequest", description = "Datos para crear una nueva publicación")
public class PublicacionRequest {
    
    private String titulo;
    private String archivoUrl;
    private String tipoMultimedia;
    
    @Schema(description = "ID del usuario o empresa que publica")
    private Long autorId;
    
    @Schema(description = "Debe ser 'USUARIO' o 'EMPRESA'")
    private String tipoAutor;

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getArchivoUrl() { return archivoUrl; }
    public void setArchivoUrl(String archivoUrl) { this.archivoUrl = archivoUrl; }

    public String getTipoMultimedia() { return tipoMultimedia; }
    public void setTipoMultimedia(String tipoMultimedia) { this.tipoMultimedia = tipoMultimedia; }

    public Long getAutorId() { return autorId; }
    public void setAutorId(Long autorId) { this.autorId = autorId; }

    public String getTipoAutor() { return tipoAutor; }
    public void setTipoAutor(String tipoAutor) { this.tipoAutor = tipoAutor; }
}