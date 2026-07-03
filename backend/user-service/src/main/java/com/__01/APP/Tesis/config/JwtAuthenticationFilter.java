package com.__01.APP.Tesis.config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.__01.APP.Tesis.Usuario.dto.UsuarioGeneralResponse;
import com.__01.APP.Tesis.Usuario.services.UsuarioGeneralService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioGeneralService usuarioService;

    public JwtAuthenticationFilter(JwtService jwtService, UsuarioGeneralService usuarioService) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // 1. Si no hay token o no empieza con "Bearer ", ignoramos y seguimos
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extraemos el token y el email
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractEmail(jwt);

        // 3. Si el token tiene email y el usuario aún no está autenticado en este ciclo
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // Buscamos al usuario en tu base de datos
            UsuarioGeneralResponse usuario = usuarioService.obtenerPorEmail(userEmail);

            if (usuario != null && jwtService.isTokenValid(jwt)) {
                // Lo autenticamos oficialmente en Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        usuario, null, new ArrayList<>()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}