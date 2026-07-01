package com.__01.APP.Tesis.Perfiles.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.__01.APP.Tesis.Usuario.dto.ApiResponse;

import com.__01.APP.Tesis.Perfiles.dto.ProfileResponse;
import com.__01.APP.Tesis.Perfiles.dto.UpdateProfileRequest;
import com.__01.APP.Tesis.Perfiles.services.PerfilService;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "*")
public class ProfileController {

    private final PerfilService perfilService;

    // Inyectamos el servicio real
    public ProfileController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable String userId) {
        // Buscamos en BD a través del servicio
        ProfileResponse response = perfilService.obtenerPorUserId(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Perfil obtenido", response));
    }

    @PatchMapping("/me")
    public ResponseEntity<?> updateMyProfile(@RequestBody UpdateProfileRequest request) {
        // En un futuro, el "userId" se sacará del Token JWT de quien hace la petición.
        // Por ahora simulamos que el usuario que actualiza tiene el ID "1" (como lo dejamos en AuthController)
        String myUserId = "1"; 
        
        // Actualizamos en BD a través del servicio
        ProfileResponse response = perfilService.actualizarPerfil(myUserId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Perfil actualizado correctamente", response));
    }
}