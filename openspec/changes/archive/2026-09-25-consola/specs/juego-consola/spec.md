# Spec Delta

## Purpose

Define cómo se juega al tres en raya desde la consola: qué se muestra, cómo se introducen las jugadas, cómo se tratan los errores y cómo termina cada partida.

## ADDED Requirements

### Requirement: Mostrar la partida
Antes de cada jugada, la consola SHALL mostrar la partida (turno y tablero) y pedir la fila y la columna, numeradas del 1 a la dimensión del tablero.

#### Scenario: Inicio del juego
- **WHEN** se arranca el juego
- **THEN** se muestra un título, la partida con "Turno: X" y el tablero vacío, y se pide "Fila y columna (1-3):"

### Requirement: Introducir una jugada
El jugador SHALL introducir la fila y la columna como dos números separados por espacio en una línea. Una entrada con otro formato o con valores fuera de 1..n MUST rechazarse con un mensaje de error, sin jugar, volviendo a pedir la jugada.

#### Scenario: Jugada válida
- **WHEN** a X le toca y el jugador escribe "1 1"
- **THEN** X se coloca en la esquina superior izquierda y se muestra "Turno: O"

#### Scenario: Entrada no numérica
- **WHEN** el jugador escribe "hola"
- **THEN** se muestra un mensaje de entrada no válida y se vuelve a pedir la jugada sin cambiar el turno

#### Scenario: Valores fuera de rango
- **WHEN** el jugador escribe "0 4"
- **THEN** se muestra un mensaje de entrada no válida y se vuelve a pedir la jugada sin cambiar el turno

### Requirement: Casilla ocupada
Si el jugador elige una casilla ya ocupada, la consola SHALL avisar de que está ocupada y volver a pedir la jugada al mismo jugador.

#### Scenario: Repetir casilla
- **WHEN** O elige una casilla donde ya está X
- **THEN** se muestra "Casilla ocupada" y sigue siendo el turno de O

### Requirement: Resultado y nueva partida
Al terminar la partida, la consola SHALL mostrar el tablero final y el resultado ("Gana X", "Gana O" o "Empate"), y preguntar si se quiere jugar otra. Con "s" MUST empezar una partida nueva; con cualquier otra respuesta, o si se acaba la entrada, el programa MUST despedirse y terminar.

#### Scenario: Victoria
- **WHEN** X completa una línea
- **THEN** se muestra "Gana X" y se pregunta "¿Otra partida? (s/n)"

#### Scenario: Empate
- **WHEN** se llena el tablero sin tres en raya
- **THEN** se muestra "Empate"

#### Scenario: Otra partida
- **WHEN** el jugador responde "s"
- **THEN** empieza una partida nueva con el tablero vacío y turno de X

#### Scenario: Salir
- **WHEN** el jugador responde "n"
- **THEN** se muestra un mensaje de despedida y el programa termina

#### Scenario: Fin de la entrada
- **WHEN** la entrada estándar se cierra en mitad de una partida
- **THEN** el programa termina sin errores
