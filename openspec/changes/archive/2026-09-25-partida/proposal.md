# Proposal

## Why

Cada vez que se juega es una Partida: tiene un Tablero, controla a quién le toca, permite jugar sobre una fila y columna y sabe decir si ha terminado y quién ha ganado. Es el punto de entrada del modelo que usará la vista de consola.

## What Changes

- Nueva clase `Partida` en `es.ilerna.tresenraya.modelo`, fiel al diagrama UML:
  - Atributos privados `turno: Ficha` y `tablero: Tablero`.
  - `+ Partida(int)`, `+ jugar(int, int): void`, `+ terminada(): boolean`, `+ ganador(): Ficha`, `+ toString(): String`.
- Tests unitarios de la clase.

## Capabilities

### New Capabilities
- `partida`: desarrollo de una partida por turnos, fin de partida y ganador.

### Modified Capabilities

## Impact

- Código nuevo: `modelo/Partida.java` y `modelo/PartidaTest.java`.
- Usa las capabilities `ficha` y `tablero`. Sin dependencias externas nuevas.
