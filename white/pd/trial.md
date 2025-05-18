---
title: "AVL Trees"
author: "PAAW"
template: includes.html
self-contained: true
filters:
  - question-sanswer.lua
---

Now that you've had an introduction to AVL trees, let's take a step back and look at where this operation called rotation even comes from.

## The Canonical Trees

Let's start by taking the numbers 1,2,3 and creating every possible valid binary search tree from these three numbers.

::: question
Draw your search trees in the space below.

::: answer
![Five Trees](../AP_shared/BinaryTree/canonical-trees.png)

These are the five possible arrangements of 1, 2, and 3 in a BST.
:::
:::

## Code Examples

::: codesolution
::: skeleton
```java
Node leftRotate(Node one) {
    // TODO: Implement left rotation

}
```
:::

::: solution
```java
Node leftRotate(Node one) {
    Node two = one.right;
    one.right = two.left;
    two.left = one;
    return two;
}
```
:::
:::