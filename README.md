# Tres en Raya

Ejercicio de repaso de Despliegue de Aplicaciones Web (2º DAW, ILERNA). Es el tres en raya en Java, jugado por consola, siguiendo el diagrama de clases del enunciado.

## Cómo ejecutarlo

Necesitas Java 21 o superior y Maven.

```
mvn clean package
java -jar target/tres-en-raya-1.0.2.jar
```

Para pasar solo los tests: `mvn test`.

## Cómo se juega

Empieza X. En cada turno se escribe la fila y la columna del 1 al 3 separadas por un espacio (por ejemplo `2 3`). Si la casilla está ocupada o lo que escribes no vale, te lo vuelve a pedir. Al acabar dice quién ha ganado o si ha sido empate, y pregunta si quieres jugar otra.

## Clases

- `Ficha`: enum con X y O. Solo sabe cuál es el siguiente turno.
- `Tablero`: guarda las fichas, dice si una jugada es posible, si está lleno y si alguna ficha ha hecho tres en raya (horizontal, vertical o las dos diagonales).
- `Partida`: tiene el tablero y lleva el turno. Sabe si ha terminado y quién ha ganado.
- `VistaConsola`: la parte de la consola; pinta la partida con su `toString()` y lee las jugadas.

La casilla vacía es `null`, y por eso `valueOf()` del tablero devuelve `Object`: o la ficha, o un `-` para pintarla.
