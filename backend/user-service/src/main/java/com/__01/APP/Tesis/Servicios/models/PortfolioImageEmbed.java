package com.__01.APP.Tesis.Servicios.models;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class PortfolioImageEmbed {
    public String url;
    public String title;
    @Column(length = 1000)
    public String description;
}