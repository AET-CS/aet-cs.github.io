/**
 * Enhanced Driver class to test the Stack implementation.
 * This class tests each method of the Stack class extensively
 * with PASS/FAIL validation and counts total results.
 */
public class StackDriver {

    private static int passCount = 0;
    private static int failCount = 0;

    public static void main(String[] args) {
        System.out.println("STACK IMPLEMENTATION TEST");
        System.out.println("========================\n");

        // Create a new Stack of Integers
        Stack<Integer> stack = new Stack<>();

        // Test 1: Test isEmpty() on a new stack
        System.out.println("Test 1: isEmpty() on a new stack");
        boolean isEmpty = stack.isEmpty();
        System.out.println("Is stack empty? " + isEmpty);
        if (isEmpty) {
            System.out.println("PASS: New stack is empty");
            passCount++;
        } else {
            System.out.println("FAIL: New stack should be empty");
            failCount++;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 2: Test size() on a new stack
        System.out.println("Test 2: size() on a new stack");
        int size = stack.size();
        System.out.println("Stack size: " + size);
        if (size == 0) {
            System.out.println("PASS: New stack has size 0");
            passCount++;
        } else {
            System.out.println("FAIL: New stack should have size 0, got " + size);
            failCount++;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 3: Push elements onto the stack
        System.out.println("Test 3: Push elements onto the stack");

        System.out.println("Pushing 10: ");
        stack.push(10);
        System.out.println("Current stack: " + stack.toString());
        if (stack.size() == 1) {
            System.out.println("PASS: Stack size is 1 after first push");
            passCount++;
        } else {
            System.out.println("FAIL: Stack size should be 1, got " + stack.size());
            failCount++;
        }

        System.out.println("Pushing 20: ");
        stack.push(20);
        System.out.println("Current stack: " + stack.toString());
				if (stack.peek() == 20) {
					System.out.println("PASS: Top element is correct");
					passCount++;
				} else {
					System.out.println("FAIL: Top element is incorrect");
          failCount++;
				}

        System.out.println("Pushing 30: ");
        stack.push(30);
        System.out.println("Current stack: " + stack.toString());
				if (stack.peek() == 30) {
					System.out.println("PASS: Top element is correct");
					passCount++;
				} else {
					System.out.println("FAIL: Top element is incorrect");
          failCount++;
				}

        System.out.println("Pushing 40: ");
        stack.push(40);
        System.out.println("Current stack: " + stack.toString());
				if (stack.peek() == 40) {
					System.out.println("PASS: Top element is correct");
					passCount++;
				} else {
					System.out.println("FAIL: Top element is incorrect");
          failCount++;
				}

        System.out.println("Pushing 50: ");
        stack.push(50);
        System.out.println("Current stack: " + stack.toString() + "\n");
				if (stack.peek() == 50) {
					System.out.println("PASS: Top element is correct");
					passCount++;
				} else {
					System.out.println("FAIL: Top element is incorrect");
          failCount++;
				}

        if (stack.size() == 5) {
            System.out.println("PASS: Stack size is 5 after pushing 5 elements");
            passCount++;
        } else {
            System.out.println("FAIL: Stack size should be 5, got " + stack.size());
            failCount++;
        }

        // Test 4: Test size() after pushing elements
        System.out.println("Test 4: size() after pushing elements");
        size = stack.size();
        System.out.println("Stack size: " + size);
        if (size == 5) {
            System.out.println("PASS: Stack size is 5");
            passCount++;
        } else {
            System.out.println("FAIL: Stack size should be 5, got " + size);
            failCount++;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 5: Test isEmpty() after pushing elements
        System.out.println("Test 5: isEmpty() after pushing elements");
        isEmpty = stack.isEmpty();
        System.out.println("Is stack empty? " + isEmpty);
        if (!isEmpty) {
            System.out.println("PASS: Stack is not empty after pushing elements");
            passCount++;
        } else {
            System.out.println("FAIL: Stack should not be empty after pushing elements");
            failCount++;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 6: Test peek()
        System.out.println("Test 6: Test peek()");
        Integer peekedElement = stack.peek();
        System.out.println("Peeked element: " + peekedElement);
        if (peekedElement != null && peekedElement == 50) {
            System.out.println("PASS: Peek returned the correct top element");
            passCount++;
        } else {
            System.out.println("FAIL: Peek should return 50, got " + peekedElement);
            failCount++;
        }
        if (stack.size() == 5) {
            System.out.println("PASS: Stack size unchanged after peek");
            passCount++;
        } else {
            System.out.println("FAIL: Stack size should be unchanged after peek, got " + stack.size());
            failCount++;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 7: Test pop()
        System.out.println("Test 7: Test pop()");
        Integer poppedElement = stack.pop();
        System.out.println("Popped element: " + poppedElement);
        if (poppedElement != null && poppedElement == 50) {
            System.out.println("PASS: Pop returned the correct top element");
            passCount++;
        } else {
            System.out.println("FAIL: Pop should return 50, got " + poppedElement);
            failCount++;
        }
        if (stack.size() == 4) {
            System.out.println("PASS: Stack size reduced after pop");
            passCount++;
        } else {
            System.out.println("FAIL: Stack size should be 4 after pop, got " + stack.size());
            failCount++;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 8: Test multiple pops
        System.out.println("Test 8: Test multiple pops");
        poppedElement = stack.pop();
        System.out.println("Popped element: " + poppedElement);
        if (poppedElement != null && poppedElement == 40) {
            System.out.println("PASS: Pop returned the correct top element");
            passCount++;
        } else {
            System.out.println("FAIL: Pop should return 40, got " + poppedElement);
            failCount++;
        }
        System.out.println("Current stack: " + stack.toString());

        poppedElement = stack.pop();
        System.out.println("Popped element: " + poppedElement);
        if (poppedElement != null && poppedElement == 30) {
            System.out.println("PASS: Pop returned the correct top element");
            passCount++;
        } else {
            System.out.println("FAIL: Pop should return 30, got " + poppedElement);
            failCount++;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 9: Test size() after popping elements
        System.out.println("Test 9: Test size() after popping elements");
        size = stack.size();
        System.out.println("Stack size: " + size);
        if (size == 2) {
            System.out.println("PASS: Stack size is 2 after popping 3 elements");
            passCount++;
        } else {
            System.out.println("FAIL: Stack size should be 2, got " + size);
            failCount++;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 10: Test search() for existing element
        System.out.println("Test 10: Test search() for existing element");
        int position = stack.search(20);
        System.out.println("Position of 20: " + position);
        if (position > -1) {
            System.out.println("PASS: Found existing element in stack");
            passCount++;
        } else {
            System.out.println("FAIL: Could not find existing element in stack");
            failCount++;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 11: Test search() for non-existing element
        System.out.println("Test 11: Test search() for non-existing element");
        position = stack.search(100);
        System.out.println("Position of 100: " + position);
        if (position == -1) {
            System.out.println("PASS: Non-existing element correctly reported as not found");
            passCount++;
        } else {
            System.out.println("FAIL: Non-existing element should return -1, got " + position);
            failCount++;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 12: Push more elements
        System.out.println("Test 12: Push more elements");
        System.out.println("Pushing 60: ");
        stack.push(60);
        System.out.println("Current stack: " + stack.toString());

        System.out.println("Pushing 70: ");
        stack.push(70);
        System.out.println("Current stack: " + stack.toString() + "\n");

        if (stack.size() == 4) {
            System.out.println("PASS: Stack size is 4 after pushing 2 more elements");
            passCount++;
        } else {
            System.out.println("FAIL: Stack size should be 4, got " + stack.size());
            failCount++;
        }

        // Test 13: Test clear()
        System.out.println("Test 13: Test clear()");
        stack.clear();
        System.out.println("Stack cleared");
        System.out.println("Current stack: " + stack.toString() + "\n");

        if (stack.size() == 0) {
            System.out.println("PASS: Stack size is 0 after clear");
            passCount++;
        } else {
            System.out.println("FAIL: Stack size should be 0 after clear, got " + stack.size());
            failCount++;
        }

        // Test 14: Test size() and isEmpty() after clear()
        System.out.println("Test 14: Test size() and isEmpty() after clear()");
        size = stack.size();
        isEmpty = stack.isEmpty();
        System.out.println("Stack size: " + size);
        System.out.println("Is stack empty? " + isEmpty);

        boolean sizeTestPassed = (size == 0);
        boolean isEmptyTestPassed = isEmpty;

        if (sizeTestPassed) {
            System.out.println("PASS: Stack size is 0 after clear");
            passCount++;
        } else {
            System.out.println("FAIL: Stack size should be 0 after clear, got " + size);
            failCount++;
        }

        if (isEmptyTestPassed) {
            System.out.println("PASS: Stack is empty after clear");
            passCount++;
        } else {
            System.out.println("FAIL: Stack should be empty after clear");
            failCount++;
        }

        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 15: Test exception handling for pop() on empty stack
        boolean exceptionTest15 = false;
        try {
            System.out.println("Test 15: Test exception handling for pop() on empty stack");
            poppedElement = stack.pop();
            System.out.println("Popped element: " + poppedElement);
            System.out.println("FAIL: NoSuchElementException was not thrown!");
            failCount++;
        } catch (java.util.NoSuchElementException e) {
            System.out.println("PASS: NoSuchElementException was thrown as expected");
            passCount++;
            exceptionTest15 = true;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 16: Test exception handling for peek() on empty stack
        boolean exceptionTest16 = false;
        try {
            System.out.println("Test 16: Test exception handling for peek() on empty stack");
            peekedElement = stack.peek();
            System.out.println("Peeked element: " + peekedElement);
            System.out.println("FAIL: NoSuchElementException was not thrown!");
            failCount++;
        } catch (java.util.NoSuchElementException e) {
            System.out.println("PASS: NoSuchElementException was thrown as expected");
            passCount++;
            exceptionTest16 = true;
        }
        System.out.println("Current stack: " + stack.toString() + "\n");

        // Test 17: Test with different types (String)
        System.out.println("Test 17: Test with different types (String)");
        Stack<String> stringStack = new Stack<>();
        System.out.println("Pushing 'apple': ");
        stringStack.push("apple");
        System.out.println("Pushing 'banana': ");
        stringStack.push("banana");
        System.out.println("Pushing 'kiwi': ");
        stringStack.push("kiwi");
        System.out.println("Current string stack: " + stringStack.toString() + "\n");

        if (stringStack.size() == 3) {
            System.out.println("PASS: String stack size is 3 after pushing 3 elements");
            passCount++;
        } else {
            System.out.println("FAIL: String stack size should be 3, got " + stringStack.size());
            failCount++;
        }

        String poppedString = stringStack.pop();
        System.out.println("Popped element: " + poppedString);
        if (poppedString != null && poppedString.equals("kiwi")) {
            System.out.println("PASS: Pop returned the correct top element");
            passCount++;
        } else {
            System.out.println("FAIL: Pop should return 'kiwi', got " + poppedString);
            failCount++;
        }
        System.out.println("Current string stack: " + stringStack.toString() + "\n");

        int applePosition = stringStack.search("apple");
        System.out.println("Position of 'apple': " + applePosition);
        if (applePosition >= 0) {
            System.out.println("PASS: Found existing element in string stack");
            passCount++;
        } else {
            System.out.println("FAIL: Could not find existing element in string stack");
            failCount++;
        }

        int orangePosition = stringStack.search("orange");
        System.out.println("Position of 'orange': " + orangePosition);
        if (orangePosition == -1) {
            System.out.println("PASS: Non-existing element correctly reported as not found");
            passCount++;
        } else {
            System.out.println("FAIL: Non-existing element should return -1, got " + orangePosition);
            failCount++;
        }
        System.out.println("Current string stack: " + stringStack.toString() + "\n");

        System.out.println("STACK IMPLEMENTATION TEST COMPLETED");
        System.out.println("=================================");
        System.out.println("TOTAL TESTS PASSED: " + passCount);
        System.out.println("TOTAL TESTS FAILED: " + failCount);
        System.out.println("=================================\n");

        System.out.println("STACK EFFICIENCY TEST STARTING");
        System.out.println("========================");
        // Create a new Stack of Integers
        Stack<Integer> efficiencyStack = new Stack<>();
        // Add 1 million elements to the stack
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            efficiencyStack.push(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to add 1 million elements: " + (endTime - startTime) + " ms");
    }
}
