---
title: "Shuffler"
---


Write a program to shuffle a deck of cards. Given parameter *even* $n$, create an array (or arraylist or stack or queue or whatever works best) of cards $1...n$. Submit your code with "main" showing all parts to javadrop. You do not have to finish all of it in class today, unless I change my mind.

## Part 1

* Print the list of all cards in order
* Perform a perfect riffle outside shuffle and print the new order
* Show several examples in your output including $n=52$

### Example

```
n = 8
1 2 3 4 5 6 7 8
1 5 2 6 3 7 4 8
```

## Part 2

* Print the list of all cards in order
* Shuffle as many times as it takes to return to the original order.
* Print each shuffle
* Output the number of shuffles required

### Example

```
n = 4
0: 1 2 3 4
1: 1 3 2 4
2: 1 2 3 4

n=4 requires 2 perfect shuffles
```

## Part 3

* Let n = 10 to 100
* For each $n$ print the number of shuffles required to return to the original order

## Part 4

* Implement "chunk shuffles". A chunk shuffle of size 3, for example, takes 3 cards from each half
* Let n = 52. For chunk size = 1 to 12, print how many shuffles are required to return to the original order.
* The last 'chunk' may be incomplete.