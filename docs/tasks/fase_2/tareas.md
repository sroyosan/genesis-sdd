# Fase 2: Capa de Dominio e Interfaces (Agnostic AI)

**Objetivo:** Diseñar la arquitectura central (Core) del negocio asegurando que esté completamente aislada de frameworks, bases de datos y las APIs específicas de cada IA.

## Tareas

- [x] **2.1. Modelos de Dominio (Puros):** Crear `data classes` en Kotlin que representen las entidades de negocio (`Message`, `SpecSection`, `SDD`, `AiResponse`). *Regla crítica:* No debe haber importaciones de Ktor, Exposed u otros frameworks en estas clases.
- [x] **2.2. Puertos de Salida (Outbound Ports):** Crear la interfaz `AiProviderPort` en el paquete `domain/ports/out`. Esta interfaz abstraerá la comunicación con cualquier IA.
  - Ejemplo: `suspend fun generateSddResponse(messages: List<Message>, modelConfig: AiConfig): AiResponse`
- [x] **2.3. Puertos de Entrada (Inbound Ports):** Crear interfaces que definan los casos de uso disponibles desde el exterior, por ejemplo, `ChatUseCasePort` en `domain/ports/in`.
- [x] **2.4. Servicios de Aplicación (Use Cases):** Implementar las clases en la capa `application` que cumplen con los Puertos de Entrada y coordinan las operaciones apoyándose en los Puertos de Salida.
- [x] **2.5. Abstracción de Function Calling:** Diseñar y modelar los esquemas o intenciones de herramientas (ej. `UpdateSpecSection`, `UpdateProjectName`) mediante clases Kotlin en el dominio. Será responsabilidad de la capa de infraestructura traducir estos modelos agnósticos al formato nativo (JSON schemas) de la IA específica en uso.
