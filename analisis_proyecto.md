# Análisis del Proyecto: SDD Genesis

## 1. Resumen: ¿Qué hace el proyecto?
**SDD Genesis** es una aplicación web interactiva diseñada para ayudar a los usuarios (principalmente Product Managers y Desarrolladores) a crear un Documento de Diseño de Software (Software Design Document - SDD) a través de una interfaz de chat asistida por Inteligencia Artificial.

Actúa como un "Arquitecto" virtual impulsado por el modelo **Gemini 3.1 Pro**. El usuario conversa con la IA sobre la aplicación que desea crear, y la IA utiliza herramientas (Function Calling) para ir rellenando y actualizando dinámicamente diferentes secciones del documento técnico, tales como:
- Visión y Propósito
- Requisitos Funcionales y No Funcionales
- Stack Tecnológico
- Modelo de Datos
- Endpoints (API)
- Flujo de Experiencia de Usuario (UX)
- Componentes Principales

La interfaz está dividida en dos paneles: un chat a la izquierda y una previsualización en vivo del documento Markdown generado a la derecha. Además, permite exportar el documento completo como un archivo `.md`.

### Stack Tecnológico:
- **Frontend:** React 19, Vite, Tailwind CSS 4.
- **Lenguaje:** TypeScript.
- **IA:** SDK `@google/genai` (Gemini API).
- **UI/UX:** Componentes de `lucide-react`, animaciones con `motion` (Framer Motion), y renderizado de Markdown con `react-markdown`.

---

## 2. Puntos Críticos (Riesgos y Problemas)
He identificado varios puntos críticos que afectan la seguridad, estabilidad y escalabilidad de la aplicación actual:

1. **Exposición de la API Key (Seguridad Crítica):** 
   En el archivo `vite.config.ts`, la variable `GEMINI_API_KEY` está siendo inyectada directamente en el código del cliente (`process.env.GEMINI_API_KEY`). Esto significa que cualquiera que inspeccione el código de la web en el navegador podrá ver y robar la clave de la API de Gemini.
2. **Falta de Persistencia de Datos:** 
   El estado de la aplicación (los mensajes del chat y el documento SDD generado) se mantiene únicamente en memoria mediante `useState`. Si el usuario recarga la página por accidente, **perderá todo el progreso** de la sesión.
3. **Crecimiento Incontrolado del Contexto (Costes y Límites):** 
   En `App.tsx`, cada vez que se envía un mensaje, se manda el historial **completo** de la conversación (`newMessages`) a la API de Gemini. A medida que la conversación se alarga, el payload será cada vez más grande, lo que aumentará el coste por token y podría llegar a superar el límite de contexto del modelo.
4. **Manejo de Errores Básico:** 
   Si el LLM devuelve una llamada de función (Function Call) con argumentos malformados, la aplicación asume que vienen con el formato correcto (`const { sectionId, content, status } = call.args as any;`). No hay validación de esquemas en el lado del cliente, lo que podría romper el estado de React si la IA alucina.

---

## 3. Mejoras Propuestas
Para convertir este proyecto en una aplicación más robusta y preparada para producción, se recomiendan las siguientes mejoras:

### Arquitectura y Seguridad
*   **Crear un Backend Proxy:** El proyecto ya tiene `express` en sus dependencias de `package.json`. Se debería crear un pequeño servidor Express (o usar Serverless Functions si se despliega en Vercel/Netlify) para realizar las peticiones a la API de Gemini. Así, la API Key se mantiene segura en el servidor y nunca llega al navegador.

### Experiencia de Usuario (UX)
*   **Persistencia Local:** Implementar `localStorage` o `IndexedDB` para guardar automáticamente el estado del proyecto actual (mensajes y documento). Esto permitirá que el usuario cierre el navegador y continúe su trabajo más tarde.
*   **Respuestas en Streaming:** Actualmente, la interfaz se queda cargando hasta que la IA genera toda la respuesta. Implementar *Server-Sent Events (SSE)* o el streaming nativo del SDK de Gemini para que el texto aparezca progresivamente, mejorando enormemente la percepción de velocidad.
*   **Edición Manual:** Permitir que el usuario haga clic en la previsualización del Markdown y pueda editar el texto manualmente. A veces es más rápido corregir un detalle a mano que pedirle a la IA que lo regenere.

### Optimización de la IA
*   **Resumen de Historial (Context Window Management):** Implementar un mecanismo que resuma los mensajes más antiguos cuando el historial supere un cierto número de tokens, manteniendo solo las instrucciones recientes y el documento actual como contexto.
*   **Validación Estricta:** Usar librerías como Zod para validar los argumentos de las funciones que devuelve la IA antes de actualizar el estado de React.

### Funcionalidades Extra
*   **Gestión de Múltiples Proyectos:** Añadir una barra lateral que permita crear nuevos documentos SDD, guardar un historial de proyectos anteriores y cambiar entre ellos.
*   **Exportación a PDF:** Además de exportar el `.md` (Markdown), añadir una opción para exportar un PDF bien formateado que esté listo para ser entregado a clientes o equipos.
