# 🧪 Ejemplos de Testing con CURL

Esta guía contiene comandos `curl` para probar todos los endpoints de la API directamente desde la terminal.

## ⚙️ Configuración Inicial

```bash
# Variables útiles (ajusta según tus datos)
BASE_URL="http://localhost:8080/api/usuarios"
USER_ID="1"
EMAIL="test@example.com"
```

---

## 1️⃣ REGISTRO DE USUARIO

### ✅ Caso de Éxito

```bash
curl -X POST "$BASE_URL/registro" \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "juan_perez",
    "email": "juan@example.com",
    "contrasena": "password123"
  }'
```

**Respuesta esperada (201 Created)**:
```json
{
  "success": true,
  "mensaje": "Usuario registrado exitosamente",
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

### ❌ Caso de Error - Email Duplicado

```bash
curl -X POST "$BASE_URL/registro" \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "juan_perez",
    "email": "juan@example.com",
    "contrasena": "password123"
  }'
```

**Respuesta esperada (400 Bad Request)**:
```json
{
  "success": false,
  "mensaje": "El email ya está registrado",
  "datos": null
}
```

### ❌ Caso de Error - Datos Inválidos

```bash
curl -X POST "$BASE_URL/registro" \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "",
    "email": "email-invalido",
    "contrasena": "123"
  }'
```

---

## 2️⃣ LOGIN

### ✅ Caso de Éxito

```bash
curl -X POST "$BASE_URL/login" \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "juan_perez",
    "contrasena": "password123"
  }'
```

**Respuesta esperada (200 OK)**:
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

### ❌ Caso de Error - Contraseña Incorrecta

```bash
curl -X POST "$BASE_URL/login" \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "juan_perez",
    "contrasena": "wrongpassword"
  }'
```

**Respuesta esperada (401 Unauthorized)**:
```json
{
  "success": false,
  "mensaje": "Nombre de usuario o contraseña incorrectos",
  "datos": null
}
```

### ❌ Caso de Error - Usuario No Existe

```bash
curl -X POST "$BASE_URL/login" \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "usuario_inexistente",
    "contrasena": "password123"
  }'
```

---

## 3️⃣ OBTENER USUARIO POR ID

### ✅ Caso de Éxito

```bash
curl -X GET "$BASE_URL/obtener/1"
```

**Respuesta esperada (200 OK)**:
```json
{
  "success": true,
  "mensaje": "Usuario obtenido",
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

### ❌ Caso de Error - Usuario No Encontrado

```bash
curl -X GET "$BASE_URL/obtener/9999"
```

**Respuesta esperada (404 Not Found)**:
```json
{
  "success": false,
  "mensaje": "Usuario no encontrado",
  "datos": null
}
```

---

## 4️⃣ OBTENER USUARIO POR EMAIL

### ✅ Caso de Éxito

```bash
curl -X GET "$BASE_URL/email/juan@example.com"
```

**Respuesta esperada (200 OK)**:
```json
{
  "success": true,
  "mensaje": "Usuario obtenido",
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

### ❌ Caso de Error - Email No Encontrado

```bash
curl -X GET "$BASE_URL/email/nodexiste@example.com"
```

**Respuesta esperada (404 Not Found)**:
```json
{
  "success": false,
  "mensaje": "Usuario no encontrado",
  "datos": null
}
```

---

## 5️⃣ LISTAR TODOS LOS USUARIOS

### ✅ Caso de Éxito

```bash
curl -X GET "$BASE_URL/listar"
```

**Respuesta esperada (200 OK)**:
```json
{
  "success": true,
  "mensaje": "Usuarios obtenidos",
  "datos": [
    {
      "id": 1,
      "nombreUsuario": "juan_perez",
      "email": "juan@example.com",
      "activo": true,
      "creadoEn": "2026-01-15T10:30:00",
      "actualizadoEn": "2026-01-15T10:30:00"
    },
    {
      "id": 2,
      "nombreUsuario": "maria_gonzalez",
      "email": "maria@example.com",
      "activo": true,
      "creadoEn": "2026-01-15T11:00:00",
      "actualizadoEn": "2026-01-15T11:00:00"
    }
  ]
}
```

---

## 6️⃣ ELIMINAR USUARIO

### ✅ Caso de Éxito

```bash
curl -X DELETE "$BASE_URL/delete/1"
```

**Respuesta esperada (200 OK)**:
```json
{
  "success": true,
  "mensaje": "Usuario eliminado exitosamente",
  "datos": null
}
```

### ❌ Caso de Error - Usuario No Encontrado

```bash
curl -X DELETE "$BASE_URL/delete/9999"
```

**Respuesta esperada (404 Not Found)**:
```json
{
  "success": false,
  "mensaje": "Usuario no encontrado",
  "datos": null
}
```

---

## 📊 Script Automatizado de Prueba Completa

Guarda este script como `test_api.sh` y ejecuta con `bash test_api.sh`:

```bash
#!/bin/bash

BASE_URL="http://localhost:8080/api/usuarios"

echo "🚀 Iniciando pruebas de API..."
echo ""

# 1. Registrar usuario
echo "1️⃣  Registrando nuevo usuario..."
curl -X POST "$BASE_URL/registro" \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "test_user_'$(date +%s)'",
    "email": "test_'$(date +%s)'@example.com",
    "contrasena": "password123"
  }' | jq .

echo ""
echo ""

# 2. Login
echo "2️⃣  Intentando login..."
curl -X POST "$BASE_URL/login" \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "test_user_'$(date +%s)'",
    "contrasena": "password123"
  }' | jq .

echo ""
echo ""

# 3. Listar usuarios
echo "3️⃣  Listando todos los usuarios..."
curl -X GET "$BASE_URL/listar" | jq .

echo ""
echo "✅ Pruebas completadas!"
```

---

## 🔍 Herramientas Alternativas

### Postman
Importa la colección desde:
```
GET http://localhost:8080/v3/api-docs
```

### Insomnia
URL de importación:
```
http://localhost:8080/v3/api-docs
```

### PowerShell (en Windows)

```powershell
# Registrar usuario
Invoke-WebRequest -Uri "http://localhost:8080/api/usuarios/registro" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body @{
    nombreUsuario="juan_perez"
    email="juan@example.com"
    contrasena="password123"
  } | ConvertTo-Json
```

---

## 📝 Guía de Códigos HTTP

| Código | Significado | Cuando ocurre |
|--------|-------------|---------------|
| 200 | OK | Operación exitosa (GET, UPDATE, DELETE) |
| 201 | Created | Usuario registrado exitosamente |
| 400 | Bad Request | Datos inválidos o email duplicado |
| 401 | Unauthorized | Credenciales incorrectas |
| 404 | Not Found | Recurso no encontrado |
| 500 | Server Error | Error interno del servidor |

---

¡Usa estos ejemplos para probar y validar tu API! 🎉
