# 💰 Ejemplo de Cajero Automático Estilo Mainframe 💰


## Evolución Adicional 📜

Posteriormente, este emocionante viaje al pasado financiero evolucionó hacia un modelo cliente-servidor utilizando la elegancia moderna de Swing. ¡Descubre cómo la nostalgia y la innovación se unen para crear una experiencia única! 🕹️📻📠

**Link de Balsamiq**:
    * https://balsamiq.cloud/sd9sfap/pe2c3l0

## Funcionalidad

**Práctica de Línea de Comandos: Simulación de un Cajero Automático**

**Objetivo:**  
Sumérgete en un viaje nostálgico al pasado con un programa de línea de comandos que simula un cajero automático al estilo de los mainframes.

**Instrucciones:**

1. **Iniciando el Programa 🚀**:
    * Al dar inicio al programa, se te pedirá ingresar un PIN y alias.
    * Si el PIN es incorrecto después de 3 intentos, el programa se cerrará con un mensaje de error.
    * Si el PIN es correcto, ¡desbloquearás el acceso al majestuoso menú principal!

2. **Menú Principal 📟**:
    * Imagina que estás en la terminal de un mainframe clásico, aquí tienes tus opciones:
        1. Consultar el tesoro 💰
        2. Hacer un depósito 💸
        3. Retirar fondos 💳
        4. Cambiar el código secreto 🔐
        5. Historial de Transacciones 💸
        6. Decir adiós 👋

3. **Consultar el Tesoro 📊**:
    * Las cifras aparecerán en la pantalla revelando tu saldo actual.

4. **Hacer un Depósito 📥**:
    * Observa cómo en la pantalla emerge un arte ASCII pidiéndote que ingreses la cantidad a depositar.
    * Una vez confirmado, tu saldo se actualizará y recibirás un "recibo" en el estilo de los viejos tiempos.

5. **Retirar Fondos 📤**:
    * Imagina que ves engranajes girando mientras introduces la cantidad que deseas retirar.
    * Luego de validar, los engranajes dan una vuelta completa y recibirás un "recibo" con la cantidad retirada.

6. **Cambiar el Código Secreto 🔑**:
    * Las luces destellantes en la consola indican que es hora de cambiar tu PIN.
    * Si logras ingresar tu PIN actual, podrás crear un nuevo código después de resolver "desafíos criptográficos".

7. **Ver tu récord de riquezas 💸**:
    * Tendrás acceso a ver todos los movimientos que hayas realizado en tu cuenta y sus respectivos montos.

8. **Decir Adiós 🏁**:
    * La pantalla te muestra un mensaje de despedida digno de las películas clásicas de ciencia ficción antes de cerrar el programa.

## Requisitos 🛠️

Asegúrate de tener instalado:

 - Java 11 📦
 - Maven 3.8.4 📦
 - MySQL 8.1.0 📦

Se recomienda usar `sdkman` en sistemas Linux para facilitar la instalación.

## Compilación 🛠️

Para compilar el proyecto:

```
mvn clean install
```

## Ejecución 🚀

```
mvn exec:java -Dexec.mainClass="bo.edu.ucb.sis213.swing.ATMApp"
```

## Instalación de la Base de Datos 💾

1. Inicia una instancia de MySQL en Docker:

```
docker run --name mysql-atmalf -e MYSQL_ROOT_PASSWORD=123456 -p 3307:3306 -d mysql:8
```

2. Conéctate a la base de datos:

```
docker exec -it mysql-atmalf mysql -u root -p
```

3. Crea la base de datos:

```
CREATE DATABASE atm;
```

4. Usa la base de datos:

```
USE atm;
```

5. Ejecuta el script `init.sql` de la carpeta `database`.
