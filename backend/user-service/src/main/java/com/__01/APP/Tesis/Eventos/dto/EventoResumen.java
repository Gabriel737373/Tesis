package com.__01.APP.Tesis.Eventos.dto;

public class EventoResumen {
    public String id;
    public String profileId;
    public String slug;
    public String title;
    public String description;
    public String startAt;
    
    public LocationDTO location;
    public CategoryDTO category;
    
    public String thumbnailUrl;
    public ProfileRefDTO profile;
    public String eventStatus;
    public String createdAt;
    public String updatedAt;

    // --- Subclases para igualar la interfaz de Next.js ---
    public static class LocationDTO {
        public String id; public String name;
        public LocationDTO(String id, String name) { this.id = id; this.name = name; }
    }
    public static class CategoryDTO {
        public String id; public String name;
        public CategoryDTO(String id, String name) { this.id = id; this.name = name; }
    }
    public static class ProfileRefDTO {
        public String id; public String name; public String slug;
        public ProfileRefDTO(String id, String name, String slug) { this.id = id; this.name = name; this.slug = slug; }
    }
}