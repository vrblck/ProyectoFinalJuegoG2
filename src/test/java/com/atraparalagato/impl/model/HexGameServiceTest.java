package com.atraparalagato.impl.model;

import com.atraparalagato.impl.service.HexGameService;
import com.atraparalagato.impl.model.HexGameState;
import com.atraparalagato.impl.model.HexPosition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HexGameServiceTest {

    static class TestableHexGameService extends HexGameService {
        public void publicExecuteCatMove(HexGameState state) {
            super.executeCatMove(state);
        }
    }

    @Test
    public void testExecuteCatMove_changesCatPosition() {
        TestableHexGameService service = new TestableHexGameService();
        HexGameState state = new HexGameState("testGameId", 9);
        state.setCatPosition(new HexPosition(0, 0));

        HexPosition initialPos = state.getCatPosition();
        service.publicExecuteCatMove(state);
        HexPosition newPos = state.getCatPosition();

        assertNotNull(newPos);
        assertNotEquals(initialPos, newPos);
    }
}
