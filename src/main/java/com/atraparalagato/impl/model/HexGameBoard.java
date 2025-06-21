package com.atraparalagato.impl.model;

import com.atraparalagato.base.model.GameBoard;
import java.util.*;
import java.util.function.Predicate;

public class HexGameBoard extends GameBoard<HexPosition> {

    public HexGameBoard(int size) {
        super(size);
    }

    /** Inicialmente no hay muros, se irán agregando con executeMove(...) */
    @Override
    protected Set<HexPosition> initializeBlockedPositions() {
        return new HashSet<>();
    }

    /** Comprueba que la posición esté dentro del “radio” del tablero */
    @Override
    public boolean isPositionInBounds(HexPosition pos) {
        int q = pos.getQ(), r = pos.getR(), s = -q - r;
        return Math.abs(q) <= size && Math.abs(r) <= size && Math.abs(s) <= size;
    }

    /** Un movimiento válido está dentro de bounds y no es un muro */
    @Override
    protected boolean isValidMove(HexPosition pos) {
        return isPositionInBounds(pos) && !isBlocked(pos);
    }

    /** Cuando el jugador coloca una pared, se agrega aquí */
    @Override
    protected void executeMove(HexPosition pos) {
        blockedPositions.add(pos);
    }

    /** Permite filtrar cualquier posición del tablero */
    @Override
    public List<HexPosition> getPositionsWhere(Predicate<HexPosition> condition) {
        List<HexPosition> result = new ArrayList<>();
        for (int q = -size; q <= size; q++) {
            for (int r = Math.max(-size, -q - size);
                 r <= Math.min(size, -q + size);
                 r++) {
                HexPosition p = new HexPosition(q, r);
                if (condition.test(p)) {
                    result.add(p);
                }
            }
        }
        return result;
    }

    /** Vecinos según tus direcciones (ya estaba bien) */
    @Override
    public List<HexPosition> getAdjacentPositions(HexPosition position) {
        int[][] dirs = {{1,0},{1,-1},{0,-1},{-1,0},{-1,1},{0,1}};
        List<HexPosition> adj = new ArrayList<>();
        for (int[] d : dirs) {
            HexPosition n = new HexPosition(position.getQ()+d[0],
                                            position.getR()+d[1]);
            if (isPositionInBounds(n)) adj.add(n);
        }
        return adj;
    }

    /** Ya NO puede ser siempre false: debe consultar el set de muros */
    @Override
    public boolean isBlocked(HexPosition pos) {
        return blockedPositions.contains(pos);
    }
}



