# Connect Four (Java Swing MVP)

AP CS-oriented Connect Four using MVC and modular player classes.

## MVP Features
- Graphical Swing interface
- MVC design (`model`, `view`, `controller` packages)
- `Player` abstraction with `HumanPlayer`, `RandomPlayer`, and `MinMaxPlayer`
- Player type selection via dropdown dialog
- Startup mode selection: `Single Game` or `Batch Play`
- Fixed 6x7 board
- Click-on-board input (chooses nearest column)
- Drop animation for each piece
- Configurable AI delay in source (`GameController`)
- Game state enum values: `PLAYING`, `TIE`, `RED_WINS`, `BLUE_WINS`

## Player API
The game requests moves using:

```java
int col = player.getMove(board, color);
```

Where:
- `board` is a copy of current board state
- `color` is `Piece.RED` or `Piece.BLUE`
- return value is chosen column `0..6`

## MinMax (Simple)
- Blue is maximizing and terminal score `+100`.
- Red is minimizing and terminal score `-100`.
- Depth is selected from dropdown choices: `MinMax-2`, `MinMax-4`, `MinMax-6`, `MinMax-8`, `MinMax-10`.
- Non-terminal boards at depth cutoff are scored as `0` (no heuristic).

## Batch Play
- Choose `Batch Play` at startup.
- Choose Red and Blue player types (AI-only in batch mode).
- Choose number of games from dropdown: `5`, `10`, `15`, `20`, `100`.
- Batch simulation runs without board animation.
- Status text and a progress bar show `completed/total` while running.
- Final results are shown in a dialog with wins/ties and percentages.

## Build & Run (from project root)

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out connectfour.ConnectFourApp
```

## IntelliJ Import (from ZIP)
- Unzip the `ConnectFour` folder.
- In IntelliJ, use `File -> Open...` and select the unzipped `ConnectFour` folder (open the folder root, not just `src`).
- If prompted, trust the project.
- Open `src/connectfour/ConnectFourApp.java` and run `ConnectFourApp.main()`.
- (If it won't run) In the Project panel, right-click `src` and choose `Mark Directory as -> Sources Root`.

If needed, set Java in `File -> Project Structure`:
- `Project SDK`: Java 11+ (Java 17 recommended)
- `Project language level`: SDK default

