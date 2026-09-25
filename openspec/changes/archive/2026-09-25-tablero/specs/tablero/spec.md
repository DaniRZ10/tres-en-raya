# Spec Delta

## Purpose

Define el tablero cuadrado sobre el que se colocan las fichas: qué jugadas acepta, cuándo está lleno, cuándo una ficha ha ganado y cómo se muestra en texto.

## ADDED Requirements

### Requirement: Creación del tablero
El tablero SHALL crearse vacío con la dimensión indicada (n filas por n columnas). Una dimensión menor que 1 MUST rechazarse con un error.

#### Scenario: Tablero nuevo vacío
- **WHEN** se crea un tablero de dimensión 3
- **THEN** tiene 3x3 casillas, todas vacías, y no está lleno

#### Scenario: Dimensión no válida
- **WHEN** se intenta crear un tablero de dimensión 0 o negativa
- **THEN** se produce un error de argumento no válido

### Requirement: Colocar una ficha
El tablero SHALL colocar una ficha en la fila y columna indicadas (base 0) e informar de si la jugada ha sido posible. La jugada MUST rechazarse, sin modificar el tablero, si la casilla está ocupada, si la posición está fuera del tablero o si no se indica ficha.

#### Scenario: Casilla libre
- **WHEN** se juega X en una casilla vacía dentro del tablero
- **THEN** la jugada es posible y la casilla pasa a contener X

#### Scenario: Casilla ocupada
- **WHEN** se juega en una casilla que ya contiene una ficha
- **THEN** la jugada no es posible y la casilla conserva su ficha original

#### Scenario: Fuera del tablero
- **WHEN** se juega con una fila o columna negativa o mayor o igual que la dimensión
- **THEN** la jugada no es posible y el tablero no cambia

#### Scenario: Sin ficha
- **WHEN** se juega sin indicar ficha
- **THEN** la jugada no es posible

### Requirement: Tablero lleno
El tablero SHALL indicar que está lleno solo cuando todas sus casillas contienen una ficha.

#### Scenario: Tablero completo
- **WHEN** se han ocupado todas las casillas
- **THEN** el tablero está lleno

#### Scenario: Queda una casilla
- **WHEN** queda al menos una casilla vacía
- **THEN** el tablero no está lleno

### Requirement: Detección de tres en raya
Una ficha SHALL ganar cuando ocupa una fila completa, una columna completa, la diagonal directa (de arriba-izquierda a abajo-derecha) o la diagonal indirecta (de arriba-derecha a abajo-izquierda). Cada una de las cuatro comprobaciones MUST poder consultarse por separado. Consultar si gana "ninguna ficha" MUST devolver que no.

#### Scenario: Gana en horizontal
- **WHEN** una ficha ocupa todas las casillas de una fila
- **THEN** esa ficha gana y la comprobación horizontal es cierta

#### Scenario: Gana en vertical
- **WHEN** una ficha ocupa todas las casillas de una columna
- **THEN** esa ficha gana y la comprobación vertical es cierta

#### Scenario: Gana en la diagonal directa
- **WHEN** una ficha ocupa las casillas (0,0), (1,1) y (2,2)
- **THEN** esa ficha gana y la comprobación de diagonal directa es cierta

#### Scenario: Gana en la diagonal indirecta
- **WHEN** una ficha ocupa las casillas (0,2), (1,1) y (2,0)
- **THEN** esa ficha gana y la comprobación de diagonal indirecta es cierta

#### Scenario: Línea incompleta o mezclada
- **WHEN** una línea tiene casillas vacías o fichas de ambos tipos
- **THEN** ninguna ficha gana por esa línea

#### Scenario: El rival no gana
- **WHEN** X ha hecho tres en raya
- **THEN** O no gana

### Requirement: Representación en texto
El tablero SHALL poder pintarse como texto: una línea por fila, con las casillas separadas por un espacio, mostrando la ficha (X u O) o un guion (-) si la casilla está vacía.

#### Scenario: Pintar un tablero con fichas
- **WHEN** se pinta un tablero 3x3 con X en (0,0) y O en (1,1)
- **THEN** el texto es "X - -", "- O -" y "- - -" en tres líneas
