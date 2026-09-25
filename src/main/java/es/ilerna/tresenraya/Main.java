package es.ilerna.tresenraya;

import java.util.Scanner;

import es.ilerna.tresenraya.vista.VistaConsola;

/**
 * Punto de entrada de la aplicación Tres en Raya.
 */
public class Main {

    /**
     * Arranca el juego por consola.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        new VistaConsola(new Scanner(System.in), System.out).iniciar();
    }
}
