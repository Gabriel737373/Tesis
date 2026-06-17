package com.__01.APP.Tesis.Multimedia.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.__01.APP.Tesis.Empresa.models.entities.EmpresaGeneral;
import com.__01.APP.Tesis.Empresa.repositories.EmpresaGeneralRepository;
import com.__01.APP.Tesis.Multimedia.Dto.PublicacionRequest;
import com.__01.APP.Tesis.Multimedia.Dto.PublicacionResponse;
import com.__01.APP.Tesis.Multimedia.Models.Entities.Publicacion;
import com.__01.APP.Tesis.Multimedia.Repository.PublicacionRepository; // Asegúrate que la ruta sea correcta
import com.__01.APP.Tesis.Usuario.models.entities.UsuarioGeneral;
import com.__01.APP.Tesis.Usuario.repositories.UsuarioGeneralRepository;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final UsuarioGeneralRepository usuarioGeneralRepository;
    private final EmpresaGeneralRepository empresaGeneralRepository;

    public PublicacionService(PublicacionRepository publicacionRepository,
                              UsuarioGeneralRepository usuarioGeneralRepository,
                              EmpresaGeneralRepository empresaGeneralRepository) {
        this.publicacionRepository = publicacionRepository;
        this.usuarioGeneralRepository = usuarioGeneralRepository;
        this.empresaGeneralRepository = empresaGeneralRepository;
    }

    // 1. CREAR PUBLICACIÓN
    public PublicacionResponse crear(PublicacionRequest dto) {
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(dto.getTitulo());
        publicacion.setArchivoUrl(dto.getArchivoUrl());
        publicacion.setTipoMultimedia(dto.getTipoMultimedia());

        // Validamos quién está haciendo la publicación
        if ("USUARIO".equalsIgnoreCase(dto.getTipoAutor())) {
            UsuarioGeneral usuario = usuarioGeneralRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));
            publicacion.setUsuario(usuario);
        } else if ("EMPRESA".equalsIgnoreCase(dto.getTipoAutor())) {
            EmpresaGeneral empresa = empresaGeneralRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("La empresa no existe"));
            publicacion.setEmpresa(empresa);
        } else {
            throw new IllegalArgumentException("El tipoAutor debe ser 'USUARIO' o 'EMPRESA'");
        }

        Publicacion guardada = publicacionRepository.save(publicacion);
        return convertToResponse(guardada);
    }

    // 2. OBTENER EL MURO GLOBAL (Todas las publicaciones)
    public List<PublicacionResponse> obtenerMuroGlobal() {
        return publicacionRepository.findAllByOrderByFechaPublicacionDesc()
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    // 3. OBTENER PUBLICACIONES DE UN USUARIO ESPECÍFICO
    public List<PublicacionResponse> obtenerPorUsuario(Long usuarioId) {
        return publicacionRepository.findByUsuarioIdOrderByFechaPublicacionDesc(usuarioId)
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    // 4. ELIMINAR PUBLICACIÓN
    public void eliminar(Long id) {
        if (!publicacionRepository.existsById(id)) {
            throw new IllegalArgumentException("La publicación no existe");
        }
        publicacionRepository.deleteById(id);
    }

    // CONVERTIDOR A DTO (Para no enviar las contraseñas del usuario al frontend)
    private PublicacionResponse convertToResponse(Publicacion pub) {
        String nombreAutor = pub.getUsuario() != null ? pub.getUsuario().getNombreUsuario() : pub.getEmpresa().getNombreEmpresa();
        String tipoAutor = pub.getUsuario() != null ? "USUARIO" : "EMPRESA";

        return new PublicacionResponse(
            pub.getId(),
            pub.getTitulo(),
            pub.getArchivoUrl(),
            pub.getTipoMultimedia(),
            pub.getFechaPublicacion(),
            nombreAutor,
            tipoAutor
        );
    }
}