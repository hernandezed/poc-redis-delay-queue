# POC Redis Delayed Queue

Esta prueba busca ejemplificar como realizar una cola de delay sin hacer uso de Redisson o RQueue.

## Porque usar una "cola" en Redis?

La realidad es que Redis no tiene un tipo de datos que soporte una cola de delay como RabbitMQ o IBM MQ. Pero en algunas
situaciones (cuando no contamos con facil acceso a estas herramientas) puede salvarnos el tener un Redis

## Porque no usar Redisson?

Basado en mi experiencia laboral, donde utilice Redisson, este trajo mas problemas que beneficios. Se ha reportado
multiples veces un mismo bug, en diferentes versiones a pesar de indicar que se habia arreglado, donde una vez perdida
la conexion con el Redis, no reconectaba.

Multiples veces vi como procesos fallaban por no poder adquirir un lock distribuido que teniamos en Redis

## Porque no usar RQueue?

RQueue es una buena implementacion de colas en Redis. El problema viene desde el momento que uno ve las dependencias que
trae su starter de spring boot. Por default, este trae no solo SpringMVC (con una version fija), sino que trae Spring
Webflux entre otras.

A veces, por no decir que siempre deberiamos hacerlo, debemos hacer que nuestros jars sean lo mas pequeños posibles
(y usar todo lo que importemos en el). Si vas a usar Reactive y WebMVC en un mismo aplicativo (why? T_T) te recomiendo
que uses RQueue.

## Como iniciar este proyecto?

Lo primero, antes de poder darle run al aplicativo es levantar la infraestructura necesaria para darle soporte.
Especificamente necesitamos tener corriendo 2 cosas en nuestra maquina:

- MariaDB
- Redis

Si estas en un entorno con Docker, te dejo tambien los comandos para levantar este entorno lo mas rapido posible

- MariaDB

```shell
docker run -d -e MARIADB_ROOT_USER=test -e MARIADB_ROOT_PASSWORD=test -p3306:3306 bitnami/mariadb:latest
```

- Redis

```shell
docker run -d -e ALLOW_EMPTY_PASSWORD=yes -e REDIS_PORT_NUMBER=7000 -p 7000:7000 bitnami/redis:latest
```

Despues de tener estas dos bases de datos, ya podes darle run al aplicativo.

### Que servicios se pueden usar?
La documentacion de los endpoints puede revisarse en:

http://localhost:5600/redis-delay/swagger-ui/index.html?configUrl=/redis-delay/v3/api-docs/swagger-config#/
