package com.__01.APP.Tesis.Servicios.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.__01.APP.Tesis.Servicios.dto.CreateServicioRequest;
import com.__01.APP.Tesis.Servicios.dto.ServicioResponse;
import com.__01.APP.Tesis.Servicios.models.Servicio;
import com.__01.APP.Tesis.Servicios.repositories.ServicioRepository;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public ServicioResponse crearServicio(CreateServicioRequest request) {
        Servicio servicio = new Servicio();
        
        // Mapeo manual de request a entidad
        servicio.setSlug(request.getSlug());
        servicio.setTitle(request.getTitle());
        servicio.setMarca(request.getMarca());
        servicio.setDescription(request.getDescription());
        servicio.setYearsExperience(request.getYearsExperience());
        servicio.setPriceMin(request.getPriceMin());
        servicio.setPriceMax(request.getPriceMax());
        servicio.setAvailability(request.getAvailability());
        servicio.setBannerUrl(request.getBannerUrl());
        servicio.setLogoUrl(request.getLogoUrl());
        servicio.setThumbnailUrl(request.getThumbnailUrl());
        servicio.setLocationId(request.getLocationId());
        servicio.setCategoryId(request.getCategoryId());
        servicio.setServiceStatus(request.getServiceStatus());

        Servicio guardado = servicioRepository.save(servicio);
        return convertirAResponse(guardado);
    }

    public List<ServicioResponse> obtenerTodos() {
        return servicioRepository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public ServicioResponse obtenerPorId(Long id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado con ID: " + id));
        return convertirAResponse(servicio);
    }

    // Utilidad para convertir la Entidad al DTO de salida
    private ServicioResponse convertirAResponse(Servicio servicio) {
        ServicioResponse response = new ServicioResponse();
        response.setId(servicio.getId());
        response.setSlug(servicio.getSlug());
        response.setTitle(servicio.getTitle());
        response.setMarca(servicio.getMarca());
        response.setDescription(servicio.getDescription());
        response.setYearsExperience(servicio.getYearsExperience());
        response.setPriceMin(servicio.getPriceMin());
        response.setPriceMax(servicio.getPriceMax());
        response.setAvailability(servicio.getAvailability());
        response.setBannerUrl(servicio.getBannerUrl());
        response.setLogoUrl(servicio.getLogoUrl());
        response.setThumbnailUrl(servicio.getThumbnailUrl());
        response.setServiceStatus(servicio.getServiceStatus());
        // Puedes agregar categoryId y locationId al response si lo necesitas en el front
        return response;
    }
}