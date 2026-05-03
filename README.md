# Genesis SDD 🚀

*Read this in other languages: [English](README.md), [Español](README.es.md).*

**Genesis SDD** is an interactive web application designed to act as an AI-powered virtual Software Architect. It helps Product Managers, Developers, and System Architects generate comprehensive **Software Design Documents (SDD)** through a conversational chat interface.

Powered by **Gemini 3.1 Pro**, the AI dynamically populates and updates different sections of your technical documentation in real-time, allowing for a collaborative and iterative design process.

## ✨ Features

- **Conversational Architecture:** Chat with the AI about your project requirements, and watch as it designs the system for you.
- **Dynamic Document Generation:** Uses AI Function Calling to update specific sections of the SDD (Vision, Functional Requirements, Tech Stack, Data Models, API Endpoints, etc.).
- **Live Markdown Preview:** See the generated documentation side-by-side with the chat.
- **Export Capabilities:** Easily export the finalized SDD as a `.md` file for your team.

## 🛠️ Technology Stack

The project is structured with a modern, decoupled architecture, currently migrating towards a clean, hexagonal backend:

### Frontend
- **Framework:** React 19 with Vite
- **Language:** TypeScript
- **Styling:** Tailwind CSS 4
- **UI/UX:** `lucide-react` for icons, `framer-motion` for animations, and `react-markdown` for document rendering.

### Backend (Under Construction 🏗️)
- **Language:** Kotlin
- **Framework:** Ktor
- **Architecture:** Clean / Hexagonal Architecture (Domain, Application, Infrastructure)
- **AI Integration:** Google Gemini API (`@google/genai` / Ktor Client)

## 🚀 Getting Started

### Prerequisites
- Node.js (v18+)
- Java JDK 17+ (for backend)
- A Gemini API Key

### Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/genesis-sdd.git
   cd genesis-sdd
   ```

2. **Environment Variables:**
   Copy the example environment file and add your Gemini API Key.
   ```bash
   cp .env.example .env
   # Edit .env and set your GEMINI_API_KEY
   ```

3. **Install Frontend Dependencies:**
   ```bash
   npm install
   ```

4. **Run the Frontend Development Server:**
   ```bash
   npm run dev
   ```

## 📝 Roadmap
- [x] Initial React Frontend Prototype
- [x] Gemini API Integration with Function Calling
- [x] Live Markdown Preview
- [ ] Migration to Kotlin (Ktor) Hexagonal Backend
- [ ] Database Persistence (Exposed)
- [ ] Multi-provider AI support

---
*Built with ❤️ and AI.*
