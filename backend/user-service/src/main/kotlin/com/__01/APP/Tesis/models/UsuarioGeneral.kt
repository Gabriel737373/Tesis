package com.__01.APP.Tesis.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "usuarios_generales", uniqueConstraints = [
    UniqueConstraint(columnNames = ["email"]),
    UniqueConstraint(columnNames = ["nombreUsuario"])
])
data class UsuarioGeneral(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    val nombreUsuario: String = "",

    @Column(nullable = false, unique = true, length = 100)
    val email: String = "",

    @Column(nullable = false)
    val contraseña: String = "",

    @Column(name = "activo", nullable = false)
    val activo: Boolean = true,

    @Column(name = "creado_en", nullable = false, updatable = false)
    val creadoEn: LocalDateTime = LocalDateTime.now(),

    @Column(name = "actualizado_en", nullable = false)
    val actualizadoEn: LocalDateTime = LocalDateTime.now()
)
