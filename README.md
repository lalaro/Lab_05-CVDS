### Escuela Colombiana de Ingeniería

### Ciclo de Vida de Desarrollo de Software

## INTEGRANTES
# Laura Valentina Rodríguez - Juan Pablo Fernandez

## RESPUESTAS 

## PARTE I. - JUGANDO A SER UN CLIENTE HTTP
1. Abra una terminal Linux o consola de comandos Windows.
2. Realice una conexión síncrona TCP/IP a través de Telnet al siguiente servidor:
- Host: www.escuelaing.edu.co
- Puerto: 80
Teniendo en cuenta los parámetros del comando telnet:

```sh
$ telnet HOST PORT
```

3. Antes de que el servidor cierre la conexión por falta de comunicación:

Revise el RFC del protocolo HTTP, sobre cómo realizar una petición GET.
Debe lucir más o menos de esta forma:

```yml
GET /with-a-resource.html HTTP/1.0
Host: www.escuelaing.edu.co
```

Con esto, solicite al servidor el recurso `sssss/abc.html`, usando la versión 1.0 de HTTP.
Copie las dos lineas de codigo con el recurso agregado y peguelas en la consola del servidor ya abierta.
Asegúrese de presionar ENTER dos veces después de ingresar el comando.

Revise el resultado obtenido.

- ¿Qué codigo de error sale?, revise el significado del mismo en la lista de códigos de estado HTTP.
R/ Solo se ve la pantalla en negro
- ¿Qué otros códigos de error existen?
R/
____________________________________________________________________________________________________________________________________
1xx (Respuestas informativas): Indican que la solicitud ha sido recibida y el servidor continúa procesando la solicitud.

100 Continue
101 Switching Protocols
102 Processing

____________________________________________________________________________________________________________________________________
2xx (Respuestas exitosas): Indican que la solicitud fue recibida, comprendida, aceptada y procesada correctamente por el servidor.

200 OK
201 Created
202 Accepted
204 No Content
206 Partial Content

____________________________________________________________________________________________________________________________________
3xx (Redirecciones): Indican que se necesita tomar más acciones para completar la solicitud.

300 Multiple Choices
301 Moved Permanently
302 Found
304 Not Modified
307 Temporary Redirect
308 Permanent Redirect

____________________________________________________________________________________________________________________________________
4xx (Errores del cliente): Indican que hubo un error en la solicitud del cliente.

400 Bad Request
401 Unauthorized
403 Forbidden
404 Not Found
405 Method Not Allowed
408 Request Timeout
429 Too Many Requests

____________________________________________________________________________________________________________________________________
5xx (Errores del servidor): Indican que hubo un error en el servidor al procesar la solicitud.

500 Internal Server Error
501 Not Implemented
502 Bad Gateway
503 Service Unavailable
504 Gateway Timeout

- ¿En qué caso se manejarán?

1. Respuestas exitosas (2xx):

En el caso de una respuesta exitosa, generalmente se procesa la respuesta y se continúa con el flujo normal de la aplicación.

2. Redirecciones (3xx):

En el caso de una redirección, el cliente puede seguir automáticamente la nueva URL proporcionada o informar al usuario y permitirle decidir si seguir o no la redirección.

3. Errores del cliente (4xx):

Los errores del cliente, como el error 400 (Solicitud incorrecta) o el error 404 (No encontrado), pueden manejarse informando al usuario sobre el error y proporcionando una forma de corregirlo (por ejemplo, ingresando una URL válida).
En algunos casos, es posible que se quiera registrar estos errores para análisis posteriores o para solucionar problemas.

5. Errores del servidor (5xx):

Los errores del servidor, como el error 500 (Error interno del servidor), generalmente se manejan mostrando un mensaje de error genérico al usuario y notificando al equipo de desarrollo para investigar y solucionar el problema.

- Responder en el README.md según lo indicado en la última sección de este laboratorio (ENTREGA).

4. Realice una nueva conexión con telnet, esta vez a:
```yml
Host: www.httpbin.org
Puerto: 80
Versión HTTP: 1.1
```

Ahora, solicite (GET) el recurso /html. 
¿Qué se obtiene como resultado?
No se ve nada por la pantalla en negro, que muestro adelante

¡Muy bien!, ¡Acaba de usar del protocolo HTTP sin un navegador Web!. Cada vez que se usa un navegador, éste se conecta a un servidor HTTP, envía peticiones
del protocolo HTTP, espera el resultado de las mismas, y si se trata de contenido HTML lo interpreta y dibuja.

5. Seleccione el contenido HTML de la respuesta y copielo al cortapapeles `CTRL-SHIFT-C`. Ejecute el comando wc (word count) para contar palabras con la
opción -c para contar el número de caracteres:

```sh
$ wc -c
```

Pegue el contenido del portapapeles con `CTRL-SHIFT-V` y presione `CTRL-D` (fin de archivo de Linux). Si no termina el comando `wc` presione `CTRL-D`
de nuevo. No presione mas de dos veces CTRL-D indica que se termino la entrada y puede cerrarle la terminal. Debe salir el resultado de la cantidad de
caracteres que tiene el contenido HTML que respondió el servidor.


Para los puntos anteriores las conexiones salen así, por eso no se puede verificar la info

<img src="Image5.jpeg" alt="i5" width="1000"/>

(El servidor se queda conectando, se queda en negro la pantalla)
El profe dijo que le iba preguntar a Aurorita


Claro está, las peticiones GET son insuficientes en muchos casos. Investigue: ¿Cuál esla diferencia entre los verbos GET y POST? ¿Qué otros tipos de
peticiones existen?
7. En la practica no se utiliza telnet para hacer peticiones a sitios web sino el comando curl con ayuda de la linea de comandos: 
```sh
$ curl "www.httpbin.org"
```

<img src="Image6.jpeg" alt="i6" width="1000"/>


Utilice ahora el parámetro -v y con el parámetro -i:

```sh
$ curl -v www.httpbin.org
$ curl -i www.httpbin.org
```

<img src="Image7.jpeg" alt="i7" width="1000"/>

¿Cuáles son las diferencias con los diferentes parámetros?

a) curl "www.httpbin.org": Este comando realiza una solicitud GET a www.httpbin.org. Se recibe una respuesta con un código de estado 200, lo que significa que la solicitud fue exitosa. El cuerpo de la respuesta es un HTML que contiene información sobre el sitio web httpbin.org.

b) curl -v www.httpbin.org: Este comando realiza una solicitud GET a www.httpbin.org en modo  ( información sobre el número total de errores emitidos por el compilador). Se recibe una respuesta con un código de estado 200, indicando que la solicitud fue exitosa. Además, se muestra información detallada sobre la conexión, los encabezados y el contenido de la respuesta.

c) curl -i www.httpbin.org: Este comando realiza una solicitud GET a www.httpbin.org e incluye los encabezados de la solicitud en la salida. Se recibe una respuesta con un código de estado 200, indicando que la solicitud fue exitosa. Además, se muestran los encabezados de la respuesta junto con el contenido.

## PARTE II. - HACIENDO UNA APLICACIÓN WEB DINÁMICA USANDO EL PATRÓN MVC
En este ejercicio, va a implementar una aplicación Web muy básica, haciendo uso de spring MVC.

Para esto usaremos la documentación oficial de Spring con que que aprenderemos las funciones básicas de este framework https://spring.io/guides/gs/serving-web-content/

Para compilar la aplicación Web 

```
mvn spring-boot:run
```

<img src="Image1.jpeg" alt="i1" width="1000"/>

Crear el archivo .jar 

```
java -jar serving-web-content-initial-0.0.1-SNAPSHOT.jar
```

<img src="Image2.jpeg" alt="i2" width="1000"/>

Lo que está en nuestra aplicación Web es:

<img src="Image3.jpeg" alt="i3" width="1000"/>

Y así se verá: 

```
http://localhost:8080/greeting?name=laura
```
o
```
http://localhost:8080/greeting
```
<img src="Image4.jpeg" alt="i4" width="1000"/>

Después de terminar el aprendizaje analice: 
- ¿Por qué MVC obtiene ese nombre? (puede apoyarse de https://www.javatpoint.com/spring-mvc-tutorial) 

MVC (Modelo-Vista-Controlador) es un patrón de diseño arquitectónico que separa una aplicación en tres componentes principales: Modelo, Vista y Controlador. Obtiene su nombre de la abreviatura de estos tres componentes:

1. Modelo (Model): Representa los datos y la lógica de negocio de la aplicación. Es responsable de interactuar con la base de datos y manejar la lógica empresarial.

2. Vista (View): Es la interfaz de usuario con la que interactúa el usuario. Presenta los datos al usuario de una manera legible y comprensible.

3. Controlador (Controller): Actúa como intermediario entre el Modelo y la Vista. Responde a las solicitudes del usuario, manipula los datos entrantes, realiza la lógica de negocio y finalmente selecciona la Vista adecuada para mostrar al usuario.


- ¿Cuáles son las ventajas de usar MVC? 
1. Soporte para multiples interfaces de usuario
2. Facilidad de pruebas
3. Separación de responsabilidades
4. Reutilización de código

- ¿Qué diferencia tiene la estructura de directorios de este proyecto comparado con las de proyectos pasados (con solo maven y java EE)? 
Para la parte de construcción de directorios, es importante tener en cuenta que ahora se tiene un nuevo directorio en src/main/*resources*
    /resources
        /static 
        /templates


- ¿Qué anotaciones usaste y cuál es la diferencia entre ellas?
1. @Controller: Esta anotación se utiliza para marcar una clase como un controlador en el patrón MVC de Spring. Indica que la clase define métodos de controlador que manejan las solicitudes entrantes.

2. @GetMapping: Esta anotación se utiliza para asignar solicitudes HTTP GET a métodos de controlador específicos. Es una forma conveniente de configurar los métodos de controlador para manejar solicitudes GET sin la necesidad de configuración adicional.

3. @RequestParam: Esta anotación se utiliza para vincular parámetros de solicitud a parámetros de método en un controlador de Spring MVC. Permite acceder a los valores de los parámetros de solicitud (como los parámetros de consulta) dentro de los métodos de controlador.

## PARTE III. - APLICACIÓN MVC PARA CONSUMO DE SERVICIO RESTful
Usando la arquitectura MVC del punto anterior (el proyecto anterior), realice una aplicación simple qué permita navegar gráficamente sobre esta API https://jsonplaceholder.typicode.com/todos/1, puede guiarse de tutoriales como https://medium.com/@nutanbhogendrasharma/consume-rest-api-in-spring-boot-web-application-354c404850f0

Luego de terminada esta parte responda: - ¿Qué es RESTful? - Si utilizo un framework como Boostrap CSS para qué el apartado gráfico se vea más profesional, ¿en qué capa se haría su uso?