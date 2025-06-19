package com.atraparalagato.impl.repository;

import com.atraparalagato.base.repository.DataRepository;
import com.atraparalagato.impl.model.HexGameState;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class H2GameRepository extends DataRepository<HexGameState, String> {
    @Override public HexGameState save(HexGameState state) { throw new UnsupportedOperationException("TODO"); }
    @Override
    public java.util.Optional<HexGameState> findById(String id) {
        throw new UnsupportedOperationException("TODO");
    }
    @Override public List<HexGameState> findAll() { throw new UnsupportedOperationException("TODO"); }
    @Override public List<HexGameState> findWhere(Predicate<HexGameState> predicate) { throw new UnsupportedOperationException("TODO"); }
    @Override public <R> List<R> findAndTransform(Predicate<HexGameState> predicate, Function<HexGameState, R> transformer) { throw new UnsupportedOperationException("TODO"); }
    @Override public <R> List<R> executeCustomQuery(String query, Function<Object, R> resultMapper) { throw new UnsupportedOperationException("TODO"); }
    @Override public List<HexGameState> findAllSorted(Function<HexGameState, ? extends Comparable<?>> sortKeyExtractor, boolean ascending) { throw new UnsupportedOperationException("TODO"); }
    @Override public List<HexGameState> findWithPagination(int offset, int limit) { throw new UnsupportedOperationException("TODO"); }
    @Override public <R> R executeInTransaction(Function<DataRepository<HexGameState, String>, R> action) { return action.apply(this); }
    @Override
    public void cleanup() {
        // Si no necesitas limpiar nada, puedes dejarlo vacío o lanzar una excepción
        // throw new UnsupportedOperationException("Not implemented yet");
    }
}