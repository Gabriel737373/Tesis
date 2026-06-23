package com.__01.APP.Tesis.Empresa.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.__01.APP.Tesis.Empresa.dto.EmpresaGeneralResponse;
import com.__01.APP.Tesis.Empresa.dto.RegistroEmpresaRequest;
import com.__01.APP.Tesis.Empresa.models.entities.EmpresaGeneral;
import com.__01.APP.Tesis.Empresa.models.entities.TipoEmpresa;
import com.__01.APP.Tesis.Empresa.repositories.EmpresaGeneralRepository;
import com.__01.APP.Tesis.Empresa.repositories.TipoEmpresaRepository;
import com.__01.APP.Tesis.Empresa.service.EmpresaGeneralService;

@ExtendWith(MockitoExtension.class)
public class EmpresaGeneralServiceTest {

    @Mock
    private EmpresaGeneralRepository empresaGeneralRepository;

    @Mock
    private TipoEmpresaRepository tipoEmpresaRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private EmpresaGeneralService empresaGeneralService;

    private RegistroEmpresaRequest requestValido;
    private TipoEmpresa tipoEmpresaMock;
    private EmpresaGeneral empresaMock;

    @BeforeEach
    public void setUp() {
        requestValido = new RegistroEmpresaRequest(
            "Tech Solutions S.A.",
            "contacto@techsolutions.com",
            "contrasenaSegura123",
            1L
        );

        tipoEmpresaMock = new TipoEmpresa("Tecnología");
        tipoEmpresaMock.setId(1L);

        empresaMock = new EmpresaGeneral(
            "Tech Solutions S.A.",
            "contacto@techsolutions.com",
            "encodedPassword"
        );
        empresaMock.setId(1L);
        empresaMock.setTipoEmpresa(tipoEmpresaMock);
    }

    // ==========================================
    // ESCENARIOS DE REGISTRO (1 Éxito, 4 Errores)
    // ==========================================

    @Test
    void registrar_EmpresaExitosa_RetornaResponse() {
        when(empresaGeneralRepository.existsByNombreEmpresa(requestValido.getNombreEmpresa())).thenReturn(false);
        when(empresaGeneralRepository.existsByEmailEmpresa(requestValido.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(requestValido.getContrasena())).thenReturn("encodedPassword");
        when(tipoEmpresaRepository.findById(1L)).thenReturn(Optional.of(tipoEmpresaMock));
        when(empresaGeneralRepository.save(any(EmpresaGeneral.class))).thenReturn(empresaMock);

        EmpresaGeneralResponse response = empresaGeneralService.registrar(requestValido);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Tech Solutions S.A.", response.getNombreEmpresa());
        assertEquals("contacto@techsolutions.com", response.getEmailEmpresa());
        verify(empresaGeneralRepository, times(1)).save(any(EmpresaGeneral.class));
    }

    @Test
    void registrar_NombreYaExiste_LanzaIllegalArgumentException() {
        // Simulamos que la base de datos dice: "Ese nombre ya lo tengo"
        when(empresaGeneralRepository.existsByNombreEmpresa(requestValido.getNombreEmpresa())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empresaGeneralService.registrar(requestValido);
        });

        assertEquals("El nombre de empresa ya existe", exception.getMessage());
        verify(empresaGeneralRepository, never()).save(any(EmpresaGeneral.class));
    }

    @Test
    void registrar_EmailYaExiste_LanzaIllegalArgumentException() {
        when(empresaGeneralRepository.existsByNombreEmpresa(requestValido.getNombreEmpresa())).thenReturn(false);
        // Simulamos que el email ya está en uso
        when(empresaGeneralRepository.existsByEmailEmpresa(requestValido.getEmail())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empresaGeneralService.registrar(requestValido);
        });

        assertEquals("El email ya está registrado", exception.getMessage());
        verify(empresaGeneralRepository, never()).save(any(EmpresaGeneral.class));
    }

    @Test
    void registrar_ContrasenaCorta_LanzaIllegalArgumentException() {
        requestValido.setContrasena("123"); // Contraseña inválida
        when(empresaGeneralRepository.existsByNombreEmpresa(requestValido.getNombreEmpresa())).thenReturn(false);
        when(empresaGeneralRepository.existsByEmailEmpresa(requestValido.getEmail())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empresaGeneralService.registrar(requestValido);
        });

        assertEquals("La contraseña debe tener al menos 6 caracteres", exception.getMessage());
        verify(empresaGeneralRepository, never()).save(any(EmpresaGeneral.class));
    }

    @Test
    void registrar_TipoEmpresaNoExiste_LanzaIllegalArgumentException() {
        when(empresaGeneralRepository.existsByNombreEmpresa(requestValido.getNombreEmpresa())).thenReturn(false);
        when(empresaGeneralRepository.existsByEmailEmpresa(requestValido.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(requestValido.getContrasena())).thenReturn("encodedPassword");
        
        // Simulamos que buscamos la categoría 1L pero la base de datos retorna vacío (Optional.empty)
        when(tipoEmpresaRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empresaGeneralService.registrar(requestValido);
        });

        assertEquals("El tipo de empresa seleccionado no existe", exception.getMessage());
        verify(empresaGeneralRepository, never()).save(any(EmpresaGeneral.class));
    }

    // ==========================================
    // ESCENARIOS DE LOGIN (Errores)
    // ==========================================

    @Test
    void verificarCredenciales_EmailNoExiste_LanzaIllegalArgumentException() {
        // Simulamos que alguien intenta logearse con un correo que no está en la BD
        when(empresaGeneralRepository.findByEmailEmpresa("fantasma@correo.com")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empresaGeneralService.verificarCredenciales("fantasma@correo.com", "clave123");
        });

        assertEquals("Credenciales inválidas", exception.getMessage());
    }

    @Test
    void verificarCredenciales_ContrasenaIncorrecta_LanzaIllegalArgumentException() {
        // Simulamos que el correo sí existe...
        when(empresaGeneralRepository.findByEmailEmpresa("contacto@techsolutions.com")).thenReturn(Optional.of(empresaMock));
        // ...pero el encriptador dice que la contraseña que mandó el usuario NO coincide con la de la BD
        when(passwordEncoder.matches("claveEquivocada", empresaMock.getContrasena())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empresaGeneralService.verificarCredenciales("contacto@techsolutions.com", "claveEquivocada");
        });

        assertEquals("Credenciales inválidas", exception.getMessage());
    }

    // ==========================================
    // ESCENARIOS DE ELIMINACIÓN (Errores)
    // ==========================================

    @Test
    void eliminarPorId_EmpresaNoExiste_LanzaIllegalArgumentException() {
        // Simulamos intentar borrar el ID 99, pero no existe
        when(empresaGeneralRepository.existsById(99L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empresaGeneralService.eliminarPorId(99L);
        });

        assertEquals("La empresa no existe", exception.getMessage());
        // Verificamos que el repositorio jamás intente ejecutar el comando DELETE
        verify(empresaGeneralRepository, never()).deleteById(anyLong());
    }
}