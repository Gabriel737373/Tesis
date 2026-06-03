# ⚙️ Configuración del Gradle Wrapper

El archivo `gradle-wrapper.jar` está faltando, pero se descargará automáticamente la primera vez que ejecutes Gradle.

## 🚀 Solución Rápida

### Opción 1: Usar el script gradlew directamente (Recomendado)

```bash
cd backend/user-service
.\gradlew.bat bootRun
```

El script descargará automáticamente Gradle 9.4.1 la primera vez.

### Opción 2: Descargar el Gradle Wrapper JAR manualmente

Si el download automático falla, descarga manualmente:

```powershell
# En PowerShell, en la carpeta backend/user-service
$ProgressPreference = 'SilentlyContinue'
$URL = "https://services.gradle.org/distributions/gradle-9.4.1-bin.zip"
$Path = "$env:GRADLE_USER_HOME\wrapper\dists\gradle-9.4.1-bin"
Invoke-WebRequest -Uri $URL -OutFile "gradle-9.4.1-bin.zip"
Expand-Archive -Path "gradle-9.4.1-bin.zip" -DestinationPath $Path
```

### Opción 3: Instalar Gradle manualmente

Descarga desde: [gradle.org/releases](https://gradle.org/releases)

Luego ejecuta:
```bash
cd backend/user-service
gradle bootRun
```

## ✅ Verificar Instalación

```bash
cd backend/user-service
.\gradlew.bat --version
```

Deberías ver algo como:
```
Gradle 9.4.1
```

## 🎯 Una vez configurado

Simplemente ejecuta:

```bash
# Compilar
.\gradlew.bat clean build

# Ejecutar
.\gradlew.bat bootRun

# Ejecutar tests
.\gradlew.bat test
```

El acceso a Swagger UI será en:
```
http://localhost:8080/swagger-ui.html
```

¡Listo! El wrapper se configurará automáticamente.
