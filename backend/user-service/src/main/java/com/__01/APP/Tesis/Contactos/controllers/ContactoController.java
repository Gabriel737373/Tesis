package com.__01.APP.Tesis.Contactos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.__01.APP.Tesis.Usuario.dto.ApiResponse;
import com.__01.APP.Tesis.Contactos.dto.ContactoResumen;
import com.__01.APP.Tesis.Contactos.dto.ContactoDetalle;
import com.__01.APP.Tesis.Contactos.services.ContactoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/contactos")
@CrossOrigin(origins = "*") // Clave para la conexión Front-Back
@Tag(name = "Contactos", description = "Bandeja de entrada y mensajería")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @GetMapping("/inbox")
    @Operation(summary = "Ver bandeja de entrada", description = "Obtiene los mensajes dirigidos al usuario actual")
    public ResponseEntity<?> getInbox() {
        try {
            // TODO: En un futuro extraer esto del Token JWT.
            // Por ahora simulamos que el usuario logueado es el ID "1" (igual que en Perfil y Auth)
            String myUserId = "1";
            
            List<ContactoResumen> inbox = contactoService.obtenerInbox(myUserId);
            return ResponseEntity.ok(new ApiResponse<>(true, "Bandeja obtenida", inbox));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Error al cargar mensajes"));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Leer mensaje", description = "Obtiene el detalle completo de un mensaje")
    public ResponseEntity<?> getMensaje(@PathVariable Long id) {
        try {
            ContactoDetalle detalle = contactoService.obtenerDetalle(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Mensaje leído", detalle));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(new ApiResponse<>(false, "Mensaje no encontrado"));
        }
    }
}