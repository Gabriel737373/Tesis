# 🚀 Inicio Rápido - Swagger Visual

## Paso 1: Compila y Ejecuta la Aplicación

### En Windows (Terminal de PowerShell o CMD)

```bash
cd backend/user-service
./gradlew.bat bootRun
```

### En Linux/Mac

```bash
cd backend/user-service
./gradlew bootRun
```

## Paso 2: Accede a Swagger UI

Abre tu navegador y ve a:

### **[Swagger UI - http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

```
http://localhost:8080/swagger-ui.html
```

## ✅ Verificación de Inicio Correcto

Cuando la aplicación esté ejecutándose correctamente, deberías ver:

- ✅ **En la consola**: Mensajes de inicio de Spring Boot sin errores
- ✅ **Puerto 8080**: Servidor escuchando en `http://localhost:8080`
- ✅ **Swagger UI**: Página interactiva con todos los endpoints visibles

## 🎯 Primeras Pruebas

1. **Abre Swagger UI**
2. **Expande "Gestión de Usuarios"**
3. **Haz clic en "POST /api/usuarios/registro"**
4. **Presiona "Try it out"**
5. **Copia este JSON en el campo de request**:
   ```json
   {
     "nombreUsuario": "test_user",
     "email": "test@example.com",
     "contrasena": "password123"
   }
   ```
6. **Haz clic en "Execute"**
7. **Verás la respuesta exitosa (201 Created)**

## 🔗 Enlaces Útiles

| Recurso | URL |
|---------|-----|
| Swagger UI (Documentación) | http://localhost:8080/swagger-ui.html |
| OpenAPI JSON | http://localhost:8080/v3/api-docs |
| Consola H2 | http://localhost:8080/h2-console |

## 📝 Notas

- La base de datos está configurada en **H2 en memoria** (se reinicia cada vez que inicia la app)
- Todos los usuarios registrados se perderán cuando se reinicie la aplicación
- Para persistencia permanente, cambiar a MySQL en `application.properties`

---

**¿Necesitas ayuda?** Ver [SWAGGER_GUIDE.md](SWAGGER_GUIDE.md) para documentación completa.
