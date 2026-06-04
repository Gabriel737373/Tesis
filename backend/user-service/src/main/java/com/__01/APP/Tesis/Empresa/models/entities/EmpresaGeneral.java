package com.__01.APP.Tesis.Empresa.models.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "empresas", uniqueConstraints = {
    @UniqueConstraint(columnNames = "emailEmpresa"),
    @UniqueConstraint(columnNames = "nombreEmpresa")
})
public class EmpresaGeneral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombreEmpresa;

    @Column(nullable = false, unique = true, length = 100)
    private String emailEmpresa;

    // ¡NUEVO!: Faltaba la contraseña para que pudieran iniciar sesión
    @Column(nullable = false)
    private String contrasena;

    @Column(length = 20) // Le quité el nullable=false temporalmente para que no falle al registrar solo con 3 datos
    private String telefonoEmpresa;

    @Column(length = 200) // Lo mismo aquí
    private String direccionEmpresa;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;
    
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    // Constructores
    public EmpresaGeneral() {
    }

    // ¡NUEVO CONSTRUCTOR!: Este es el que busca tu Service exactamente
    public EmpresaGeneral(String nombreEmpresa, String emailEmpresa, String contrasena) {
        this.nombreEmpresa = nombreEmpresa;
        this.emailEmpresa = emailEmpresa;
        this.contrasena = contrasena;
    }

    // (Opcional) Puedes mantener el constructor largo si lo usas en otro lado
    public EmpresaGeneral(String nombreEmpresa, String emailEmpresa, String contrasena, String telefonoEmpresa, String direccionEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
        this.emailEmpresa = emailEmpresa;
        this.contrasena = contrasena;
        this.telefonoEmpresa = telefonoEmpresa;
        this.direccionEmpresa = direccionEmpresa;
    }

    // Funciones automáticas para las fechas (¡Muy recomendado!)
    @PrePersist
    protected void onCreate() {
        creadoEn = LocalDateTime.now();
        actualizadoEn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        actualizadoEn = LocalDateTime.now();
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    
    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getEmailEmpresa() { return emailEmpresa; }
    public void setEmailEmpresa(String emailEmpresa) { this.emailEmpresa = emailEmpresa; }

    // Getters y Setters de la contraseña
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getTelefonoEmpresa() { return telefonoEmpresa; }
    public void setTelefonoEmpresa(String telefonoEmpresa) { this.telefonoEmpresa = telefonoEmpresa; }

    public String getDireccionEmpresa() { return direccionEmpresa; }
    public void setDireccionEmpresa(String direccionEmpresa) { this.direccionEmpresa = direccionEmpresa; }

    public LocalDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(LocalDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }

    public LocalDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(LocalDateTime creadoEn) { this.creadoEn = creadoEn; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaGeneral that = (EmpresaGeneral) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}