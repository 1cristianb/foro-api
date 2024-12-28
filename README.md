# Foro Hub | Challenge de Alura - ONE Oracle
<p align="center" >
  <img src="https://app.aluracursos.com/assets/images/logos/logo-aluraespanhol.svg" alt="Alura logo">
  <img src="https://cdn2.gnarususercontent.com.br/6/449886/e4621638-6168-4948-a623-76dcfdefd99c.png" alt="ONE Oracle logo">
</p>

Foro Hub es una API REST desarrollada con Spring Boot para gestionar un sistema de foros. La API permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los tópicos, así como implementar un sistema de autenticación y autorización.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
  - Spring Data JPA
  - Spring Security
- **MySQL** (Base de datos relacional)
- **Flyway** (Migración de base de datos)
- **JWT** (Json Web Tokens para autenticación)
- **Maven** (Gestor de dependencias)

## Instalación y configuración

### Requisitos previos

- Tener instalado Java 17 o superior.
- Tener MySQL configurado y corriendo.
- Tener configurado un cliente HTTP (Postman, Insomnia, etc.) para probar los endpoints.

### Configuración de la base de datos

1. Crear una base de datos en MySQL, por ejemplo, `foro_hub`.
2. Configurar los datos de conexión en el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.flyway.enabled=true
```

## Ejecución del proyecto
1. Clonar el repositorio:

```
git clone https://github.com/1cristianb/foro-api.git)
cd foro-api
```
2. Ejecutar el proyecto:

```
mvn spring-boot:run
```
3. Acceder a la API en http://localhost:8080.

## Endpoints principales

- ### Crear un tópico
  URL: POST /topicos

- ### Actualizar un tópico
  URL: PUT /topicos/{id}

- ### Eliminar un tópico
  URL: DELETE /topicos/{id}

- ### Obtener todos los tópicos
  URL: GET /topicos

- ### Obtener detalles de un tópico
  URL: GET /topicos/{id}

## Validaciones

La API implementa validaciones utilizando Hibernate Validator. Ejemplos:

- Campos como titulo, mensaje y autor deben ser no vacíos (@NotBlank).
- El campo status debe ser un valor válido del enum StatusTopico (@NotNull).

## Seguridad

- Implementa autenticación basada en usuarios mediante UserDetails y UserDetailsService.

- Utiliza JWT (Json Web Tokens) para la autenticación de los usuarios.

- Configuraciones necesarias en `application.properties`:
  ```
  api.security.secret=${JWT_SECRET}
  api.security.expiration=${JWT_EXPIRATION}
  ```
- Se utiliza un filtro de seguridad personalizado para manejar la autorización de los endpoints.
  
## Migraciones con Flyway
Las migraciones de base de datos se gestionan con Flyway. Los scripts SQL se encuentran en el directorio src/main/resources/db/migration.

## Autor
Cristian Boschi
