# Tasks

## 1. Estructura y jugadas

- [ ] 1.1 Crear `modelo/Tablero.java` con el atributo privado `Ficha[][] tablero` y el constructor `Tablero(int)` que valida la dimensión (verificar: tests de creación y de dimensión no válida)
- [ ] 1.2 Implementar `jugar(Ficha, int, int)` con validación de ficha, rango y casilla ocupada (verificar: tests de casilla libre, ocupada, fuera de rango y sin ficha)
- [ ] 1.3 Implementar `estaLleno()` (verificar: tests de tablero completo y con una casilla libre)

## 2. Detección de victoria

- [ ] 2.1 Implementar los cuatro auxiliares protected `ganaHorizontal`, `ganaVertical`, `ganaDiagonalDirecta` y `ganaDiagonalIndirecta` (verificar: un test por dirección para X y para O)
- [ ] 2.2 Implementar `gana(Ficha)` como OR de los cuatro, devolviendo false con null (verificar: tests de línea incompleta, mezclada, rival que no gana y tablero vacío)

## 3. Representación

- [ ] 3.1 Implementar `valueOf(Ficha): Object` privado y `toString()` (verificar: test de pintado del escenario de la spec)
- [ ] 3.2 Javadoc en todos los métodos públicos y protected (verificar: revisión del código)
