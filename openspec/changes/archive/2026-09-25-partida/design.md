# Design

## Context

Ver proposal.md. El diagrama fija `- turno` (asociación con Ficha) y `- tablero` (asociación con Tablero), y un `jugar(int, int)` que devuelve `void`.

## Goals / Non-Goals

**Goals:**
- Orquestar la partida delegando en Ficha (siguiente turno) y en Tablero (jugadas, lleno, victoria).

**Non-Goals:**
- Getters de turno o de casillas: el diagrama no los tiene; el estado se consulta con `toString()`, `terminada()` y `ganador()`.
- Informar del motivo de una jugada rechazada: `jugar` es `void` por diagrama.

## Decisions

- **Delegación**: `jugar(f, c)` llama a `tablero.jugar(turno, f, c)` y solo si devuelve `true` hace `turno = turno.siguiente()`. La Partida nunca calcula el turno ni toca casillas por sí misma.
- **Guarda de fin**: `jugar` no hace nada si `terminada()`. Sin ella, se podría seguir colocando fichas tras un tres en raya.
- **`terminada()`** = `tablero.estaLleno() || tablero.gana(Ficha.X) || tablero.gana(Ficha.O)`.
- **`ganador()`** recorre `Ficha.values()` y devuelve la primera que `gana`, o `null`. Alternativa descartada: guardar el ganador en un atributo, que añadiría un atributo fuera del diagrama.
- **`toString()`** = `"Turno: " + turno + "\n" + tablero`.

## Risks / Trade-offs

- [Con `jugar` void, quien llama no sabe si la jugada se aceptó] → La vista puede compararlo con `toString()` antes y después (decisión 7 del contexto del proyecto).
- [Tras terminar, `toString()` muestra el turno de la ficha que "tocaría"] → Aceptable: la vista muestra el resultado con `ganador()`.
