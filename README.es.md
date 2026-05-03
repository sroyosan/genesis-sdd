# Genesis SDD 🚀

*Lee esto en otros idiomas: [English](README.md), [Español](README.es.md).*

**Genesis SDD** es una aplicación web interactiva diseñada para actuar como un Arquitecto de Software virtual impulsado por IA. Ayuda a Product Managers, Desarrolladores y Arquitectos de Sistemas a generar **Documentos de Diseño de Software (SDD)** completos a través de una interfaz de chat conversacional.

Desarrollada con **Gemini 3.1 Pro**, la IA rellena y actualiza dinámicamente diferentes secciones de tu documentación técnica en tiempo real, permitiendo un proceso de diseño colaborativo e iterativo.

## ✨ Características

- **Arquitectura Conversacional:** Chatea con la IA sobre los requisitos de tu proyecto y observa cómo diseña el sistema por ti.
- **Generación Dinámica de Documentos:** Utiliza el "Function Calling" de la IA para actualizar secciones específicas del SDD (Visión, Requisitos Funcionales, Stack Tecnológico, Modelos de Datos, Endpoints de la API, etc.).
- **Previsualización en vivo:** Ve la documentación generada en Markdown al lado del chat.
- **Exportación:** Exporta fácilmente el SDD finalizado como un archivo `.md` para tu equipo.

## 🛠️ Stack Tecnológico

El proyecto está estructurado con una arquitectura moderna y desacoplada, actualmente en proceso de migración hacia un backend limpio y hexagonal:

### Frontend
- **Framework:** React 19 con Vite
- **Lenguaje:** TypeScript
- **Estilos:** Tailwind CSS 4
- **UI/UX:** `lucide-react` para iconos, `framer-motion` para animaciones y `react-markdown` para renderizar el documento.

### Backend (En Construcción 🏗️)
- **Lenguaje:** Kotlin
- **Framework:** Ktor
- **Arquitectura:** Arquitectura Limpia / Hexagonal (Dominio, Aplicación, Infraestructura)
- **Integración de IA:** Google Gemini API (`@google/genai` / Ktor Client)

## 🚀 Empezando

### Requisitos Previos
- Node.js (v18+)
- Java JDK 17+ (para el backend)
- Una API Key de Gemini

### Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/tu-usuario/genesis-sdd.git
   cd genesis-sdd
   ```

2. **Variables de Entorno:**
   Copia el archivo de entorno de ejemplo y añade tu API Key de Gemini.
   ```bash
   cp .env.example .env
   # Edita .env y configura tu GEMINI_API_KEY
   ```

3. **Instalar Dependencias del Frontend:**
   ```bash
   npm install
   ```

4. **Iniciar el Servidor de Desarrollo del Frontend:**
   ```bash
   npm run dev
   ```

## 📝 Roadmap
- [x] Prototipo Inicial en React
- [x] Integración de la API de Gemini con Function Calling
- [x] Previsualización en vivo de Markdown
- [ ] Migración a Backend Hexagonal en Kotlin (Ktor)
- [ ] Persistencia en Base de Datos (Exposed)
- [ ] Soporte Multi-proveedor de IA

---
*Construido con ❤️ e IA.*
