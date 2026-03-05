# Connect Four (Java Swing MVP)

AP CS-oriented Connect Four using MVC and modular player classes.

## MVP Features
- Graphical Swing interface
- MVC design (`model`, `view`, `controller` packages)
- `Player` abstraction with `HumanPlayer` and `RandomPlayer`
- Fixed 6x7 board
- Click-on-board input (chooses nearest column)
- Drop animation for each piece
- Configurable AI delay in source (`GameController`)
- Game state enum values: `PLAYING`, `TIE`, `RED_WINS`, `BLUE_WINS`

## Input Reliability Note
- Board input is handled on mouse press (`mousePressed`) rather than mouse click to reduce missed human moves during fast play.
- Human turns keep accepting clicks until a valid move is consumed for that turn.

## Player API
The game requests moves using:

```java
int col = player.getMove(board, color);
```

Where:
- `board` is a copy of current board state
- `color` is `Piece.RED` or `Piece.BLUE`
- return value is chosen column `0..6`

## Build & Run (from project root)

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out connectfour.ConnectFourApp
```

## Extend Later
- Add `MinMaxPlayer extends Player`
- Add autonomous N-game runner to record results
