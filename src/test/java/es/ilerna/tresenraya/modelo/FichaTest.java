package es.ilerna.tresenraya.modelo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FichaTest {

    @Test
    void soloExistenLasFichasXyO() {
        assertArrayEquals(new Ficha[] {Ficha.X, Ficha.O}, Ficha.values());
    }

    @Test
    void despuesDeXVaO() {
        assertEquals(Ficha.O, Ficha.X.siguiente());
    }

    @Test
    void despuesDeOVaX() {
        assertEquals(Ficha.X, Ficha.O.siguiente());
    }

    @Test
    void dosTurnosVuelvenALaMismaFicha() {
        for (Ficha ficha : Ficha.values()) {
            assertEquals(ficha, ficha.siguiente().siguiente());
        }
    }
}
