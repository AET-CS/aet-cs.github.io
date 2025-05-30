---
title: "Intro to BFS"
layout: single
# Optional title customizations
excerpt: "Learning BFS to search trees"
---

BFS (breadth-first-search) is a way to search (or traverse) a tree data structure. This tree does *not* have to be binary. Each node can have any number of children. (Other than that it's like a binary tree). Examples include: the files in a directory structure on your computer (nodes are folders, children are files or folders inside that folder), a family tree (children are literal children, although if we include marriages, this isn't quite a proper tree anymore); and the friend tree (first level is all your close friends, then second level is all their close friends, etc. To be a tree you only list each person once)

BFS can be implemented with a queue. Starting at the root, we add all the children of the root to a queue. Then we start popping elements off the (front of) the queue and pushing that node's children to the (end of) the queue. Repeat until the queue is empty. Here's an algorithm

```python
function BFS(root):
  Queue q = new Queue()
  q.push(root)
  while (q is not empty) do:
    current = q.pop()
    for each child in getChildren(current) do:
        q.push(child)
```

## Example

{% capture question1 %}
Make a depth 3 permutation tree for {A,B,C}. The root of a permutation tree is an empty string. Each node is a string containing a subset of {A,B,C,D} in some order. The children of a node $s$ containing a string of length $n$ are all strings on $n+1$ created by appending a character *not* already in $s$ onto the end of $s$.
{% endcapture %}

{% capture answer1 %}
<img src="https://aet-cs.github.io/white/AP_shared/TreeSearch/BFS-0.svg" alt="Five Trees" style="width: 100%;" />
{% endcapture %}
{% include answer-box.html question = question1 content=answer1 %}

{% capture question2 %}
Now trace BFS starting at the root of the tree you just made. Show the contents of the stack after each push and pop.
{% endcapture %}

{% capture answer2 %}
<img src="https://aet-cs.github.io/white/AP_shared/TreeSearch/BFS-1.svg" alt="Five Trees" style="width: 100%;" />
{% endcapture %}
{% include answer-box.html question=question2 content=answer2 %}

## Coding Assignment

Code up this BFS algorithm in Java. Specifically, generate all string permutations of {A,B,C,D} such as "ACDB" etc, by performing a BFS on the tree of substrings.  Let the start state (root) be empty string "". Write a `getChildren(String state)` method that returns a list/array of the children of `state`, made by appending one new letter to `state`.

The output of your code should be all the *leaves* in the search tree. (There will be 24). You should print a leaf as soon as you pop it off the tree and recognize it's a left.

Note: this tree does not need *Node*s. In fact you never actually create a tree!. You build it as you search, just the parts you need. This is critical for large problems (like playing chess) where you can't possibly build the whole tree.

Please submit your class file (with main that shows the output) to javadrop by Tuesday 9:00PM. We'll code up a bigger problem in class!

## BFS as Uninformed Goal Search

Interesting BFS problems are often stated as a "goal search." In a goal search problem, you can imagine moving through a search space with states, a goal, and a start state. For example, a maze is a goal search problem. The states are valid locations in the maze, the start state is the entrance, the goal state is the exit. The children of any one state are all the states you can get to in one step, say, going North, South, East or West. Goal search BFS can be written by adding a return if you discover the goal state.

```python
function BFS(root) returns a state:
  Queue q = new Queue()
  q.push(root)
  while (q is not empty) do:
    current = q.pop()
    for each child in getChildren(current) do:
      q.push(child)
      if (isGoalState(child)):
        return child
  return null
```

In many goal search problems, it's not enough to return the goal itself. You also need to return the path to the goal. You can reconstruct the path by storing a back-pointer to the parent of each state in the tree. In this case, we do need to build actual `nodes` because they store more than just the state

```python
class Node
    state: string or int or array, etc
    parent: Node
    (other optional fields, like depth, children, etc.)


function BFS(root) returns a Node:
  Queue q = new Queue()
  Node rootNode = new Node(state = root, parent = null)
  q.push(root)

  while (q is not empty) do:
    current = q.pop()
    for each child in getChildren(current.state) do:
        childNode = new Node(state = child, parent = current)
        q.push(childNode)
        if (isGoalState(child)):
            return child
  return null

function getSolutionPath(root) returns a stack of states:
    goal = BFS(root)
    path = new stack()

    if goal is not null:
        current = goal
        while(current is not null)
            path.push(current.state)
            current = current.parent
    return path
```