# 🚀 **TIENDA BACKEND**

Este proyecto es un backend desarrollado con **Spring Boot** para gestionar productos, categorías y usuarios. Incluye autenticación con **JWT**, documentación con **Swagger**, y pruebas unitarias con **JUnit**.

---

## 🎯 **Objetivos Principales**

- Implementar **CRUD** completo para productos, categorías y usuarios.
- Manejo seguro de autenticación y autorización con **JWT**.
- Exposición de API REST documentada con **Swagger UI**.
- Validación de datos en las peticiones.
- Pruebas unitarias con **JUnit** y **Mockito**.

---

## 🛠 **Tecnologías Utilizadas**

- ☕ **Java 17**
- 🚀 **Spring Boot**  (Spring Web, Spring Security, Spring Data JPA)
- 🗃 **MySQL** (Base de datos relacional)
- 🔐 **JWT** (Autenticación y autorización)
- 📄 **Swagger OpenAPI** (Documentación de API REST)
- ✅ **JUnit 5** y **Mockito** (Pruebas unitarias)
- 🛠 **Maven 3.9.6** (Gestión de dependencias y compilación)

---

## 📂 **Estructura del Proyecto**

```bash
src/
├── main/
│   ├── java/com/tienda/
│   │   ├── config/               # configuracion general del proyecto y seguridad 
│   │   ├── controller/           # Controladores 
│   │   ├── dto/                  # dto para las entidades
│   │   ├── entity/             # Entidades
│   │   ├── Enum/           # enum
│   │   ├── exceptions/    # excepciones 
│   │   ├── jwtAuth/            # jwt
│   │   │   ├── authEntity/
│   │   │   ├── controller/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   ├── mapper/               # mapper de las entidades
│   │   ├── repository/         # repositorio
│   │   ├── service/              # servicios creados
│   ├── resources/        
│   │   ├── application.properties  # configuracion de la base de datos  
│   │   ├── schema.sql          # Script opcional para inicializar la DB
├── test/                             # Pruebas unitarias
│   ├── java/
│   │   ├── bakend-principal-tienda/
│   │   │   ├── service/
│   │   │   ├── controller/
└── pom.xml  # Dependencias y configuración de Maven
```

---

## 📦 **Instalación y Configuración**

### 📌 **Prerrequisitos**
Antes de ejecutar el proyecto, asegúrate de tener instalado:
- ☕ **Java 17**
- 🛠 **Maven**
- 🗄 **MySQL**

### 📂 **Clonar el Repositorio**
```bash
git clone https://github.com/Fabrix2629/tienda-backend-principal.git
cd tienda-backend-principal
```

### ⚙️ **Configuración de Base de Datos**
Configura el archivo `application.properties` con las credenciales de tu base de datos MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_tienda_00
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 🚀 **Compilar y Ejecutar**
```bash
mvn clean install
mvn spring-boot:run
```
La API estará disponible en `http://localhost:8080`

---

## 🔄 **Endpoints Principales**

### 👤 **Usuarios**
- `POST /api/usuarios` - Crear un usuario
- `GET /api/usuarios/findAll` - Obtener todos los usuarios
- `GET /api/usuarios/findById/{id}` - Obtener un usuario por ID
- `PUT /api/usuarios/{id}` - Actualizar un usuario
- `DELETE /api/usuarios/{id}` - Eliminar un usuario

### 🛒 **Productos**
- `POST /api/productos` - Crear un producto
- `GET /api/productos/findAll` - Listar todos los productos
- `GET /api/productos/findById/{id}` - Obtener un producto por ID
- `PUT /api/productos/{id}` - Actualizar un producto
- `DELETE /api/productos/{id}` - Eliminar un producto

### 📂 **Categorías**
- `POST /api/categorias` - Crear una categoría
- `GET /api/categorias/findAll` - Listar todas las categorías
- `GET /api/categorias/findById/{id}` - Obtener una categoría por ID
- `PUT /api/categorias/{id}` - Actualizar una categoría
- `DELETE /api/categorias/{id}/with-products` - Eliminar una categoría con sus productos

### 🔐 **Autenticación con JWT**
- `POST /auth/login` - Iniciar sesión
- `POST /auth/register` - Registrar un usuario

---

## 🔐 **Seguridad con JWT**
El backend usa **Spring Security** y **JWT** para la autenticación y autorización.

📍 **Ubicación:** `src/main/java/com/tienda/security/JwtUtil.java`

📌 **Generación del token:**
```java
public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
}
```

📌 **Validación del token:**
```java
public boolean validateToken(String token) {
    try {
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return true;
    } catch (Exception e) {
        return false;
    }
}
```

---

## 📑 **Documentación con Swagger**
Este proyecto usa **Springdoc OpenAPI** para la documentación de API.

📍 **Accede a la documentación en:**
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

📌 **Configuración en `application.properties`:**
```properties
springdoc.api-docs.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html
```

---

## ✅ **Pruebas Unitarias con JUnit y Mockito**
El backend incluye **pruebas unitarias** para los servicios y controladores.

📍 **Ejemplo de prueba con Mockito:**
```java
@Test
public void testGetAllUsuarios() {
    List<Usuario> usuarios = List.of(new Usuario(1L, "Fabrisio", "fabrisio@mail.com"));
    when(usuarioRepository.findAll()).thenReturn(usuarios);
    List<Usuario> resultado = usuarioService.getAllUsuarios();
    assertEquals(1, resultado.size());
}
```

📌 **Ejecutar pruebas:**
```bash
mvn test
```

---

## 🤝 **Contribuciones**
Si deseas contribuir a este proyecto:
1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Sube tu rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

---

## 📜 **Licencia**
Este proyecto está bajo la licencia **MIT**.