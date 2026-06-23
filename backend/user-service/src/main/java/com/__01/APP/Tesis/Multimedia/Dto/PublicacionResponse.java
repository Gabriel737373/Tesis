package com.__01.APP.Tesis.Multimedia.Dto;

import java.time.LocalDateTime;

public class PublicacionResponse {
    
    private Long id;
    private String titulo;
    private String archivoUrl;
    private String tipoMultimedia;
    private LocalDateTime fechaPublicacion;
    private String nombreAutor;
    private String tipoAutor;

    // Constructor vacío (siempre es buena práctica tenerlo)
    public PublicacionResponse() {}

    // Constructor completo
    public PublicacionResponse(Long id, String titulo, String archivoUrl, String tipoMultimedia, 
                               LocalDateTime fechaPublicacion, String nombreAutor, String tipoAutor) {
        this.id = id;
        this.titulo = titulo;
        this.archivoUrl = archivoUrl;
        this.tipoMultimedia = tipoMultimedia;
        this.fechaPublicacion = fechaPublicacion;
        this.nombreAutor = nombreAutor;
        this.tipoAutor = tipoAutor;
    }

    // Getters y Setters completos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getArchivoUrl() { return archivoUrl; }
    public void setArchivoUrl(String archivoUrl) { this.archivoUrl = archivoUrl; }

    public String getTipoMultimedia() { return tipoMultimedia; }
    public void setTipoMultimedia(String tipoMultimedia) { this.tipoMultimedia = tipoMultimedia; }

    public LocalDateTime getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDateTime fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public String getNombreAutor() { return nombreAutor; }
    public void setNombreAutor(String nombreAutor) { this.nombreAutor = nombreAutor; }

    public String getTipoAutor() { return tipoAutor; }
    public void setTipoAutor(String tipoAutor) { this.tipoAutor = tipoAutor; }
}