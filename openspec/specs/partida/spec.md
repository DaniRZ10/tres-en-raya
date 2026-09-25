# partida Specification

## Purpose
Define cómo se desarrolla una partida de tres en raya: turnos alternos empezando por X, jugadas sobre el tablero, fin de partida y ganador.

## Requirements

### Requirement: Inicio de partida
Una partida nueva SHALL tener un tablero vacío de la dimensión indicada y el turno SHALL corresponder a X.

#### Scenario: Partida recién creada
- **WHEN** se crea una partida de dimensión 3
- **THEN** el tablero 3x3 está vacío, le toca a X, no está terminada y no hay ganador

### Requirement: Jugar un turno
Al jugar sobre una fila y columna, la partida SHALL pedir al tablero que coloque la ficha del turno actual en esa casilla. Solo si el tablero acepta la jugada, el turno MUST pasar a la ficha siguiente. Si la jugada no es posible (casilla ocupada o fuera del tablero), el turno MUST mantenerse.

#### Scenario: Jugada válida
- **WHEN** X juega en una casilla libre
- **THEN** la casilla contiene X y el turno pasa a O

#### Scenario: Turnos alternos
- **WHEN** se hacen varias jugadas válidas seguidas
- **THEN** las fichas colocadas alternan X, O, X...

#### Scenario: Casilla ocupada
- **WHEN** O intenta jugar en una casilla ocupada
- **THEN** el tablero no cambia y sigue siendo el turno de O

#### Scenario: Fuera del tablero
- **WHEN** se juega en una posición fuera del tablero
- **THEN** el tablero no cambia y el turno se mantiene

### Requirement: Fin de partida
La partida SHALL estar terminada cuando el tablero está lleno o cuando alguna ficha ha hecho tres en raya. Una vez terminada, las jugadas MUST ignorarse.

#### Scenario: Termina por victoria
- **WHEN** una ficha completa una línea
- **THEN** la partida está terminada aunque queden casillas libres

#### Scenario: Termina por tablero lleno
- **WHEN** se ocupan todas las casillas sin tres en raya
- **THEN** la partida está terminada

#### Scenario: Jugar tras terminar
- **WHEN** se intenta jugar en una casilla libre de una partida terminada
- **THEN** el tablero y el turno no cambian

### Requirement: Ganador
La partida SHALL indicar qué ficha ha ganado; si nadie ha ganado (partida en curso o empate) MUST indicar que no hay ganador.

#### Scenario: Gana X
- **WHEN** X hace tres en raya
- **THEN** el ganador es X

#### Scenario: Gana O
- **WHEN** O hace tres en raya
- **THEN** el ganador es O

#### Scenario: Empate
- **WHEN** el tablero se llena sin tres en raya
- **THEN** no hay ganador

### Requirement: Representación en texto
La partida SHALL poder pintarse como texto: una primera línea "Turno: " seguida de la ficha a la que le toca, y a continuación el tablero pintado.

#### Scenario: Pintar la partida
- **WHEN** se pinta una partida 3x3 en la que X ha jugado en (0,0)
- **THEN** el texto es "Turno: O" seguido de las líneas "X - -", "- - -" y "- - -"
