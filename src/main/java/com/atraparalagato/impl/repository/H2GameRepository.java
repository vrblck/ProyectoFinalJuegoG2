package com.atraparalagato.impl.repository;

import com.atraparalagato.base.model.GameState;
import com.atraparalagato.impl.model.HexPosition;
import com.atraparalagato.base.repository.DataRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class H2GameRepository extends DataRepository<GameState<HexPosition>, String> {
    private final Map<String, GameState<HexPosition>> storage = new ConcurrentHashMap<>();

    @Override
    public GameState<HexPosition> save(GameState<HexPosition> entity) {
        storage.put(entity.getGameId(), entity);
        return entity;
    }

    @Override
    public Optional<GameState<HexPosition>> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<GameState<HexPosition>> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<GameState<HexPosition>> findWhere(Predicate<GameState<HexPosition>> condition) {
        List<GameState<HexPosition>> result = new ArrayList<>();
        for (GameState<HexPosition> state : storage.values()) {
            if (condition.test(state)) result.add(state);
        }
        return result;
    }

    @Override
    public <R> List<R> findAndTransform(Predicate<GameState<HexPosition>> condition, Function<GameState<HexPosition>, R> transformer) {
        List<R> result = new ArrayList<>();
        for (GameState<HexPosition> state : storage.values()) {
            if (condition.test(state)) result.add(transformer.apply(state));
        }
        return result;
    }

    @Override
    public long countWhere(Predicate<GameState<HexPosition>> condition) {
        return storage.values().stream().filter(condition).count();
    }

    @Override
    public boolean deleteById(String id) {
        return storage.remove(id) != null;
    }

    @Override
    public long deleteWhere(Predicate<GameState<HexPosition>> condition) {
        long count = 0;
        Iterator<Map.Entry<String, GameState<HexPosition>>> it = storage.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, GameState<HexPosition>> entry = it.next();
            if (condition.test(entry.getValue())) {
                it.remove();
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean existsById(String id) {
        return storage.containsKey(id);
    }

    @Override
    public <R> R executeInTransaction(Function<DataRepository<GameState<HexPosition>, String>, R> operation) {
        return operation.apply(this);
    }

    @Override
    public List<GameState<HexPosition>> findWithPagination(int page, int size) {
        List<GameState<HexPosition>> all = findAll();
        int from = Math.min(page * size, all.size());
        int to = Math.min(from + size, all.size());
        return all.subList(from, to);
    }

    @Override
    public List<GameState<HexPosition>> findAllSorted(Function<GameState<HexPosition>, ? extends Comparable<?>> sortKeyExtractor, boolean ascending) {
        List<GameState<HexPosition>> all = findAll();
        all.sort(Comparator.comparing(game -> (Comparable) sortKeyExtractor.apply(game)));
        if (!ascending) Collections.reverse(all);
        return all;
    }

    @Override
    public <R> List<R> executeCustomQuery(String query, Function<Object, R> resultMapper) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    protected void initialize() {
        // Implementación mínima
    }

    @Override
    protected void cleanup() {
        // Implementación mínima
    }
}
