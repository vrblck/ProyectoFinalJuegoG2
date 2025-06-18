package com.atraparalagato.impl.service;

import com.atraparalagato.base.service.GameService;
import com.atraparalagato.base.repository.DataRepository;
import com.atraparalagato.impl.model.HexGameBoard;
import com.atraparalagato.impl.model.HexGameState;
import com.atraparalagato.impl.model.HexPosition;
import com.atraparalagato.impl.repository.H2GameRepository;
import com.atraparalagato.base.strategy.CatMovementStrategy;
import com.atraparalagato.impl.strategy.AStarCatMovement;
import com.atraparalagato.impl.strategy.BFSCatMovement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementación esqueleto de GameService para el juego hexagonal.
 * 
 * Los estudiantes deben completar los métodos marcados con TODO.
 * 
 * Conceptos a implementar:
 * - Orquestación de todos los componentes del juego
 * - Lógica de negocio compleja
 * - Manejo de eventos y callbacks
 * - Validaciones avanzadas
 * - Integración con repositorio y estrategias
 */
public class HexGameService extends GameService<HexPosition, HexGameBoard, HexGameState> {

    private final DataRepository<HexGameState, String> repository;

    public HexGameService() {
        this.repository = new H2GameRepository();
    }

    /**
     * Crear un nuevo juego con configuración personalizada.
     */
    public HexGameState createGame(int boardSize, String difficulty, Map<String, Object> options) {
        if (boardSize < 5 || boardSize > 15) throw new IllegalArgumentException("Tamaño de tablero inválido");
        String gameId = UUID.randomUUID().toString();
        HexGameState state = new HexGameState(gameId, boardSize);
        // Puedes guardar la dificultad en el estado si lo deseas
        repository.save(state);
        return state;
    }

    /**
     * Ejecutar movimiento del jugador con validaciones avanzadas.
     */
    public Optional<HexGameState> executePlayerMove(String gameId, HexPosition position, String playerId) {
        Optional<HexGameState> optState = repository.findById(gameId);
        if (!optState.isPresent()) return Optional.empty();
        HexGameState state = optState.get();
        if (state.isGameFinished()) return Optional.of(state);
        if (!state.canExecuteMove(position)) return Optional.of(state);

        // Ejecutar movimiento del jugador
        state.performMove(position);

        // Mover el gato según la dificultad (aquí fijo "medium", puedes mejorarlo)
        CatMovementStrategy strategy = createMovementStrategy("medium", state.getGameBoard());
        HexPosition catNext = strategy.getNextMove(state.getCatPosition(), state.getGameBoard());
        state.setCatPosition(catNext);

        state.updateGameStatus();
        repository.save(state);
        return Optional.of(state);
    }

    /**
     * Obtener estado del juego con información enriquecida.
     */
    public Optional<Map<String, Object>> getEnrichedGameState(String gameId) {
        Optional<HexGameState> optState = repository.findById(gameId);
        if (!optState.isPresent()) return Optional.empty();
        HexGameState state = optState.get();
        Map<String, Object> enriched = new HashMap<>();
        enriched.put("gameId", state.getGameId());
        enriched.put("catPosition", state.getCatPosition());
        enriched.put("blockedCells", state.getGameBoard().getPositionsWhere(state.getGameBoard()::isBlocked));
        enriched.put("status", state.getStatus());
        enriched.put("moveCount", state.getAdvancedStatistics().get("moveCount"));
        enriched.put("playerWon", state.hasPlayerWon());
        enriched.put("score", state.calculateScore());
        return Optional.of(enriched);
    }

    /**
     * Crear la estrategia de movimiento del gato según dificultad.
     */
    @Override
    public CatMovementStrategy createMovementStrategy(String difficulty, HexGameBoard board) {
        if ("hard".equalsIgnoreCase(difficulty)) {
            return new AStarCatMovement();
        } else {
            return new BFSCatMovement();
        }
    }

    @Override
    public Optional<HexPosition> getSuggestedMove(String gameId) {
        // Implementación simple: no hay sugerencia
        return Optional.empty();
    }

    @Override
    public boolean isValidMove(String gameId, HexPosition position) {
        Optional<HexGameState> state = repository.findById(gameId);
        return state.map(s -> s.canExecuteMove(position)).orElse(false);
    }

    @Override
    public Map<String, Object> getGameStatistics(String gameId) {
        // Implementación simple
        return new HashMap<>();
    }

    @Override
    public HexPosition getTargetPosition(com.atraparalagato.base.model.GameState<HexPosition> state) {
        // Implementación simple: retorna null o el objetivo que desees
        return null;
    }

    @Override
    public void initializeGame(
        com.atraparalagato.base.model.GameState<HexPosition> state,
        com.atraparalagato.base.model.GameBoard<HexPosition> board
    ) {
        // Implementación vacía o lógica de inicialización si la necesitas
    }
}