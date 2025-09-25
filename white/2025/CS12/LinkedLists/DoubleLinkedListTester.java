import java.util.NoSuchElementException;

/**
 * LinkedListTester
 * A driver program to test all methods of the DoubleLinkedList class
 */
public class DoubleLinkedListTester {

    private static int testsPassed = 0;
    private static int totalTests = 0;

    public static void main(String[] args) {
        System.out.println("DOUBLE LINKED LIST TESTER");
        System.out.println("==================\n");

        // Run all tests (order chosen to reward minimal head-based implementations)
        testConstructor();
        testInternalPointers();
        testSize();
        testIsEmpty();
        testAddFirst();
        testGetFirst();
        testRemoveFirst();
        testAddLast();
        testGetLast();
        testRemoveLast();
        testAddAtIndex();
        testGetAtIndex();
        testRemoveAtIndex();
        testIndexOf();
        testContains();
        testToString();
        testClear();
        testReverse();

        // Print summary
        System.out.println("\nTEST SUMMARY");
        System.out.println("============");
        System.out.println("Tests passed: " + testsPassed + "/" + totalTests +
                " (" + (int) (100.0 * testsPassed / totalTests) + "%)");
    }

    private static void testInternalPointers() {
        printTestHeader("internal pointers (debug)");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        System.out.println("Initially:");
        printHeadTail(list);

        System.out.println("addFirst \"X\"");
        list.addFirst("X");
        System.out.println("    List now: " + list.toString());
        printHeadTail(list);

        System.out.println("addLast \"Y\"");
        list.addLast("Y");
        System.out.println("    List now: " + list.toString());
        printHeadTail(list);

        // skipped internal add at index to avoid exercising add(index) here

        System.out.println("removeFirst");
        list.removeFirst();
        System.out.println("    List now: " + list.toString());
        printHeadTail(list);

        System.out.println("removeLast");
        list.removeLast();
        System.out.println("    List now: " + list.toString());
        printHeadTail(list);
    }

    // Reflection-based helper to print head/tail node data (works because Node is
    // private)
    private static <T> void printHeadTail(DoubleLinkedList<T> list) {
        try {
            java.lang.reflect.Field headField = list.getClass().getDeclaredField("head");
            java.lang.reflect.Field tailField = list.getClass().getDeclaredField("tail");
            headField.setAccessible(true);
            tailField.setAccessible(true);
            Object head = headField.get(list);
            Object tail = tailField.get(list);

            Object headData = null;
            Object tailData = null;
            if (head != null) {
                java.lang.reflect.Field dataField = head.getClass().getDeclaredField("data");
                dataField.setAccessible(true);
                headData = dataField.get(head);
            }
            if (tail != null) {
                java.lang.reflect.Field dataField = tail.getClass().getDeclaredField("data");
                dataField.setAccessible(true);
                tailData = dataField.get(tail);
            }

            System.out.println(
                    "    head.data = " + String.valueOf(headData) + ", tail.data = " + String.valueOf(tailData));
        } catch (Exception e) {
            System.out.println("    (could not read head/tail via reflection: " + e + ")");
        }
    }

    private static void testConstructor() {
        printTestHeader("Constructor");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        printTestResult("New list has size 0", 0, list.size());
        printTestResult("New list is empty", true, list.isEmpty());
    }

    private static void testAddFirst() {
        printTestHeader("addFirst");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        System.out.println("adding \"B\" to front");
        list.addFirst("B");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding \"A\" to front");
        list.addFirst("A");
        System.out.println("    List now: " + list.toString());
        printTestResult("Size after adding 2 elements", 2, list.size(), list);
        printTestResult("First element is correct", "A", list.getFirst(), list);
        printTestResult("Last element is correct", "B", list.getLast(), list);
    }

    private static void testAddLast() {
        printTestHeader("addLast");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        System.out.println("adding \"A\" to end");
        list.addLast("A");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding \"B\" to end");
        list.addLast("B");
        System.out.println("    List now: " + list.toString());
        printTestResult("Size after adding 2 elements", 2, list.size(), list);
        printTestResult("First element is correct", "A", list.getFirst(), list);
        printTestResult("Last element is correct", "B", list.getLast(), list);
    }

    private static void testSize() {
        printTestHeader("size");

        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        printTestResult("Empty list has size 0", 0, list.size());

        System.out.println("adding 1 to front");
        list.addFirst(1);
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 2 to front");
        list.addFirst(2);
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 3 to front");
        list.addFirst(3);
        System.out.println("    List now: " + list.toString());
        printTestResult("List with 3 elements has size 3", 3, list.size(), list);

        System.out.println("removing first element");
        list.removeFirst();
        System.out.println("    List now: " + list.toString());
        printTestResult("Size decreases after removal", 2, list.size(), list);
    }

    private static void testIsEmpty() {
        printTestHeader("isEmpty");

        DoubleLinkedList<Double> list = new DoubleLinkedList<>();
        printTestResult("New list is empty", true, list.isEmpty());

        System.out.println("adding 1.0 to front");
        list.addFirst(1.0);
        System.out.println("    List now: " + list.toString());
        printTestResult("List with elements is not empty", false, list.isEmpty(), list);

        System.out.println("removing first element");
        list.removeFirst();
        System.out.println("    List now: " + list.toString());
        printTestResult("List is empty after removing all elements", true, list.isEmpty(), list);
    }

    private static void testGetFirst() {
        printTestHeader("getFirst");

        DoubleLinkedList<Character> list = new DoubleLinkedList<>();

        boolean test1 = false;
        try {
            list.getFirst();
        } catch (NoSuchElementException e) {
            test1 = true;
        }
        printTestResult("getFirst throws exception on empty list", true, test1);

        System.out.println("adding 'B' to front");
        list.addFirst('B');
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'A' to front");
        list.addFirst('A');
        System.out.println("    List now: " + list.toString());
        printTestResult("getFirst returns correct element", Character.valueOf('A'), list.getFirst(), list);

        System.out.println("removing first element");
        list.removeFirst();
        System.out.println("    List now: " + list.toString());
        printTestResult("getFirst returns new first after removal", Character.valueOf('B'), list.getFirst(), list);
    }

    private static void testGetLast() {
        printTestHeader("getLast");

        DoubleLinkedList<Character> list = new DoubleLinkedList<>();

        boolean test1 = false;
        try {
            list.getLast();
        } catch (NoSuchElementException e) {
            test1 = true;
        }
        printTestResult("getLast throws exception on empty list", true, test1);

        System.out.println("adding 'A' to end");
        list.addLast('A');
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'B' to end");
        list.addLast('B');
        System.out.println("    List now: " + list.toString());
        printTestResult("getLast returns correct element", Character.valueOf('B'), list.getLast(), list);

        System.out.println("removing last element");
        list.removeLast();
        System.out.println("    List now: " + list.toString());
        printTestResult("getLast returns new last after removal", Character.valueOf('A'), list.getLast(), list);
    }

    private static void testRemoveFirst() {
        printTestHeader("removeFirst");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();

        boolean test1 = false;
        try {
            list.removeFirst();
        } catch (NoSuchElementException e) {
            test1 = true;
        }
        printTestResult("removeFirst throws exception on empty list", true, test1);

        System.out.println("adding 'A' to end");
        list.addLast("A");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'B' to end");
        list.addLast("B");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'C' to end");
        list.addLast("C");
        System.out.println("    List now: " + list.toString());

        System.out.println("removing first element");
        String removed = list.removeFirst();
        System.out.println("    Removed: " + removed);
        System.out.println("    List now: " + list.toString());
        printTestResult("removeFirst returns correct element", "A", removed, list);
        printTestResult("Size decreases after removeFirst", 2, list.size(), list);
        printTestResult("New first element is correct", "B", list.getFirst(), list);
    }

    private static void testRemoveLast() {
        printTestHeader("removeLast");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();

        boolean test1 = false;
        try {
            list.removeLast();
        } catch (NoSuchElementException e) {
            test1 = true;
        }
        printTestResult("removeLast throws exception on empty list", true, test1);

        System.out.println("adding 'A' to end");
        list.addLast("A");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'B' to end");
        list.addLast("B");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'C' to end");
        list.addLast("C");
        System.out.println("    List now: " + list.toString());

        System.out.println("removing last element");
        String removed = list.removeLast();
        System.out.println("    Removed: " + removed);
        System.out.println("    List now: " + list.toString());
        printTestResult("removeLast returns correct element", "C", removed, list);
        printTestResult("Size decreases after removeLast", 2, list.size(), list);
        printTestResult("New last element is correct", "B", list.getLast(), list);
    }

    private static void testAddAtIndex() {
        printTestHeader("add at index");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();

        // Test adding at index 0 (empty list)
        System.out.println("adding 'B' at index 0");
        list.add(0, "B");
        System.out.println("    List now: " + list.toString());
        printTestResult("Add to empty list", "B", list.getFirst(), list);

        // Test adding at beginning
        System.out.println("adding 'A' at index 0");
        list.add(0, "A");
        System.out.println("    List now: " + list.toString());
        printTestResult("Add at beginning", "A", list.getFirst(), list);

        // Test adding at end
        System.out.println("adding 'D' at index 2 (end)");
        list.add(2, "D");
        System.out.println("    List now: " + list.toString());
        printTestResult("Add at end", "D", list.getLast(), list);

        // Test adding in middle
        System.out.println("adding 'C' at index 2 (middle)");
        list.add(2, "C");
        System.out.println("    List now: " + list.toString());
        printTestResult("Add in middle", "C", list.get(2), list);

        // Test invalid indices
        boolean threw = false;
        try {
            list.add(-1, "X");
        } catch (IndexOutOfBoundsException e) {
            threw = true;
        }
        printTestResult("Exception for negative index", true, threw, list);

        threw = false;
        try {
            list.add(5, "X");
        } catch (IndexOutOfBoundsException e) {
            threw = true;
        }
        printTestResult("Exception for index > size", true, threw, list);
    }

    private static void testGetAtIndex() {
        printTestHeader("get at index");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        System.out.println("adding 'A' to end");
        list.addLast("A");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'B' to end");
        list.addLast("B");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'C' to end");
        list.addLast("C");
        System.out.println("    List now: " + list.toString());
        printTestResult("Get first element", "A", list.get(0), list);
        printTestResult("Get middle element", "B", list.get(1), list);
        printTestResult("Get last element", "C", list.get(2), list);

        // Test invalid indices
        boolean threw = false;
        try {
            list.get(-1);
        } catch (IndexOutOfBoundsException e) {
            threw = true;
        }
        printTestResult("Exception for negative index", true, threw, list);

        threw = false;
        try {
            list.get(3);
        } catch (IndexOutOfBoundsException e) {
            threw = true;
        }
        printTestResult("Exception for index >= size", true, threw, list);
    }

    private static void testRemoveAtIndex() {
        printTestHeader("remove at index");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        System.out.println("adding 'A' to end");
        list.addLast("A");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'B' to end");
        list.addLast("B");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'C' to end");
        list.addLast("C");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'D' to end");
        list.addLast("D");
        System.out.println("    List now: " + list.toString());
        // Test removing first element
        System.out.println("removing element at index 0");
        String removed = list.remove(0);
        System.out.println("    Removed: " + removed);
        System.out.println("    List now: " + list.toString());
        printTestResult("Remove first element returns correct value", "A", removed, list);
        printTestResult("List state correct after removing first", 3, list.size(), list);

        // Test removing middle element
        System.out.println("removing element at index 1 (middle)");
        removed = list.remove(1);
        System.out.println("    Removed: " + removed);
        System.out.println("    List now: " + list.toString());
        printTestResult("Remove middle element returns correct value", "C", removed, list);
        printTestResult("List state correct after removing middle", "D", list.get(1), list);

        // Test removing last element
        System.out.println("removing element at index 1 (last)");
        removed = list.remove(1);
        System.out.println("    Removed: " + removed);
        System.out.println("    List now: " + list.toString());
        printTestResult("Remove last element returns correct value", "D", removed, list);
        printTestResult("List state correct after removing last", 1, list.size(), list);

        // Test invalid indices
        boolean threw = false;
        try {
            list.remove(-1);
        } catch (IndexOutOfBoundsException e) {
            threw = true;
        }
        printTestResult("Exception for negative index", true, threw, list);

        threw = false;
        try {
            list.remove(1);
        } catch (IndexOutOfBoundsException e) {
            threw = true;
        }
        printTestResult("Exception for index >= size", true, threw, list);
    }

    private static void testIndexOf() {
        printTestHeader("indexOf");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();

        // Test empty list
        printTestResult("Empty list returns -1", -1, list.indexOf("A"), list);

        System.out.println("adding 'A' to end");
        list.addLast("A");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'B' to end");
        list.addLast("B");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'C' to end");
        list.addLast("C");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'B' to end");
        list.addLast("B");
        System.out.println("    List now: " + list.toString());

        // Test finding elements
        printTestResult("First element found", 0, list.indexOf("A"), list);
        printTestResult("Middle element found (first occurrence)", 1, list.indexOf("B"), list);
        printTestResult("Last element found", 2, list.indexOf("C"), list);

        // Test element not in list
        printTestResult("Non-existent element returns -1", -1, list.indexOf("X"), list);

        // Test null element
        printTestResult("Null returns -1 when not in list", -1, list.indexOf(null), list);

        System.out.println("adding null to end");
        list.addLast(null);
        System.out.println("    List now: " + list.toString());
        printTestResult("Null element found when present", 4, list.indexOf(null), list);
    }

    private static void testContains() {
        printTestHeader("contains");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();

        // Test empty list
        printTestResult("Empty list returns false", false, list.contains("A"), list);

        System.out.println("adding 'A' to end");
        list.addLast("A");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'B' to end");
        list.addLast("B");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'C' to end");
        list.addLast("C");
        System.out.println("    List now: " + list.toString());

        // Test finding elements
        printTestResult("First element found", true, list.contains("A"), list);
        printTestResult("Middle element found", true, list.contains("B"), list);
        printTestResult("Last element found", true, list.contains("C"), list);

        // Test element not in list
        printTestResult("Non-existent element returns false", false, list.contains("X"), list);

        // Test null element
        printTestResult("Null returns false when not in list", false, list.contains(null), list);

        System.out.println("adding null to end");
        list.addLast(null);
        System.out.println("    List now: " + list.toString());
        printTestResult("Null element found when present", true, list.contains(null), list);
    }

    private static void testToString() {
        printTestHeader("toString");

        DoubleLinkedList<String> list = new DoubleLinkedList<>();

        // Test empty list
        // Test empty list
        printTestResult("Empty list returns []", "[]", list.toString(), list);

        // Test single element
        System.out.println("adding 'A' to end");
        list.addLast("A");
        System.out.println("    List now: " + list.toString());
        printTestResult("Single element formatted correctly", "[A]", list.toString(), list);

        // Test multiple elements
        System.out.println("adding 'B' to end");
        list.addLast("B");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'C' to end");
        list.addLast("C");
        System.out.println("    List now: " + list.toString());
        printTestResult("Multiple elements formatted correctly", "[A, B, C]", list.toString(), list);
    }

    private static void testClear() {
        printTestHeader("clear");

        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        System.out.println("adding 1 to end");
        list.addLast(1);
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 2 to end");
        list.addLast(2);
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 3 to end");
        list.addLast(3);
        System.out.println("    List now: " + list.toString());

        System.out.println("clearing list");
        list.clear();
        System.out.println("    List now: " + list.toString());
        printTestResult("List is empty after clear", true, list.isEmpty(), list);
        printTestResult("Size is 0 after clear", 0, list.size(), list);

        boolean threw = false;
        try {
            list.getFirst();
        } catch (NoSuchElementException e) {
            threw = true;
        }
        printTestResult("Cannot access elements after clear", true, threw, list);
    }

    private static void testReverse() {
        printTestHeader("reverse (challenge)");

        // Test empty list
        DoubleLinkedList<String> emptyList = new DoubleLinkedList<>();
        System.out.println("reversing empty list");
        emptyList.reverse();
        System.out.println("    List now: " + emptyList.toString());
        printTestResult("Empty list remains empty after reverse", true, emptyList.isEmpty(), emptyList);

        // Test single element
        DoubleLinkedList<String> singleList = new DoubleLinkedList<>();
        System.out.println("adding 'A' to end");
        singleList.addLast("A");
        System.out.println("    List now: " + singleList.toString());
        System.out.println("reversing single-element list");
        singleList.reverse();
        System.out.println("    List now: " + singleList.toString());
        printTestResult("Single element list unchanged after reverse", true,
                singleList.getFirst().equals("A") && singleList.size() == 1, singleList);

        // Test multiple elements
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        System.out.println("adding 'A' to end");
        list.addLast("A");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'B' to end");
        list.addLast("B");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'C' to end");
        list.addLast("C");
        System.out.println("    List now: " + list.toString());

        System.out.println("adding 'D' to end");
        list.addLast("D");
        System.out.println("    List now: " + list.toString());

        System.out.println("reversing list");
        list.reverse();
        System.out.println("    List now: " + list.toString());
        printTestResult("Size unchanged after reverse", 4, list.size(), list);
        printTestResult("Elements reversed correctly", true,
                list.get(0).equals("D") &&
                        list.get(1).equals("C") &&
                        list.get(2).equals("B") &&
                        list.get(3).equals("A"),
                list);
    }

    private static void printTestHeader(String methodName) {
        System.out.println("\nTesting " + methodName + ":");
        System.out.println("-".repeat(20));
    }

    private static void printTestResult(String testDescription, boolean passed) {
        totalTests++;
        if (passed) {
            testsPassed++;
            System.out.println("✓ PASS: " + testDescription);
        } else {
            System.out.println("✗ FAIL: " + testDescription);
        }
    }

    // Enhanced helpers that print expected vs received on failure
    private static void printTestResult(String testDescription, Object expected, Object received) {
        boolean passed = (expected == null) ? received == null : expected.equals(received);
        totalTests++;
        if (passed) {
            testsPassed++;
            System.out.println("✓ PASS: " + testDescription);
        } else {
            System.out.println("✗ FAIL: " + testDescription);
            System.out.println("    Expected: " + String.valueOf(expected));
            System.out.println("    Received: " + String.valueOf(received));
        }
    }

    // Variant that also prints list contents when provided (before/after state can
    // be included in the message)
    private static <T> void printTestResult(String testDescription, Object expected, Object received,
            DoubleLinkedList<T> list) {
        boolean passed = (expected == null) ? received == null : expected.equals(received);
        totalTests++;
        if (passed) {
            testsPassed++;
            System.out.println("✓ PASS: " + testDescription);
        } else {
            System.out.println("✗ FAIL: " + testDescription);
            System.out.println("    Expected: " + String.valueOf(expected));
            System.out.println("    Received: " + String.valueOf(received));
            try {
                System.out.println("    List contents: " + list.toString());
            } catch (Exception e) {
                System.out.println("    List contents: <unavailable> (toString threw)");
            }
        }
    }
}
