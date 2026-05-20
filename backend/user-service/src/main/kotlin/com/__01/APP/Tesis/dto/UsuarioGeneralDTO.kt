package com.__01.APP.Tesis.dto

import java.time.LocalDateTime

data class RegistroRequest(
    val nombreUsuario: String,
    val email: String,
    val contraseña: String
)

data class LoginRequest(
    val nombreUsuario: String,
    val contraseña: String
)

data class UsuarioGeneralResponse(
    val id: Long,
    val nombreUsuario: String,
    val email: String,
    val activo: Boolean,
    val creadoEn: LocalDateTime,
    val actualizadoEn: LocalDateTime
)

data class ApiResponse<T>(
    val success: Boolean,
    val mensaje: String,
    val datos: T? = null
)
