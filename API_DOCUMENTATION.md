# 📚 Documentación de la API - Índice Principal

¡Tu API ahora tiene una **interfaz visual interactiva** para probar todos los endpoints! 🎉

## 🚀 Inicio Rápido

👉 **[QUICK_START.md](QUICK_START.md)** - Cómo ejecutar y acceder a Swagger en 2 minutos

---

## 📖 Guías Completas

### 1. **[SWAGGER_GUIDE.md](SWAGGER_GUIDE.md)** 📊
Guía completa sobre cómo usar la interfaz visual Swagger para:
- ✅ Acceder a Swagger UI
- ✅ Explorar todos los endpoints
- ✅ Probar cada endpoint interactivamente
- ✅ Ver ejemplos de respuestas exitosas y errores
- ✅ Entender el flujo recomendado de pruebas

### 2. **[CURL_EXAMPLES.md](CURL_EXAMPLES.md)** 🧪
Ejemplos de comandos CURL para probar desde la terminal:
- ✅ Códigos exactos para cada endpoint
- ✅ Casos de éxito y error
- ✅ Respuestas esperadas completas
- ✅ Scripts automatizados de prueba

---

## 🎯 Acceso Directo

| Recurso | URL |
|---------|-----|
| **Swagger UI** (Interfaz Visual) | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| **OpenAPI JSON** (Especificación) | [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) |
| **H2 Console** (Base de Datos) | [http://localhost:8080/h2-console](http://localhost:8080/h2-console) |

---

## 📝 Qué se ha Configurado

### ✅ Dependencias Agregadas
- `springdoc-openapi-starter-webmvc-ui` v2.0.2 - Para Swagger/OpenAPI

### ✅ Configuración
- **OpenApiConfig.java** - Configuración de OpenAPI con información de la API
- **Anotaciones en controladores** - @Operation, @ApiResponse para documentación
- **Anotaciones en DTOs** - @Schema con descripciones y ejemplos

### ✅ Endpoints Documentados
1. **POST /api/usuarios/registro** - Registrar nuevo usuario
2. **POST /api/usuarios/login** - Autenticar usuario
3. **GET /api/usuarios/obtener/{id}** - Obtener usuario por ID
4. **GET /api/usuarios/email/{email}** - Obtener usuario por email
5. **GET /api/usuarios/listar** - Listar todos los usuarios
6. **DELETE /api/usuarios/delete/{id}** - Eliminar usuario

---

## 🔄 Flujo de Uso Típico

```
1. Abre QUICK_START.md
        ↓
2. Ejecuta: gradlew.bat bootRun
        ↓
3. Abre: http://localhost:8080/swagger-ui.html
        ↓
4. Prueba los endpoints directamente en el navegador
        ↓
5. O usa CURL_EXAMPLES.md para probar desde terminal
```

---

## 📊 Vista de Swagger

Cuando abras Swagger UI verás:
- 📋 Lista de todos los endpoints agrupados por etiqueta
- 📝 Descripción detallada de cada endpoint
- 📨 Campos de entrada con ejemplos
- ✅ Ejemplos de respuestas exitosas (200, 201)
- ❌ Ejemplos de respuestas de error (400, 401, 404, 500)
- 🧪 Botón "Try it out" para probar interactivamente

---

## 🎓 Aprende Más

### Cambiar de Base de Datos
Edita `src/main/resources/application.properties`:
```properties
# Cambiar de H2 (en memoria) a MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/tesis_db
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

### Agregar Más Endpoints
1. Crea un nuevo método en el controlador
2. Agrega anotaciones `@Operation` y `@ApiResponses`
3. La documentación en Swagger se actualiza automáticamente

### Personalizar Swagger
Edita `OpenApiConfig.java` para cambiar:
- Título de la API
- Versión
- Descripción
- Información de contacto
- Servidores

---

## 💡 Consejos de Testing

✅ **Comienza por el registro** - Crea un usuario de prueba
✅ **Guarda el ID** - Lo necesitarás para otras pruebas
✅ **Prueba casos de error** - Intentiona operaciones inválidas
✅ **Observa códigos HTTP** - Ayudan a entender el resultado
✅ **Lee los mensajes** - La API proporciona mensajes descriptivos

---

## 🐛 Troubleshooting

### "Port 8080 already in use"
```bash
# Windows - Buscar proceso en puerto 8080
netstat -ano | findstr :8080

# Matar el proceso (reemplaza PID)
taskkill /PID <PID> /F
```

### "Swagger UI no carga"
1. Verifica que la aplicación esté corriendo: `http://localhost:8080/actuator/health`
2. Espera a que el build de Gradle termine
3. Recarga la página (Ctrl+F5)

### "Error de compilación"
```bash
# Limpia el build anterior
./gradlew clean build
```

---

## 🚀 Próximas Mejoras

- [ ] Agregar JWT para autenticación segura
- [ ] Implementar roles y permisos
- [ ] Agregar más validaciones
- [ ] Documentar modelos adicionales
- [ ] Crear tests unitarios
- [ ] Agregar rate limiting
- [ ] Implementar paginación

---

## 📞 Soporte

Para más información sobre:
- **Swagger/OpenAPI**: [https://swagger.io/](https://swagger.io/)
- **Spring Boot**: [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
- **Springdoc OpenAPI**: [https://springdoc.org/](https://springdoc.org/)

---

**¡Listo para comenzar?** → [Ver QUICK_START.md](QUICK_START.md)

¡Disfruta explorando tu API! 🎉
