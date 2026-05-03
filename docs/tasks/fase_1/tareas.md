# Fase 1: Inicialización del Proyecto Backend

**Objetivo:** Establecer la base del proyecto utilizando Kotlin y configurar la estructura base orientada a la Arquitectura Hexagonal (Ports and Adapters).

## Tareas

- [x] **1.1. Inicializar Proyecto Kotlin/Gradle:** Crear un nuevo proyecto base de Kotlin para el backend.
- [x] **1.2. Configurar Infraestructura Web (Ktor):** Configurar Ktor Server con los módulos necesarios (Routing, ContentNegotiation con Kotlinx Serialization, CORS). Dentro del diseño hexagonal, Ktor es estrictamente un Adaptador de Entrada (Inbound Adapter).
- [x] **1.3. Definir Estructura de Paquetes Hexagonal:** Crear la siguiente jerarquía de paquetes principal:
  - `domain`: Clases del dominio, modelos puros y puertos (interfaces) de entrada y salida. Sin ninguna dependencia de Ktor o frameworks externos.
  - `application`: Casos de uso que orquestan el flujo de negocio llamando a los puertos del dominio.
  - `infrastructure`: Implementación de adaptadores. Incluye `adapters/in` (Controladores/Rutas Ktor) y `adapters/out` (Clientes IA, Repositorios DB).
- [x] **1.4. Configuración de Entornos:** Implementar la gestión de variables de entorno (usando `application.conf` o librerías de `dotenv`) para asegurar que credenciales (API Keys) y configuraciones queden fuera del código fuente.
