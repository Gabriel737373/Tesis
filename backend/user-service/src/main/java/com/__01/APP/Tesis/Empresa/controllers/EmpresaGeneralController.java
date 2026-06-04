package com.__01.APP.Tesis.Empresa.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
