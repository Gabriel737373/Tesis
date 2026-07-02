package com.__01.APP.Tesis.Servicios.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.__01.APP.Tesis.Servicios.models.ContactInfoEmbed;
import com.__01.APP.Tesis.Servicios.models.PortfolioImageEmbed;

public class ServicioResponse {

    private Long id;
    private String slug;
    private String title;
    private String marca;
    private String description;
    
    private Integer yearsExperience;
    private Integer priceMin;
    private Integer priceMax;
    private String availability;

    private String bannerUrl;
    private String logoUrl;
    private String thumbnailUrl;

    private Long locationId;
    private Long categoryId;

    private String serviceStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public List<ContactInfoEmbed> contactInfo;
    public List<PortfolioImageEmbed> portfolio;

    // --- GETTERS Y SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getYearsExperience() { return yearsExperience; }
    public void setYearsExperience(Integer yearsExperience) { this.yearsExperience = yearsExperience; }

    public Integer getPriceMin() { return priceMin; }
    public void setPriceMin(Integer priceMin) { this.priceMin = priceMin; }

    public Integer getPriceMax() { return priceMax; }
    public void setPriceMax(Integer priceMax) { this.priceMax = priceMax; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public String getBannerUrl() { return bannerUrl; }
    public void setBannerUrl(String bannerUrl) { this.bannerUrl = bannerUrl; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getServiceStatus() { return serviceStatus; }
    public void setServiceStatus(String serviceStatus) { this.serviceStatus = serviceStatus; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<ContactInfoEmbed> getContactInfo() { return contactInfo; }
    public void setContactInfo(List<ContactInfoEmbed> contactInfo) { this.contactInfo = contactInfo; }

    public List<PortfolioImageEmbed> getPortfolio() { return portfolio; }
    public void setPortfolio(List<PortfolioImageEmbed> portfolio) { this.portfolio = portfolio; }
}