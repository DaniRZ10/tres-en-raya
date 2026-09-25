package es.ilerna.tresenraya.modelo;

/**
 * Una partida de tres en raya.
 * <p>
 * Tiene un {@link Tablero} y controla el turno. Para jugar se apoya en el tablero,
 * y para saber el siguiente turno, en la {@link Ficha}.
 */
public class Partida {

    /** Ficha a la que le toca jugar. */
    private Ficha turno;

    /** Tablero sobre el que se juega la partida. */
    private Tablero tablero;

    /**
     * Crea una partida sobre un tablero vacío. Empieza jugando {@link Ficha#X}.
     *
     * @param dimension número de filas y columnas del tablero (3 para el tres en raya clásico)
     * @throws IllegalArgumentException si la dimensión es menor que 1
     */
    public Partida(int dimension) {
        tablero = new Tablero(dimension);
        turno = Ficha.X;
    }

    /**
     * Juega el turno actual en una casilla. Le pide al tablero que coloque la ficha del turno
     * y, solo si la jugada es posible, pasa el turno a la ficha siguiente. Si la partida ya
     * está terminada, no hace nada.
     *
     * @param fila    fila de la casilla (base 0)
     * @param columna columna de la casilla (base 0)
     */
    public void jugar(int fila, int columna) {
        if (terminada()) {
            return;
        }
        if (tablero.jugar(turno, fila, columna)) {
            turno = turno.siguiente();
        }
    }

    /**
     * Indica si la partida ha terminado: el tablero está lleno o alguna ficha ha hecho tres en raya.
     *
     * @return {@code true} si la partida ha terminado
     */
    public boolean terminada() {
        return tablero.estaLleno() || tablero.gana(Ficha.X) || tablero.gana(Ficha.O);
    }

    /**
     * Devuelve la ficha ganadora.
     *
     * @return la ficha que ha hecho tres en raya, o {@code null} si nadie ha ganado
     *         (partida en curso o empate)
     */
    public Ficha ganador() {
        for (Ficha ficha : Ficha.values()) {
            if (tablero.gana(ficha)) {
                return ficha;
            }
        }
        return null;
    }

    /**
     * Pinta la partida: el turno actual y, debajo, el tablero.
     *
     * @return representación en texto de la partida
     */
    @Override
    public String toString() {
        return "Turno: " + turno + "\n" + tablero;
    }
}
