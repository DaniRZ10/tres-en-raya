# Design

## Context

Ver proposal.md. El modelo expone solo la interfaz del diagrama: `jugar(int, int)` es `void` y no hay getters.

## Goals / Non-Goals

**Goals:**
- Jugar partidas completas por consola usando solo la API pública de `Partida`.
- Poder testear la vista sin teclado real.

**Non-Goals:**
- Interfaz gráfica.
- Modificar el modelo para adaptarlo a la vista.

## Decisions

- **Inyección de E/S**: `VistaConsola(Scanner entrada, PrintStream salida)`. `Main` le pasa `System.in` y `System.out`; los tests, un `ByteArrayInputStream` y un `ByteArrayOutputStream`. Alternativa descartada: usar `System.in/out` directamente, que obliga a redirigir flujos globales en los tests.
- **Coordenadas en base 1 para el jugador**: más naturales ("1 1" = esquina); se convierten a base 0 antes de llamar a `jugar`.
- **Lectura por líneas** (`nextLine`) y parseo manual: evita que un token erróneo deje el `Scanner` en un estado inconsistente.
- **Detección de casilla ocupada comparando `toString()`**: como `jugar()` es `void` por diagrama, la vista guarda `partida.toString()` antes de jugar y lo compara después; si no cambia, la jugada fue rechazada. El rango ya se valida antes, así que el único motivo posible es casilla ocupada. Alternativa descartada: añadir un getter o cambiar la firma de `jugar`, que rompe el diagrama.
- **Dimensión 3** en una constante de la vista.

## Risks / Trade-offs

- [Comparar `toString()` acopla la vista al formato del texto] → Solo se compara igualdad antes/después, no se analiza el contenido; cualquier formato sirve.
