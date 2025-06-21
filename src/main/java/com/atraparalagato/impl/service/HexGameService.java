package com.atraparalagato.impl.service;

import com.atraparalagato.base.model.GameBoard;
import com.atraparalagato.base.model.GameState;
import com.atraparalagato.base.repository.DataRepository;
import com.atraparalagato.base.service.GameService;
import com.atraparalagato.impl.model.HexGameBoard;
import com.atraparalagato.impl.model.HexGameState;
import com.atraparalagato.impl.model.HexPosition;
import com.atraparalagato.impl.repository.H2GameRepository;
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
            id -> new HexGameState(id, HexGameService.lastBoardSize)
        );
    }

    public void setLastBoardSize(int boardSize) {
        HexGameService.lastBoardSize = boardSize;
    }

    @Override
    protected void initializeGame(GameState<HexPosition> gameState, GameBoard<HexPosition> board) {
        if (gameState instanceof HexGameState hexState && board instanceof HexGameBoard) {
            // Posición inicial del gato en el centro
            hexState.setCatPosition(new HexPosition(0, 0));
            // No configurar aquí onGameEnded, se maneja en el hook override
        }
        // Opcional: otros callbacks
        gameState.setOnStateChanged(gs -> {});
    }

    @Override
    protected void executeCatMove(GameState<HexPosition> gs) {
        HexGameState state = (HexGameState) gs;
        HexGameBoard board = state.getGameBoard();

        BFSCatMovement strat = new BFSCatMovement(board);
        Optional<HexPosition> next = strat.findBestMove(state.getCatPosition(), null);

        next.ifPresent(pos -> {
            state.setCatPosition(pos);
            onCatMoved(state, pos);
        });
    }

    @Override
    protected void onGameEnded(GameState<HexPosition> gs) {
        HexGameState state = (HexGameState) gs;
        if (state.hasPlayerWon()) {
            System.out.println("¡Ganaste, atrapaste al gato!");
        } else {
            System.out.println("¡Perdiste, el gato escapó!");
        }
    }

    @Override
    protected HexPosition getTargetPosition(GameState<HexPosition> gameState) {
        // No utilizado por BFSCatMovement
        return null;
    }

    @Override
    public Object getGameStatistics(String gameId) {
        return null;
    }

    @Override
    public boolean isValidMove(String gameId, HexPosition position) {
        return false;
    }

    @Override
    public Optional<HexPosition> getSuggestedMove(String gameId) {
        return Optional.empty();
    }

    public Optional<GameState<HexPosition>> getGameState(String gameId) {
        return super.loadGameState(gameId);
    }
}


