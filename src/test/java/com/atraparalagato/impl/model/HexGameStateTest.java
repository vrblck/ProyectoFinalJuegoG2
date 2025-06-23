package com.atraparalagato.impl.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HexGameStateTest {

    @Test
    public void testBlockingTiles() {
        HexGameBoard board = new HexGameBoard(3);

        HexPosition blockPos = new HexPosition(1, -1);
        assertFalse(board.isTileBlocked(blockPos));

        board.blockTile(blockPos);
        assertTrue(board.isTileBlocked(blockPos));
    }

    @Test
    public void testValidMovesAfterBlocking() {
        HexGameBoard board = new HexGameBoard(3);

        HexPosition blockPos = new HexPosition(0, 1);
        board.blockTile(blockPos);

        assertTrue(board.isTileBlocked(blockPos));
        assertFalse(board.isValidMove(blockPos));

        HexPosition freePos = new HexPosition(1, 0);
        assertTrue(board.isValidMove(freePos));
    }
}
