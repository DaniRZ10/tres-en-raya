package es.ilerna.tresenraya.vista;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class VistaConsolaTest {

    /** X gana completando la fila 1: X(1,1) O(2,1) X(1,2) O(2,2) X(1,3). */
    private static final String VICTORIA_X = "1 1\n2 1\n1 2\n2 2\n1 3\n";

    /** Tablero final X O X / X O O / O X X, sin tres en raya. */
    private static final String EMPATE = "1 1\n1 2\n1 3\n2 2\n2 1\n2 3\n3 2\n3 1\n3 3\n";

    /** Ejecuta el juego con la entrada indicada y devuelve todo lo que se ha mostrado. */
    private String jugar(String entrada) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream salida = new PrintStream(buffer, true, StandardCharsets.UTF_8);
        Scanner scanner = new Scanner(new ByteArrayInputStream(entrada.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);
        new VistaConsola(scanner, salida).iniciar();
        // println() usa el salto de línea del sistema (\r\n en Windows); se normaliza a \n
        return buffer.toString(StandardCharsets.UTF_8).replace(System.lineSeparator(), "\n");
    }

    private int apariciones(String texto, String buscado) {
        return texto.split(java.util.regex.Pattern.quote(buscado), -1).length - 1;
    }

    @Test
    void alArrancarMuestraTituloPartidaVaciaYPideJugada() {
        String salida = jugar("");
        assertTrue(salida.startsWith("=== TRES EN RAYA ==="));
        assertTrue(salida.contains("Turno: X\n- - -\n- - -\n- - -\nFila y columna (1-3): "));
    }

    @Test
    void jugadaValidaColocaLaFichaYPasaElTurno() {
        String salida = jugar("1 1\n");
        assertTrue(salida.contains("Turno: O\nX - -\n- - -\n- - -\n"));
    }

    @Test
    void entradaNoNumericaSeRechazaSinCambiarElTurno() {
        String salida = jugar("hola\n");
        assertTrue(salida.contains("Entrada no válida"));
        assertEquals(2, apariciones(salida, "Turno: X\n- - -\n- - -\n- - -\n"));
        assertFalse(salida.contains("Turno: O"));
    }

    @Test
    void valoresFueraDeRangoSeRechazan() {
        String salida = jugar("0 4\n1\n1 2 3\n");
        assertEquals(3, apariciones(salida, "Entrada no válida"));
        assertFalse(salida.contains("Turno: O"));
    }

    @Test
    void casillaOcupadaAvisaYMantieneElTurno() {
        String salida = jugar("1 1\n1 1\n");
        assertTrue(salida.contains("Casilla ocupada"));
        assertEquals(2, apariciones(salida, "Turno: O\nX - -\n- - -\n- - -\n"));
    }

    @Test
    void victoriaMuestraGanadorYPreguntaOtraPartida() {
        String salida = jugar(VICTORIA_X + "n\n");
        assertTrue(salida.contains("X X X\nO O -\n- - -\n¡Gana X!"));
        assertTrue(salida.contains("¿Otra partida? (s/n): "));
        assertTrue(salida.endsWith("¡Hasta la próxima!\n"));
    }

    @Test
    void victoriaDeO() {
        // X(1,1) O(1,3) X(2,1) O(2,2) X(3,3) O(3,1): O completa la diagonal indirecta
        String salida = jugar("1 1\n1 3\n2 1\n2 2\n3 3\n3 1\nn\n");
        assertTrue(salida.contains("¡Gana O!"));
    }

    @Test
    void empateSinGanador() {
        String salida = jugar(EMPATE + "n\n");
        assertTrue(salida.contains("X O X\nX O O\nO X X\nEmpate."));
        assertFalse(salida.contains("¡Gana"));
    }

    @Test
    void conSEmpiezaOtraPartidaNueva() {
        // Tras "s" se acaba la entrada: la segunda partida se muestra vacía una vez y termina
        String salida = jugar(VICTORIA_X + "s\n");
        assertEquals(2, apariciones(salida, "Turno: X\n- - -\n- - -\n- - -\n"));
        assertEquals(1, apariciones(salida, "¡Gana X!"));
    }

    @Test
    void conNTerminaTrasUnaPartida() {
        String salida = jugar(VICTORIA_X + "n\n");
        assertEquals(1, apariciones(salida, "¿Otra partida?"));
        assertEquals(1, apariciones(salida, "Turno: X\n- - -\n- - -\n- - -\n"));
    }

    @Test
    void finDeEntradaEnMitadDePartidaTerminaSinErrores() {
        String salida = jugar("1 1\n");
        assertTrue(salida.endsWith("¡Hasta la próxima!\n"));
        assertFalse(salida.contains("¿Otra partida?"));
    }
}
