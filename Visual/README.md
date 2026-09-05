# Proyecto: Sistema de Autenticación de Inventario

Este proyecto permite realizar el inicio de sesión contra una base de datos MySQL utilizando Servlets de Java.

## Contenido del repositorio
- `src/`: Contiene todo el código fuente del proyecto.
- `Modelo_Inventario_Jair.sql`: Script necesario para crear la base de datos y las tablas.

## Requisitos para ejecutar
1. **Base de Datos:** Importar el archivo `Modelo_Inventario_Jair.sql` en MySQL Workbench.
2. **Servidor:** Desplegar el proyecto en Apache Tomcat 11.
3. **Credenciales:** La conexión está configurada con:
   - Usuario: `root`
   - Contraseña: `Luzemi07`

## Notas técnicas
- El proyecto utiliza el archivo `web.xml` para la configuración de los Servlets, asegurando compatibilidad con el estándar Jakarta EE.
- El login redirige las peticiones al `AuthServlet` para la validación de credenciales.