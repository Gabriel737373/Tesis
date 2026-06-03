package com.__01.APP.Tesis.Usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "ApiResponse",
    description = "Respuesta estándar de la API con datos genéricos"
)
public class ApiResponse<T> {
    @Schema(description = "Indica si la operación fue exitosa", example = "true")
    private Boolean success;
    
    @Schema(description = "Mensaje descriptivo de la operación", example = "Usuario registrado exitosamente")
    private String mensaje;
    
    @Schema(description = "Datos de respuesta (puede ser nulo en operaciones sin datos)")
    private T datos;

    public ApiResponse() {}

    public ApiResponse(Boolean success, String mensaje) {
        this.success = success;
        this.mensaje = mensaje;
        this.datos = null;
    }

    public ApiResponse(Boolean success, String mensaje, T datos) {
        this.success = success;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getDatos() {
        return datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }
}
