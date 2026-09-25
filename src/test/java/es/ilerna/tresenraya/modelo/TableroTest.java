package es.ilerna.tresenraya.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class TableroTest {

    private static final String VACIO = "- - -\n- - -\n- - -\n";

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero(3);
    }

    // --- Creación ---

    @Test
    void tableroNuevoEstaVacioYNoLleno() {
        assertEquals(VACIO, tablero.toString());
        assertFalse(tablero.estaLleno());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -3})
    void dimensionNoValidaLanzaError(int dimension) {
        assertThrows(IllegalArgumentException.class, () -> new Tablero(dimension));
    }

    // --- Jugadas ---

    @Test
    void jugarEnCasillaLibreEsPosible() {
        assertTrue(tablero.jugar(Ficha.X, 1, 2));
        assertEquals("- - -\n- - X\n- - -\n", tablero.toString());
    }

    @Test
    void jugarEnCasillaOcupadaNoEsPosibleYConservaLaFicha() {
        tablero.jugar(Ficha.X, 0, 0);
        assertFalse(tablero.jugar(Ficha.O, 0, 0));
        assertEquals("X - -\n- - -\n- - -\n", tablero.toString());
    }

    @Test
    void jugarFueraDelTableroNoEsPosibleNiLoModifica() {
        assertFalse(tablero.jugar(Ficha.X, -1, 0));
        assertFalse(tablero.jugar(Ficha.X, 0, -1));
        assertFalse(tablero.jugar(Ficha.X, 3, 0));
        assertFalse(tablero.jugar(Ficha.X, 0, 3));
        assertEquals(VACIO, tablero.toString());
    }

    @Test
    void jugarSinFichaNoEsPosible() {
        assertFalse(tablero.jugar(null, 0, 0));
        assertEquals(VACIO, tablero.toString());
    }

    // --- Lleno ---

    @Test
    void tableroCompletoEstaLleno() {
        llenarSinGanador();
        assertTrue(tablero.estaLleno());
    }

    @Test
    void conUnaCasillaLibreNoEstaLleno() {
        // Mismo reparto que llenarSinGanador() pero dejando libre (2,2)
        jugar(Ficha.X, 0, 0, 0, 2, 1, 0, 2, 1);
        jugar(Ficha.O, 0, 1, 1, 1, 1, 2, 2, 0);
        assertFalse(tablero.estaLleno());
    }

    // --- Victoria ---

    @ParameterizedTest
    @EnumSource(Ficha.class)
    void ganaEnHorizontal(Ficha ficha) {
        jugar(ficha, 1, 0, 1, 1, 1, 2);
        assertTrue(tablero.ganaHorizontal(ficha));
        assertTrue(tablero.gana(ficha));
        assertFalse(tablero.gana(ficha.siguiente()));
    }

    @ParameterizedTest
    @EnumSource(Ficha.class)
    void ganaEnVertical(Ficha ficha) {
        jugar(ficha, 0, 2, 1, 2, 2, 2);
        assertTrue(tablero.ganaVertical(ficha));
        assertTrue(tablero.gana(ficha));
        assertFalse(tablero.gana(ficha.siguiente()));
    }

    @ParameterizedTest
    @EnumSource(Ficha.class)
    void ganaEnDiagonalDirecta(Ficha ficha) {
        jugar(ficha, 0, 0, 1, 1, 2, 2);
        assertTrue(tablero.ganaDiagonalDirecta(ficha));
        assertFalse(tablero.ganaDiagonalIndirecta(ficha));
        assertTrue(tablero.gana(ficha));
    }

    @ParameterizedTest
    @EnumSource(Ficha.class)
    void ganaEnDiagonalIndirecta(Ficha ficha) {
        jugar(ficha, 0, 2, 1, 1, 2, 0);
        assertTrue(tablero.ganaDiagonalIndirecta(ficha));
        assertFalse(tablero.ganaDiagonalDirecta(ficha));
        assertTrue(tablero.gana(ficha));
    }

    @Test
    void lineaIncompletaNoGana() {
        jugar(Ficha.X, 0, 0, 0, 1);
        assertFalse(tablero.gana(Ficha.X));
    }

    @Test
    void lineaMezcladaNoGana() {
        jugar(Ficha.X, 0, 0, 0, 1);
        jugar(Ficha.O, 0, 2);
        assertFalse(tablero.ganaHorizontal(Ficha.X));
        assertFalse(tablero.gana(Ficha.X));
        assertFalse(tablero.gana(Ficha.O));
    }

    @Test
    void tableroLlenoSinTresEnRayaNoTieneGanador() {
        llenarSinGanador();
        assertFalse(tablero.gana(Ficha.X));
        assertFalse(tablero.gana(Ficha.O));
    }

    @Test
    void ningunaFichaNoGanaNiEnTableroVacio() {
        assertFalse(tablero.gana(null));
    }

    // --- Representación ---

    @Test
    void pintaFichasYGuionesPorFilas() {
        tablero.jugar(Ficha.X, 0, 0);
        tablero.jugar(Ficha.O, 1, 1);
        assertEquals("X - -\n- O -\n- - -\n", tablero.toString());
    }

    @Test
    void funcionaConOtraDimension() {
        Tablero grande = new Tablero(4);
        for (int i = 0; i < 4; i++) {
            grande.jugar(Ficha.O, i, 3 - i);
        }
        assertTrue(grande.ganaDiagonalIndirecta(Ficha.O));
        assertEquals(4, grande.toString().split("\n").length);
    }

    // --- Utilidades ---

    /** Juega la ficha en cada par (fila, columna) indicado. */
    private void jugar(Ficha ficha, int... posiciones) {
        for (int i = 0; i < posiciones.length; i += 2) {
            assertTrue(tablero.jugar(ficha, posiciones[i], posiciones[i + 1]));
        }
    }

    /**
     * X O X
     * X O O
     * O X X
     */
    private void llenarSinGanador() {
        jugar(Ficha.X, 0, 0, 0, 2, 1, 0, 2, 1, 2, 2);
        jugar(Ficha.O, 0, 1, 1, 1, 1, 2, 2, 0);
    }
}
