package com.__01.APP.Tesis.Empresa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.__01.APP.Tesis.Empresa.dto.EmpresaGeneralResponse;
import com.__01.APP.Tesis.Empresa.models.entities.EmpresaGeneral;
import com.__01.APP.Tesis.Empresa.repositories.EmpresaGeneralRepository;

@Service
public class EmpresaGeneralService {

    private final EmpresaGeneralRepository empresaGeneralRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public EmpresaGeneralService(EmpresaGeneralRepository empresaGeneralRepository, 
                                 BCryptPasswordEncoder passwordEncoder) {
        this.empresaGeneralRepository = empresaGeneralRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public EmpresaGeneralResponse registrar(String nombreEmpresa, String emailEmpresa, String contrasena) {
        if (empresaGeneralRepository.existsByNombreEmpresa(nombreEmpresa)) {
            throw new IllegalArgumentException("El nombre de empresa ya existe");
        }
        if (empresaGeneralRepository.existsByEmailEmpresa(emailEmpresa)) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if (contrasena.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }
        
        EmpresaGeneral empresa = new EmpresaGeneral(
            nombreEmpresa,
            emailEmpresa,
            passwordEncoder.encode(contrasena)
        );
        empresa.setActualizadoEn(LocalDateTime.now());
        
        EmpresaGeneral empresaGuardada = empresaGeneralRepository.save(empresa);
        return convertToResponse(empresaGuardada);
    }

    public EmpresaGeneralResponse obtenerPorId(Long id) {
        Optional<EmpresaGeneral> empresa = empresaGeneralRepository.findById(id);
        return empresa.map(this::convertToResponse).orElse(null);
    }

    public EmpresaGeneralResponse obtenerPorNombreEmpresa(String nombreEmpresa) {
        Optional<EmpresaGeneral> empresa = empresaGeneralRepository.findByNombreEmpresa(nombreEmpresa);
        return empresa.map(this::convertToResponse).orElse(null);
    }

    public EmpresaGeneralResponse obtenerPorEmail(String emailEmpresa) {
        Optional<EmpresaGeneral> empresa = empresaGeneralRepository.findByEmailEmpresa(emailEmpresa);
        return empresa.map(this::convertToResponse).orElse(null);
    }

    public List<EmpresaGeneralResponse> obtenerTodas() {
        return empresaGeneralRepository.findAll()
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    public boolean eliminarPorId(Long id) {
        if (!empresaGeneralRepository.existsById(id)) {
            return false;

        }
        empresaGeneralRepository.deleteById(id);
        return true;
    }

    public EmpresaGeneralResponse verificarCredenciales(String emailEmpresa, String contrasena) {
        Optional<EmpresaGeneral> empresaOpt = empresaGeneralRepository.findByEmailEmpresa(emailEmpresa);
        if (empresaOpt.isPresent()) {
            EmpresaGeneral empresa = empresaOpt.get();
            if (passwordEncoder.matches(contrasena, empresa.getContrasena())) {
                return convertToResponse(empresa);
            }
        }
        throw new IllegalArgumentException("Credenciales inválidas");
    }

    private EmpresaGeneralResponse convertToResponse(EmpresaGeneral empresa) {
        return new EmpresaGeneralResponse(
            empresa.getId(),
            empresa.getNombreEmpresa(),
            empresa.getEmailEmpresa(),
            empresa.getTelefonoEmpresa(),
            empresa.getDireccionEmpresa()
        );
    }

    

}
