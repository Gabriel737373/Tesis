package com.__01.APP.Tesis.Perfiles.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.__01.APP.Tesis.Perfiles.dto.ProfileResponse;
import com.__01.APP.Tesis.Perfiles.dto.UpdateProfileRequest;
import com.__01.APP.Tesis.Perfiles.services.PerfilService;
import com.__01.APP.Tesis.Usuario.dto.ApiResponse;

@RestController
@RequestMapping("/api/profiles")
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
        String myUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // Actualizamos en BD a través del servicio
        ProfileResponse response = perfilService.actualizarPerfil(myUserId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Perfil actualizado correctamente", response));
    }
}