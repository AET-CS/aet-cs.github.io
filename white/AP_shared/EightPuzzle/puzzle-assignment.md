# Assignment: 8-Puzzle Solver with Breadth-First Search

## Overview
In this assignment, you will implement a solver for the classic 8-puzzle game using the Breadth-First Search (BFS) algorithm. The 8-puzzle consists of a 3×3 grid with tiles numbered 1-8 and one empty space. The goal is to slide tiles into the empty space to arrange them in numerical order.

This project will help you understand:
- How to perform graph search on implicit state spaces
- State representation and manipulation
- Avoiding repeated states in search algorithms
- Breaking down complex problems into manageable pieces

## Background
The 8-puzzle is a sliding puzzle where tiles can only move into the adjacent empty space. Starting from a scrambled configuration, your solver will find a sequence of moves that leads to the goal state:

```
Goal State:
1 2 3
4 5 6
7 8 _
```

## Your Task
You will implement three methods across two classes:

### 1. In `PuzzleModel.java`:
- **`randomMove()`** - Makes a single random valid move
- **`solve(PuzzleView view)`** - Calls your solver and returns the solution path

### 2. Create `PuzzleSolver.java`:
- Implement the entire class with a BFS algorithm to solve the puzzle

## Provided Code
You are given a complete GUI application with:
- `PuzzleApp.java` - Main application with buttons for "New Game", "Random Move", and "Solve"
- `PuzzleController.java` - Handles user interactions
- `PuzzleModel.java` - Manages puzzle state (partially complete)
- `PuzzleView.java` - Displays the puzzle with animations

## Implementation Details

### State Representation
The puzzle state is represented as a 1D array of integers:
- Numbers 1-8 represent tiles
- 0 represents the empty space
- Array positions map to grid positions in row-major order:
  ```
  Array index: [0][1][2][3][4][5][6][7][8] corresponds to
  Grid position:
  [0,0][0,1][0,2]
  [1,0][1,1][1,2]
  [2,0][2,1][2,2]
  ```
- You will need to convert from 1D to 2D indices and back (think about how to do this)

### Method Specifications

#### 1. `randomMove()` in PuzzleModel (Implement First!)
```java
public boolean randomMove()
```
- Get all possible moves from the current position
- Randomly select one valid move
- Execute the move using the existing `move(int position)` method
- Return true if a move was made, false otherwise
- **Start here!** This is the simplest method and will help you understand how moves work

#### 2. `solve(PuzzleView view)` in PuzzleModel
```java
public Stack<Integer> solve(PuzzleView view)
```
- Create a new PuzzleSolver instance
- Get the current board state using `getBoard()`
- Call the solver's `bfs_solve()` method
- Return the solution path as a Stack of positions

#### 3. `PuzzleSolver` Class Structure
Your PuzzleSolver class should include:

**Inner Node Class:**
```java
private class Node {
    int[] state;      // The puzzle configuration
    Node parent;      // The node that led to this state
    int move;         // The move (position) that was made to reach this state
    // Add any other fields you find useful
}
```
The Node class is essential for:
- Storing states in the BFS queue
- Tracking the path back to the start
- Reconstructing the solution

**Helper Methods:**
- `findBlank(int[] state)` - Returns the position (0-8) of the empty space
- `validMoves(int[] state)` - Returns a list of positions that can move into the blank
- `makeMove(int[] state, int move)` - Returns a NEW state array after moving the tile at 'move' position
- `isSolved(int[] state)` - Checks if the state matches the goal configuration

**Main Algorithm:**
- `bfs_solve(int[] state)` - Implements BFS to find solution path using a Queue of Node objects

### BFS Algorithm Overview
1. Create a queue of Node objects for states to explore
2. Create a Set to track visited states (use `Arrays.toString(state)` as the key)
3. Create the initial Node with the starting state
4. While the queue is not empty:
   - Remove a Node from the queue
   - If it's the goal state, reconstruct and return the path
   - For each valid move from this state:
     - Create the new state
     - If not visited, create a new Node and add to queue

### Critical Implementation Notes

#### Implementation order
- I would first figure out how to convert from 1D to 2D indices and back
- Add 'main' to your puzzleSolver class and test JUST those methods
- Then implement findBlank, given a valid state.
- Implement 'validMoves' -- which positions are adjacent to a blank? Check your answer!
- Implement 'makeMove' -- be sure to COPY the original array and do *not* modify it
- isSolved should be easy
- Finally, work on BFS. Test it with simple puzzles (like you move the blank one position away)

#### Avoiding Repeated States
- **Why it matters**: Without tracking visited states, BFS will explore the same configurations repeatedly, causing infinite loops
- **How to check**: Convert state arrays to strings using `Arrays.toString(state)`
- **When to check**: Before adding any new state to the queue

#### Copying Arrays (Avoiding Aliases)
- **The problem**: Java arrays are reference types. Writing `int[] newState = state` creates an alias, not a copy
- **The solution**: Use `System.arraycopy()` or create a new array and copy elements
- **Example**:
  ```java
  int[] newState = new int[state.length];
  System.arraycopy(state, 0, newState, 0, state.length);
  ```

#### Reconstructing the Solution Path
- Track parent states and the moves that led to them
- When you find the goal, work backwards to build the solution
- Return moves as a Stack (so they can be popped in correct order)

## Testing Your Implementation
1. **For early testing**: Consider temporarily changing MAX_SHUFFLES in PuzzleModel to a small number like 10, so puzzles are easier to solve while debugging
2. Click "New Game" to scramble the puzzle
3. Test "Random Move" first - it should make valid moves only
4. Click "Solve" - the puzzle should animate to the solution
5. Try multiple puzzles to ensure reliability
6. Don't forget to change MAX_SHUFFLES back to 1000 for final testing!

## Deliverables
1. **Code Files**:
   - Your completed `PuzzleModel.java` with `randomMove()` and `solve()` methods
   - Your complete `PuzzleSolver.java` implementation
   - All other provided files (unchanged)

2. **Reflection** (1-2 pages):
   - Explain how your BFS algorithm works
   - Discuss why avoiding repeated states is crucial
   - Describe any challenges you faced and how you solved them
   - What did you learn about breaking down complex problems?

## Extensions (Optional)
- Add a move counter to track solution length
- Implement detection of unsolvable puzzles
- Add different search algorithms (DFS, A*)
- Create a 15-puzzle (4×4) version

## Hints
- **Start with `randomMove()`** - it's the simplest method and helps you understand the move mechanics
- The Node class is crucial - think carefully about what information each Node needs to store
- Start by implementing and testing each helper method individually
- Use print statements to debug your BFS (remove before submission)
- Test with a puzzle that's one move from solved, then two moves, etc.
- Remember that not all positions are valid moves - only tiles adjacent to the blank can move
- Your BFS queue should store Node objects, not just states

## Due Date
This assignment is due one week from today. Submit all code files and your reflection to javadrop
