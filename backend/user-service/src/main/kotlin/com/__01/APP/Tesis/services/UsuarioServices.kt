package com.__01.APP.Tesis.services

import com.__01.APP.Tesis.models.UsuarioGeneral
import com.__01.APP.Tesis.repositories.UsuarioGeneralRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UsuarioGeneralService(
    private val usuarioGeneralRepository: UsuarioGeneralRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun registrar(nombreUsuario: String, email: String, contraseña: String): UsuarioGeneral {
        if (usuarioGeneralRepository.existsByNombreUsuario(nombreUsuario)) {
            throw IllegalArgumentException("El nombre de usuario ya existe")
        }
        if (usuarioGeneralRepository.existsByEmail(email)) {
            throw IllegalArgumentException("El email ya está registrado")
        }
        if (contraseña.length < 6) {
            throw IllegalArgumentException("La contraseña debe tener al menos 6 caracteres")
        }

        val usuarioEncriptado = UsuarioGeneral(
            nombreUsuario = nombreUsuario,
            email = email,
            contraseña = passwordEncoder.encode(contraseña),
            activo = true,
            creadoEn = LocalDateTime.now(),
            actualizadoEn = LocalDateTime.now()
        )
        return usuarioGeneralRepository.save(usuarioEncriptado)
    }

    fun obtenerPorId(id: Long): UsuarioGeneral? =
        usuarioGeneralRepository.findById(id).orElse(null)

    fun obtenerPorNombreUsuario(nombreUsuario: String): UsuarioGeneral? =
        usuarioGeneralRepository.findByNombreUsuario(nombreUsuario)

    fun obtenerPorEmail(email: String): UsuarioGeneral? =
        usuarioGeneralRepository.findByEmail(email)

    fun listar(): List<UsuarioGeneral> =
        usuarioGeneralRepository.findAll()

    fun eliminar(id: Long): Boolean {
        if (!usuarioGeneralRepository.existsById(id)) {
            return false
        }
        usuarioGeneralRepository.deleteById(id)
        return true
    }

    fun verificarCredenciales(nombreUsuario: String, contraseña: String): UsuarioGeneral? {
        val usuario = usuarioGeneralRepository.findByNombreUsuario(nombreUsuario)
        return if (usuario != null && passwordEncoder.matches(contraseña, usuario.contraseña)) {
            usuario
        } else null
    }
}

