# Atrapar al Gato

Un juego web donde el objetivo es atrapar a un gato en un tablero hexagonal. El gato intentará escapar usando el mejor camino posible hacia el borde del tablero.

## Requisitos

- Java 17 o superior
- Maven 3.6 o superior

## Cómo ejecutar el proyecto

1. Clona el repositorio:
```bash
git clone https://github.com/tu-usuario/atrapar-al-gato.git
cd atrapar-al-gato
```

2. Compila y ejecuta el proyecto con Maven:
```bash
mvn spring-boot:run
```

3. Abre tu navegador y visita:
```
http://localhost:8081
```


## Cómo jugar

1. Haz clic en "Nuevo Juego" para comenzar una partida.
2. El gato (🐱) comenzará en el centro del tablero.
3. En cada turno, haz clic en una celda hexagonal para bloquearla.
4. El gato se moverá automáticamente intentando escapar.
5. Tu objetivo es atrapar al gato bloqueando todas sus posibles rutas de escape.
6. Si el gato llega al borde del tablero, habrá escapado y perderás.

## Tecnologías utilizadas

- Backend:
  - Java 17
  - Spring Boot 3.2.3
  - Maven

- Frontend:
  - HTML5
  - CSS3
  - JavaScript (Vanilla)


## Estructura del proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── atraparalagato/
│   │           ├── controller/
│   │           │   └── GameController.java
│   │           ├── model/
│   │           │   ├── GameState.java
│   │           │   └── HexPosition.java
│   │           ├── service/
│   │           │   └── GameService.java
│   │           └── AtraparAlGatoApplication.java
│   └── resources/
│       └── static/
│           ├── index.html
│           ├── styles.css
│           └── game.js
```
## Diagrama de flujo del juego

```
El siguiente diagrama de flujo representa la arquitectura general del proyecto Atrapar al Gato. En la parte superior se encuentra el frontend, encargado de la interacción con el usuario y la presentación visual. Las acciones del usuario se envían al backend a través de una API REST, gestionada por el controlador GameController.

El controlador delega las operaciones al servicio principal, HexGameService, que coordina la lógica del juego. Este servicio utiliza diferentes componentes de implementación: modelos para representar el estado del juego y el tablero, estrategias para el movimiento del gato y un repositorio para la persistencia de datos. Cada uno de estos componentes implementa una interfaz o clase base, lo que permite una arquitectura flexible y fácilmente extensible.

Este diseño modular facilita la separación de responsabilidades, el mantenimiento del código y la incorporación de nuevas funcionalidades en el futuro.
```
## API Endpoints

- `POST /api/game/start?boardSize={size}`: Inicia un nuevo juego
- `POST /api/game/block?gameId={id}&q={q}&r={r}`: Bloquea una celda y mueve al gato
- `GET /api/game/state/{gameId}`: Obtiene el estado actual del juego # ProyectoFinalJuego
