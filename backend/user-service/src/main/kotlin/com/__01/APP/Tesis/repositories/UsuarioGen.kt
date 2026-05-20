package com.__01.APP.Tesis.repositories

import com.__01.APP.Tesis.models.UsuarioGeneral
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioGeneralRepository : JpaRepository<UsuarioGeneral, Long> {
    fun findByNombreUsuario(nombreUsuario: String): UsuarioGeneral?
    fun existsByNombreUsuario(nombreUsuario: String): Boolean
    fun findByEmail(email: String): UsuarioGeneral?
    fun existsByEmail(email: String): Boolean
}

