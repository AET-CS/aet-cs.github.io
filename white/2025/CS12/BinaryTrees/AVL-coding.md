---
title: Coding AVL insert
---


## height
You'll need to add a private *height* field. You should also write an accessor method `getheight(Node n)` that returns the height of `n` if `n` is a Node and 0 if `n` is null. (This will simplify other parts of your code)

Updating the height happens every time you insert or rotate.

## Rotations

You'll need to define the new methods for each of the four rotations. Follow the code from class. The parameter to each rotation is the **root** of the subtree that is rotating. You will return the root of the new subtree after rotation.

## Insert

The logic for inserting recursively is subtle. Here's an outline. You should fill in the missing pieces, starting with your existing `insert` logic.


```java
private Node insert_recursive(Node root, int data) {
        // your regular insertion code
        if (root == null) {
            root = new Node(data);
        } else if (data < root.data) {
            root.left = insert_recursive(root.left, data);
        } else {
            root.right = insert_recursive(root.right, data);
        }
        // now add code to update the height of the root
        // (and only the root -- why?? -- because recursion!)


        // next get the balance factor of the root
        // why just the root?? recursion!


        // Finally check if you need to do any of the 4 rotations
        // These may change the root (and the subtree underneath it)

        // finally we return the root
        // because of recursion, this 'return' happens for each node
        // we touch while inserting
        return root;
    }
```

## Count Nodes

I don't remember if you already implemented this but if not, please implement `count_nodes()` to return the number of nodes in a tree, or 0 for a null pointer.

```java
    public int count_nodes() {
        return count_nodes_recursive(this.root);
    }
```