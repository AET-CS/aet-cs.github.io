---
title: Tic Tac Toe
---

## Overview

Write a text-based Tic Tac Toe game where you (X) play against a computer opponent (O) that moves randomly. Check the board after each move and stop when the game is over. Submit to javadrop at the end of class. You may work with a partner.

## Requirements

- Display the board after each move
- You are always X and move first
- Prompt for your move (row and column, or a single number 1-9—your choice)
- Computer picks a random empty space for O
- Detect when someone wins or the board is full (tie)
- Announce the result

## Example Partial Game

```
1 | 2 | 3
---------
4 | 5 | 6
---------
7 | 8 | 9

Your move (1-9): 5

1 | 2 | 3
---------
4 | X | 6
---------
7 | 8 | 9

Computer plays O...

1 | 2 | O
---------
4 | X | 6
---------
7 | 8 | 9

Your move (1-9): 1

X | 2 | O
---------
4 | X | 6
---------
7 | 8 | 9

Computer plays O...

X | 2 | O
---------
4 | X | 6
---------
O | 8 | 9

Your move (1-9): 9

X | 2 | O
---------
4 | X | 6
---------
O | 8 | X

X wins!
```