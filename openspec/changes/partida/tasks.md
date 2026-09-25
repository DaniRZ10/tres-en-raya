# Tasks

## 1. Partida

- [x] 1.1 Crear `modelo/Partida.java` con los atributos privados `turno` (inicial X) y `tablero`, y el constructor `Partida(int)` (verificar: test de partida recién creada)
- [x] 1.2 Implementar `jugar(int, int)` delegando en el tablero, avanzando el turno solo si la jugada se acepta e ignorando jugadas con la partida terminada (verificar: tests de jugada válida, turnos alternos, casilla ocupada, fuera del tablero y jugar tras terminar)
- [x] 1.3 Implementar `terminada()` y `ganador()` (verificar: tests de victoria de X, victoria de O y empate)
- [x] 1.4 Implementar `toString()` y Javadoc de todos los métodos públicos (verificar: test de pintado del escenario de la spec)
