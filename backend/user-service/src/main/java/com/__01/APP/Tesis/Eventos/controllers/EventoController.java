package com.__01.APP.Tesis.Eventos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.__01.APP.Tesis.Eventos.dto.CrearEventoRequest;
import com.__01.APP.Tesis.Eventos.dto.EventoResumen;
import com.__01.APP.Tesis.Eventos.services.EventoService;
import com.__01.APP.Tesis.Usuario.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*") // Clave para la conexión Front-Back
@Tag(name = "Eventos", description = "Endpoints para crear y listar eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    @Operation(summary = "Crear un evento")
    public ResponseEntity<?> crearEvento(@RequestBody CrearEventoRequest request) {
        try {
            EventoResumen response = eventoService.crearEvento(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Evento creado", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error: " + e.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Listar todos los eventos")
    public ResponseEntity<?> listarEventos() {
        try {
            List<EventoResumen> eventos = eventoService.obtenerTodos();
            return ResponseEntity.ok(new ApiResponse<>(true, "Eventos listados", eventos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalles de un evento por ID")
    public ResponseEntity<?> obtenerEvento(@PathVariable Long id) {
        try {
            EventoResumen response = eventoService.obtenerPorId(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Evento encontrado", response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage()));
        }
    }
}