package com.__01.APP.Tesis.Perfiles.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "perfiles")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aquí enlazamos abstractamente con el usuario o empresa (usando su ID como String)
    @Column(nullable = false, unique = true)
    private String userId;

    private String name;
    private String bannerUrl;
    private String logoUrl;
    private String industry;
    
    @Column(length = 2000)
    private String description;
    
    private String location;
    private String founded;
    private String employees;
    private String website;
    private String whatsapp;

    // Magia de Spring Boot: Guarda la lista de Strings en una tabla aparte automáticamente
    @ElementCollection
    @CollectionTable(name = "perfil_tags", joinColumns = @JoinColumn(name = "perfil_id"))
    @Column(name = "tag")
    private List<String> tags;

    // Lo mismo para las redes sociales
    @ElementCollection
    @CollectionTable(name = "perfil_social_links", joinColumns = @JoinColumn(name = "perfil_id"))
    private List<SocialLinkEmbed> socialLinks;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // (Agrega los Getters y Setters de todo)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBannerUrl() { return bannerUrl; }
    public void setBannerUrl(String bannerUrl) { this.bannerUrl = bannerUrl; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getFounded() { return founded; }
    public void setFounded(String founded) { this.founded = founded; }

    public String getEmployees() { return employees; }
    public void setEmployees(String employees) { this.employees = employees; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getWhatsapp() { return whatsapp; }
    public void setWhatsapp(String whatsapp) { this.whatsapp = whatsapp; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public List<SocialLinkEmbed> getSocialLinks() { return socialLinks; }
    public void setSocialLinks(List<SocialLinkEmbed> socialLinks) { this.socialLinks = socialLinks; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}