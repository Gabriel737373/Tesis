# 📚 Documentación Visual de Endpoints con Swagger/OpenAPI

## Acceso a la Interfaz Swagger UI

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación interactiva en:

### **URL Principal**
```
http://localhost:8080/swagger-ui.html
```

### **Documentación en JSON (OpenAPI)**
```
http://localhost:8080/v3/api-docs
```

---

## 🚀 Cómo Usar Swagger para Probar los Endpoints

### 1. **Abrir Swagger UI**
   - Navega a `http://localhost:8080/swagger-ui.html`
   - Verás una interfaz interactiva con todos los endpoints documentados

### 2. **Explorar Endpoints**
   - Los endpoints están organizados por etiqueta: **"Gestión de Usuarios"**
   - Cada endpoint muestra:
     - 📝 **Descripción**: Qué hace el endpoint
     - 📨 **Parámetros**: Datos necesarios para la solicitud
     - ✅ **Respuestas Exitosas**: Ejemplos con datos de ejemplo
     - ❌ **Respuestas de Error**: Posibles errores y sus causas

### 3. **Probar un Endpoint**
   - Haz clic en el endpoint que deseas probar
   - Se expandirá mostrando más detalles
   - Haz clic en el botón **"Try it out"**
   - Completa los campos requeridos con datos de prueba
   - Haz clic en **"Execute"**
   - Verás la respuesta en tiempo real

---

## 📋 Endpoints Disponibles

### 1. **POST /api/usuarios/registro**
**Descripción**: Registra un nuevo usuario

**Casos de Éxito (201 Created)**:
```json
{
  "success": true,
  "mensaje": "Usuario registrado exitosamente",
  "datos": {
    "id": 1,
    "nombreUsuario": "maria_gonzalez",
    "email": "maria@example.com",
    "activo": true,
    "creadoEn": "2026-01-15T10:30:00",
    "actualizadoEn": "2026-01-15T10:30:00"
  }
}
```

**Casos de Error**:
- **400 Bad Request**: Email duplicado o datos inválidos
  ```json
  {
    "success": false,
    "mensaje": "El email ya está registrado",
    "datos": null
  }
  ```
- **500 Internal Server Error**: Error del servidor
  ```json
  {
    "success": false,
    "mensaje": "Error interno del servidor",
    "datos": null
  }
  ```

**Datos de Prueba**:
```json
{
  "nombreUsuario": "juan_perez",
  "email": "juan@example.com",
  "contrasena": "password123"
}
```

---

### 2. **POST /api/usuarios/login**
**Descripción**: Autentica un usuario

**Caso de Éxito (200 OK)**:
```json
{
  "success": true,
  "mensaje": "Login exitoso",
  "datos": {
    "id": 1,
    "nombreUsuario": "juan_perez",
    "email": "juan@example.com",
    "activo": true,
    "creadoEn": "2026-01-10T08:00:00",
    "actualizadoEn": "2026-01-15T10:30:00"
  }
}
```

**Casos de Error**:
- **401 Unauthorized**: Credenciales inválidas
  ```json
  {
    "success": false,
    "mensaje": "Nombre de usuario o contraseña incorrectos",
    "datos": null
  }
  ```

**Datos de Prueba**:
```json
{
  "nombreUsuario": "juan_perez",
  "contrasena": "password123"
}
```

---

### 3. **GET /api/usuarios/obtener/{id}**
**Descripción**: Obtiene un usuario por su ID

**Parámetro**:
- `id` (path parameter): ID del usuario (ej: 1)

**Caso de Éxito (200 OK)**:
```json
{
  "success": true,
  "mensaje": "Usuario obtenido",
  "datos": {
    "id": 1,
    "nombreUsuario": "juan_perez",
    "email": "juan@example.com",
    "activo": true,
    "creadoEn": "2026-01-10T08:00:00",
    "actualizadoEn": "2026-01-15T10:30:00"
  }
}
```

**Caso de Error**:
- **404 Not Found**: Usuario no encontrado
  ```json
  {
    "success": false,
    "mensaje": "Usuario no encontrado",
    "datos": null
  }
  ```

---

### 4. **GET /api/usuarios/email/{email}**
**Descripción**: Obtiene un usuario por su email

**Parámetro**:
- `email` (path parameter): Email del usuario (ej: juan@example.com)

**Caso de Éxito (200 OK)**:
```json
{
  "success": true,
  "mensaje": "Usuario obtenido",
  "datos": {
    "id": 2,
    "nombreUsuario": "maria_gonzalez",
    "email": "maria@example.com",
    "activo": true,
    "creadoEn": "2026-01-15T10:30:00",
    "actualizadoEn": "2026-01-15T10:30:00"
  }
}
```

**Caso de Error**:
- **404 Not Found**: Usuario no encontrado
  ```json
  {
    "success": false,
    "mensaje": "Usuario no encontrado",
    "datos": null
  }
  ```

---

### 5. **GET /api/usuarios/listar**
**Descripción**: Obtiene la lista de todos los usuarios

**Caso de Éxito (200 OK)**:
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
      "creadoEn": "2026-01-10T08:00:00",
      "actualizadoEn": "2026-01-15T10:30:00"
    },
    {
      "id": 2,
      "nombreUsuario": "maria_gonzalez",
      "email": "maria@example.com",
      "activo": true,
      "creadoEn": "2026-01-15T10:30:00",
      "actualizadoEn": "2026-01-15T10:30:00"
    }
  ]
}
```

---

### 6. **DELETE /api/usuarios/delete/{id}**
**Descripción**: Elimina un usuario

**Parámetro**:
- `id` (path parameter): ID del usuario a eliminar (ej: 1)

**Caso de Éxito (200 OK)**:
```json
{
  "success": true,
  "mensaje": "Usuario eliminado exitosamente",
  "datos": null
}
```

**Casos de Error**:
- **404 Not Found**: Usuario no encontrado
  ```json
  {
    "success": false,
    "mensaje": "Usuario no encontrado",
    "datos": null
  }
  ```

---

## 🔧 Configuración de Swagger

La configuración se encuentra en:
- **Clase**: `com.__01.APP.Tesis.config.OpenApiConfig`
- **Ubicación**: `src/main/java/com/__01/APP/Tesis/config/OpenApiConfig.java`

Aquí se define:
- Título y descripción de la API
- Información de contacto
- URL del servidor

---

## 🎯 Flujo de Prueba Recomendado

### 1. **Primero, Registra un Usuario**
   - Endpoint: `POST /api/usuarios/registro`
   - Usa datos de prueba válidos
   - Obtendrás un usuario con ID (guárdalo para las siguientes pruebas)

### 2. **Luego, Prueba el Login**
   - Endpoint: `POST /api/usuarios/login`
   - Usa el mismo `nombreUsuario` y `contrasena` del paso 1
   - Verifica que obtengas los datos del usuario

### 3. **Obtén el Usuario por ID**
   - Endpoint: `GET /api/usuarios/obtener/{id}`
   - Usa el ID obtenido en el paso 1
   - Verifica que obtengas el mismo usuario

### 4. **Obtén el Usuario por Email**
   - Endpoint: `GET /api/usuarios/email/{email}`
   - Usa el email del usuario creado
   - Verifica que obtengas el mismo usuario

### 5. **Lista Todos los Usuarios**
   - Endpoint: `GET /api/usuarios/listar`
   - Verifica que tu usuario aparezca en la lista

### 6. **Finalmente, Prueba Errores**
   - **Login con credenciales inválidas**: Usuario/contraseña incorrectos
   - **Obtener usuario inexistente**: ID o email que no exista
   - **Registrar con email duplicado**: Usa un email ya registrado

### 7. **Elimina el Usuario (Opcional)**
   - Endpoint: `DELETE /api/usuarios/delete/{id}`
   - Usa el ID de un usuario existente
   - Verifica que el usuario se elimine correctamente

---

## 📊 Diagrama de Respuesta

Todas las respuestas siguen este formato:

```
{
  "success": boolean,      // true para éxito, false para error
  "mensaje": string,       // Descripción del resultado
  "datos": object|null     // Datos de respuesta (null en casos de error)
}
```

---

## 💡 Consejos

1. **Guarda IDs**: Cuando registres un usuario, copia el ID para usarlo en otras pruebas
2. **Prueba Errores**: Intenta operaciones inválidas para ver cómo responde la API
3. **Verifica Campos**: Observa los tipos de datos en las respuestas
4. **Lee Descripciones**: Swagger proporciona descripciones detalladas en cada endpoint

---

## 🚀 Próximos Pasos

- Implementar autenticación JWT
- Agregar roles y permisos
- Documentar más endpoints según sea necesario
- Agregar validaciones más estrictas

¡Disfruta usando Swagger para explorar y probar tu API! 🎉
