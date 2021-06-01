# XMEN-WS
Este proyecto sirve para que identifiques si una secuencia de ADN enviada corresponde a un mutante o no. Adicional te provee estadisticas de las verificaciones realizadas.

## Guia del proyecto
Encontraras toda la información de pruebas y contenido general del proyecto en este repositorio:
| Descripción | Ruta |
| ------ | ------ |
| Especificaciones | /Project documents |
| Pruebas de carga | /Project documents/Load testing |
| Cobertura de pruebas | /Project documents/Test coverage |

## Proyecto en local
Este proyecto fue desarrollado en Java 8 con Spring Boot en el IDE STS (Spring Tool Suite), mas sin embargo su base es maven. Por lo cual si requieres ejecutarlo en local debes:
* Clonar el proyecto
* Ya descargado, configurarlo en un IDE y realizar la respectiva ejecución.
* En caso de que no desees en IDE y si cuentas con Maven instalado, basta con posicionarte en el directorio raiz y jecutar el siguiente comando: 
  ```sh
  mvnw.cmd spring-boot:run
  ```
  En caso de querer iniciarlo sin los test cases:
  ```sh
  mvnw.cmd spring-boot:run -Dmaven.test.skip=true
  ```
* Por defecto esta configurado con el puerto **8080**, pero lo puedes cambiar en el archivo de ***application.properties*** agregando o modificando la siguiente propiedad:
  ```sh
  server.port=8081
  ```
* Verificas que todo haya corrido bien observando si sale algo como esto:
  ![Iniciar](/Project documents/Screenshots/Iniciar.jpg "Iniciando...")
* Para terminar basta con que vayas al navegador y ejecutes **localhost:8080** o el puerto que configuraste:
  ![Ejecutar](/Project documents/Screenshots/Funcionando.jpg "Ejecutando...")

## Proyecto en la nube
Este protecto ha sido subido a la nube y lo puedes usar:
| Descripción | EndPoint |
| ------ | ------ |
| Verificar ADN | https://xmen-ws.rj.r.appspot.com/mutant |
| Obtener las estadisticas | https://xmen-ws.rj.r.appspot.com/stats |