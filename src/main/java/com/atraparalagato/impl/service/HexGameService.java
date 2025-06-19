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
import java.util.function.Function;
import java.util.function.Supplier;

public class HexGameService extends GameService<HexPosition> {

    public HexGameService() {
        super(
            new HexGameBoard(9), // GameBoard<HexPosition>
            new BFSCatMovement(new HexGameBoard(9)), // CatMovementStrategy<HexPosition>
            new H2GameRepository(), // DataRepository<HexGameState, String>
            () -> UUID.randomUUID().toString(), // Supplier<String>
            HexGameBoard::new, // Function<Integer, GameBoard<HexPosition>>
            id -> new HexGameState(id, 9) // Function<String, GameState<HexPosition>>
        );
    }

    @Override
    protected void initializeGame(GameState<HexPosition> gameState, GameBoard<HexPosition> board) {
        // Implementación mínima
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
}