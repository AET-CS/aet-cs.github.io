# Tic Tac Toe Player

Modify your code so that you a class **MyPlayer** with a method **getMove(String board, char player)**. When `getMove` is called, return an int 1-9 for the move your player wants to make.

- **board:** the board is a length 9 String with 'x' or 'o' or '.' in each location. This will always be a valid game board with at least one open square. The string '.........' indicates a new game. The first 3 chars indicate the first row of the game board. For example the string '.x.oo.xx.' is the board
```
_ X _
O O _
X X _
```
And the valid moves are: `[1,3,6,9]`

- **player:** 'x' or 'o' indicating the player who will make the move

You can test your player with this [Driver file](./Tester.java).

You should implement your best strategy in MyPlayer. When you are ready upload your strategy to JavaDrop and see how it fares against 4 other strategies of increasing complexity.

**Can you win a majority of games?**