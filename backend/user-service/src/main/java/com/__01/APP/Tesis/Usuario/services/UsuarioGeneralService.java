package com.__01.APP.Tesis.Usuario.services;

import com.__01.APP.Tesis.Usuario.dto.UsuarioGeneralResponse;
import com.__01.APP.Tesis.Usuario.models.UsuarioGeneral;
import com.__01.APP.Tesis.Usuario.repositories.UsuarioGeneralRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioGeneralService {

    private final UsuarioGeneralRepository usuarioGeneralRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioGeneralService(UsuarioGeneralRepository usuarioGeneralRepository, 
                                 BCryptPasswordEncoder passwordEncoder) {
        this.usuarioGeneralRepository = usuarioGeneralRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioGeneralResponse registrar(String nombreUsuario, String email, String contrasena) {
        if (usuarioGeneralRepository.existsByNombreUsuario(nombreUsuario)) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }
        if (usuarioGeneralRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if (contrasena.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        UsuarioGeneral usuario = new UsuarioGeneral(
            nombreUsuario,
            email,
            passwordEncoder.encode(contrasena)
        );
        usuario.setActualizadoEn(LocalDateTime.now());
        
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
