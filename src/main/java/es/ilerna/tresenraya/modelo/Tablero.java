package es.ilerna.tresenraya.modelo;

/**
 * Tablero cuadrado sobre el que se colocan las {@link Ficha fichas}.
 * <p>
 * Sabe si una jugada es posible, si está lleno y si alguna ficha ha hecho tres en raya
 * (fila, columna o cualquiera de las dos diagonales completas). Las casillas vacías
 * se representan con {@code null}.
 */
public class Tablero {

    /** Casillas del tablero; {@code null} significa casilla vacía. */
    private Ficha[][] tablero;

    /**
     * Crea un tablero vacío de {@code dimension} x {@code dimension} casillas.
     *
     * @param dimension número de filas y de columnas (3 para el tres en raya clásico)
     * @throws IllegalArgumentException si la dimensión es menor que 1
     */
    public Tablero(int dimension) {
        if (dimension < 1) {
            throw new IllegalArgumentException("La dimensión del tablero debe ser al menos 1: " + dimension);
        }
        tablero = new Ficha[dimension][dimension];
    }

    /**
     * Intenta colocar una ficha en una casilla.
     *
     * @param ficha   ficha que se coloca
     * @param fila    fila de la casilla (base 0)
     * @param columna columna de la casilla (base 0)
     * @return {@code true} si la jugada es posible y se ha realizado; {@code false} si no hay ficha,
     *         la posición está fuera del tablero o la casilla está ocupada (el tablero no cambia)
     */
    public boolean jugar(Ficha ficha, int fila, int columna) {
        boolean fueraDeRango = fila < 0 || fila >= tablero.length || columna < 0 || columna >= tablero.length;
        if (ficha == null || fueraDeRango || tablero[fila][columna] != null) {
            return false;
        }
        tablero[fila][columna] = ficha;
        return true;
    }

    /**
     * Indica si todas las casillas están ocupadas.
     *
     * @return {@code true} si no queda ninguna casilla vacía
     */
    public boolean estaLleno() {
        for (Ficha[] fila : tablero) {
            for (Ficha casilla : fila) {
                if (casilla == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Indica si una ficha ha hecho tres en raya en horizontal, vertical o en alguna diagonal.
     *
     * @param ficha ficha que se comprueba
     * @return {@code true} si la ficha gana; {@code false} también si {@code ficha} es {@code null}
     */
    public boolean gana(Ficha ficha) {
        if (ficha == null) {
            return false;
        }
        return ganaHorizontal(ficha) || ganaVertical(ficha)
                || ganaDiagonalDirecta(ficha) || ganaDiagonalIndirecta(ficha);
    }

    /**
     * Comprueba si la ficha ocupa alguna fila completa.
     *
     * @param ficha ficha que se comprueba
     * @return {@code true} si hay una fila entera con esa ficha
     */
    protected boolean ganaHorizontal(Ficha ficha) {
        for (int fila = 0; fila < tablero.length; fila++) {
            boolean completa = true;
            for (int columna = 0; columna < tablero.length && completa; columna++) {
                completa = tablero[fila][columna] == ficha;
            }
            if (completa) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si la ficha ocupa alguna columna completa.
     *
     * @param ficha ficha que se comprueba
     * @return {@code true} si hay una columna entera con esa ficha
     */
    protected boolean ganaVertical(Ficha ficha) {
        for (int columna = 0; columna < tablero.length; columna++) {
            boolean completa = true;
            for (int fila = 0; fila < tablero.length && completa; fila++) {
                completa = tablero[fila][columna] == ficha;
            }
            if (completa) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si la ficha ocupa la diagonal directa: de arriba-izquierda a abajo-derecha,
     * casillas {@code [i][i]}.
     *
     * @param ficha ficha que se comprueba
     * @return {@code true} si la diagonal directa entera tiene esa ficha
     */
    protected boolean ganaDiagonalDirecta(Ficha ficha) {
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i][i] != ficha) {
                return false;
            }
        }
        return true;
    }

    /**
     * Comprueba si la ficha ocupa la diagonal indirecta: de arriba-derecha a abajo-izquierda,
     * casillas {@code [i][n-1-i]}.
     *
     * @param ficha ficha que se comprueba
     * @return {@code true} si la diagonal indirecta entera tiene esa ficha
     */
    protected boolean ganaDiagonalIndirecta(Ficha ficha) {
        int n = tablero.length;
        for (int i = 0; i < n; i++) {
            if (tablero[i][n - 1 - i] != ficha) {
                return false;
            }
        }
        return true;
    }

    /**
     * Pinta el tablero: una línea por fila, casillas separadas por un espacio
     * y un guion en las casillas vacías.
     *
     * @return representación en texto del tablero
     */
    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        for (Ficha[] fila : tablero) {
            for (int columna = 0; columna < fila.length; columna++) {
                if (columna > 0) {
                    texto.append(' ');
                }
                texto.append(valueOf(fila[columna]));
            }
            texto.append('\n');
        }
        return texto.toString();
    }

    /**
     * Valor que se pinta para una casilla. Devuelve {@code Object} porque puede ser
     * la propia {@link Ficha} o un {@code String} cuando la casilla está vacía.
     *
     * @param ficha contenido de la casilla
     * @return la ficha, o {@code "-"} si la casilla está vacía
     */
    private Object valueOf(Ficha ficha) {
        return ficha == null ? "-" : ficha;
    }
}
