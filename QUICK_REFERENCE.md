# 📋 Referencia Rápida - Swagger API

## ⚡ Inicio Rápido (Copiar y Pegar)

```bash
# 1. Abre terminal en backend/user-service
cd backend/user-service

# 2. Ejecuta la aplicación
.\gradlew.bat bootRun

# 3. Abre en navegador
http://localhost:8080/swagger-ui.html
```

---

## 📡 Endpoints Rápidos

### 📝 Registrar Usuario
**POST** `http://localhost:8080/api/usuarios/registro`
```json
{
  "nombreUsuario": "juan_perez",
  "email": "juan@example.com",
  "contrasena": "password123"
}
```
**Respuesta**: 201 Created ✅ / 400 Bad Request ❌

---

### 🔐 Login
**POST** `http://localhost:8080/api/usuarios/login`
```json
{
  "nombreUsuario": "juan_perez",
  "contrasena": "password123"
}
```
**Respuesta**: 200 OK ✅ / 401 Unauthorized ❌

---

### 👤 Obtener Usuario por ID
**GET** `http://localhost:8080/api/usuarios/obtener/1`
**Respuesta**: 200 OK ✅ / 404 Not Found ❌

---

### 📧 Obtener Usuario por Email
**GET** `http://localhost:8080/api/usuarios/email/juan@example.com`
**Respuesta**: 200 OK ✅ / 404 Not Found ❌

---

### 📋 Listar Todos los Usuarios
**GET** `http://localhost:8080/api/usuarios/listar`
**Respuesta**: 200 OK ✅ / 500 Error ❌

---

### 🗑️ Eliminar Usuario
**DELETE** `http://localhost:8080/api/usuarios/delete/1`
**Respuesta**: 200 OK ✅ / 404 Not Found ❌

---

## 🐚 Comandos CURL Rápidos

```bash
# Registrar
curl -X POST "http://localhost:8080/api/usuarios/registro" \
  -H "Content-Type: application/json" \
  -d '{"nombreUsuario":"test","email":"test@example.com","contrasena":"123456"}'

# Login
curl -X POST "http://localhost:8080/api/usuarios/login" \
  -H "Content-Type: application/json" \
  -d '{"nombreUsuario":"test","contrasena":"123456"}'

# Obtener por ID
curl "http://localhost:8080/api/usuarios/obtener/1"

# Listar todos
curl "http://localhost:8080/api/usuarios/listar"

# Eliminar
curl -X DELETE "http://localhost:8080/api/usuarios/delete/1"
```

---

## 🌐 URLs Principales

| URL | Descripción |
|-----|-------------|
| http://localhost:8080/swagger-ui.html | 📊 Swagger UI (Interfaz Visual) |
| http://localhost:8080/v3/api-docs | 📄 OpenAPI JSON |
| http://localhost:8080/h2-console | 💾 Base de Datos H2 |
| http://localhost:8080/actuator/health | ❤️ Estado de Salud |

---

## 📊 Códigos de Respuesta

| Código | Significado | Ejemplo |
|--------|-------------|---------|
| 200 | OK | GET exitoso |
| 201 | Created | Usuario registrado |
| 400 | Bad Request | Datos inválidos |
| 401 | Unauthorized | Credenciales incorrectas |
| 404 | Not Found | Recurso no existe |
| 500 | Error | Error del servidor |

---

## 💾 Estructura de Respuesta

```json
{
  "success": true/false,     // Resultado
  "mensaje": "...",          // Descripción
  "datos": {...}             // Datos (null si error)
}
```

---

## 🔧 Troubleshooting

```bash
# Puerto en uso
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Limpiar build
.\gradlew.bat clean

# Rebuild
.\gradlew.bat build -x test
```

---

## 📚 Documentación Completa

- [README.md](README.md) - Inicio
- [QUICK_START.md](QUICK_START.md) - 2 minutos
- [SWAGGER_GUIDE.md](SWAGGER_GUIDE.md) - Completo
- [CURL_EXAMPLES.md](CURL_EXAMPLES.md) - Ejemplos
- [IMPLEMENTACION.md](IMPLEMENTACION.md) - Detalles

---

## ✨ Tipografía de URLs

```
Base:     http://localhost:8080
Grupo:    /api/usuarios
Recurso:  /registro, /login, /obtener/{id}, etc.
Completo: http://localhost:8080/api/usuarios/registro
```

---

## 🎯 Próximo Paso

👉 **Abre Swagger UI**: http://localhost:8080/swagger-ui.html

¡Listo! 🚀
