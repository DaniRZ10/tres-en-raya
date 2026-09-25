package es.ilerna.tresenraya.vista;

import java.io.PrintStream;
import java.util.Scanner;

import es.ilerna.tresenraya.modelo.Ficha;
import es.ilerna.tresenraya.modelo.Partida;

/**
 * Interfaz de consola para jugar al tres en raya.
 * <p>
 * Solo usa la API pública de {@link Partida} definida en el diagrama de clases: pinta la
 * partida con su {@code toString()}, juega con {@code jugar(fila, columna)} y consulta
 * {@code terminada()} y {@code ganador()}.
 */
public class VistaConsola {

    /** Dimensión del tablero: tres en raya clásico. */
    private static final int DIMENSION = 3;

    private final Scanner entrada;
    private final PrintStream salida;

    /**
     * Crea la vista con la entrada y la salida indicadas.
     *
     * @param entrada de donde se leen las jugadas (normalmente {@code System.in})
     * @param salida  donde se pinta el juego (normalmente {@code System.out})
     */
    public VistaConsola(Scanner entrada, PrintStream salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    /**
     * Juega partidas hasta que el jugador no quiera otra o se acabe la entrada.
     */
    public void iniciar() {
        salida.println("=== TRES EN RAYA ===");
        boolean otra = true;
        while (otra) {
            boolean completada = jugarPartida(new Partida(DIMENSION));
            otra = completada && preguntarOtraPartida();
        }
        salida.println("¡Hasta la próxima!");
    }

    /**
     * Juega una partida completa.
     *
     * @return {@code true} si la partida ha terminado; {@code false} si se acabó la entrada antes
     */
    private boolean jugarPartida(Partida partida) {
        while (!partida.terminada()) {
            salida.println();
            salida.print(partida);
            salida.print("Fila y columna (1-" + DIMENSION + "): ");
            if (!entrada.hasNextLine()) {
                salida.println();
                return false;
            }
            int[] casilla = leerCasilla(entrada.nextLine());
            if (casilla == null) {
                salida.println("Entrada no válida: escribe dos números del 1 al " + DIMENSION
                        + " separados por un espacio (por ejemplo: 2 3).");
                continue;
            }
            // jugar() es void en el diagrama y no dice si la jugada se aceptó. Como el rango ya
            // está validado, si la partida pinta igual antes y después, la casilla estaba ocupada.
            String antes = partida.toString();
            partida.jugar(casilla[0], casilla[1]);
            if (antes.equals(partida.toString())) {
                salida.println("Casilla ocupada, elige otra.");
            }
        }
        mostrarResultado(partida);
        return true;
    }

    /**
     * Convierte una línea "fila columna" (base 1) en coordenadas en base 0.
     *
     * @return {fila, columna} en base 0, o {@code null} si la entrada no es válida
     */
    private int[] leerCasilla(String linea) {
        String[] partes = linea.trim().split("\\s+");
        if (partes.length != 2) {
            return null;
        }
        try {
            int fila = Integer.parseInt(partes[0]);
            int columna = Integer.parseInt(partes[1]);
            if (fila < 1 || fila > DIMENSION || columna < 1 || columna > DIMENSION) {
                return null;
            }
            return new int[] {fila - 1, columna - 1};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void mostrarResultado(Partida partida) {
        salida.println();
        salida.print(partida);
        Ficha ganador = partida.ganador();
        salida.println(ganador == null ? "Empate." : "¡Gana " + ganador + "!");
    }

    private boolean preguntarOtraPartida() {
        salida.print("¿Otra partida? (s/n): ");
        if (!entrada.hasNextLine()) {
            salida.println();
            return false;
        }
        return entrada.nextLine().trim().equalsIgnoreCase("s");
    }
}
