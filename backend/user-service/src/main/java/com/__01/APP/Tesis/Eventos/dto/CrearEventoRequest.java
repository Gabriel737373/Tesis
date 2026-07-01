package com.__01.APP.Tesis.Eventos.dto;

import java.time.LocalDateTime;

public class CrearEventoRequest {
    public String slug;
    public String title;
    public String description;
    public LocalDateTime startAt; // Fecha y hora en que ocurrirá el evento
    public String thumbnailUrl;
    public Long locationId;
    public Long categoryId;
    public String eventStatus;
}