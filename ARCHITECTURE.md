# Arquitectura de Microservicios - APP Tesis

## Visión General

La aplicación está diseñada siguiendo el patrón de **arquitectura de microservicios** donde:

- Cada servicio es independiente y puede escalar por separado
- Comunicación mediante API REST (HTTP/JSON)
- Base de datos dedicada por servicio (polyglot persistence)
- Deployment en contenedores Docker

## Servicios

### 1. User Service (Backend)
**Ubicación:** `backend/user-service/`

**Responsabilidades:**
- Gestión de usuarios
- Autenticación y autorización
- Validación de datos

**Stack Tecnológico:**
- Spring Boot 4.0.6
- Kotlin 2.2.21
- Spring Data JPA
- Spring Security
- H2 (desarrollo) / MySQL (producción)

**API Endpoints:**
```
GET    /api/usuarios           - Listar usuarios
GET    /api/usuarios/{id}      - Obtener usuario específico
POST   /api/usuarios           - Crear usuario
PUT    /api/usuarios/{id}      - Actualizar usuario
DELETE /api/usuarios/{id}      - Eliminar usuario
```

**Puertos:**
- Desarrollo: 8080
- Docker: 8080

### 2. Frontend (React)
**Ubicación:** `frontend/`

**Responsabilidades:**
- Interfaz de usuario
- Comunicación con servicios backend
- Gestión de estado

**Stack Tecnológico:**
- React 18
- Axios (HTTP client)
- CSS3

**Estructura de Carpetas:**
```
frontend/
├── public/              # Archivos estáticos
├── src/
│   ├── components/      # Componentes reutilizables
│   ├── pages/          # Páginas principales
│   ├── services/       # Servicios API
│   ├── hooks/          # Hooks personalizados
│   ├── context/        # Context API para estado global
│   ├── styles/         # Estilos globales
│   └── utils/          # Funciones utilitarias
└── __tests__/          # Tests

```

**Puertos:**
- Desarrollo: 3000
- Docker: 3000

### 3. Database (MySQL)
**Ubicación:** Docker service

**Responsabilidades:**
- Almacenamiento de datos
- Persistencia

**Puertos:**
- Docker: 3306

## Flujo de Datos

```
Usuario -> Frontend (React)
              ↓
        Axios/HTTP
              ↓
        Backend (Spring Boot)
              ↓
        Spring Data JPA
              ↓
        MySQL Database
```

## Comunicación entre Servicios

### Frontend → Backend
- **Método:** HTTP REST
- **Formato:** JSON
- **Autenticación:** Basic Auth (configurable para JWT)
- **Timeout:** 5000ms

## Deployment

### Desarrollo Local
```bash
# Terminal 1: Backend
cd backend/user-service
./gradlew bootRun

# Terminal 2: Frontend
cd frontend
npm install
npm start
```

### Docker Compose
```bash
docker-compose up --build
```

## Variables de Entorno Críticas

```
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/tesis_db
SPRING_DATASOURCE_USERNAME=root
REACT_APP_API_URL=http://localhost:8080
REACT_APP_API_TIMEOUT=5000
```

## Escalabilidad Futura

Para agregar nuevos microservicios:

1. Crear `backend/nuevo-servicio/`
2. Copiar configuración de `user-service`
3. Actualizar `docker-compose.yml`
4. Configurar rutas en frontend
5. Implementar API Gateway (si es necesario)

## Security

- ✅ Spring Security implementado
- ✅ Validación de entrada (Jakarta Validation)
- ✅ CORS (configurable)
- ⚠️ TODO: JWT Tokens
- ⚠️ TODO: Rate Limiting
- ⚠️ TODO: API Gateway

## Monitoreo

Servicios recomendados:
- Prometheus (métricas)
- ELK Stack (logs)
- Jaeger (tracing distribuido)

## Referencias

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev)
- [Docker Compose](https://docs.docker.com/compose/)
- [REST API Best Practices](https://restfulapi.net/)
