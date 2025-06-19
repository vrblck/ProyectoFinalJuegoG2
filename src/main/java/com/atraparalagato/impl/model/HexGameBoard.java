package com.atraparalagato.impl.model;

import com.atraparalagato.base.model.GameBoard;
import java.util.*;
import java.util.function.Predicate;

/**
 * Clase concreta para el tablero del juego Hex.
 */
public class HexGameBoard extends GameBoard<HexPosition> {
    public HexGameBoard(int size) { super(size); }

    @Override protected Set<HexPosition> initializeBlockedPositions() { return new HashSet<>(); }
    @Override public boolean isPositionInBounds(HexPosition position) { return true; }
    @Override protected boolean isValidMove(HexPosition position) { return true; }
    @Override protected void executeMove(HexPosition position) {}
    @Override public List<HexPosition> getPositionsWhere(Predicate<HexPosition> condition) { return new ArrayList<>(); }
    @Override public List<HexPosition> getAdjacentPositions(HexPosition position) { return new ArrayList<>(); }
    @Override public boolean isBlocked(HexPosition position) { return false; }
}