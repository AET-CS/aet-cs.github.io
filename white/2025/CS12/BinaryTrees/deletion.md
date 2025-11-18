# Delete from Binary Tree

Here's some detailed psuedocode for recursive delete. Note how every branch returns a node. This is one of the keys to making recursive algorithms work on data structures like this.
```
FUNCTION delete_recursive(target, node):

    // BASE CASE: Target not found
    IF node is NULL:
        RETURN NULL

    // SEARCH PHASE: Navigate to target
    IF target < node.data:
        node.left = delete_recursive(target, node.left)
        RETURN node

    IF target > node.data:
        node.right = delete_recursive(target, node.right)
        RETURN node

    // FOUND THE TARGET! Handle deletion based on children

    // CASE 1: Leaf node (no children)
    IF node.left is NULL AND node.right is NULL:
        RETURN NULL

    // CASE 2a: Only right child exists
    IF node.left is NULL:
        RETURN node.right

    // CASE 2b: Only left child exists
    IF node.right is NULL:
        RETURN node.left

    // CASE 3: Two children
    predecessor_value = find_predecessor(node)
    node.data = predecessor_value
    node.left = delete_recursive(predecessor_value, node.left)
    RETURN node


FUNCTION find_predecessor(node):
    // Find the rightmost node in the left subtree
    current = node.left
    WHILE current.right is not NULL:
        current = current.right
    RETURN current.data


FUNCTION delete(target):
    // Public method - don't forget to update root!
    root = delete_recursive(target, root)
```
