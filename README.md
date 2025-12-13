#  AplicacionActividad3 – Historial de Acciones
Aplicación Android desarrollada en Java que implementa un sistema de auditoría interna
para registrar las acciones CRUD realizadas por el usuario dentro de una aplicación de
gestión de tareas o notas.

El sistema permite consultar un historial de acciones como creación, actualización y
eliminación de registros, mostrando información relevante como el tipo de acción, la
fecha de ejecución y un detalle del registro afectado.

## 🛠️ Tecnologías utilizadas
- Android Studio
- Java
- Room (SQLite)
- RecyclerView
- Arquitectura MVC

## 📋 Funcionalidades
- Registro automático de acciones CRUD
- Consulta completa del historial
- Filtro de historial por fecha o tipo de acción
- Persistencia de datos con Room

## ⚙️ Instalación y ejecución
1. Clonar el repositorio:
```bash
git clone https://github.com/OctavioBarrazaSosa/AplicacionActividad3
Abrir el proyecto en Android Studio

Esperar a que Gradle sincronice

Ejecutar en emulador o dispositivo físico
| Columna    | Tipo     |
| ---------- | -------- |
| history_id | int (PK) |
| action     | text     |
| created_at | text     |
| details    | text     |
