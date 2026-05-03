# Plan de Migración y Mejoras: Backend con Kotlin (Ktor)

Este documento detalla las tareas y fases necesarias para migrar la lógica de Inteligencia Artificial del cliente (Frontend) a un nuevo servidor Backend construido con Kotlin y Ktor, así como la implementación de mejoras arquitectónicas para soportar múltiples proveedores de IA.

## Fase 1: Inicialización del Proyecto Backend
**Objetivo:** Establecer la base del proyecto Ktor y preparar el entorno de desarrollo.

*   [ ] **1.1. Crear estructura del proyecto Ktor:** Inicializar un nuevo proyecto Kotlin usando Gradle (o Maven) con Ktor Server. Configurar los plugins necesarios (Routing, ContentNegotiation, CORS, WebSockets si es necesario).
*   [ ] **1.2. Configuración de Entorno:** Configurar la gestión de variables de entorno (`.env` o `application.conf`) para almacenar de forma segura las API Keys y otros secretos.
*   [ ] **1.3. Estructura de Paquetes (Clean Architecture):** Definir una estructura clara para el proyecto (ej. `domain`, `data`, `presentation/routes`, `di`).

## Fase 2: Capa de Dominio e Interfaces (Agnostic AI)
**Objetivo:** Diseñar la arquitectura para soportar múltiples IAs de manera intercambiable.

*   [ ] **2.1. Definir Modelo de Datos:** Crear las `data classes` en Kotlin equivalentes a `Message`, `SpecSection`, y `SDD` que existen en el frontend.
*   [ ] **2.2. Interfaz `AiProvider`:** Crear una interfaz común (ej. `AiService` o `AiProvider`) que defina métodos abstractos como `generateSddResponse(messages: List<Message>): AiResponse`.
*   [ ] **2.3. Definición de Function Calls:** Abstraer los esquemas de Function Calling (`update_spec_section`, `update_project_name`) para que sean independientes del proveedor específico y se puedan mapear al formato que cada IA necesite.

## Fase 3: Implementación de Proveedores de IA
**Objetivo:** Implementar la lógica concreta para cada proveedor de IA soportado.

*   [ ] **3.1. Proveedor Gemini:** Implementar la interfaz `AiProvider` utilizando el SDK oficial de Google o llamadas REST directas para Gemini 3.1 Pro (migrando la lógica actual de `aiService.ts`).
*   [ ] **3.2. Proveedor OpenAI (Mejora):** Implementar la interfaz usando la API de OpenAI (modelos GPT-4o, etc.).
*   [ ] **3.3. Proveedor Anthropic (Mejora):** Implementar la interfaz usando la API de Claude.
*   [ ] **3.4. Gestor / Factoría de IA:** Crear una clase o patrón factory que determine qué proveedor instanciar según la preferencia del usuario o la configuración.

## Fase 4: Exposición de la API (Ktor Routes)
**Objetivo:** Crear los endpoints que consumirá el frontend.

*   [ ] **4.1. Endpoint de Chat (`POST /api/chat`):** Crear el endpoint que reciba el historial de mensajes, procese la solicitud con el `AiProvider` seleccionado, y devuelva la respuesta (texto y llamadas a funciones).
*   [ ] **4.2. (Opcional/Mejora) WebSockets / SSE:** Implementar Server-Sent Events (SSE) o WebSockets en Ktor para enviar la respuesta de la IA en tiempo real (streaming) al frontend, mejorando la UX.
*   [ ] **4.3. Endpoints de Sesión:** Crear endpoints para crear, obtener y guardar el estado del SDD y los mensajes (al principio en memoria, luego con base de datos).

## Fase 5: Migración y Adaptación del Frontend
**Objetivo:** Conectar el frontend en React al nuevo backend de Ktor.

*   [ ] **5.1. Actualizar `aiService.ts`:** Eliminar la dependencia de `@google/genai` en el frontend. Cambiar las funciones para que hagan peticiones HTTP (`fetch` o `axios`) a la nueva API de Ktor.
*   [ ] **5.2. Manejo de Estado y UI:** Ajustar el frontend para manejar la respuesta del servidor backend (especialmente si se implementa streaming).
*   [ ] **5.3. Selector de Modelo:** Añadir un dropdown o configuración en la UI para permitir al usuario seleccionar qué IA quiere utilizar (Gemini, GPT-4, Claude).
*   [ ] **5.4. Eliminar Riesgos de Seguridad:** Borrar la variable `GEMINI_API_KEY` de `vite.config.ts` y del lado del cliente.

## Fase 6: Persistencia (Mejora Crítica)
**Objetivo:** Guardar los proyectos para no perderlos al recargar.

*   [ ] **6.1. Base de Datos en Backend:** Integrar Exposed o Ktorm en Ktor con SQLite o PostgreSQL.
*   [ ] **6.2. Endpoints CRUD de Proyectos:** Permitir al frontend listar proyectos guardados, cargar uno existente y guardar los cambios.

---
**Siguientes Pasos:**
Una vez aprobado este plan, el primer paso práctico será inicializar el proyecto Ktor en una carpeta como `backend-ktor` dentro del repositorio, y definir la estructura inicial.
