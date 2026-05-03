# Fase 3: Implementación de Proveedores de IA (Infraestructura)

**Objetivo:** Implementar los Adaptadores de Salida (Outbound Adapters) encargados de la comunicación concreta con los diversos LLMs.

## Tareas

- [x] **3.1. Adaptador Gemini:** Crear la clase que implemente `AiProviderPort` en el paquete `infrastructure/adapters/out/ai/gemini`. Usará llamadas REST o el SDK correspondiente para interactuar con Gemini 3.1 Pro.
- [ ] **3.2. Adaptador OpenAI (Mejora):** Crear la implementación de `AiProviderPort` para comunicarse con la API de OpenAI (GPT-4o) en `infrastructure/adapters/out/ai/openai`.
- [ ] **3.3. Adaptador Anthropic (Mejora):** Crear la implementación de `AiProviderPort` para la API de Claude en `infrastructure/adapters/out/ai/anthropic`.
- [x] **3.4. Factoría / Gestor de Proveedores:** Implementar un mecanismo de resolución (Factory o Strategy pattern) en la capa de infraestructura o inyección de dependencias (DI) que provea la instancia correcta de `AiProviderPort` basado en la petición del usuario.
- [x] **3.5. Mapeadores de Infraestructura (Mappers):** Crear funciones para traducir los modelos de dominio puros (y los esquemas abstractos de Function Calling) a los DTOs que cada proveedor de IA específico requiere para sus peticiones HTTP.
