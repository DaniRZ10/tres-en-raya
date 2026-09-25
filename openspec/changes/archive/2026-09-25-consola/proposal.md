# Proposal

## Why

El modelo ya permite jugar una partida, pero falta la forma de jugarla: el ejercicio se juega por consola, mostrando la partida con su `toString()` y pidiendo al jugador la fila y la columna de cada jugada.

## What Changes

- Nueva clase `VistaConsola` en `es.ilerna.tresenraya.vista` con el bucle de juego por consola.
- `Main` pasa a lanzar la vista con la entrada y salida estándar.
- Validación de la entrada del jugador y mensajes de casilla ocupada, resultado y nueva partida.
- Tests de la vista con entrada simulada.

## Capabilities

### New Capabilities
- `juego-consola`: interacción con el jugador por consola para jugar una o varias partidas.

### Modified Capabilities

## Impact

- Código nuevo: `vista/VistaConsola.java` y `vista/VistaConsolaTest.java`; se modifica `Main.java`.
- El modelo no se modifica. Sin dependencias externas nuevas.
