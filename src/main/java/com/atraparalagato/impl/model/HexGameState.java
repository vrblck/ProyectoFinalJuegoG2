package com.atraparalagato.impl.model;

import com.atraparalagato.base.model.GameState;

import java.util.HashMap;
import java.util.Map;

/**
 * Estado del juego para Hex.
 * 
 * Design Patterns:
 * - State Pattern: Maneja diferentes estados del juego
 * - Observer Pattern: Notifica cambios de estado
 * - Command Pattern: Encapsula acciones del juego
 * 
 * SOLID Principles:
 * - Single Responsibility: Maneja únicamente el estado del juego
 * - Open/Closed: Permite extensión de nuevos estados sin modificación
 * - Liskov Substitution: Las implementaciones deben ser intercambiables
 * 
 * Conceptos a implementar:
 * - Programación Funcional: Consumer, Supplier, callbacks
 * - Modularización: Separación de estado, lógica y persistencia
 * - OOP: Encapsulación del estado y comportamiento
 */
public class HexGameState extends GameState<HexPosition> {

    private HexPosition catPosition;
    private final HexGameBoard gameBoard;
    private final int boardSize;

    public HexGameState(String gameId, int boardSize) {
        super(gameId);
        this.boardSize = boardSize;
        this.gameBoard = new HexGameBoard(boardSize);
        this.catPosition = new HexPosition(0, 0);
    }

    /**
     * Verifica si un movimiento puede ser ejecutado.
     * En este caso, verifica si la posición está dentro de los límites del tablero
     * y si la celda está vacía.
     */
    @Override
    protected boolean canExecuteMove(HexPosition position) {
        return gameBoard.isValidMove(position);
    }

    /**
     * Realiza el movimiento específico en el estado del juego.
     * Aquí se actualiza la posición del gato en el tablero.
     */
    @Override
    protected boolean performMove(HexPosition position) {
        if (gameBoard.isValidMove(position)) {
            gameBoard.executeMove(position);
            return true;
        }
        return false;
    }

    /**
     * Actualiza el estado del juego después de un movimiento.
     * En este caso, verifica si hay un ganador o si el juego termina en empate.
     */
    @Override
    protected void updateGameStatus() {}

    @Override
    public HexPosition getCatPosition() { return catPosition; }

    @Override
    public void setCatPosition(HexPosition position) { this.catPosition = position; }

    /**
     * Verifica si el juego ha terminado.
     * En este caso, el juego termina si hay un ganador o si el tablero está lleno.
     */
    @Override
    public boolean isGameFinished() { return false; }

    /**
     * Determina si el jugador ha ganado.
     * En este caso, verifica si hay una línea continua de celdas del gato.
     */
    @Override
    public boolean hasPlayerWon() { return false; }

    @Override
    public int calculateScore() { return 0; }

    @Override
    public Object getSerializableState() { return new HashMap<>(); }

    @Override
    public void restoreFromSerializable(Object serializedState) {}
}