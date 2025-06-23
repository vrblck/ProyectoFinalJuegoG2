package com.atraparalagato.impl.model;

import com.atraparalagato.impl.strategy.BFSCatMovement;
import com.atraparalagato.impl.model.HexGameBoard;
import com.atraparalagato.impl.model.HexPosition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class BFSCatMovementTest {

    // Subclase para exponer métodos protected
    static class TestableBFSCatMovement extends BFSCatMovement {
        public TestableBFSCatMovement(HexGameBoard board) {
            super(board);
        }

        @Override
        public List<HexPosition> getPossibleMoves(HexPosition pos) {
            return super.getPossibleMoves(pos);
        }

        @Override
        public Predicate<HexPosition> getGoalPredicate() {
            return super.getGoalPredicate();
        }
        
        // Método público para exponer selectBestMove
        public Optional<HexPosition> publicSelectBestMove(List<HexPosition> possibleMoves, HexPosition currentPosition, HexPosition ignore) {
            return super.selectBestMove(possibleMoves, currentPosition, ignore);
        }
    }

    @Test
    public void testGetPossibleMoves_noBlocked() {
        HexGameBoard board = new HexGameBoard(2);
        TestableBFSCatMovement bfs = new TestableBFSCatMovement(board);

        HexPosition pos = new HexPosition(0, 0);
        List<HexPosition> moves = bfs.getPossibleMoves(pos);

        assertFalse(moves.isEmpty());
        assertTrue(moves.stream().allMatch(p -> !board.isBlocked(p)));
    }

    @Test
    public void testGetGoalPredicate_detectsBorder() {
        HexGameBoard board = new HexGameBoard(2);
        TestableBFSCatMovement bfs = new TestableBFSCatMovement(board);

        Predicate<HexPosition> goal = bfs.getGoalPredicate();

        // Posición en borde
        HexPosition borderPos = new HexPosition(2, 0);
        assertTrue(goal.test(borderPos));

        // Posición interior
        HexPosition insidePos = new HexPosition(0, 0);
        assertFalse(goal.test(insidePos));
    }

    @Test
    public void testGetFullPath_findsPath() {
        HexGameBoard board = new HexGameBoard(2);
        TestableBFSCatMovement bfs = new TestableBFSCatMovement(board);

        HexPosition start = new HexPosition(0, 0);
        List<HexPosition> path = bfs.getFullPath(start, null);

        assertNotNull(path);
        assertTrue(path.get(0).equals(start));
    }

    @Test
    public void testSelectBestMove_returnsNextStep() {
        HexGameBoard board = new HexGameBoard(2);
        TestableBFSCatMovement bfs = new TestableBFSCatMovement(board);

        HexPosition start = new HexPosition(0, 0);
        Optional<HexPosition> best = bfs.publicSelectBestMove(
            bfs.getPossibleMoves(start),
            start,
            null
        );

        assertTrue(best.isPresent());
        assertNotEquals(start, best.get());
    }
}
