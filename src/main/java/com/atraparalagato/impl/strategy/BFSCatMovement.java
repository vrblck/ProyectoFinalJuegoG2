package com.atraparalagato.impl.strategy;

import com.atraparalagato.base.model.GameBoard;
import com.atraparalagato.base.strategy.CatMovementStrategy;
import com.atraparalagato.impl.model.HexPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Comparator;
import java.util.Queue;          
import java.util.LinkedList;     
import java.util.Map;             
import java.util.HashMap;         
import java.util.Collections;     

/**
 * Estrategia de movimiento del gato basada en el algoritmo BFS (Breadth-First Search).
 * 
 * Design Patterns:
 * - Strategy Pattern: Implementa un algoritmo específico de movimiento
 * 
 * SOLID Principles:
 * - Single Responsibility: Solo maneja la lógica de movimiento del gato usando BFS
 * - Open/Closed: Abierta para nuevos algoritmos, cerrada para modificación
 * - Dependency Inversion: Depende de abstracciones, no implementaciones concretas
 * 
 * Conceptos implementados:
 * - Programación Funcional: Uso de Function, Predicate
 * - Algoritmos: BFS
 * - Modularización: Separación de heurísticas y algoritmos
 */
public class BFSCatMovement extends CatMovementStrategy<HexPosition> {

    public BFSCatMovement(GameBoard<HexPosition> board) {
        super(board);
    }

    /**
     * Obtiene todos los movimientos posibles desde una posición usando BFS.
     * (Este método debe ser implementado completamente por los estudiantes).
     */
    @Override
    protected List<HexPosition> getPossibleMoves(HexPosition currentPosition) {
        // Movimiento simple: cualquier adyacente no bloqueado
        return board.getAdjacentPositions(currentPosition).stream()
            .filter(pos -> !board.isBlocked(pos))
            .toList();
    }

    /**
     * Selecciona el mejor movimiento de una lista de movimientos posibles.
     * En esta implementación, simplemente retorna el primer movimiento posible.
     */
    @Override
    protected Optional<HexPosition> selectBestMove(List<HexPosition> possibleMoves,
                                               HexPosition currentPosition,
                                               HexPosition ignore) {
    List<HexPosition> path = getFullPath(currentPosition, null);
    return path.size() >= 2
        ? Optional.of(path.get(1))
        : Optional.empty();
}
    /**
     * Función heurística para evaluar qué tan buena es una posición.
     * En esta implementación, todas las posiciones tienen la misma evaluación.
     */
    @Override
    protected Function<HexPosition, Double> getHeuristicFunction(HexPosition targetPosition) {
        return pos -> 0.0;
    }

    /**
     * Predicado para determinar si una posición es un objetivo válido.
     * En esta implementación, ninguna posición es considerada un objetivo.
     */
    @Override
    protected Predicate<HexPosition> getGoalPredicate() {
       return pos ->
        Math.abs(pos.getQ()) == board.getSize() ||
        Math.abs(pos.getR()) == board.getSize() ||
        Math.abs(pos.getS()) == board.getSize(); 
    }

    /**
     * Calcula el costo de moverse de una posición a otra.
     * En esta implementación, todos los movimientos tienen el mismo costo.
     */
    @Override
    protected double getMoveCost(HexPosition from, HexPosition to) {
        return 1.0;
    }

    /**
     * Verifica si existe un camino desde la posición actual hasta el objetivo.
     * (Este método debe ser implementado completamente por los estudiantes).
     */
    @Override
    public boolean hasPathToGoal(HexPosition currentPosition) {
        // TODO: Implementar la lógica para verificar el camino al objetivo usando BFS
        return false;
    }

    /**
     * Obtiene el camino completo desde la posición actual hasta el objetivo.
     * (Este método debe ser implementado completamente por los estudiantes).
     */
    @Override
public List<HexPosition> getFullPath(HexPosition start, HexPosition ignore) {
    Queue<HexPosition> queue = new LinkedList<>();
    Map<HexPosition, HexPosition> parent = new HashMap<>();
    queue.add(start);
    parent.put(start, null);

    while (!queue.isEmpty()) {
        HexPosition curr = queue.poll();
        if (getGoalPredicate().test(curr)) {
            List<HexPosition> path = new ArrayList<>();
            for (HexPosition p = curr; p != null; p = parent.get(p))
                path.add(p);
            Collections.reverse(path);
            return path;
        }
        for (HexPosition next : getPossibleMoves(curr)) {
            if (!parent.containsKey(next)) {
                parent.put(next, curr);
                queue.add(next);
            }
        }
    }
    return List.of(start);
}

}