package com.__01.APP.Tesis.dto;

public class ApiResponse<T> {
    private Boolean success;
    private String mensaje;
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
