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
```
## Diagrama de flujo del juego

```
[Inicio]
   |
   v
[Petición HTTP al endpoint /api/game/...]
   |
   v
¿game.use-example-implementation = true?
   |                         |
  Sí                        No
   |                         |
   v                         v
[Usar lógica de ejemplo]   [Usar lógica de estudiantes]
   |                         |
   v                         v
[Servicios y lógica de     [Servicios y lógica de
 ExampleGameService]        HexGameService]
   |                         |
   v                         v
[Actualizar estado del     [Actualizar estado del
 juego, mover gato, etc.]   juego, mover gato, etc.]
   |                         |
   v                         v
[Construir respuesta       [Construir respuesta
 con datos del juego]       con datos del juego]
   |                         |
   v                         v
[Enviar respuesta HTTP al frontend]
   |
   v
[Fin]
```
## API Endpoints

- `POST /api/game/start?boardSize={size}`: Inicia un nuevo juego
- `POST /api/game/block?gameId={id}&q={q}&r={r}`: Bloquea una celda y mueve al gato
- `GET /api/game/state/{gameId}`: Obtiene el estado actual del juego # ProyectoFinalJuego
