package com.atraparalagato.impl.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexGameBoardTest {

    private HexGameBoard board;
    private HexPosition pos;

    @BeforeEach
    void setUp() {
        board = new HexGameBoard(3);
        pos = new HexPosition(1, -1);
    }

    @Test
    void testIsPositionInBounds() {
        assertTrue(board.isPositionInBounds(new HexPosition(0, 0)));
        assertTrue(board.isPositionInBounds(new HexPosition(2, -1)));
        assertFalse(board.isPositionInBounds(new HexPosition(4, -1)));
    }

    @Test
    void testExecuteMoveBlocksTile() {
        board.executeMove(pos);
        assertTrue(board.isBlocked(pos));
    }

    @Test
    void testIsValidMove() {
        assertTrue(board.isValidMove(pos));
        board.executeMove(pos);
        assertFalse(board.isValidMove(pos));
    }

    @Test
    void testGetAdjacentPositions() {
        HexPosition center = new HexPosition(0, 0);
        var adj = board.getAdjacentPositions(center);

        assertEquals(6, adj.size());
        assertTrue(adj.contains(new HexPosition(1, 0)));
        assertTrue(adj.contains(new HexPosition(0, 1)));
    }
}