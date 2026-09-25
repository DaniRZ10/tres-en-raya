# ficha Specification

## Purpose
Define las fichas con las que se juega al tres en raya y cómo se calcula el turno siguiente a partir de la ficha actual.

## Requirements

### Requirement: Valores de ficha
El sistema SHALL ofrecer exactamente dos fichas: X y O.

#### Scenario: Fichas disponibles
- **WHEN** se consultan los valores posibles de ficha
- **THEN** existen únicamente X y O, en ese orden

### Requirement: Cálculo del siguiente turno
La ficha SHALL saber calcular cuál es la ficha del turno siguiente, alternando entre X y O. Esta es su única responsabilidad.

#### Scenario: Después de X va O
- **WHEN** se pide el siguiente turno a la ficha X
- **THEN** se obtiene O

#### Scenario: Después de O va X
- **WHEN** se pide el siguiente turno a la ficha O
- **THEN** se obtiene X

#### Scenario: Dos turnos vuelven a la misma ficha
- **WHEN** se pide el siguiente turno dos veces seguidas partiendo de una ficha
- **THEN** se obtiene la ficha de partida
