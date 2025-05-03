
# Banking API - Backend Java con Arquitectura Hexagonal

Este proyecto es una API RESTful orientada al sector financiero, desarrollada con **Java 21** y **Spring Boot 3**, implementando una arquitectura hexagonal para garantizar una estructura limpia, mantenible y escalable. Permite gestionar cuentas bancarias, saldos y transacciones básicas.

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3.x
- PostgreSQL
- Spring Data JPA
- Lombok
- JUnit 5 + Mockito
- Springdoc OpenAPI (Swagger UI)
- Docker / Docker Compose
- Arquitectura Hexagonal (Ports and Adapters)

## Estructura del Proyecto (Hexagonal)

```
src
├── domain
│   ├── model              # Entidades del dominio
│   └── port
│       ├── in             # Interfaces de casos de uso
│       └── out            # Interfaces de persistencia
├── application
│   └── service            # Implementación de lógica de negocio
├── infrastructure
│   ├── adapter
│   │   ├── in             # Controladores REST
│   │   └── out            # Adaptadores (JPA, DB, etc.)
│   └── config             # Configuraciones generales
└── MainApplication.java   # Punto de entrada
```

## Configuración de Variables de Entorno

La conexión a la base de datos se gestiona mediante variables de entorno:

| Variable       | Descripción                        | Ejemplo                                           |
|----------------|------------------------------------|---------------------------------------------------|
| `DB_URL`       | URL de conexión JDBC a PostgreSQL  | `jdbc:postgresql://localhost:5432/bankdb`         |
| `DB_USERNAME`  | Usuario de base de datos           | `postgres`                                        |
| `DB_PASSWORD`  | Contraseña de base de datos        | `1234`                                            |

Estas deben estar definidas en tu entorno de ejecución o contenedor.

## Ejecución Local

### Paso 1: Establecer variables de entorno

**Linux/macOS:**

```bash
export DB_URL=jdbc:postgresql://localhost:5432/bankdb
export DB_USERNAME=postgres
export DB_PASSWORD=1234
```

**Windows CMD:**

```cmd
set DB_URL=jdbc:postgresql://localhost:5432/bankdb
set DB_USERNAME=postgres
set DB_PASSWORD=1234
```

### Paso 2: Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

## Ejecutar con Docker

```bash
docker-compose up --build
```

Esto levantará:

- Una base de datos PostgreSQL (contenedor `db`)
- La aplicación Spring Boot (contenedor `app`) con variables de entorno inyectadas

## Swagger - Documentación de la API

Una vez en ejecución, accede a:

📎 [http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/swagger-ui/index.html)

## Pruebas

Ejecuta todas las pruebas unitarias con:

```bash
./mvnw test
```
## Supuestos

- Las operaciones financieras son básicas (no incluyen transferencias entre usuarios aún).
- PostgreSQL es el motor de base de datos predeterminado.

## Autor

Desarrollado como parte de un **reto técnico para Backend Java Senior** con enfoque en el sector **bancario o financiero**.
