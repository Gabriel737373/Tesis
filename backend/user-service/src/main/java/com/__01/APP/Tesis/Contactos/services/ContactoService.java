package com.__01.APP.Tesis.Contactos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.__01.APP.Tesis.Contactos.dto.ContactoDetalle;
import com.__01.APP.Tesis.Contactos.dto.ContactoResumen;
import com.__01.APP.Tesis.Contactos.dto.CreateContactoRequest;
import com.__01.APP.Tesis.Contactos.models.Contacto;
import com.__01.APP.Tesis.Contactos.repositories.ContactoRepository;

@Service
public class ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoService(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    // Obtiene la bandeja de entrada (resumen)
    public List<ContactoResumen> obtenerInbox(String receiverId) {
        return contactoRepository.findByReceiverIdOrderByCreatedAtDesc(receiverId)
                .stream().map(this::convertirAResumen)
                .collect(Collectors.toList());
    }

    // Obtiene el mensaje completo y LO MARCA COMO LEÍDO
    public ContactoDetalle obtenerDetalle(Long id) {
        Contacto contacto = contactoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mensaje no encontrado"));
        
        // Si no estaba leído, lo marcamos como leído
        if (!contacto.isRead()) {
            contacto.setRead(true);
            contactoRepository.save(contacto);
        }
        
        return convertirADetalle(contacto);
    }

    // --- MÉTODOS DE CONVERSIÓN ---
    private ContactoResumen convertirAResumen(Contacto contacto) {
        ContactoResumen resumen = new ContactoResumen();
        resumen.id = contacto.getId().toString();
        resumen.name = contacto.getName();
        resumen.email = contacto.getEmail();
        resumen.phone = contacto.getPhone();
        resumen.company = contacto.getCompany();
        
        // Enviar solo un extracto del mensaje para la vista previa
        String extracto = contacto.getMessage();
        if (extracto != null && extracto.length() > 50) {
            extracto = extracto.substring(0, 47) + "...";
        }
        resumen.lastMessage = extracto;
        
        resumen.lastMessageAt = contacto.getCreatedAt() != null ? contacto.getCreatedAt().toString() : null;
        resumen.unread = !contacto.isRead(); // El front pide "unread", que es lo opuesto a "isRead"
        
        return resumen;
    }

    private ContactoDetalle convertirADetalle(Contacto contacto) {
        ContactoDetalle detalle = new ContactoDetalle();
        detalle.id = contacto.getId().toString();
        detalle.name = contacto.getName();
        detalle.email = contacto.getEmail();
        detalle.phone = contacto.getPhone();
        detalle.company = contacto.getCompany();
        detalle.message = contacto.getMessage();
        detalle.subject = contacto.getSubject();
        detalle.read = contacto.isRead();
        detalle.createdAt = contacto.getCreatedAt() != null ? contacto.getCreatedAt().toString() : null;
        detalle.updatedAt = contacto.getUpdatedAt() != null ? contacto.getUpdatedAt().toString() : null;
        
        return detalle;
    }

    public ContactoDetalle enviarMensaje(CreateContactoRequest request) {
    Contacto contacto = new Contacto();
    contacto.setReceiverId(request.receiverId);
    contacto.setName(request.name);
    contacto.setEmail(request.email);
    contacto.setPhone(request.phone);
    contacto.setCompany(request.company);
    contacto.setSubject(request.subject);
    contacto.setMessage(request.message);
    contacto.setRead(false); // Por defecto entra como "No leído"

    Contacto guardado = contactoRepository.save(contacto);
    return convertirADetalle(guardado);
    }
}