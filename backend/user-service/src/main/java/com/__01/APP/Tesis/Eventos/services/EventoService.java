package com.__01.APP.Tesis.Eventos.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.__01.APP.Tesis.Eventos.dto.CrearEventoRequest;
import com.__01.APP.Tesis.Eventos.dto.EventoResumen;
import com.__01.APP.Tesis.Eventos.models.Evento;
import com.__01.APP.Tesis.Eventos.repositories.EventoRepository;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public EventoResumen crearEvento(CrearEventoRequest request) {
        Evento evento = new Evento();
        evento.setSlug(request.slug);
        evento.setTitle(request.title);
        evento.setDescription(request.description);
        evento.setStartAt(request.startAt);
        evento.setThumbnailUrl(request.thumbnailUrl);
        evento.setLocationId(request.locationId);
        evento.setCategoryId(request.categoryId);
        evento.setEventStatus(request.eventStatus);

        Evento guardado = eventoRepository.save(evento);
        return convertirAResponse(guardado);
    }

    public List<EventoResumen> obtenerTodos() {
        return eventoRepository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public EventoResumen obtenerPorId(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
        return convertirAResponse(evento);
    }

    private EventoResumen convertirAResponse(Evento evento) {
        EventoResumen response = new EventoResumen();
        response.id = evento.getId().toString();
        response.profileId = "1"; // Simulado por ahora
        response.slug = evento.getSlug();
        response.title = evento.getTitle();
        response.description = evento.getDescription();
        if (evento.getStartAt() != null) response.startAt = evento.getStartAt().toString();
        
        response.thumbnailUrl = evento.getThumbnailUrl();
        response.eventStatus = evento.getEventStatus();
        if (evento.getCreatedAt() != null) response.createdAt = evento.getCreatedAt().toString();
        if (evento.getUpdatedAt() != null) response.updatedAt = evento.getUpdatedAt().toString();

        // Sub-objetos mockeados para que el FrontEnd no tire error al leerlos
        // (En un futuro los traerás haciendo JOIN con la base de datos de catálogos y perfiles)
        response.location = new EventoResumen.LocationDTO(
            evento.getLocationId() != null ? evento.getLocationId().toString() : "1", "Ubicación"
        );
        response.category = new EventoResumen.CategoryDTO(
            evento.getCategoryId() != null ? evento.getCategoryId().toString() : "1", "Categoría"
        );
        response.profile = new EventoResumen.ProfileRefDTO("1", "Organizador", "organizador");

        return response;
    }
}