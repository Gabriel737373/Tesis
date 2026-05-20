#!/bin/bash

echo "╔════════════════════════════════════════════════════════════╗"
echo "║     Verificación de Estructura de Microservicios          ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""

MISSING_ITEMS=0

# Verificar backend
echo "📦 Backend Structure:"
if [ -d "backend/user-service" ]; then
    echo "  ✅ backend/user-service"
else
    echo "  ❌ backend/user-service"
    ((MISSING_ITEMS++))
fi

if [ -f "backend/user-service/build.gradle.kts" ]; then
    echo "  ✅ build.gradle.kts"
else
    echo "  ❌ build.gradle.kts"
    ((MISSING_ITEMS++))
fi

if [ -f "backend/user-service/src/main/kotlin/com/__01/APP/Tesis/Application.kt" ]; then
    echo "  ✅ Application.kt"
else
    echo "  ❌ Application.kt"
    ((MISSING_ITEMS++))
fi

# Verificar frontend
echo ""
echo "🎨 Frontend Structure:"
if [ -d "frontend" ]; then
    echo "  ✅ frontend/"
else
    echo "  ❌ frontend/"
    ((MISSING_ITEMS++))
fi

if [ -f "frontend/package.json" ]; then
    echo "  ✅ package.json"
else
    echo "  ❌ package.json"
    ((MISSING_ITEMS++))
fi

if [ -d "frontend/src" ]; then
    echo "  ✅ src/"
else
    echo "  ❌ src/"
    ((MISSING_ITEMS++))
fi

if [ -d "frontend/public" ]; then
    echo "  ✅ public/"
else
    echo "  ❌ public/"
    ((MISSING_ITEMS++))
fi

# Verificar Docker
echo ""
echo "🐳 Docker Configuration:"
if [ -f "docker-compose.yml" ]; then
    echo "  ✅ docker-compose.yml"
else
    echo "  ❌ docker-compose.yml"
    ((MISSING_ITEMS++))
fi

if [ -f "backend/user-service/Dockerfile" ]; then
    echo "  ✅ backend/user-service/Dockerfile"
else
    echo "  ❌ backend/user-service/Dockerfile"
    ((MISSING_ITEMS++))
fi

if [ -f "frontend/Dockerfile" ]; then
    echo "  ✅ frontend/Dockerfile"
else
    echo "  ❌ frontend/Dockerfile"
    ((MISSING_ITEMS++))
fi

# Verificar documentación
echo ""
echo "📚 Documentation:"
if [ -f "README.md" ]; then
    echo "  ✅ README.md"
else
    echo "  ❌ README.md"
    ((MISSING_ITEMS++))
fi

if [ -f ".env.example" ]; then
    echo "  ✅ .env.example"
else
    echo "  ❌ .env.example"
    ((MISSING_ITEMS++))
fi

# Resultado final
echo ""
echo "╔════════════════════════════════════════════════════════════╗"
if [ $MISSING_ITEMS -eq 0 ]; then
    echo "║  ✅ Estructura verificada correctamente                      ║"
else
    echo "║  ❌ Se encontraron $MISSING_ITEMS elemento(s) faltante(s)                ║"
fi
echo "╚════════════════════════════════════════════════════════════╝"
echo ""

# Mostrar estructura de directorios
echo "📂 Estructura de directorios actual:"
echo ""
tree -L 2 -I '.git|node_modules|build|dist' 2>/dev/null || find . -maxdepth 2 -type d | grep -v '\.git\|node_modules\|build\|dist' | sort

exit $MISSING_ITEMS
