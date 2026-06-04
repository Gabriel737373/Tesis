package com.__01.APP.Tesis.Empresa.service;

import java.time.LocalDateTime;

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
        if (empresaGeneralRepository.existsByEmail(emailEmpresa)) {
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
        return empresaGeneralRepository.findById(id)
                .map(this::convertToResponse)
                .orElse(null);
    }

    public EmpresaGeneralResponse obtenerPorNombreEmpresa(String nombreEmpresa) {
        return empresaGeneralRepository.findByNombreEmpresa(nombreEmpresa)
                .map(this::convertToResponse)
                .orElse(null);
    }

    public EmpresaGeneralResponse obtenerPorEmail(String emailEmpresa) {
        return empresaGeneralRepository.findByEmail(emailEmpresa)
                .map(this::convertToResponse)
                .orElse(null);
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
