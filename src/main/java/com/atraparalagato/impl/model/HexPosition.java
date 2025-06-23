package com.atraparalagato.impl.model;

import com.atraparalagato.base.model.Position;
import java.util.Objects;

/**
 * Clase para representar posiciones en un tablero hexagonal.
 */
public class HexPosition extends Position {
    private final int q;
    private final int r;

    public HexPosition(int q, int r) {
        this.q = q;
        this.r = r;
    }

    public int getQ() { return q; }
    public int getR() { return r; }
    public int getS() { return -q - r; }

    @Override
    public double distanceTo(Position other) {
        if (!(other instanceof HexPosition)) return Double.POSITIVE_INFINITY;
        HexPosition o = (HexPosition) other;
        return (Math.abs(q - o.q) + Math.abs(r - o.r) + Math.abs(getS() - o.getS())) / 2.0;
    }

    @Override
    public Position add(Position other) {
        if (!(other instanceof HexPosition)) throw new IllegalArgumentException();
        HexPosition o = (HexPosition) other;
        return new HexPosition(q + o.q, r + o.r);
    }

    @Override
    public Position subtract(Position other) {
        if (!(other instanceof HexPosition)) throw new IllegalArgumentException();
        HexPosition o = (HexPosition) other;
        return new HexPosition(q - o.q, r - o.r);
    }

    @Override
    public boolean isAdjacentTo(Position other) {
        if (!(other instanceof HexPosition)) return false;
        HexPosition o = (HexPosition) other;
        int dq = Math.abs(q - o.q);
        int dr = Math.abs(r - o.r);
        int ds = Math.abs(getS() - o.getS());
        return (dq + dr + ds) == 2;
    }

    @Override
    public boolean isWithinBounds(int maxSize) {
        // Ajuste para que pase el test: 
        // maxSize indica el rango máximo permitido para q, r y s
        return Math.abs(q) <= maxSize && Math.abs(r) <= maxSize && Math.abs(getS()) <= maxSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HexPosition)) return false;
        HexPosition other = (HexPosition) o;
        return this.q == other.q && this.r == other.r;
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, r);
    }

    @Override
    public String toString() {
        return "HexPosition(" + q + "," + r + ")";
    }
}
