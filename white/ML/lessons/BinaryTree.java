/**
 * BinaryTree.java
 * A single class implementation of a binary tree with testing functionality
 */
public class BinaryTree {
    // Node class for the binary tree
    private static class Node {
        int data;
        Node left;
        Node right;
        
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    private Node root;
    private int size;
    private int testsPassed;
    private int testsFailed;
    
    // Constructor
    public BinaryTree() {
        this.root = null;
        this.size = 0;
        this.testsPassed = 0;
        this.testsFailed = 0;
    }
    
    // Insert a value into the binary tree
    public void insert(int value) {
        root = insertRec(root, value);
        size++;
    }
    
    private Node insertRec(Node root, int value) {
        // If the tree is empty, return a new node
        if (root == null) {
            return new Node(value);
        }
        
        // Otherwise, recur down the tree
        if (value < root.data) {
            root.left = insertRec(root.left, value);
        } else if (value > root.data) {
            root.right = insertRec(root.right, value);
        }
        
        // Return the unchanged node pointer
        return root;
    }
    
    // Search for a value in the binary tree
    public boolean search(int value) {
        return searchRec(root, value);
    }
    
    private boolean searchRec(Node root, int value) {
        // Base case: root is null or the value is at root
        if (root == null) {
            return false;
        }
        if (root.data == value) {
            return true;
        }
        
        // Value is greater than root's data, search right subtree
        if (root.data < value) {
            return searchRec(root.right, value);
        }
        
        // Value is less than root's data, search left subtree
        return searchRec(root.left, value);
    }
    
    // Delete a value from the binary tree
    public void delete(int value) {
        root = deleteRec(root, value);
        size--;
    }
    
    private Node deleteRec(Node root, int value) {
        // Base case: If the tree is empty
        if (root == null) {
            return null;
        }
        
        // Otherwise, recur down the tree
        if (value < root.data) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.data) {
            root.right = deleteRec(root.right, value);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            
            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.data = minValue(root.right);
            
            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }
        
        return root;
    }
    
    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }
    
    // Get the size of the binary tree
    public int size() {
        return size;
    }
    
    // Check if the binary tree is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // In-order traversal
    public void inOrderTraversal() {
        System.out.print("In-order Traversal: ");
        inOrderTraversalRec(root);
        System.out.println();
    }
    
    private void inOrderTraversalRec(Node root) {
        if (root != null) {
            inOrderTraversalRec(root.left);
            System.out.print(root.data + " ");
            inOrderTraversalRec(root.right);
        }
    }
    
    // Pre-order traversal
    public void preOrderTraversal() {
        System.out.print("Pre-order Traversal: ");
        preOrderTraversalRec(root);
        System.out.println();
    }
    
    private void preOrderTraversalRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderTraversalRec(root.left);
            preOrderTraversalRec(root.right);
        }
    }
    
    // Post-order traversal
    public void postOrderTraversal() {
        System.out.print("Post-order Traversal: ");
        postOrderTraversalRec(root);
        System.out.println();
    }
    
    private void postOrderTraversalRec(Node root) {
        if (root != null) {
            postOrderTraversalRec(root.left);
            postOrderTraversalRec(root.right);
            System.out.print(root.data + " ");
        }
    }
    
    // Find height of the tree
    public int height() {
        return height(root);
    }
    
    private int height(Node root) {
        if (root == null) {
            return -1;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }
    
    // Test framework
    private void runTest(String testName, boolean condition) {
        if (condition) {
            System.out.println("✓ PASS: " + testName);
            testsPassed++;
        } else {
            System.out.println("✗ FAIL: " + testName);
            testsFailed++;
        }
    }
    
    // Print test summary
    private void printTestSummary() {
        System.out.println("\n===== TEST SUMMARY =====");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Total Tests: " + (testsPassed + testsFailed));
        
        if (testsFailed == 0) {
            System.out.println("All tests passed successfully!");
        } else {
            System.out.println("Some tests failed. Please check the implementation.");
        }
    }
    
    // Main method with tests
    public static void main(String[] args) {
        System.out.println("Starting Binary Tree Tests...\n");
        
        BinaryTree tree = new BinaryTree();
        
        // Test 1: Check if tree is initially empty
        tree.runTest("Tree should be empty initially", tree.isEmpty());
        
        // Test 2: Insert values and check size
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
        
        tree.runTest("After inserting 7 nodes, tree size should be 7", tree.size() == 7);
        tree.runTest("Tree should not be empty after insertion", !tree.isEmpty());
        
        // Display tree structure with traversals
        System.out.println("\nTree traversals after insertion:");
        tree.inOrderTraversal();
        tree.preOrderTraversal();
        tree.postOrderTraversal();
        
        // Test 3: Search for values
        tree.runTest("Search for existing value 30 should return true", tree.search(30));
        tree.runTest("Search for existing value 80 should return true", tree.search(80));
        tree.runTest("Search for non-existing value 100 should return false", !tree.search(100));
        
        // Test 4: Height of the tree
        tree.runTest("Height of the tree should be 2", tree.height() == 2);
        
        // Test 5: Delete leaf node
        System.out.println("\nDeleting leaf node 20...");
        tree.delete(20);
        tree.runTest("After deleting a leaf node, size should be 6", tree.size() == 6);
        tree.runTest("Search for deleted value 20 should return false", !tree.search(20));
        
        // Display tree structure after deletion
        System.out.println("\nTree traversals after deleting leaf node:");
        tree.inOrderTraversal();
        
        // Test 6: Delete node with one child
        System.out.println("\nDeleting node 30 with one child...");
        tree.delete(30);
        tree.runTest("After deleting a node with one child, size should be 5", tree.size() == 5);
        tree.runTest("Search for deleted value 30 should return false", !tree.search(30));
        
        // Display tree structure after deletion
        System.out.println("\nTree traversals after deleting node with one child:");
        tree.inOrderTraversal();
        
        // Test 7: Delete node with two children
        System.out.println("\nDeleting node 50 (root) with two children...");
        tree.delete(50);
        tree.runTest("After deleting root node, size should be 4", tree.size() == 4);
        tree.runTest("Search for deleted value 50 should return false", !tree.search(50));
        
        // Display tree structure after root deletion
        System.out.println("\nTree traversals after deleting root node:");
        tree.inOrderTraversal();
        tree.preOrderTraversal();
        tree.postOrderTraversal();
        
        // Test 8: Insert more nodes and check height
        System.out.println("\nInserting more nodes to test height...");
        tree.insert(55);
        tree.insert(65);
        tree.insert(75);
        tree.insert(85);
        
        tree.runTest("After inserting more nodes, size should be 8", tree.size() == 8);
        tree.runTest("Height of the tree should now be 3", tree.height() == 3);
        
        // Display final tree structure
        System.out.println("\nFinal tree traversals:");
        tree.inOrderTraversal();
        tree.preOrderTraversal();
        tree.postOrderTraversal();
        
        // Print test summary
        tree.printTestSummary();
    }
}
