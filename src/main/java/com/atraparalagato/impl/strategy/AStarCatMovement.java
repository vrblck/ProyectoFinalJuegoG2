package com.atraparalagato.impl.strategy;

import com.atraparalagato.base.model.GameBoard;
import com.atraparalagato.base.strategy.CatMovementStrategy;
import com.atraparalagato.impl.model.HexPosition;

import java.util.ArrayList;
import java.util.List;

public class AStarCatMovement extends CatMovementStrategy<HexPosition> {
    public AStarCatMovement(GameBoard<HexPosition> board) {
        super(board);
    }

    @Override
    public List<HexPosition> getFullPath(HexPosition from, HexPosition to) {
        // Implementa la lógica aquí
        return new ArrayList<>();
    }

    @Override
    public boolean hasPathToGoal(HexPosition currentPosition) {
        // Implementación mínima (puedes mejorarla)
        return true;
    }

    @Override
    protected double getMoveCost(HexPosition from, HexPosition to) {
        // Costo uniforme, por ejemplo:
        return 1.0;
    }

    @Override
    protected java.util.function.Predicate<HexPosition> getGoalPredicate() {
        // Ejemplo: objetivo es llegar al borde del tablero
        return pos -> {
            int size = board.getSize();
            return Math.abs(pos.getQ()) >= size || Math.abs(pos.getR()) >= size || Math.abs(pos.getS()) >= size;
        };
    }

    @Override
    protected java.util.function.Function<HexPosition, Double> getHeuristicFunction(HexPosition goal) {
        return pos -> (double) (Math.abs(pos.getQ() - goal.getQ()) + Math.abs(pos.getR() - goal.getR()));
    }

    @Override
    protected HexPosition selectBestMove(
        java.util.List<HexPosition> candidates,
        HexPosition from,
        HexPosition goal
    ) {
        // Ejemplo simple: retorna el primero (ajusta según tu lógica)
        return candidates.isEmpty() ? null : candidates.get(0);
    }
}