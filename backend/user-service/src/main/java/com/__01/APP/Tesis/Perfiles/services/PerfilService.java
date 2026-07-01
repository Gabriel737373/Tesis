package com.__01.APP.Tesis.Perfiles.services;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.__01.APP.Tesis.Perfiles.dto.ProfileResponse;
import com.__01.APP.Tesis.Perfiles.dto.SocialLinkDto;
import com.__01.APP.Tesis.Perfiles.dto.UpdateProfileRequest;
import com.__01.APP.Tesis.Perfiles.models.Perfil;
import com.__01.APP.Tesis.Perfiles.models.SocialLinkEmbed;
import com.__01.APP.Tesis.Perfiles.repositories.PerfilRepository;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public ProfileResponse obtenerPorUserId(String userId) {
        // Busca el perfil. Si no existe, devuelve uno vacío amarrado a ese userId.
        Perfil perfil = perfilRepository.findByUserId(userId).orElse(new Perfil());
        perfil.setUserId(userId);
        return convertirAResponse(perfil);
    }

    public ProfileResponse actualizarPerfil(String userId, UpdateProfileRequest request) {
        // Buscamos si ya tiene perfil, o creamos uno nuevo
        Perfil perfil = perfilRepository.findByUserId(userId).orElse(new Perfil());
        perfil.setUserId(userId);

        // Actualizamos los datos
        if (request.name != null) perfil.setName(request.name);
        if (request.bannerUrl != null) perfil.setBannerUrl(request.bannerUrl);
        if (request.logoUrl != null) perfil.setLogoUrl(request.logoUrl);
        if (request.industry != null) perfil.setIndustry(request.industry);
        if (request.description != null) perfil.setDescription(request.description);
        if (request.location != null) perfil.setLocation(request.location);
        if (request.founded != null) perfil.setFounded(request.founded);
        if (request.employees != null) perfil.setEmployees(request.employees);
        if (request.website != null) perfil.setWebsite(request.website);
        if (request.whatsapp != null) perfil.setWhatsapp(request.whatsapp);
        if (request.tags != null) perfil.setTags(request.tags);

        // Mapear redes sociales
        if (request.socialLinks != null) {
            perfil.setSocialLinks(request.socialLinks.stream().map(dto -> {
                SocialLinkEmbed embed = new SocialLinkEmbed();
                embed.setPlatform(dto.platform);
                embed.setUrl(dto.url);
                embed.setOrden(dto.orden);
                return embed;
            }).collect(Collectors.toList()));
        }

        Perfil guardado = perfilRepository.save(perfil);
        return convertirAResponse(guardado);
    }

    private ProfileResponse convertirAResponse(Perfil perfil) {
        ProfileResponse response = new ProfileResponse();
        if (perfil.getId() != null) response.id = perfil.getId().toString();
        response.userId = perfil.getUserId();
        response.name = perfil.getName();
        response.bannerUrl = perfil.getBannerUrl();
        response.logoUrl = perfil.getLogoUrl();
        response.industry = perfil.getIndustry();
        response.description = perfil.getDescription();
        response.location = perfil.getLocation();
        response.founded = perfil.getFounded();
        response.employees = perfil.getEmployees();
        response.website = perfil.getWebsite();
        response.whatsapp = perfil.getWhatsapp();
        response.tags = perfil.getTags();

        if (perfil.getSocialLinks() != null) {
            response.socialLinks = perfil.getSocialLinks().stream().map(embed -> {
                SocialLinkDto dto = new SocialLinkDto();
                dto.platform = embed.getPlatform();
                dto.url = embed.getUrl();
                dto.orden = embed.getOrden();
                return dto;
            }).collect(Collectors.toList());
        }

        if (perfil.getCreatedAt() != null) response.createdAt = perfil.getCreatedAt().toString();
        if (perfil.getUpdatedAt() != null) response.updatedAt = perfil.getUpdatedAt().toString();

        return response;
    }
}