package com.__01.APP.Tesis.controllers

import com.__01.APP.Tesis.dto.ApiResponse
import com.__01.APP.Tesis.dto.LoginRequest
import com.__01.APP.Tesis.dto.RegistroRequest
import com.__01.APP.Tesis.dto.UsuarioGeneralResponse
import com.__01.APP.Tesis.services.UsuarioGeneralService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = ["*"])
class UsuarioGeneralController(
    private val usuarioGeneralService: UsuarioGeneralService
) {

    @PostMapping("/registro")
    fun registro(@RequestBody request: RegistroRequest): ResponseEntity<ApiResponse<UsuarioGeneralResponse>> {
        return try {
            val usuario = usuarioGeneralService.registrar(
                request.nombreUsuario,
                request.email,
                request.contraseña
            )
            val response = usuario.toResponse()
            ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse(true, "Usuario registrado exitosamente", response))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse(false, e.message ?: "Error en el registro"))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse(false, "Error interno del servidor"))
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<ApiResponse<UsuarioGeneralResponse>> {
        return try {
            val usuario = usuarioGeneralService.verificarCredenciales(
                request.nombreUsuario,
                request.contraseña
            )
            if (usuario != null) {
                val response = usuario.toResponse()
                ResponseEntity.ok(ApiResponse(true, "Login exitoso", response))
            } else {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse(false, "Nombre de usuario o contraseña incorrectos"))
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse(false, "Error interno del servidor"))
        }
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<ApiResponse<UsuarioGeneralResponse>> {
        return try {
            val usuario = usuarioGeneralService.obtenerPorId(id)
            if (usuario != null) {
                val response = usuario.toResponse()
                ResponseEntity.ok(ApiResponse(true, "Usuario obtenido", response))
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse(false, "Usuario no encontrado"))
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse(false, "Error interno del servidor"))
        }
    }

    @GetMapping("/email/{email}")
    fun obtenerPorEmail(@PathVariable email: String): ResponseEntity<ApiResponse<UsuarioGeneralResponse>> {
        return try {
            val usuario = usuarioGeneralService.obtenerPorEmail(email)
            if (usuario != null) {
                val response = usuario.toResponse()
                ResponseEntity.ok(ApiResponse(true, "Usuario obtenido", response))
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse(false, "Usuario no encontrado"))
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse(false, "Error interno del servidor"))
        }
    }

    @GetMapping
    fun listarTodos(): ResponseEntity<ApiResponse<List<UsuarioGeneralResponse>>> {
        return try {
            val usuarios = usuarioGeneralService.listar()
            val response = usuarios.map { it.toResponse() }
            ResponseEntity.ok(ApiResponse(true, "Usuarios obtenidos", response))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse(false, "Error interno del servidor"))
        }
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<ApiResponse<Nothing>> {
        return try {
            val eliminado = usuarioGeneralService.eliminar(id)
            if (eliminado) {
                ResponseEntity.ok(ApiResponse(true, "Usuario eliminado exitosamente"))
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse(false, "Usuario no encontrado"))
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse(false, "Error interno del servidor"))
        }
    }
}

private fun com.__01.APP.Tesis.models.UsuarioGeneral.toResponse() = UsuarioGeneralResponse(
    id = id,
    nombreUsuario = nombreUsuario,
    email = email,
    activo = activo,
    creadoEn = creadoEn,
    actualizadoEn = actualizadoEn
)
