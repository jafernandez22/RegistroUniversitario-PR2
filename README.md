# Sistema de Registro Universitario

Este proyecto es una aplicación para la gestión de una universidad, que permite administrar tanto estudiantes, docentes, materias, inscripciones a cursos, como evaluaciones a docentes. Fue desarrollado utilizando un stack basado en **Jakarta EE** y **Spring Framework** para aprovechar las ventajas de las tecnologías modernas de Java.

## Objetivo del Proyecto

El propósito de este sistema es simplificar la administración universitaria, facilitando procesos como la inscripción de estudiantes a materias, la gestión de evaluaciones docentes, y la configuración de información relacionada con el personal docente y los cursos en la universidad.

## Tecnologías Utilizadas

El proyecto está construido con las siguientes tecnologías:

- **Java 23**: Lenguaje de desarrollo principal.
- **Jakarta EE**: Estructura de capas empresariales.
- **Spring Framework**:
  - **Spring Data JPA**: Gestión eficiente de interacciones con la base de datos.
  - **Spring MVC**: Controladores para manejar las peticiones del frontend.
  - **Spring Security**: Para la configuración y protección del sistema.
- **Lombok**: Reducción del código repetitivo mediante anotaciones.
- **Swagger**: Documentación interactiva para las APIs.
- **Maven**: Herramienta para la gestión de dependencias y construcción del proyecto.
- **Base de Datos**: Se utiliza un esquema representativo (nombre sugerido: `universidad`).

## Características Principales
- Gestión de estudiantes, CRUD con validaciones.
- Administración de materias y docentes.
- Gestión de Materias: CRUD completo con asignación de docentes.
- Sistema de Inscripciones: Permite a estudiantes inscribirse en materias.
- Caché con Redis: Optimización de rendimiento para consultas frecuentes.
- Autenticación y Autorización: Sistema de login basado en JWT con roles (Administrador, Docente, Estudiante)
- Evaluación de desempeño docente por parte de estudiantes.
- Registro y diseño de estructuras de inscripción.
- Manejo de excepciones globales mediante un controlador centralizado.
- Documentación del API generada automáticamente con Swagger.

## Estructura del Proyecto

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com.universidad/
│   │       ├── controller/          # Controladores para exponer los endpoints del API.
│   │       ├── service/             # Lógica de negocio, implementaciones y contratos (interfaces).
│   │       ├── model/               # Clases de modelo (Entidades JPA).
│   │       ├── repository/          # Acceso a datos.
│   │       ├── dto/                 # Clases de transferencia de datos
│   │       ├── validation/          # Controlador de excepciones globales
│   │       ├── config/              # Configuraciones generales (Base de datos, Seguridad, Swagger)
|   |       ├── security/       # Configuración de seguridad y JWT
│   └── resources/
│       ├── application.properties   # Configuración de la aplicación
```

## Instalación

Sigue los pasos a continuación para correr el proyecto en tu entorno local:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/jafernandez22/RegistroUniversitario-PR2.git
   ```

2. Configura el acceso a la base de datos que utiliza el proyecto:
   - Edita el archivo `application.properties` ubicado en `src/main/resources/`.
   - Proporciona los valores correctos para las propiedades de conexión, por ejemplo:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/universidad
     spring.datasource.username=<usuario>
     spring.datasource.password=<contraseña>
     spring.jpa.hibernate.ddl-auto=update
     ```

3. Genera el artefacto del proyecto utilizando **Maven**:
   ```bash
   mvn clean install
   ```

4. Despliega la aplicación en un servidor local o ejecuta directamente desde **Spring Boot**:
   ```bash
   mvn spring-boot:run
   ```

5. Accede a la aplicación desde tu navegador o a través de herramientas como Postman en:
   - **URL principal**: [http://localhost:8080](http://localhost:8080)
   - **Documentación API (Swagger)**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Seguridad
El sistema implementa seguridad basada en roles:
-ADMIN: Acceso completo a todas las funcionalidades
-DOCENTE: Gestión de materias asignadas y evaluaciones
-ESTUDIANTE: Inscripción a materias y consulta de información
La autenticación se realiza mediante JWT (JSON Web Tokens) que deben incluirse en el encabezado Authorization de las solicitudes.
