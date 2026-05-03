# Fase 6: Persistencia (Infraestructura)

**Objetivo:** Implementar almacenamiento duradero para los proyectos y sesiones, manteniendo el aislamiento de la base de datos de acuerdo con la Arquitectura Hexagonal.

## Tareas

- [x] **6.1. Definición de Puertos de Repositorio:** Declarar interfaces como `ProjectRepositoryPort` en el paquete de `domain/ports/out`.
- [x] **6.2. Configuración de Base de Datos:** Integrar y configurar un ORM o librería (ej. Exposed o Ktorm) y un motor de base de datos (SQLite o PostgreSQL) dentro del paquete de infraestructura.
- [x] **6.3. Implementación de Adaptador DB:** Crear clases en `infrastructure/adapters/out/db` que implementen `ProjectRepositoryPort`.
  - *Restricción de Diseño:* Las entidades o tablas de Exposed/Ktorm **NO** deben salir de la capa de infraestructura. El adaptador debe transformar las filas de base de datos a modelos puros de dominio al retornarlas.
- [x] **6.4. Endpoints CRUD de Proyectos:** Desarrollar Adaptadores de Entrada web en Ktor (GET, POST, PUT, DELETE) para permitir al frontend listar, cargar y guardar el estado persistente de los proyectos invocando a los casos de uso de la aplicación.
