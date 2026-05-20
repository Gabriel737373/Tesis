# APP Tesis - Arquitectura de Microservicios

Proyecto de tesis que implementa una arquitectura de microservicios con Spring Boot (Kotlin) en el backend y React en el frontend.

## Estructura del Proyecto

```
tesis/
├── backend/
│   ├── user-service/          # Servicio de usuarios (Spring Boot + Kotlin)
│   │   ├── src/
│   │   ├── build.gradle.kts
│   │   ├── Dockerfile
│   │   └── ...
│   └── shared/                # Código compartido entre servicios (si aplica)
│
├── frontend/                  # Aplicación React
│   ├── public/
│   ├── src/
│   │   ├── components/        # Componentes reutilizables
│   │   ├── pages/            # Páginas principales
│   │   ├── services/         # Servicios API
│   │   ├── hooks/            # Hooks personalizados
│   │   ├── context/          # Context API
│   │   ├── styles/           # Estilos globales
│   │   └── utils/            # Utilidades
│   ├── package.json
│   ├── Dockerfile
│   └── README.md
│
├── docker-compose.yml        # Orquestación de servicios
├── .env.example             # Variables de entorno de ejemplo
└── README.md               # Este archivo

## Requisitos Previos

- **Java 21** o superior
- **Node.js 18** o superior  
- **Docker** y **Docker Compose**
- **Gradle** (se incluye gradle wrapper)

## Instalación y Ejecución

### Opción 1: Desarrollo Local

#### Backend (User Service)
```bash
cd backend/user-service
./gradlew clean build
./gradlew bootRun
```
El servicio estará disponible en: `http://localhost:8080`

#### Frontend
```bash
cd frontend
npm install
npm start
```
La aplicación estará disponible en: `http://localhost:3000`

### Opción 2: Docker Compose

```bash
docker-compose up --build
```

Servicios disponibles:
- **Frontend**: http://localhost:3000
- **User Service**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console
- **MySQL**: localhost:3306

## Credenciales por Defecto

**Backend (Spring Security)**
- Usuario: `admin`
- Contraseña: `admin123`

**MySQL**
- Usuario: `root`
- Contraseña: `rootpass`

## API Endpoints

### Usuarios
- `GET /api/usuarios` - Obtener todos los usuarios
- `GET /api/usuarios/{id}` - Obtener usuario por ID
- `POST /api/usuarios` - Crear nuevo usuario
- `PUT /api/usuarios/{id}` - Actualizar usuario
- `DELETE /api/usuarios/{id}` - Eliminar usuario

## Componentes del Proyecto

### Backend
- **Spring Boot 4.0.6**
- **Kotlin 2.2.21**
- **Spring Data JPA**
- **Spring Security**
- **Validación con Jakarta**
- **Base de datos: H2 (desarrollo) / MySQL (producción)**

### Frontend
- **React 18**
- **Axios** para llamadas HTTP
- **CSS3** para estilos

## Desarrollo

Para agregar nuevos microservicios:

1. Crear carpeta en `backend/nuevo-servicio`
2. Usar como template el `user-service`
3. Actualizar `docker-compose.yml`
4. Actualizar este README

## Testing

### Backend
```bash
cd backend/user-service
./gradlew test
```

### Frontend
```bash
cd frontend
npm test
```

## Variables de Entorno

Ver `.env.example` para todas las variables disponibles.

## Contribuciones

1. Crear rama feature: `git checkout -b feature/mi-feature`
2. Commit cambios: `git commit -am 'Add feature'`
3. Push: `git push origin feature/mi-feature`
4. Abrir Pull Request

## Licencia

Proyecto de tesis - 2026
