# README

- [Información del proyecto](#información-del-proyecto)
- [Cómo ejecutar el código](#cómo-ejecutar-el-código)
  - [Ejecución directa](#ejecución-directa)
  - [Ejecución dockerizada](#ejecución-dockerizada)
- [Llamada al endpoint](#llamada-al-endpoint)
  - [Swagger](#swagger)

## Información del proyecto

El proyecto es una prueba técnica. La tarea consiste en la creación de un endopoint REST capaz de recuperar y transformar datos de una base de datos H2.

El endpoint debe aceptar como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Debe devolver como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

>[!NOTE]
>La aplicación se ha creado utilizando las versiones :
>
> - Maven 3.9.9
>
> - SpringBoot 3.2.9
>
> - Java 17.0.11

## Cómo ejecutar el código

Para lanzar la aplicación se pueden utilizar dos métodos :

### Ejecución directa

Desde el directorio raíz utilizar el comando `mvn spring-boot:run`

>[!IMPORTANT]
>Será necesario tener el sistema configurado con las versiones especificadas en el punto anterior).

### Ejecución dockerizada

1. Tener en ejecución el _daemon docker_ :
   - Windows : Ejecutar Docker Desktop
   - Linux : Ejecutar  `systemctl start docker` (si no está ya en ejecución)
2. Construir la imagen :
    - Docker build : `docker build -t ivan-duran:latest .`
    - Docker compose : `docker compose build`
3. Lanzar el contenedor de la imagen :
    - Docker run : `docker run -p 8080:8080 ivan-duran:latest`
    - Docker compose : `docker compose up`

## Llamada al endpoint

Ejecutar una llamada `GET` (utilizando el navegador o una herramienta como postman) al endpoint correspondiente.

- Endpoint : `/prices`
- QueryParams :
  - startDate : `yyyy-mm-dd-hh.mm.ss`
  - productId : valor numérico
  - brandId : valor numérico

>[!TIP]
>Ejemplo de llamada válida :
>
> <http://localhost:8080/prices?startDate=2020-06-14-00.00.00&productId=35455&brandId=1>

### Swagger

La aplicación dispone de [documentación Swagger](http://localhost:8080/swagger-ui/index.html) (acceso sólo disponible con la aplicación en ejecución).
