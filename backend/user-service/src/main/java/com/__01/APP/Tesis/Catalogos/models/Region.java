package com.__01.APP.Tesis.Catalogos.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "regiones")
public class Region {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String slug;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ubicacion> locations;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public List<Ubicacion> getLocations() { return locations; }
    public void setLocations(List<Ubicacion> locations) { this.locations = locations; }
}