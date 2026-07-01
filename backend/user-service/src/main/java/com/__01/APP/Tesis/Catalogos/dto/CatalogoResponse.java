package com.__01.APP.Tesis.Catalogos.dto;

import java.util.List;

public class CatalogoResponse {

    public static class CategoriaDTO {
        public String id; public String name; public String type;
        public CategoriaDTO(String id, String name, String type) { this.id=id; this.name=name; this.type=type; }
    }

    public static class RegionDTO {
        public String id; public String name; public String slug;
        public List<LocationMiniDTO> locations;
        public RegionDTO(String id, String name, String slug, List<LocationMiniDTO> locs) {
            this.id=id; this.name=name; this.slug=slug; this.locations=locs;
        }
    }

    public static class LocationMiniDTO {
        public String id; public String name;
        public LocationMiniDTO(String id, String name) { this.id=id; this.name=name; }
    }

    public static class UbicacionDTO {
        public String id; public String name; public RegionMiniDTO region;
        public UbicacionDTO(String id, String name, RegionMiniDTO reg) { this.id=id; this.name=name; this.region=reg; }
    }

    public static class RegionMiniDTO {
        public String id; public String name; public String slug;
        public RegionMiniDTO(String id, String name, String slug) { this.id=id; this.name=name; this.slug=slug; }
    }
}