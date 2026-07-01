package com.__01.APP.Tesis.Perfiles.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class SocialLinkEmbed {
    
    private String platform;
    private String url;
    private Integer orden;

    // (Agrega los Getters y Setters)
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }
}