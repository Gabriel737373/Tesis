package com.__01.APP.Tesis.Empresa.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.__01.APP.Tesis.Empresa.dto.EmpresaGeneralResponse;
import com.__01.APP.Tesis.Empresa.dto.RegistroEmpresaRequest;
import com.__01.APP.Tesis.Empresa.service.EmpresaGeneralService;
import com.__01.APP.Tesis.Usuario.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = {"*"})
@Tag(name = "Gestión de Empresas", description = "Endpoints para gestión de empresas")
public class EmpresaGeneralController {

    private final EmpresaGeneralService empresaGeneralService;

    public EmpresaGeneralController(EmpresaGeneralService empresaGeneralService) {
        this.empresaGeneralService = empresaGeneralService;
    }

    @PostMapping("/registro")
    @Operation(summary = "Registrar nueva empresa", description = "Crea una nueva empresa en el sistema con validaciones de email y contraseña")
    public ResponseEntity<ApiResponse<EmpresaGeneralResponse>> registro(@RequestBody RegistroEmpresaRequest request) {
        try {
            // Ahora pasamos el objeto request directamente
            EmpresaGeneralResponse response = empresaGeneralService.registrar(request);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Empresa registrada exitosamente", response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión de empresa", description = "Autentica a la empresa y retorna sus datos si las credenciales son válidas")
    public ResponseEntity<ApiResponse<EmpresaGeneralResponse>> login(@RequestBody RegistroEmpresaRequest request) {
        try {
            EmpresaGeneralResponse response = empresaGeneralService.verificarCredenciales(
                request.getEmail(),
                request.getContrasena()
            );
            return ResponseEntity.ok(new ApiResponse<>(true, "Inicio de sesión exitoso", response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener empresa por ID", description = "Retorna los datos de una empresa específica por su ID")
    public ResponseEntity<ApiResponse<EmpresaGeneralResponse>> obtenerPorId(@PathVariable Long id) {
        EmpresaGeneralResponse response = empresaGeneralService.obtenerPorId(id);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "Empresa no encontrada"));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Empresa encontrada", response));
    }

    @GetMapping("/todos")
    @Operation(summary = "Obtener todas las empresas", description = "Retorna una lista de todas las empresas registradas en el sistema")
    public ResponseEntity<ApiResponse<List<EmpresaGeneralResponse>>> obtenerTodas() {
        List<EmpresaGeneralResponse> responses = empresaGeneralService.obtenerTodas();
        return ResponseEntity.ok(new ApiResponse<>(true, "Empresas encontradas", responses));
    }

    @GetMapping("/buscar/{nombre}")
    @Operation(summary = "Buscar empresas por nombre", description = "Retorna una lista de empresas que coinciden con el nombre proporcionado")
    public ResponseEntity<ApiResponse<List<EmpresaGeneralResponse>>> buscarPorNombre(@PathVariable String nombre) {
        List<EmpresaGeneralResponse> responses = empresaGeneralService.obtenerTodas().stream()
            .filter(empresa -> empresa.getNombreEmpresa().toLowerCase().contains(nombre.toLowerCase()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, "Empresas encontradas", responses));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar empresa por ID", description = "Elimina una empresa específica por su ID")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        try {
            empresaGeneralService.eliminarPorId(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Empresa eliminada exitosamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno del servidor"));
        }
    }   

}
