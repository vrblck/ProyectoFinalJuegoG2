package com.atraparalagato.impl.service;

import com.atraparalagato.base.model.GameBoard;
import com.atraparalagato.base.model.GameState;
import com.atraparalagato.base.repository.DataRepository;
import com.atraparalagato.base.service.GameService;
import com.atraparalagato.impl.model.HexGameBoard;
import com.atraparalagato.impl.model.HexGameState;
import com.atraparalagato.impl.model.HexPosition;
import com.atraparalagato.impl.repository.H2GameRepository;
import com.atraparalagato.base.strategy.CatMovementStrategy;
import com.atraparalagato.impl.strategy.BFSCatMovement;

import java.util.Optional;
import java.util.UUID;

public class HexGameService extends GameService<HexPosition> {

    private static int lastBoardSize = 9;

    public HexGameService() {
        super(
            new HexGameBoard(9),
            new BFSCatMovement(new HexGameBoard(9)),
            new H2GameRepository(),
            () -> UUID.randomUUID().toString(),
            HexGameBoard::new,
            id -> new HexGameState(id, HexGameService.lastBoardSize) // acceso estático correcto
        );
    }

    // Método para actualizar el tamaño antes de crear el juego
    public void setLastBoardSize(int boardSize) {
        HexGameService.lastBoardSize = boardSize;
    }

    @Override
    protected void initializeGame(GameState<HexPosition> gameState, GameBoard<HexPosition> board) {
        // Inicializa el estado del juego como en el ejemplo
        if (gameState instanceof HexGameState hexGameState && board instanceof HexGameBoard hexBoard) {
            
            hexGameState.setCatPosition(new HexPosition(0, 0));
        }
        // Puedes agregar callbacks si lo deseas
        gameState.setOnStateChanged(gs -> {});
        gameState.setOnGameEnded(gs -> {});
    }

    @Override
    protected HexPosition getTargetPosition(GameState<HexPosition> gameState) {
        // Implementación mínima
        return null;
    }

    @Override
    public Object getGameStatistics(String gameId) {
        // Implementación mínima
        return null;
    }

    @Override
    public boolean isValidMove(String gameId, HexPosition position) {
        // Implementación mínima
        return false;
    }

    @Override
    public Optional<HexPosition> getSuggestedMove(String gameId) {
        // Implementación mínima
        return Optional.empty();
    }

    // NO sobrescribas startNewGame(int) ni accedas a lastBoardSize como this.lastBoardSize

    // Si necesitas exponer el estado del juego:
    public Optional<GameState<HexPosition>> getGameState(String gameId) {
        return super.loadGameState(gameId);
    }
}