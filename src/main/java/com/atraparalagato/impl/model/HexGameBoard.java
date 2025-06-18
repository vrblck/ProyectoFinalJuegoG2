package com.atraparalagato.impl.model;

import com.atraparalagato.base.model.GameBoard;
import java.util.*;
import java.util.function.Predicate;

public class HexGameBoard extends GameBoard<HexPosition> {
    private final int size;
    private final Set<HexPosition> blocked = new HashSet<>();

    public HexGameBoard(int size) {
        super(size);
        this.size = size;
    }

    @Override
    public Set<HexPosition> initializeBlockedPositions() {
        return new HashSet<>();
    }

    @Override
    public boolean isPositionInBounds(HexPosition pos) {
        int q = pos.getQ();
        int r = pos.getR();
        int s = -q - r;
        int radius = (size - 1) / 2;
        return Math.abs(q) <= radius && Math.abs(r) <= radius && Math.abs(s) <= radius;
    }

    @Override
    public boolean isValidMove(HexPosition pos) {
        return isPositionInBounds(pos) && !blocked.contains(pos);
    }

    @Override
    public void executeMove(HexPosition pos) {
        if (isValidMove(pos)) {
            blocked.add(pos);
        }
    }

    @Override
    public List<HexPosition> getPositionsWhere(Predicate<HexPosition> predicate) {
        List<HexPosition> result = new ArrayList<>();
        int radius = (size - 1) / 2;
        for (int q = -radius; q <= radius; q++) {
            for (int r = -radius; r <= radius; r++) {
                int s = -q - r;
                if (Math.abs(s) <= radius) {
                    HexPosition pos = new HexPosition(q, r);
                    if (predicate.test(pos)) {
                        result.add(pos);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<HexPosition> getAdjacentPositions(HexPosition pos) {
        int[][] directions = {
            {1, 0}, {1, -1}, {0, -1},
            {-1, 0}, {-1, 1}, {0, 1}
        };
        List<HexPosition> adj = new ArrayList<>();
        for (int[] dir : directions) {
            HexPosition neighbor = new HexPosition(pos.getQ() + dir[0], pos.getR() + dir[1]);
            if (isPositionInBounds(neighbor)) {
                adj.add(neighbor);
            }
        }
        return adj;
    }

    public boolean isBlocked(HexPosition pos) {
        return blocked.contains(pos);
    }
}