package com.__01.APP.Tesis.Usuario.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.__01.APP.Tesis.Usuario.dto.RegistroRequest;
import com.__01.APP.Tesis.Usuario.dto.UsuarioGeneralResponse;
import com.__01.APP.Tesis.Usuario.models.entities.TipoUsuario;
import com.__01.APP.Tesis.Usuario.models.entities.UsuarioGeneral;
import com.__01.APP.Tesis.Usuario.repositories.TipoUsuarioRepository;
import com.__01.APP.Tesis.Usuario.repositories.UsuarioGeneralRepository;

@Service
public class UsuarioGeneralService {

    private final UsuarioGeneralRepository usuarioGeneralRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository; // INYECCIÓN NUEVA
    private final BCryptPasswordEncoder passwordEncoder;

    // Constructor actualizado
    public UsuarioGeneralService(UsuarioGeneralRepository usuarioGeneralRepository, 
                                 TipoUsuarioRepository tipoUsuarioRepository,
                                 BCryptPasswordEncoder passwordEncoder) {
        this.usuarioGeneralRepository = usuarioGeneralRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Actualizado para recibir el DTO completo
    public UsuarioGeneralResponse registrar(RegistroRequest dto) {
        if (usuarioGeneralRepository.existsByNombreUsuario(dto.getNombreUsuario())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }
        if (usuarioGeneralRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if (dto.getContrasena().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        UsuarioGeneral usuario = new UsuarioGeneral(
            dto.getNombreUsuario(),
            dto.getEmail(),
            passwordEncoder.encode(dto.getContrasena())
        );
        usuario.setActualizadoEn(LocalDateTime.now());

        // BUSCAR EL TIPO Y ASIGNARLO
        if (dto.getTipoUsuarioId() != null) {
            TipoUsuario tipo = tipoUsuarioRepository.findById(dto.getTipoUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("El tipo de usuario seleccionado no existe"));
            usuario.setTipoUsuario(tipo);
        }
        
        UsuarioGeneral usuarioGuardado = usuarioGeneralRepository.save(usuario);
        return convertToResponse(usuarioGuardado);
    }

    public UsuarioGeneralResponse obtenerPorId(Long id) {
        Optional<UsuarioGeneral> usuario = usuarioGeneralRepository.findById(id);
        return usuario.map(this::convertToResponse).orElse(null);
    }

    public UsuarioGeneralResponse obtenerPorNombreUsuario(String nombreUsuario) {
        Optional<UsuarioGeneral> usuario = usuarioGeneralRepository.findByNombreUsuario(nombreUsuario);
        return usuario.map(this::convertToResponse).orElse(null);
    }

    public UsuarioGeneralResponse obtenerPorEmail(String email) {
        Optional<UsuarioGeneral> usuario = usuarioGeneralRepository.findByEmail(email);
        return usuario.map(this::convertToResponse).orElse(null);
    }

    public List<UsuarioGeneralResponse> listar() {
        return usuarioGeneralRepository.findAll()
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    public boolean eliminar(Long id) {
        if (!usuarioGeneralRepository.existsById(id)) {
            return false;
        }
        usuarioGeneralRepository.deleteById(id);
        return true;
    }

    public UsuarioGeneralResponse verificarCredenciales(String nombreUsuario, String contrasena) {
        Optional<UsuarioGeneral> usuario = usuarioGeneralRepository.findByNombreUsuario(nombreUsuario);
        if (usuario.isPresent() && passwordEncoder.matches(contrasena, usuario.get().getContrasena())) {
            return convertToResponse(usuario.get());
        }
        return null;
    }

    private UsuarioGeneralResponse convertToResponse(UsuarioGeneral usuario) {
        return new UsuarioGeneralResponse(
            usuario.getId(),
            usuario.getNombreUsuario(),
            usuario.getEmail(),
            usuario.getActivo(),
            usuario.getCreadoEn(),
            usuario.getActualizadoEn()
        );
    }
}