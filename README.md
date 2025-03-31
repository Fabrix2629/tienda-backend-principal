## 1️⃣ **TIENDA BACKEND PRINCIPAL**

Este proyecto es un backend Spring Boot para la gestión de productos, categorías y usuarios con documentación Swagger integrada.

---

## 2️⃣ **Objetivos Principales**

```md
## 🎯 Objetivos Principales
- Gestionar CRUD completo de productos
- Administrar categorías de productos
- Manejar usuarios del sistema
- Proporcionar API REST documentada
- Validar tipos de datos en requests
```

---

## 3️⃣ **Tecnologías Utilizadas**

- 🖥 **Java 17**
- 🚀 **Spring Boot** (Spring Web, Spring Data JPA, Lombok, MySQL Driver)
- 🛠 **Maven 3.9.6**
- 🗃 **JPA/Hibernate**
- ✅ **JUnit** (para pruebas unitarias)
- 📄 **Swagger** (para documentación de API)

---

## 4️⃣ **Instalación y Configuración**

### 📌 Prerrequisitos
Antes de ejecutar el proyecto, asegúrate de tener instalado:
- ☕ **Java 17**
- 🔧 **Maven**
- 🗄 **MySQL**

### 📂 Clonar el Repositorio
```bash
git clone https://github.com/Fabrix2629/tienda-backend-principal.git
cd tienda-backend-principal
```

### ⚙️ Configuración de Base de Datos
Configura el archivo `application.properties` o `application.yml` con las credenciales de tu base de datos MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

### 🚀 Compilar y Ejecutar
Para compilar y ejecutar la aplicación, usa los siguientes comandos:
```bash
mvn clean install
mvn spring-boot:run
```

---

## 5️⃣ **Endpoints Principales**

### 👤 Usuarios
- `POST /api/usuarios` - Crear un usuario
- `GET /api/usuarios/findAll` - Obtener todos los usuarios
- `GET /api/usuarios/findById/{id}` - Obtener un usuario por ID
- `PUT /api/usuarios/{id}` - Actualizar un usuario
- `DELETE /api/usuarios/{id}` - Eliminar un usuario

### 🛒 Productos
- `POST /api/productos` - Crear un producto
- `GET /api/productos/findAll` - Listar todos los productos
- `GET /api/productos/findById/{id}` - Obtener un producto por ID
- `PUT /api/productos/{id}` - Actualizar un producto
- `DELETE /api/productos/{id}` - Eliminar un producto

### 📂 Categorías
- `POST /api/categorias` - Crear una categoría
- `GET /api/categorias/findAll` - Listar todas las categorías
- `GET /api/categorias/findById/{id}` - Obtener una categoría por ID
- `PUT /api/categorias/{id}` - Actualizar una categoría
- `DELETE /api/categorias/{id}/with-products` - Eliminar una categoría con sus productos

---
### 🔐 Aceso Login
- `POST /auth/login - Iniciar sesión`
- `POST /auth/register - Registrar un usuario`
---
## 🔌 Dependencias

### Core Spring Boot
- `spring-boot-starter-data-jpa` - Persistencia de datos con JPA/Hibernate
- `spring-boot-starter-web` - Construcción de APIs REST
- `spring-boot-starter-security` - Autenticación y autorización
- `spring-boot-starter-test` - Testing framework

### Base de Datos
- `mysql-connector-j` - Driver para MySQL 8+
- `hibernate-core` - ORM framework (incluido en JPA starter)

### Seguridad
- `jjwt-api` + `jjwt-impl` + `jjwt-jackson` - Para JWT tokens (v0.11.5)

### Desarrollo
- `lombok` - Reduce boilerplate code (v1.18.22)
- `spring-boot-devtools` - Recarga en caliente
- `jackson-databind` - Procesamiento JSON

### Documentación
- `springdoc-openapi-starter-webmvc-ui` - Swagger UI (v2.8.6)

### Testing
- `junit-jupiter` - JUnit 5 (v5.9.3)
- `mockito-junit-jupiter` - Mocking (v5.7.0)
- `spring-security-test` - Testing de seguridad

### 🔧 Plugins Maven

```xml
<plugins>
  <!-- Plugin principal de Spring Boot -->
  <plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
      <excludes>
        <exclude>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
        </exclude>
      </excludes>
    </configuration>
  </plugin>

  <!-- Plugin para cobertura de código con JaCoCo -->
  <plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.11</version>
    <executions>
      <execution>
        <id>prepare-agent</id>
        <goals>
          <goal>prepare-agent</goal>
        </goals>
      </execution>
      <execution>
        <id>report</id>
        <phase>test</phase>
        <goals>
          <goal>report</goal>
        </goals>
      </execution>
    </executions>
  </plugin>

  <!-- Plugin para compilación con soporte Lombok -->
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
      <annotationProcessorPaths>
        <path>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
          <version>1.18.22</version>
        </path>
      </annotationProcessorPaths>
    </configuration>
  </plugin>
</plugins>

Esta versión incluye:
1. Los tres plugins principales con sus configuraciones clave
2. Comentarios descriptivos para cada plugin
3. Formateo limpio para mejor legibilidad
4. Las versiones específicas donde son relevantes

```

## 8️⃣ **🤝 Contribuciones**
Si deseas contribuir a este proyecto, por favor sigue estos pasos:
1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Sube tu rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

---

## 9️⃣ **📜 Licencia**
Este proyecto está bajo la licencia **MIT**.

