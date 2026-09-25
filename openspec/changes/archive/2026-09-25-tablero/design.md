# Design

## Context

Ver proposal.md. El diagrama fija firmas y visibilidades: `- tablero: Ficha[][]` (agregación de Ficha), cuatro auxiliares `#` (protected) y `- valueOf(Ficha): Object` (private).

## Goals / Non-Goals

**Goals:**
- Implementar exactamente la interfaz del diagrama para cualquier dimensión n.

**Non-Goals:**
- Getters de casillas u otros métodos públicos: no están en el diagrama.
- Victorias con menos fichas que la dimensión (p. ej. 3 en raya en un 5x5): se gana completando la línea entera.

## Decisions

- **Casilla vacía = `null`** en `Ficha[][]`. Alternativa descartada: un valor `VACIA` en el enum, que contradice el diagrama (solo X y O).
- **`valueOf(Ficha): Object`** devuelve la propia Ficha o el `String` `"-"` si es `null`. Por eso su tipo de retorno es `Object`: el supertipo común de ambos. `toString()` lo usa para no repetir la lógica de la casilla vacía.
- **`gana(Ficha)`** es el OR de los cuatro auxiliares. Con `null` devuelve `false` directamente; si no, en un tablero vacío la "diagonal de nulls" daría una victoria falsa.
- **`jugar`** comprueba rango y ocupación antes de escribir, así que una jugada rechazada nunca modifica el tablero.
- **`toString`** usa `StringBuilder` y termina cada fila en salto de línea.

## Risks / Trade-offs

- [Los auxiliares protected no se ven desde otros paquetes] → Los tests viven en el mismo paquete y pueden invocarlos directamente.
