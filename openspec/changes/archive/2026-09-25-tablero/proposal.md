# Proposal

## Why

La Partida no puede hacerlo todo sola: necesita un Tablero donde colocar las fichas, que le diga si una jugada es posible y que sepa estudiar si está lleno o si alguna ficha ha hecho tres en raya.

## What Changes

- Nueva clase `Tablero` en `es.ilerna.tresenraya.modelo`, fiel al diagrama UML:
  - `+ Tablero(int)`, `+ jugar(Ficha, int, int): boolean`, `+ estaLleno(): boolean`, `+ gana(Ficha): boolean`.
  - Cuatro auxiliares `protected`: `ganaHorizontal`, `ganaVertical`, `ganaDiagonalDirecta`, `ganaDiagonalIndirecta`.
  - `+ toString(): String` para pintar el tablero y `- valueOf(Ficha): Object` como apoyo.
- Tests unitarios de la clase.

## Capabilities

### New Capabilities
- `tablero`: colocación de fichas en casillas, detección de tablero lleno y de tres en raya, y representación en texto.

### Modified Capabilities

## Impact

- Código nuevo: `modelo/Tablero.java` y `modelo/TableroTest.java`.
- Depende de la capability `ficha`. Sin dependencias externas nuevas.
