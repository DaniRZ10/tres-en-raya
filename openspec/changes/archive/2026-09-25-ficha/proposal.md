# Proposal

## Why

El juego necesita representar las dos fichas posibles (X y O) y saber qué turno va después de cada una. El enunciado asigna esta única responsabilidad al enumerado Ficha, y es la base sobre la que se apoyan Tablero y Partida.

## What Changes

- Nuevo enumerado `Ficha` en `es.ilerna.tresenraya.modelo` con los valores `X` y `O`.
- Nuevo método público `siguiente(): Ficha` que devuelve la ficha del turno siguiente.
- Tests unitarios del enumerado.

## Capabilities

### New Capabilities
- `ficha`: fichas del juego y cálculo del siguiente turno.

### Modified Capabilities

## Impact

- Código nuevo: `modelo/Ficha.java` y `modelo/FichaTest.java`.
- Sin dependencias nuevas.
