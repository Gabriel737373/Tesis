package com.__01.APP.Tesis.Empresa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.__01.APP.Tesis.Empresa.dto.EmpresaGeneralResponse;
import com.__01.APP.Tesis.Empresa.dto.RegistroEmpresaRequest;
import com.__01.APP.Tesis.Empresa.models.entities.EmpresaGeneral;
import com.__01.APP.Tesis.Empresa.models.entities.TipoEmpresa;
import com.__01.APP.Tesis.Empresa.repositories.EmpresaGeneralRepository;
import com.__01.APP.Tesis.Empresa.repositories.TipoEmpresaRepository;

@Service
public class EmpresaGeneralService {

    private final EmpresaGeneralRepository empresaGeneralRepository;
    private final TipoEmpresaRepository tipoEmpresaRepository; 
    private final BCryptPasswordEncoder passwordEncoder;

    public EmpresaGeneralService(EmpresaGeneralRepository empresaGeneralRepository, 
                                 TipoEmpresaRepository tipoEmpresaRepository,
                                 BCryptPasswordEncoder passwordEncoder) {
        this.empresaGeneralRepository = empresaGeneralRepository;
        this.tipoEmpresaRepository = tipoEmpresaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. REGISTRAR
    public EmpresaGeneralResponse registrar(RegistroEmpresaRequest dto) {
        if (empresaGeneralRepository.existsByNombreEmpresa(dto.getNombreEmpresa())) {
            throw new IllegalArgumentException("El nombre de empresa ya existe");
        }
        if (empresaGeneralRepository.existsByEmailEmpresa(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if (dto.getContrasena().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }
        
        EmpresaGeneral empresa = new EmpresaGeneral(
            dto.getNombreEmpresa(),
            dto.getEmail(),
            passwordEncoder.encode(dto.getContrasena())
        );

        if (dto.getTipoEmpresaId() != null) {
            TipoEmpresa tipo = tipoEmpresaRepository.findById(dto.getTipoEmpresaId())
                .orElseThrow(() -> new IllegalArgumentException("El tipo de empresa seleccionado no existe"));
            empresa.setTipoEmpresa(tipo);
        }
        
        EmpresaGeneral empresaGuardada = empresaGeneralRepository.save(empresa);
        return convertToResponse(empresaGuardada);
    }

    // 2. INICIAR SESIÓN (Verificar Credenciales)
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

    // 3. OBTENER POR ID
    public EmpresaGeneralResponse obtenerPorId(Long id) {
        return empresaGeneralRepository.findById(id)
            .map(this::convertToResponse)
            .orElse(null);
    }

    // 4. OBTENER TODAS
    public List<EmpresaGeneralResponse> obtenerTodas() {
        return empresaGeneralRepository.findAll()
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    // 5. ELIMINAR POR ID
    public void eliminarPorId(Long id) {
        if (!empresaGeneralRepository.existsById(id)) {
            throw new IllegalArgumentException("La empresa no existe");
        }
        empresaGeneralRepository.deleteById(id);
    }

    // CONVERTIDOR DE ENTIDAD A DTO
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