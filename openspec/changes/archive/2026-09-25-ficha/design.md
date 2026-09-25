# Design

## Context

El diagrama UML fija `<<enum>> Ficha` con `+ X`, `+ O` y `+ siguiente(): Ficha`.

## Goals / Non-Goals

**Goals:**
- Implementar el enumerado tal y como aparece en el diagrama.

**Non-Goals:**
- Representar la casilla vacía como un tercer valor: la casilla vacía se modela con `null` en el Tablero, porque el diagrama solo define X y O.

## Decisions

- `siguiente()` se implementa con un operador ternario (`this == X ? O : X`). Alternativa descartada: `values()[(ordinal() + 1) % 2]`, más genérica pero menos legible para dos valores.

## Risks / Trade-offs

- [Añadir un tercer valor rompería la alternancia] → El diagrama solo contempla X y O; los tests lo fijan.
