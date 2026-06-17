package com.__01.APP.Tesis.Multimedia.Models.Entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import com.__01.APP.Tesis.Usuario.models.entities.UsuarioGeneral;
import com.__01.APP.Tesis.Empresa.models.entities.EmpresaGeneral;

@Entity
@Table(name = "publicaciones")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false)
    private String archivoUrl;

    @Column(nullable = false, length = 20)
    private String tipoMultimedia; // "imagen", "video", etc.

    @Column(name = "fecha_publicacion", nullable = false, updatable = false)
    private LocalDateTime fechaPublicacion;

    // --- RELACIONES AÑADIDAS ---
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = true)
    private UsuarioGeneral usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = true)
    private EmpresaGeneral empresa;

    public Publicacion() {
    }

    @PrePersist
    protected void onCreate() {
        this.fechaPublicacion = LocalDateTime.now();
    }

    // Getters y Setters
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

    public UsuarioGeneral getUsuario() { return usuario; }
    public void setUsuario(UsuarioGeneral usuario) { this.usuario = usuario; }

    public EmpresaGeneral getEmpresa() { return empresa; }
    public void setEmpresa(EmpresaGeneral empresa) { this.empresa = empresa; }

    // equals y hashCode basados en ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publicacion that = (Publicacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}