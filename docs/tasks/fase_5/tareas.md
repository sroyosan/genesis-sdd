# Fase 5: Migración y Adaptación del Frontend

**Objetivo:** Actualizar el cliente React para que interactúe exclusivamente con los endpoints del nuevo backend, finalizando la migración de responsabilidades.

## Tareas

- [x] **5.1. Actualización de `aiService.ts`:** Eliminar las dependencias directas (`@google/genai`) del frontend. Refactorizar el servicio para hacer peticiones HTTP asíncronas hacia `/api/chat` en Ktor.
- [ ] **5.2. Adaptación de la UI y Manejo de Estado:** Ajustar el frontend para acoplarse a los nuevos DTOs que responde el backend y, de ser necesario, procesar las respuestas parciales si se implementó el Streaming.
- [ ] **5.3. Selector de Proveedor IA:** Diseñar y agregar a la interfaz de usuario un selector (dropdown) para escoger el LLM (Gemini, OpenAI, Claude) y enviarlo como parámetro en la petición al backend.
- [x] **5.4. Saneamiento de Credenciales:** Remover definitivamente las variables como `GEMINI_API_KEY` de los archivos del cliente (como `vite.config.ts` y `.env` locales del frontend) para mitigar el riesgo de seguridad.
