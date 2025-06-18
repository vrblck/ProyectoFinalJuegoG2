package com.atraparalagato.impl.strategy;

import com.atraparalagato.base.model.GameBoard;
import com.atraparalagato.base.strategy.CatMovementStrategy;
import com.atraparalagato.impl.model.HexPosition;

public class BFSCatMovement implements CatMovementStrategy<HexPosition> {
    @Override
    public HexPosition getNextMove(HexPosition catPosition, GameBoard<HexPosition> board) {
        return catPosition;
    }
}