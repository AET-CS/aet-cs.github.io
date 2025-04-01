/**
 * Enhanced Driver class to test the Queue implementation.
 * This class tests each method of the Queue class extensively
 * with PASS/FAIL validation and counts total results.
 */
public class QueueDriver {

    private static int passCount = 0;
    private static int failCount = 0;

    public static void main(String[] args) {
        System.out.println("QUEUE IMPLEMENTATION TEST");
        System.out.println("========================\n");

        // Create a new Queue of Integers
        Queue<Integer> queue = new Queue<>();

        // Test 1: Test isEmpty() on a new queue
        System.out.println("Test 1: isEmpty() on a new queue");
        boolean isEmpty = queue.isEmpty();
        System.out.println("Is queue empty? " + isEmpty);
        if (isEmpty) {
            System.out.println("PASS: New queue is empty");
            passCount++;
        } else {
            System.out.println("FAIL: New queue should be empty");
            failCount++;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 2: Test size() on a new queue
        System.out.println("Test 2: size() on a new queue");
        int size = queue.size();
        System.out.println("Queue size: " + size);
        if (size == 0) {
            System.out.println("PASS: New queue has size 0");
            passCount++;
        } else {
            System.out.println("FAIL: New queue should have size 0, got " + size);
            failCount++;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 3: Push elements onto the queue
        System.out.println("Test 3: Push elements onto the queue");

        System.out.println("Pushing 10: ");
        queue.push(10);
        System.out.println("Current queue: " + queue.toString());
        if (queue.size() == 1) {
            System.out.println("PASS: Queue size is 1 after first push");
            passCount++;
        } else {
            System.out.println("FAIL: Queue size should be 1, got " + queue.size());
            failCount++;
        }

        System.out.println("Pushing 20: ");
        queue.push(20);
        System.out.println("Current queue: " + queue.toString());
				if (queue.peek() == 10) {
					System.out.println("PASS: Top element is correct");
					passCount++;
				} else {
					System.out.println("FAIL: Top element is incorrect");
          failCount++;
				}

        System.out.println("Pushing 30: ");
        queue.push(30);
        System.out.println("Current queue: " + queue.toString());
				if (queue.peek() == 10) {
					System.out.println("PASS: Top element is correct");
					passCount++;
				} else {
					System.out.println("FAIL: Top element is incorrect");
          failCount++;
				}

        System.out.println("Pushing 40: ");
        queue.push(40);
        System.out.println("Current queue: " + queue.toString());
				if (queue.peek() == 10) {
					System.out.println("PASS: Top element is correct");
					passCount++;
				} else {
					System.out.println("FAIL: Top element is incorrect");
          failCount++;
				}

        System.out.println("Pushing 50: ");
        queue.push(50);
        System.out.println("Current queue: " + queue.toString() + "\n");
				if (queue.peek() == 10) {
					System.out.println("PASS: Top element is correct");
					passCount++;
				} else {
					System.out.println("FAIL: Top element is incorrect");
          failCount++;
				}

        if (queue.size() == 5) {
            System.out.println("PASS: Queue size is 5 after pushing 5 elements");
            passCount++;
        } else {
            System.out.println("FAIL: Queue size should be 5, got " + queue.size());
            failCount++;
        }

        // Test 4: Test size() after pushing elements
        System.out.println("Test 4: size() after pushing elements");
        size = queue.size();
        System.out.println("Queue size: " + size);
        if (size == 5) {
            System.out.println("PASS: Queue size is 5");
            passCount++;
        } else {
            System.out.println("FAIL: Queue size should be 5, got " + size);
            failCount++;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 5: Test isEmpty() after pushing elements
        System.out.println("Test 5: isEmpty() after pushing elements");
        isEmpty = queue.isEmpty();
        System.out.println("Is queue empty? " + isEmpty);
        if (!isEmpty) {
            System.out.println("PASS: Queue is not empty after pushing elements");
            passCount++;
        } else {
            System.out.println("FAIL: Queue should not be empty after pushing elements");
            failCount++;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 6: Test peek()
        System.out.println("Test 6: Test peek()");
        Integer peekedElement = queue.peek();
        System.out.println("Peeked element: " + peekedElement);
        if (peekedElement != null && peekedElement == 10) {
            System.out.println("PASS: Peek returned the correct top element");
            passCount++;
        } else {
            System.out.println("FAIL: Peek should return 10, got " + peekedElement);
            failCount++;
        }
        if (queue.size() == 5) {
            System.out.println("PASS: Queue size unchanged after peek");
            passCount++;
        } else {
            System.out.println("FAIL: Queue size should be unchanged after peek, got " + queue.size());
            failCount++;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 7: Test pop()
        System.out.println("Test 7: Test pop()");
        Integer poppedElement = queue.pop();
        System.out.println("Popped element: " + poppedElement);
        if (poppedElement != null && poppedElement == 10) {
            System.out.println("PASS: Pop returned the correct top element");
            passCount++;
        } else {
            System.out.println("FAIL: Pop should return 10, got " + poppedElement);
            failCount++;
        }
        if (queue.size() == 4) {
            System.out.println("PASS: Queue size reduced after pop");
            passCount++;
        } else {
            System.out.println("FAIL: Queue size should be 4 after pop, got " + queue.size());
            failCount++;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 8: Test multiple pops
        System.out.println("Test 8: Test multiple pops");
        poppedElement = queue.pop();
        System.out.println("Popped element: " + poppedElement);
        if (poppedElement != null && poppedElement == 20) {
            System.out.println("PASS: Pop returned the correct top element");
            passCount++;
        } else {
            System.out.println("FAIL: Pop should return 20, got " + poppedElement);
            failCount++;
        }
        System.out.println("Current queue: " + queue.toString());

        poppedElement = queue.pop();
        System.out.println("Popped element: " + poppedElement);
        if (poppedElement != null && poppedElement == 30) {
            System.out.println("PASS: Pop returned the correct top element");
            passCount++;
        } else {
            System.out.println("FAIL: Pop should return 30, got " + poppedElement);
            failCount++;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 9: Test size() after popping elements
        System.out.println("Test 9: Test size() after popping elements");
        size = queue.size();
        System.out.println("Queue size: " + size);
        if (size == 2) {
            System.out.println("PASS: Queue size is 2 after popping 3 elements");
            passCount++;
        } else {
            System.out.println("FAIL: Queue size should be 2, got " + size);
            failCount++;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 10: Test search() for existing element
        System.out.println("Test 10: Test search() for existing element");
        int position = queue.search(40);
        System.out.println("Position of 40: " + position);
        if (position > -1) {
            System.out.println("PASS: Found existing element in queue");
            passCount++;
        } else {
            System.out.println("FAIL: Could not find existing element in queue");
            failCount++;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 11: Test search() for non-existing element
        System.out.println("Test 11: Test search() for non-existing element");
        position = queue.search(100);
        System.out.println("Position of 100: " + position);
        if (position == -1) {
            System.out.println("PASS: Non-existing element correctly reported as not found");
            passCount++;
        } else {
            System.out.println("FAIL: Non-existing element should return -1, got " + position);
            failCount++;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 12: Push more elements
        System.out.println("Test 12: Push more elements");
        System.out.println("Pushing 60: ");
        queue.push(60);
        System.out.println("Current queue: " + queue.toString());

        System.out.println("Pushing 70: ");
        queue.push(70);
        System.out.println("Current queue: " + queue.toString() + "\n");

        if (queue.size() == 4) {
            System.out.println("PASS: Queue size is 4 after pushing 2 more elements");
            passCount++;
        } else {
            System.out.println("FAIL: Queue size should be 4, got " + queue.size());
            failCount++;
        }

        // Test 13: Test clear()
        System.out.println("Test 13: Test clear()");
        queue.clear();
        System.out.println("Queue cleared");
        System.out.println("Current queue: " + queue.toString() + "\n");

        if (queue.size() == 0) {
            System.out.println("PASS: Queue size is 0 after clear");
            passCount++;
        } else {
            System.out.println("FAIL: Queue size should be 0 after clear, got " + queue.size());
            failCount++;
        }

        // Test 14: Test size() and isEmpty() after clear()
        System.out.println("Test 14: Test size() and isEmpty() after clear()");
        size = queue.size();
        isEmpty = queue.isEmpty();
        System.out.println("Queue size: " + size);
        System.out.println("Is queue empty? " + isEmpty);

        boolean sizeTestPassed = (size == 0);
        boolean isEmptyTestPassed = isEmpty;

        if (sizeTestPassed) {
            System.out.println("PASS: Queue size is 0 after clear");
            passCount++;
        } else {
            System.out.println("FAIL: Queue size should be 0 after clear, got " + size);
            failCount++;
        }

        if (isEmptyTestPassed) {
            System.out.println("PASS: Queue is empty after clear");
            passCount++;
        } else {
            System.out.println("FAIL: Queue should be empty after clear");
            failCount++;
        }

        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 15: Test exception handling for pop() on empty queue
        boolean exceptionTest15 = false;
        try {
            System.out.println("Test 15: Test exception handling for pop() on empty queue");
            poppedElement = queue.pop();
            System.out.println("Popped element: " + poppedElement);
            System.out.println("FAIL: NoSuchElementException was not thrown!");
            failCount++;
        } catch (java.util.NoSuchElementException e) {
            System.out.println("PASS: NoSuchElementException was thrown as expected");
            passCount++;
            exceptionTest15 = true;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 16: Test exception handling for peek() on empty queue
        boolean exceptionTest16 = false;
        try {
            System.out.println("Test 16: Test exception handling for peek() on empty queue");
            peekedElement = queue.peek();
            System.out.println("Peeked element: " + peekedElement);
            System.out.println("FAIL: NoSuchElementException was not thrown!");
            failCount++;
        } catch (java.util.NoSuchElementException e) {
            System.out.println("PASS: NoSuchElementException was thrown as expected");
            passCount++;
            exceptionTest16 = true;
        }
        System.out.println("Current queue: " + queue.toString() + "\n");

        // Test 17: Test with different types (String)
        System.out.println("Test 17: Test with different types (String)");
        Queue<String> stringQueue = new Queue<>();
        System.out.println("Pushing 'apple': ");
        stringQueue.push("apple");
        System.out.println("Pushing 'banana': ");
        stringQueue.push("banana");
        System.out.println("Pushing 'kiwi': ");
        stringQueue.push("kiwi");
        System.out.println("Current string queue: " + stringQueue.toString() + "\n");

        if (stringQueue.size() == 3) {
            System.out.println("PASS: String queue size is 3 after pushing 3 elements");
            passCount++;
        } else {
            System.out.println("FAIL: String queue size should be 3, got " + stringQueue.size());
            failCount++;
        }

        String poppedString = stringQueue.pop();
        System.out.println("Popped element: " + poppedString);
        if (poppedString != null && poppedString.equals("apple")) {
            System.out.println("PASS: Pop returned the correct top element");
            passCount++;
        } else {
            System.out.println("FAIL: Pop should return 'apple', got " + poppedString);
            failCount++;
        }
        System.out.println("Current string queue: " + stringQueue.toString() + "\n");

        int kiwiPosition = stringQueue.search("kiwi");
        System.out.println("Position of 'kiwi': " + kiwiPosition);
        if (kiwiPosition >= 0) {
            System.out.println("PASS: Found existing element in string queue");
            passCount++;
        } else {
            System.out.println("FAIL: Could not find existing element in string queue");
            failCount++;
        }

        int orangePosition = stringQueue.search("orange");
        System.out.println("Position of 'orange': " + orangePosition);
        if (orangePosition == -1) {
            System.out.println("PASS: Non-existing element correctly reported as not found");
            passCount++;
        } else {
            System.out.println("FAIL: Non-existing element should return -1, got " + orangePosition);
            failCount++;
        }
        System.out.println("Current string queue: " + stringQueue.toString() + "\n");

        System.out.println("QUEUE IMPLEMENTATION TEST COMPLETED");
        System.out.println("=================================");
        System.out.println("TOTAL TESTS PASSED: " + passCount);
        System.out.println("TOTAL TESTS FAILED: " + failCount);
        System.out.println("=================================\n");

        System.out.println("QUEUE PUSH EFFICIENCY TEST STARTING");
        System.out.println("========================");
        // Create a new Queue of Integers
        Queue<Integer> efficiencyQueue = new Queue<>();
        // Add 1 million elements to the queue
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            efficiencyQueue.push(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to add 1 million elements: " + (endTime - startTime) + " ms");

        System.out.println("QUEUE PUSH EFFICIENCY TEST FINISHED");

        System.out.println("QUEUE POP EFFICIENCY TEST STARTING");
        System.out.println("========================");
				// POP 1 million elements to the queue
				startTime = System.currentTimeMillis();
				for (int i = 0; i < 1_000_000; i++) {
						efficiencyQueue.pop();
				}
				endTime = System.currentTimeMillis();
				System.out.println("Time taken to pop 1 million elements: " + (endTime - startTime) + " ms");
				System.out.println("QUEUE POP EFFICIENCY TEST FINISHED");

    }
}
