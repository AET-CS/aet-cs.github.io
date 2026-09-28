# Towers of Hanoi

You have two files. `HanoiView.java` draws the pegs and animates the moves; you don't need to read it. `Towers.java` is yours.

The pegs are numbered 0, 1, 2. Each peg is an `ArrayList<Integer>` of disk sizes, with the **bottom disk at index 0** and the **top disk at the end**. Disks are numbered 1 (smallest) to n. So with 3 disks, the starting position is

```
pegs.get(0)  →  [3, 2, 1]
pegs.get(1)  →  []
pegs.get(2)  →  []
```

The goal is to move the whole tower to peg 2. You may move one disk at a time, always the top disk of a peg, and you may never put a disk on top of a smaller one.

Run `Towers` now. It compiles, but nothing works yet.

## Part 1: Playing by hand

Fill in `move` and `isLegal`.

- `move(from, to)` takes the top disk off peg `from` and puts it on peg `to`. The call to `view.show(from, to)` is already there. It must come **after** you change the pegs, because it checks that your pegs match the move.
- `isLegal(from, to)` returns whether the top disk of `from` may go on `to`. Think about what happens when either peg is empty.

When both work, you can play: click a peg to pick up its top disk, then click another peg to drop it. An illegal drop does nothing.

1. Solve the 3-disk puzzle by hand. How many moves did you use? Can you do it in fewer?
2. Solve the 4-disk puzzle by hand. What was the hardest part?

## Part 2: The recursion

Fill in `hanoi(n, from, to, spare)`, which moves the top `n` disks from peg `from` to peg `to`, using peg `spare` for temporary storage. Then press **Solve**.

Hint: forget about the small disks for a moment and think about the biggest one. When the biggest disk moves from `from` to `to`, where must all the other disks be?

Don't forget the base case. What is the smallest `n` for which there's nothing to do?

If you get an argument in the wrong order, the view shows the first illegal move in red and pauses.

## Part 3: Exploring

3. Turn on **Step mode** and solve 3 disks. Before each press of Step, predict the next move. Were you ever wrong?
4. Solve for n = 1, 2, …, 10 and make a table of the number of moves. Guess a formula. Then explain, using your `hanoi` method, why the formula has to be right.
5. The move log indents each move by how deep in the recursion it happened. At n = 5, which disk moves on the least-indented line? Which disk moves on every odd-numbered move?
6. Break your code on purpose: swap two of the arguments in one of your recursive calls. What happens? Try a few different swaps. Does any wrong version still solve the puzzle?
7. A legend says monks are moving a tower of 64 golden disks, one disk per second, and the world will end when they finish. How long do we have?
