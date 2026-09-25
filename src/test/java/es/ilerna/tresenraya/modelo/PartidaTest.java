package es.ilerna.tresenraya.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartidaTest {

    private Partida partida;

    @BeforeEach
    void setUp() {
        partida = new Partida(3);
    }

    @Test
    void partidaNuevaEmpiezaConXYTableroVacio() {
        assertEquals("Turno: X\n- - -\n- - -\n- - -\n", partida.toString());
        assertFalse(partida.terminada());
        assertNull(partida.ganador());
    }

    @Test
    void dimensionNoValidaLanzaError() {
        assertThrows(IllegalArgumentException.class, () -> new Partida(0));
    }

    @Test
    void jugadaValidaColocaLaFichaYPasaElTurno() {
        partida.jugar(0, 0);
        assertEquals("Turno: O\nX - -\n- - -\n- - -\n", partida.toString());
    }

    @Test
    void losTurnosAlternanXYO() {
        partida.jugar(0, 0);
        partida.jugar(1, 1);
        partida.jugar(2, 2);
        assertEquals("Turno: O\nX - -\n- O -\n- - X\n", partida.toString());
    }

    @Test
    void casillaOcupadaNoCambiaTableroNiTurno() {
        partida.jugar(0, 0);
        String antes = partida.toString();
        partida.jugar(0, 0);
        assertEquals(antes, partida.toString());
        assertTrue(antes.startsWith("Turno: O"));
    }

    @Test
    void jugadaFueraDelTableroNoCambiaTableroNiTurno() {
        String antes = partida.toString();
        partida.jugar(3, 0);
        partida.jugar(0, -1);
        assertEquals(antes, partida.toString());
    }

    @Test
    void ganaXYLaPartidaTermina() {
        jugarSecuencia(0, 0, 1, 0, 0, 1, 1, 1, 0, 2); // X completa la fila 0
        assertTrue(partida.terminada());
        assertEquals(Ficha.X, partida.ganador());
    }

    @Test
    void ganaOYLaPartidaTermina() {
        jugarSecuencia(0, 0, 0, 2, 1, 0, 1, 1, 2, 2, 2, 0); // O completa la diagonal indirecta
        assertTrue(partida.terminada());
        assertEquals(Ficha.O, partida.ganador());
    }

    @Test
    void empateConTableroLlenoSinGanador() {
        // X O X
        // X O O
        // O X X
        jugarSecuencia(0, 0, 0, 1, 0, 2, 1, 1, 1, 0, 1, 2, 2, 1, 2, 0, 2, 2);
        assertTrue(partida.terminada());
        assertNull(partida.ganador());
    }

    @Test
    void trasTerminarLasJugadasSeIgnoran() {
        jugarSecuencia(0, 0, 1, 0, 0, 1, 1, 1, 0, 2); // gana X
        String antes = partida.toString();
        partida.jugar(2, 2);
        assertEquals(antes, partida.toString());
        assertEquals(Ficha.X, partida.ganador());
    }

    @Test
    void enCursoNoHayGanador() {
        jugarSecuencia(0, 0, 1, 1);
        assertFalse(partida.terminada());
        assertNull(partida.ganador());
    }

    /** Juega por turnos cada par (fila, columna) indicado. */
    private void jugarSecuencia(int... posiciones) {
        for (int i = 0; i < posiciones.length; i += 2) {
            partida.jugar(posiciones[i], posiciones[i + 1]);
        }
    }
}
