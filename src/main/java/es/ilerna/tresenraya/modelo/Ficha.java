package es.ilerna.tresenraya.modelo;

/**
 * Fichas con las que se juega al tres en raya.
 * <p>
 * Su única responsabilidad es saber calcular cuál es el {@link #siguiente() siguiente} turno.
 * La casilla vacía no es una ficha: el {@link Tablero} la representa con {@code null}.
 */
public enum Ficha {
    X, O;

    /**
     * Calcula la ficha a la que le toca jugar después de esta.
     *
     * @return {@link #O} si esta ficha es {@link #X}, y {@link #X} si es {@link #O}
     */
    public Ficha siguiente() {
        return this == X ? O : X;
    }
}
