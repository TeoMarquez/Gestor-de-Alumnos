# EstuRegistro

EstuRegistro es una aplicación diseñada para la gestión estudiantil en instituciones educativas. Permite realizar diversas operaciones sobre una base de datos de estudiantes, como búsqueda, edición y visualización de información.

## A tener en cuenta

### Conexión a la base de datos

La clase `DB2` proporciona métodos para conectarse a una base de datos MySQL local:

```java
// Datos de conexión a tu base de datos
private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
private static final String USUARIO = "tu_usuario";
private static final String CONTRASENA = "tu_contraseña";
```

#### Funcionalidades principales

- **Editar listas:** Permite acceder a funciones de edición de datos.
- **Buscar estudiantes:** Permite buscar estudiantes por diferentes criterios.

## 🛠️ Tecnologías Utilizadas

- **Java:** Lenguaje de programación principal.
- **Mysql** Base de datos
- **Xampp:** Utilizado para conectar java a la base de datos

## 📋 Ejecución

1. **Clonar el Repositorio:**
   ```
   git clone https://github.com/TeoMarquez/Gestor-de-Alumnos
   ```
2. **Instalar Dependencias:**
   - Asegúrate de tener añadido al proyecto el archivo que se encuentra en Componentes/mysql-connector-java-5.1.46.jar
     

3. **Ejecutar el Script:**
   - Ejecuta el script `principal.java`
     
