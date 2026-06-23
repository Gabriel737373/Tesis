package com.__01.APP.Tesis.Usuario.Service;

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

import com.__01.APP.Tesis.Usuario.dto.RegistroRequest;
import com.__01.APP.Tesis.Usuario.dto.UsuarioGeneralResponse;
import com.__01.APP.Tesis.Usuario.models.entities.TipoUsuario;
import com.__01.APP.Tesis.Usuario.models.entities.UsuarioGeneral;
import com.__01.APP.Tesis.Usuario.repositories.TipoUsuarioRepository;
import com.__01.APP.Tesis.Usuario.repositories.UsuarioGeneralRepository;

// ¡Esta es la importación clave que faltaba por la diferencia de carpetas!
import com.__01.APP.Tesis.Usuario.services.UsuarioGeneralService;

@ExtendWith(MockitoExtension.class)
public class UsuarioGeneralServiceTest {

    @Mock
    private UsuarioGeneralRepository usuarioGeneralRepository;

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioGeneralService usuarioGeneralService;

    private RegistroRequest requestValido;
    private TipoUsuario tipoUsuarioMock;
    private UsuarioGeneral usuarioMock;

    @BeforeEach
    public void setUp() {
        requestValido = new RegistroRequest(
            "juan_perez",
            "juan@example.com",
            "contrasenaSegura123",
            1L
        );

        tipoUsuarioMock = new TipoUsuario("Administrador");
        tipoUsuarioMock.setId(1L);

        usuarioMock = new UsuarioGeneral(
            "juan_perez",
            "juan@example.com",
            "encodedPassword"
        );
        usuarioMock.setId(1L);
        usuarioMock.setActivo(true);
        usuarioMock.setTipoUsuario(tipoUsuarioMock);
    }

    // ==========================================
    // ESCENARIOS DE REGISTRO
    // ==========================================

    @Test
    public void registrar_UsuarioExitoso_RetornaResponse() {
        when(usuarioGeneralRepository.existsByNombreUsuario(requestValido.getNombreUsuario())).thenReturn(false);
        when(usuarioGeneralRepository.existsByEmail(requestValido.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(requestValido.getContrasena())).thenReturn("encodedPassword");
        when(tipoUsuarioRepository.findById(1L)).thenReturn(Optional.of(tipoUsuarioMock));
        when(usuarioGeneralRepository.save(any(UsuarioGeneral.class))).thenReturn(usuarioMock);

        UsuarioGeneralResponse response = usuarioGeneralService.registrar(requestValido);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("juan_perez", response.getNombreUsuario());
        assertEquals("juan@example.com", response.getEmail());
        assertTrue(response.getActivo());
        verify(usuarioGeneralRepository, times(1)).save(any(UsuarioGeneral.class));
    }

    @Test
    public void registrar_NombreUsuarioYaExiste_LanzaIllegalArgumentException() {
        when(usuarioGeneralRepository.existsByNombreUsuario(requestValido.getNombreUsuario())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioGeneralService.registrar(requestValido);
        });

        assertEquals("El nombre de usuario ya existe", exception.getMessage());
        verify(usuarioGeneralRepository, never()).save(any(UsuarioGeneral.class));
    }

    @Test
    public void registrar_EmailYaExiste_LanzaIllegalArgumentException() {
        when(usuarioGeneralRepository.existsByNombreUsuario(requestValido.getNombreUsuario())).thenReturn(false);
        when(usuarioGeneralRepository.existsByEmail(requestValido.getEmail())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioGeneralService.registrar(requestValido);
        });

        assertEquals("El email ya está registrado", exception.getMessage());
        verify(usuarioGeneralRepository, never()).save(any(UsuarioGeneral.class));
    }

    @Test
    public void registrar_ContrasenaCorta_LanzaIllegalArgumentException() {
        requestValido.setContrasena("12345"); // 5 caracteres
        when(usuarioGeneralRepository.existsByNombreUsuario(requestValido.getNombreUsuario())).thenReturn(false);
        when(usuarioGeneralRepository.existsByEmail(requestValido.getEmail())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioGeneralService.registrar(requestValido);
        });

        assertEquals("La contraseña debe tener al menos 6 caracteres", exception.getMessage());
        verify(usuarioGeneralRepository, never()).save(any(UsuarioGeneral.class));
    }

    @Test
    public void registrar_TipoUsuarioNoExiste_LanzaIllegalArgumentException() {
        when(usuarioGeneralRepository.existsByNombreUsuario(requestValido.getNombreUsuario())).thenReturn(false);
        when(usuarioGeneralRepository.existsByEmail(requestValido.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(requestValido.getContrasena())).thenReturn("encodedPassword");
        
        when(tipoUsuarioRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioGeneralService.registrar(requestValido);
        });

        assertEquals("El tipo de usuario seleccionado no existe", exception.getMessage());
        verify(usuarioGeneralRepository, never()).save(any(UsuarioGeneral.class));
    }

    // ==========================================
    // ESCENARIOS DE LOGIN 
    // ==========================================

    @Test
    public void verificarCredenciales_UsuarioNoExiste_RetornaNull() {
        when(usuarioGeneralRepository.findByNombreUsuario("usuario_fantasma")).thenReturn(Optional.empty());

        UsuarioGeneralResponse response = usuarioGeneralService.verificarCredenciales("usuario_fantasma", "clave123");

        assertNull(response);
    }

    @Test
    public void verificarCredenciales_ContrasenaIncorrecta_RetornaNull() {
        when(usuarioGeneralRepository.findByNombreUsuario("juan_perez")).thenReturn(Optional.of(usuarioMock));
        when(passwordEncoder.matches("claveEquivocada", usuarioMock.getContrasena())).thenReturn(false);

        UsuarioGeneralResponse response = usuarioGeneralService.verificarCredenciales("juan_perez", "claveEquivocada");

        assertNull(response);
    }

    // ==========================================
    // ESCENARIOS DE ELIMINACIÓN 
    // ==========================================

    @Test
    public void eliminar_UsuarioNoExiste_RetornaFalse() {
        when(usuarioGeneralRepository.existsById(99L)).thenReturn(false);

        boolean resultado = usuarioGeneralService.eliminar(99L);

        assertFalse(resultado);
        verify(usuarioGeneralRepository, never()).deleteById(anyLong());
    }
}