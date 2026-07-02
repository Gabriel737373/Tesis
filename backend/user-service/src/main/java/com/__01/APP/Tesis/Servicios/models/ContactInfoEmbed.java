package com.__01.APP.Tesis.Servicios.models;
import jakarta.persistence.Embeddable;

@Embeddable
public class ContactInfoEmbed {
    public String type; // "whatsapp", "instagram", "email"
    public String value;
}