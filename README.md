# TaskMaster - Backend


---

## Tecnologías Utilizadas

###  Backend
-  **Java 17**
-  **Spring Boot 3**
-  **Spring Security con JWT**
-  **Spring Data JPA**
-  **MySQL**
-  **Maven**
-  **MapStruct (para mapeo DTO ↔ Entity)**
-  **Lombok (para reducir boilerplate)**


---

##  Funcionalidades
Registro de usuarios  

Login con autenticación JWT  

CRUD de tareas, estados, categorías y usuarios (crear, leer, actualizar, eliminar)
Roles de usuario (`USER`, `ADMIN`)  

Seguridad en endpoints (solo usuarios autenticados pueden gestionar tareas)

Validación de datos en requests

Creación automática de un usuario ADMIN si no existe al iniciar la aplicación

---

##  Estructura del Proyecto

```
Gestor-Tareas/
├─ src/                      <- Código fuente de Spring Boot
│   ├─ main/                 <- Paquetes de dominio, service, repository, controller
│   └─ test/                 <- application.yml, banner, etc.
├─ pom.xml                   <- Proyecto Maven
├─ README.md
└─ .gitignore
```
---

# Configuración Local


1. **Clonar el repositorio**

```bash
git clone <https://github.com/JoseLuis-DM/TaskMasterBackend.git>
cd TaskMaster
```

2. **Configurar base de datos**

El entorno en que se encuentra es dev, se puede cambiar de entorno en `src/main/resources/application.yml`:.  
Es necesario que agregues tu usuario de base de datos y tu contraseña.    
Opcional: MySQL crea la DB automáticamente con el nombre `TaskMasterdev`.  
Si quieres usar otra configuración, modifica `src/main/resources/application-dev.yml`:

```properties
spring:
datasource:
url: jdbc:mysql://localhost:3306/taskmasterdev?createDatabaseIfNotExist=true
username: ${DB_USER}                 <- Usuario
password: ${DB_PASSWORD}             <- Contraseña

```

3. **Instalar dependencias y compilar**

```bash
mvn clean install
```

4. **Ejecutar la aplicación**

```bash
mvn spring-boot:run
```

- El backend se levantará por defecto en:
```
http://localhost:8080
```

- Al iniciar, si no existe un usuario `ADMIN`, se creará automáticamente con credenciales iniciales.
  > Revisar la consola para ver el correo y contraseña asignados y necesitan cambiarlos.

---

### Probar Endpoints con Postman

Para facilitar las pruebas de los endpoints, se incluye una **colección de Postman** junto con un archivo de variables de ejemplo.

### 1. Importar la colección
- Abre Postman.
- Ve a **File → Import** o haz clic en **Import**.
- Selecciona el archivo `TaskMaster-Endpoints`.


### 2. Ejecutar los endpoints
- Selecciona la colección importada y el entorno correspondiente.
- Ejecuta los requests directamente desde Postman.
- Para endpoints protegidos, asegúrate de obtener un JWT mediante `POST /api/auth/login` y asignarlo a la variable correspondiente en el entorno.

> Todos los endpoints de tareas, usuarios, categorias y estados requieren **JWT en el header Authorization: Bearer <token>**.
---


##  Notas

- La base de datos TaskMasterdev se puede crear automáticamente si MySQL está corriendo, pero se puede crear manualmente.