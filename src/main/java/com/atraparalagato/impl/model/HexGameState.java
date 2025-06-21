package com.atraparalagato.impl.model;

import com.atraparalagato.base.model.GameState;

import java.util.HashMap;
import java.util.Map;

/**
 * Estado del juego para Hex.
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

    @Override
    protected boolean canExecuteMove(HexPosition position) {
        return gameBoard.isValidMove(position);
    }

    @Override
    protected boolean performMove(HexPosition position) {
        if (gameBoard.isValidMove(position)) {
            gameBoard.executeMove(position); // bloquea la celda
            return true;
        }
        return false;
    }

    @Override
    protected void updateGameStatus() {
        if (isCatAtBorder()) {
            setStatus(GameStatus.PLAYER_LOST);   // El gato escapó
        } else if (isCatTrapped()) {
            setStatus(GameStatus.PLAYER_WON);    // El gato está atrapado
        } else {
            setStatus(GameStatus.IN_PROGRESS);
        }
    }

    private boolean isCatAtBorder() {
        int q = catPosition.getQ(), r = catPosition.getR(), s = catPosition.getS();
        return Math.abs(q) == boardSize
            || Math.abs(r) == boardSize
            || Math.abs(s) == boardSize;
    }

    private boolean isCatTrapped() {
        return gameBoard.getAdjacentPositions(catPosition)
                        .stream()
                        .allMatch(gameBoard::isBlocked);
    }

    @Override
    public HexPosition getCatPosition() {
        return catPosition;
    }

    @Override
    public void setCatPosition(HexPosition position) {
        this.catPosition = position;
        updateGameStatus();
        // Disparamos la notificación: si terminó, llamará a onGameEnded
        notifyStateChanged();
    }

    /** Ahora devuelve true si el estado ya no es IN_PROGRESS */
    @Override
    public boolean isGameFinished() {
        return getStatus() != GameStatus.IN_PROGRESS;
    }

    /** Devuelve true sólo si el jugador (las paredes) ha ganado */
    @Override
    public boolean hasPlayerWon() {
        return getStatus() == GameStatus.PLAYER_WON;
    }

    @Override
    public int calculateScore() {
        // Opcional: puedes basarlo en moveCount u otras métricas
        return moveCount;
    }

    @Override
    public Object getSerializableState() {
        Map<String,Object> m = new HashMap<>();
        m.put("catPosition", catPosition);
        m.put("blocked", gameBoard.getBlockedPositions());
        m.put("status", getStatus());
        return m;
    }

    @Override
    public void restoreFromSerializable(Object serializedState) {
        // Implementa si guardas/recuperas estado
    }

    public HexGameBoard getGameBoard() {
        return gameBoard;
    }
}


