package com.atraparalagato.impl.model;

import com.atraparalagato.base.model.GameState;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementación esqueleto de GameState para tableros hexagonales.
 * 
 * Los estudiantes deben completar los métodos marcados con TODO.
 * 
 * Conceptos a implementar:
 * - Estado del juego más sofisticado que ExampleGameState
 * - Sistema de puntuación avanzado
 * - Lógica de victoria/derrota más compleja
 * - Serialización eficiente
 * - Manejo de eventos y callbacks
 */
public class HexGameState extends GameState<HexPosition> {
    private final int boardSize;
    private final HexGameBoard gameBoard;
    private HexPosition catPosition;

    public HexGameState(String gameId, int boardSize) {
        super(gameId);
        this.boardSize = boardSize;
        this.gameBoard = new HexGameBoard(boardSize);
        this.catPosition = new HexPosition(0, 0);
        setStatus(GameStatus.IN_PROGRESS);
    }

    @Override
    public boolean canExecuteMove(HexPosition pos) {
        return gameBoard.isValidMove(pos);
    }

    @Override
    public boolean performMove(HexPosition pos) {
        if (!canExecuteMove(pos)) return false;
        gameBoard.executeMove(pos);
        updateGameStatus();
        return true;
    }

    @Override
    public void updateGameStatus() {
        // Implementación mínima
    }

    @Override
    public HexPosition getCatPosition() {
        return catPosition;
    }

    @Override
    public void setCatPosition(HexPosition pos) {
        this.catPosition = pos;
    }

    @Override
    public boolean isGameFinished() {
        return false;
    }

    @Override
    public boolean hasPlayerWon() {
        return false;
    }

    @Override
    public int calculateScore() {
        return 0;
    }

    @Override
    public Map<String, Object> getSerializableState() {
        Map<String, Object> map = new HashMap<>();
        map.put("catQ", catPosition.getQ());
        map.put("catR", catPosition.getR());
        map.put("boardSize", boardSize);
        return map;
    }

    @Override
    public void restoreFromSerializable(Object state) {
        Map<String, Object> map = (Map<String, Object>) state;
        int q = (int) map.get("catQ");
        int r = (int) map.get("catR");
        this.catPosition = new HexPosition(q, r);
    }

    public HexGameBoard getGameBoard() {
        return gameBoard;
    }

    public Map<String, Object> getAdvancedStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("moveCount", getMoveCount());
        // Agrega más estadísticas si lo deseas
        return stats;
    }
}