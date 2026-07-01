package com.__01.APP.Tesis.Contactos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.__01.APP.Tesis.Contactos.dto.ContactoDetalle;
import com.__01.APP.Tesis.Contactos.dto.ContactoResumen;
import com.__01.APP.Tesis.Contactos.dto.CreateContactoRequest;
import com.__01.APP.Tesis.Contactos.services.ContactoService;
import com.__01.APP.Tesis.Usuario.dto.ApiResponse;

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

    @PostMapping
    @Operation(summary = "Enviar un mensaje", description = "Crea un nuevo mensaje dirigido a un usuario")
    public ResponseEntity<?> enviarMensaje(@RequestBody CreateContactoRequest request) {
        try {
            ContactoDetalle response = contactoService.enviarMensaje(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Mensaje enviado con éxito", response));
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error al enviar el mensaje: " + e.getMessage()));
        }
    }
}