# 🛒 ExerciseTecSupermarket

API REST desarrollada con **Java + Spring Boot** para la gestión básica de ventas en una cadena de supermercados.

Este proyecto fue realizado como una **prueba técnica backend**, enfocada en evaluar conocimientos en:

- Desarrollo de APIs REST
- Uso de Spring Boot
- Manejo de JPA / Hibernate
- Modelado de relaciones entre entidades
- Uso de DTOs
- Manejo de errores HTTP
- Organización modular del proyecto

---

# 🎯 Objetivo del proyecto

El objetivo de esta API es **digitalizar el control de ventas de una cadena de supermercados**, permitiendo gestionar:

- Productos disponibles para venta
- Sucursales del supermercado
- Ventas realizadas en cada sucursal
- Productos vendidos dentro de cada venta

El sistema permite posteriormente realizar consultas como:

- Ventas por sucursal
- Ventas por fecha
- Totalización de ingresos
- Análisis de productos más vendidos

---

# 🧩 Descripción del caso

Una reconocida cadena de supermercados desea **digitalizar su sistema de control de ventas**.

Para ello se requiere una API que permita de forma básica:

- Registrar productos con sus respectivos precios.
- Gestionar las sucursales donde se venden los productos.
- Registrar ventas realizadas en una sucursal.
- Asociar productos a cada venta con sus cantidades.

Posteriormente, el sistema permitirá consultar:

- Ventas por sucursal
- Ventas por fecha
- Total de ingresos
- Productos más vendidos

---

# 🏗️ Entidades principales

El sistema se basa en tres entidades principales.

## 📍 Sucursal

Representa una ubicación física del supermercado.

Ejemplo:

- Sucursal Centro
- Sucursal Norte
- Sucursal Plaza

Atributos comunes:

- id
- nombre
- dirección
- ciudad

---

## 🛒 Producto

Representa un artículo que puede venderse en una sucursal.

Ejemplos:

- Arroz
- Agua embotellada
- Aceite
- Pan

Atributos comunes:

- id
- nombre
- precio
- categoría

---

## 💳 Venta

Representa una transacción de compra realizada en una sucursal.

Una venta contiene:

- sucursal
- fecha
- lista de productos vendidos
- cantidad de cada producto

---

# 🔗 Relaciones del modelo

Las relaciones entre entidades son las siguientes:

### Una sucursal puede tener muchas ventas

Sucursal 1 --- N Venta

---

### Una venta puede tener muchos productos

Venta 1 --- N DetalleVenta

---

### Un producto puede aparecer en muchas ventas

Producto 1 --- N DetalleVenta

---

Esto genera una relación **muchos a muchos entre ventas y productos**, que normalmente se resuelve mediante una entidad intermedia como:

Venta  
DetalleVenta  
Producto  

---

# 📊 Diagrama del Modelo de Datos

El siguiente diagrama muestra las entidades principales del sistema y sus relaciones.

![Diagrama UML](diagramaUML.png)

# ⚙️ Tecnologías utilizadas

Este proyecto utiliza las siguientes tecnologías:

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- Base de datos relacional
- DTOs
- ResponseEntity
- Streams / Lambdas

---

# 📋 Requisitos técnicos cubiertos

El proyecto cumple con los siguientes requisitos técnicos:

✔ Uso de **Spring Boot con JPA** para persistencia de datos  
✔ Uso de **base de datos relacional (H2 o MySQL)**  
✔ Implementación de **endpoints RESTful**  
✔ Operaciones **CRUD completas**  
✔ Uso de **DTOs** para separar el modelo de dominio de la representación externa  
✔ Manejo adecuado de **errores y excepciones**  
✔ Uso de **ResponseEntity** con códigos HTTP correctos  
✔ Uso de **Streams o Lambdas** en al menos una operación del backend  
✔ Organización modular del proyecto  

---

# 📂 Estructura del proyecto

La estructura del proyecto sigue una organización por capas:

src  
 └─ main  
     ├─ java  
     │   └─ com.example.supermarket  
     │       ├─ controller  
     │       ├─ service  
     │       ├─ repository  
     │       ├─ dto  
     │       ├─ model  
     │       ├─ exception  
     │       └─ mapper  
     │  
     └─ resources  
         ├─ application.properties  
         └─ data.sql  

---

# 🚀 Funcionalidades principales

El sistema permite realizar las siguientes operaciones.

---

# 📦 Gestión de Productos

### Obtener listado de productos

GET /api/productos

Devuelve todos los productos registrados.

---

### Registrar nuevo producto

POST /api/productos

Ejemplo de request:

```json
{
  "nombre": "Arroz",
  "precio": 25.5,
  "categoria": "Granos"
}
```

---

### Actualizar producto existente

PUT /api/productos/{id}

Permite modificar los datos de un producto específico.

---

### Eliminar producto

DELETE /api/productos/{id}

Elimina un producto del sistema.

---

# 🏪 Gestión de Sucursales

### Obtener listado de sucursales

GET /api/sucursales

---

### Registrar nueva sucursal

POST /api/sucursales

---

### Actualizar sucursal

PUT /api/sucursales/{id}

---

### Eliminar sucursal

DELETE /api/sucursales/{id}

---

# 💰 Gestión de Ventas

### Registrar una venta

POST /api/ventas

Ejemplo de request:

```json
{
  "sucursalId": 1,
  "detalle": [
    {
      "productoId": 10,
      "cantidad": 2
    },
    {
      "productoId": 5,
      "cantidad": 1
    }
  ]
}
```

---

### Obtener ventas por sucursal y fecha

GET /api/ventas?sucursalId=1&fecha=2025-06-01

Devuelve todas las ventas realizadas en una sucursal en una fecha específica.

---

### Eliminar o anular una venta

DELETE /api/ventas/{id}

Se recomienda utilizar **borrado lógico**.

---

# ⚠️ Reglas funcionales

Las ventas deben cumplir con las siguientes reglas:

- Cada venta debe pertenecer a una sucursal válida.
- Cada producto debe existir previamente.
- Las cantidades deben ser mayores a cero.
- No se pueden registrar ventas sin productos.
- Las ventas **no deben modificarse después de registradas** sin permisos especiales.

---

# 🧠 Buenas prácticas aplicadas

Este proyecto aplica varias buenas prácticas de desarrollo backend:

- Separación entre entidades y DTOs
- Uso de ResponseEntity para control de respuestas HTTP
- Manejo de errores y excepciones
- Arquitectura organizada por capas
- Código mantenible y desacoplado
- Uso de streams y lambdas
- Uso de repositorios JPA

---

# ▶️ Cómo ejecutar el proyecto

## 1 Clonar el repositorio

```
git clone https://github.com/Raul-dd/ExerciseTecSupermarket.git
```

---

## 2 Entrar al proyecto

```
cd ExerciseTecSupermarket
```

---

## 3 Compilar el proyecto

```
mvn clean install
```

---

## 4 Ejecutar la aplicación

```
mvn spring-boot:run
```

---

# ⚙️ Configuración

La configuración principal del proyecto se encuentra en:

src/main/resources/application.properties

Ejemplo de configuración usando H2:

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.h2.console.enabled=true
```

---

# 🔮 Mejoras futuras

Algunas mejoras que podrían implementarse en el sistema:

- Autenticación con JWT
- Documentación con Swagger / OpenAPI
- Paginación en endpoints
- Reportes de ventas
- Productos más vendidos
- Ingresos por sucursal
- Pruebas unitarias
- Pruebas de integración
- Cache de consultas frecuentes

---

# 👨‍💻 Autor

Raúl  

Repositorio:

https://github.com/Raul-dd/ExerciseTecSupermarket

---

# ⭐ Notas

Este proyecto forma parte de una **evaluación técnica backend** y demuestra habilidades en:

- Spring Boot
- Diseño de APIs REST
- Modelado de entidades
- Persistencia con JPA
- Organización de proyectos backend
