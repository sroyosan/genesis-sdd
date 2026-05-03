# Fase 4: Exposición de la API (Adaptadores de Entrada)

**Objetivo:** Exponer las capacidades de la aplicación hacia el exterior (Frontend) creando endpoints de red, actuando como Adaptador de Entrada (Inbound Adapter).

## Tareas

- [x] **4.1. Endpoint de Chat (`POST /api/chat`):** 
  - Declarar la ruta en Ktor dentro de `infrastructure/adapters/in/web`.
  - Recibir DTOs HTTP y mapearlos a Modelos de Dominio.
  - Llamar al caso de uso pertinente (`ChatUseCasePort`).
  - Mapear la respuesta obtenida del caso de uso a DTOs de salida (JSON) para el cliente web.
- [ ] **4.2. (Opcional/Mejora) WebSockets o SSE para Streaming:** Crear endpoints de WebSocket o Server-Sent Events en Ktor para habilitar el streaming de la respuesta. Asegurar que esta lógica en Ktor solo retransmita flujos y no contenga lógica de negocio.
- [x] **4.3. Endpoints de Sesión/Estado:** Implementar las rutas HTTP para gestionar el inicio y guardado en memoria de sesiones, interactuando con los respectivos casos de uso de la aplicación.
