package com.__01.APP.Tesis.Usuario.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    private String id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Column(nullable = false)
    private String autorId;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

    // Constructores
    public Post() {
        this.creadoEn = LocalDateTime.now();
        this.actualizadoEn = LocalDateTime.now();
    }

    public Post(String id, String titulo, String contenido, String autorId) {
        this();
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.autorId = autorId;
    }

    // Métodos
    public void actualizarTitulo(String nuevoTitulo) {
        if (nuevoTitulo != null && !nuevoTitulo.isBlank()) {
            this.titulo = nuevoTitulo.trim();
            marcarActualizado();
        }
    }

    public void actualizarContenido(String nuevoContenido) {
        if (nuevoContenido != null && !nuevoContenido.isBlank()) {
            this.contenido = nuevoContenido.trim();
            marcarActualizado();
        }
    }

    private void marcarActualizado() {
        this.actualizadoEn = LocalDateTime.now();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getAutorId() {
        return autorId;
    }

    public void setAutorId(String autorId) {
        this.autorId = autorId;
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

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", autorId='" + autorId + '\'' +
                ", creadoEn=" + creadoEn +
                ", actualizadoEn=" + actualizadoEn +
                '}';
    }
}
