# Project: banco-ripley-back

Challenge to apply as a Senior Full Stack in Banco Ripley

Problem to solve:

```
Desarrollar una nueva HU:
La HU nos habla de desarrollar una aplicación en que permita autenticar a clientes de un banco, y
recuperar la password de clientes que la hayan olvidado., para esto se debe generar:
* Los Microservicios API Rest necesarios para autenticar un cliente y recuperar la password
de cliente.
* ✅ El Desarrollo de Microservicios usar java Sprint boot
* ✅ En el Front End, desarrollar una aplicación web que permita las dos funcionalidades
mencionadas. trabajar con Framework React.js
* ✅ Usar como base datos la base datos de gusto (relacional o no relacional).
* ❌ Armar una arquitectura AWS que soporte la solución y señalar las buenas practicas
conocidas de desarrollo y despliegue continuos.
* ✅ Entorno de desarrollo
    Entorno Java (Spring boot)
        # mvn -v
        Apache Maven 3.0.5 (Red Hat 3.0.5-17)
        Maven home: /usr/share/maven
        Java version: 1.8.0_265, vendor: Oracle Corporation
        Java home: /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.265.b01-1.amzn2.0.1.x86_64/jre
        Default locale: en_US, platform encoding: UTF-8
        OS name: "linux", version: "4.14.200-155.322.amzn2.x86_64", arch: "amd64",
        family: "unix"
    Entorno React.js
        # node -v
        v12.19.0
        # npm -v
        6.14.8`
```

# Commands

### Locally

Needs to have Maven installed

* The first time need to install dependencies: `mvn install`
* Run this command on the terminal: `mvn spring-boot:run`
    * Call to any endpoint in: `http://localhost:8080/`
* To run the tests: `mvn test`

### Docker

* To build the image: `docker build -t ripley-back .`
* To run the image: `docker run -p 8080:8080 -t ripley-back`
* Call to any endpoint in: `http://localhost:8080/`

# Tools used in this project:

* [JUnit 5](https://junit.org/junit5/)
* [Lombok](https://projectlombok.org/)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [H2](https://www.h2database.com/)
* [Docker](https://www.docker.com/)
