package com.atraparalagato.impl.repository;

import com.atraparalagato.base.model.GameState;
import com.atraparalagato.impl.model.HexGameState;
import com.atraparalagato.impl.model.HexPosition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class H2GameRepositoryTest {

    private H2GameRepository repository;

    @BeforeEach
    void setUp() {
        repository = new H2GameRepository();
    }

    @Test
    void testSaveAndFindById() {
        HexGameState gameState = new HexGameState("game-1", 3);
        repository.save(gameState);

        Optional<GameState<HexPosition>> found = repository.findById("game-1");
        assertTrue(found.isPresent());
        assertEquals("game-1", found.get().getGameId());
    }

    @Test
    void testFindAllAndCount() {
        repository.save(new HexGameState("game-1", 3));
        repository.save(new HexGameState("game-2", 3));
        List<GameState<HexPosition>> all = repository.findAll();

        assertEquals(2, all.size());
        assertEquals(2, repository.countWhere(gs -> true));
    }

    @Test
    void testDeleteById() {
        HexGameState gameState = new HexGameState("game-1", 3);
        repository.save(gameState);
        boolean deleted = repository.deleteById("game-1");

        assertTrue(deleted);
        assertFalse(repository.findById("game-1").isPresent());
    }

    @Test
    void testDeleteWhere() {
        repository.save(new HexGameState("game-1", 3));
        repository.save(new HexGameState("game-2", 3));
        long deletedCount = repository.deleteWhere(gs -> gs.getGameId().equals("game-1"));

        assertEquals(1, deletedCount);
        assertFalse(repository.findById("game-1").isPresent());
        assertTrue(repository.findById("game-2").isPresent());
    }

    @Test
    void testExistsById() {
        repository.save(new HexGameState("game-1", 3));
        assertTrue(repository.existsById("game-1"));
        assertFalse(repository.existsById("game-2"));
    }

    @Test
    void testFindWithPagination() {
        for (int i = 1; i <= 10; i++) {
            repository.save(new HexGameState("game-" + i, 3));
        }
        List<GameState<HexPosition>> page1 = repository.findWithPagination(0, 5);
        List<GameState<HexPosition>> page2 = repository.findWithPagination(1, 5);

        assertEquals(5, page1.size());
        assertEquals(5, page2.size());
        assertEquals("game-1", page1.get(0).getGameId());
        assertEquals("game-6", page2.get(0).getGameId());
    }

    @Test
    void testFindAllSorted() {
        repository.save(new HexGameState("game-b", 3));
        repository.save(new HexGameState("game-a", 3));
        List<GameState<HexPosition>> sortedAsc = repository.findAllSorted(GameState::getGameId, true);
        List<GameState<HexPosition>> sortedDesc = repository.findAllSorted(GameState::getGameId, false);

        assertEquals("game-a", sortedAsc.get(0).getGameId());
        assertEquals("game-b", sortedDesc.get(0).getGameId());
    }
}

