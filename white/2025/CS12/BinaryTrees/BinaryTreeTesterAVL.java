public class BinaryTreeTesterAVL {

    // Counter for passed tests
    private static int passCount = 0;
    // Counter for total tests
    private static int totalTests = 0;

    /**
     * Helper method to run a test and report results
     *
     * @param testName Name of the test
     * @param expected Expected output of the test
     * @param actual   Actual output of the test
     */
    private static void runTest(String testName, Object expected, Object actual) {
        totalTests++;
        System.out.println("\nTesting " + testName);
        System.out.println("Expected output: " + expected);
        System.out.println("Actual output: " + actual);

        boolean passed = false;
        if (expected == null && actual == null) {
            passed = true;
        } else if (expected != null && expected.equals(actual)) {
            passed = true;
        }

        if (passed) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL");
        }
    }

    public static void main(String[] args) {
        System.out.println("Binary Tree Tester\n");

        // Test 1: Empty tree constructor
        BinaryTree emptyTree;
        emptyTree = new BinaryTree();
        runTest("Empty tree constructor", null, emptyTree.root);

        // Test 2: Constructor with data
        BinaryTree singleNodeTree = new BinaryTree(10);
        boolean singleNodeTest = singleNodeTree.root != null &&
                Integer.valueOf(10).equals(singleNodeTree.root.data);
        runTest("Constructor with data", true, singleNodeTest);

        // Create a tree for multiple tests
        BinaryTree tree = new BinaryTree();

        // Test 3: Insert into empty tree
        tree.insert(50);
        boolean insertTest1 = tree.root != null && tree.root.data == 50;
        runTest("Insert into empty tree", true, insertTest1);

        // Test 4: Insert smaller value (left child)
        tree.insert(30);
        boolean insertTest2 = tree.root.left != null && tree.root.left.data == 30;
        runTest("Insert smaller value (left child)", true, insertTest2);

        // Test 5: Insert larger value (right child)
        tree.insert(70);
        boolean insertTest3 = tree.root.right != null && tree.root.right.data == 70;
        runTest("Insert larger value (right child)", true, insertTest3);

        // Test 6: Insert more values to create a balanced tree
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        boolean insertTest4 = tree.root.left.left != null && tree.root.left.left.data == 20 &&
                tree.root.left.right != null && tree.root.left.right.data == 40 &&
                tree.root.right.left != null && tree.root.right.left.data == 60 &&
                tree.root.right.right != null && tree.root.right.right.data == 80;
        runTest("Insert multiple values", true, insertTest4);

        // Test 7: Count nodes on empty tree
        BinaryTree emptyTreeForCount = new BinaryTree();
        runTest("Count nodes on empty tree", 0, emptyTreeForCount.count_nodes());

        // Test 8: Count nodes on tree with just root
        BinaryTree singleNodeTreeForCount = new BinaryTree(5);
        runTest("Count nodes on tree with just root", 1, singleNodeTreeForCount.count_nodes());

        // Test 9: Count nodes on larger tree
        runTest("Count nodes on larger tree", 7, tree.count_nodes());

        // Test 10: Depth of empty tree
        BinaryTree emptyTreeForDepth = new BinaryTree();
        runTest("Depth of empty tree", 0, emptyTreeForDepth.depth());

        // Test 11: Depth of tree with just root
        BinaryTree singleNodeTreeForDepth = new BinaryTree(5);
        runTest("Depth of tree with just root", 1, singleNodeTreeForDepth.depth());

        // Test 12: Depth of larger tree
        runTest("Depth of larger tree", 3, tree.depth());

        String traversalResult = tree.in_order_traversal().trim();
        runTest("In order traversal", "20 30 40 50 60 70 80", traversalResult);

        // Test 13: Create a special case tree for a different traversal
        BinaryTree specialTree = new BinaryTree();
        specialTree.insert(25);
        specialTree.insert(15);
        specialTree.insert(35);
        specialTree.insert(10);
        specialTree.insert(20);

        String specialTraversalResult = specialTree.in_order_traversal().trim();
        runTest("In order traversal", "10 15 20 25 35", specialTraversalResult);

        double startTime = System.nanoTime();
        BinaryTree speedTree = new BinaryTree();
        int limit = 1_000_000;
        for (int i = 0; i < limit; i++) {
            int data = (2 * i * i + 1) % limit;
            speedTree.insert(data);
            // System.out.println(speedTree.pre_order_traversal());
        }
        double endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1000000;
        System.out.println("\nTime to insert " + limit + " entries: " + duration + "  ms");
        System.out.println("\nDepth of " + limit + " entries: " + speedTree.depth());

        runTest("Insert " + limit, true, duration < 10000);
        System.out.println();

        // Print test summary
        System.out.println("\n\nTest Summary:");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Tests passed: " + passCount);

        if (passCount == totalTests) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed. Please check the results above.");
        }
    }
}