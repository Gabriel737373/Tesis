# 🎉 Tu API Está Completamente Documentada con Swagger/OpenAPI

¡Se ha configurado un **entorno visual interactivo** para documentar y probar todos tus endpoints!

---

## 📚 Documentación

### 🚀 **¿Primero uso?**
👉 **[QUICK_START.md](QUICK_START.md)** - Cómo iniciar en 2 minutos

### 📖 **Guías Completas**
- **[SWAGGER_GUIDE.md](SWAGGER_GUIDE.md)** - Cómo usar Swagger UI
- **[CURL_EXAMPLES.md](CURL_EXAMPLES.md)** - Ejemplos de prueba desde terminal
- **[API_DOCUMENTATION.md](API_DOCUMENTATION.md)** - Documentación completa
- **[IMPLEMENTACION.md](IMPLEMENTACION.md)** - Qué se cambió y por qué

### 🎨 **Visualización**
- **[INDEX.html](INDEX.html)** - Página visual (abrir en navegador)

---

## 🌐 Acceso a Swagger

Una vez que la aplicación esté ejecutándose en `http://localhost:8080`:

### **Interfaz Visual (Recomendado)**
```
http://localhost:8080/swagger-ui.html
```

### **Especificación OpenAPI JSON**
```
http://localhost:8080/v3/api-docs
```

### **Consola de Base de Datos**
```
http://localhost:8080/h2-console
```

---

## ⚡ Inicio Rápido (3 pasos)

### 1️⃣ Abre una terminal
```bash
cd backend/user-service
```

### 2️⃣ Ejecuta la aplicación
```bash
.\gradlew.bat bootRun
```

### 3️⃣ Abre Swagger en tu navegador
```
http://localhost:8080/swagger-ui.html
```

¡**Listo!** Ahora puedes probar todos tus endpoints de forma visual.

---

## 📡 Endpoints Disponibles

| Método | URL | Descripción |
|--------|-----|-------------|
| 🟢 POST | `/api/usuarios/registro` | Registrar nuevo usuario |
| 🟢 POST | `/api/usuarios/login` | Iniciar sesión |
| 🔵 GET | `/api/usuarios/obtener/{id}` | Obtener usuario por ID |
| 🔵 GET | `/api/usuarios/email/{email}` | Obtener usuario por email |
| 🔵 GET | `/api/usuarios/listar` | Listar todos los usuarios |
| 🔴 DELETE | `/api/usuarios/delete/{id}` | Eliminar usuario |

Cada endpoint está **completamente documentado** con:
- ✅ Ejemplos de respuesta exitosa
- ❌ Ejemplos de respuesta de error
- 📝 Descripciones detalladas
- 🔢 Códigos HTTP apropiados

---

## 🧪 Probar un Endpoint en Swagger

1. Abre [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
2. Expandir "Gestión de Usuarios"
3. Selecciona un endpoint
4. Haz clic en "**Try it out**"
5. Completa los parámetros
6. Haz clic en "**Execute**"
7. ¡Ve la respuesta en tiempo real! 🎉

---

## 📖 Ejemplo de Respuesta

### Caso de Éxito
```json
{
  "success": true,
  "mensaje": "Login exitoso",
  "datos": {
    "id": 1,
    "nombreUsuario": "juan_perez",
    "email": "juan@example.com",
    "activo": true,
    "creadoEn": "2026-01-15T10:30:00",
    "actualizadoEn": "2026-01-15T10:30:00"
  }
}
```

### Caso de Error
```json
{
  "success": false,
  "mensaje": "Nombre de usuario o contraseña incorrectos",
  "datos": null
}
```

---

## 🛠️ Qué se Configuró

✅ **Dependencia**: `springdoc-openapi-starter-webmvc-ui`  
✅ **Configuración**: `OpenApiConfig.java`  
✅ **Anotaciones**: Agregadas a controladores y DTOs  
✅ **Documentación**: 6 archivos Markdown + HTML  
✅ **Ejemplos**: CURL y JSON para cada endpoint  

---

## 🎯 Flujo de Prueba Recomendado

```
1. Registra un usuario (POST /api/usuarios/registro)
        ↓
2. Inicia sesión (POST /api/usuarios/login)
        ↓
3. Obtén usuario por ID (GET /api/usuarios/obtener/{id})
        ↓
4. Obtén usuario por email (GET /api/usuarios/email/{email})
        ↓
5. Lista todos (GET /api/usuarios/listar)
        ↓
6. Prueba errores (datos inválidos)
        ↓
7. Elimina usuario (DELETE /api/usuarios/delete/{id})
```

---

## 🚨 Notas Importantes

⚠️ **Base de Datos en Memoria**: Los datos se pierden al reiniciar la app  
ℹ️ **Swagger se actualiza automáticamente** cuando cambias el código  
💡 **Usa "Try it out"** para probar directamente en el navegador  

---

## 📞 Necesitas Ayuda?

| Pregunta | Respuesta |
|----------|----------|
| ¿Cómo ejecuto? | Ver [QUICK_START.md](QUICK_START.md) |
| ¿Cómo uso Swagger? | Ver [SWAGGER_GUIDE.md](SWAGGER_GUIDE.md) |
| ¿Ejemplos CURL? | Ver [CURL_EXAMPLES.md](CURL_EXAMPLES.md) |
| ¿Qué cambió? | Ver [IMPLEMENTACION.md](IMPLEMENTACION.md) |
| ¿Toda la documentación? | Ver [API_DOCUMENTATION.md](API_DOCUMENTATION.md) |

---

## 🔗 Enlaces Útiles

- [Swagger/OpenAPI Docs](https://swagger.io/docs/)
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Springdoc OpenAPI](https://springdoc.org/)

---

## ✨ Resumen

Tu API ahora tiene:
- 📊 **Documentación visual completa**
- 🧪 **Interfaz para probar endpoints**
- 📝 **Ejemplos de éxito y error**
- 🎨 **Interfaz Swagger UI intuitiva**
- 🔄 **Documentación autogenerada**

---

## 🚀 ¡Comienza Ahora!

1. Ejecuta: `.\gradlew.bat bootRun`
2. Abre: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
3. ¡Disfruta! 🎉

---

**Última actualización**: 2026-01-15  
**Estado**: ✅ Completado y Listo
