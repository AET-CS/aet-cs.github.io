## Repository-specific guidance for AI coding agents

This repo contains teaching materials and small Java assignments (example: `white/2025/CS12/hidden/LinkedLists`). The immediate editable units are plain Java source files and small hand-written test drivers.

Follow these concrete rules when making code changes:

- Preserve public APIs: do not change public class, method, or constructor signatures in student-facing files (e.g. `LinkedList.java`). The test harness (`LinkedListTester.java`) depends on those signatures.
- This assignment uses plain single-file compilation and a simple test driver. Compile and run examples from the folder containing the sources:

  - Example (from the `LinkedLists` folder):

    javac LinkedList.java LinkedListTester.java
    java LinkedListTester

- Exceptions and behavior expected by tests (implement exactly as tests expect):
  - getFirst/getLast/removeFirst/removeLast must throw java.util.NoSuchElementException when the list is empty.
  - add(index, element), get(index), remove(index) must throw IndexOutOfBoundsException for invalid indices (negative or >= size where appropriate).
  - indexOf must return -1 when an element is not found and must correctly handle null values (tests add null and expect a valid index).
  - toString must produce "[]" for empty lists, "[A]" for single element, and bracketed, comma+space separated values for multiple elements (e.g. "[A, B, C]").

- Implementation patterns to follow (observed in `LinkedList.java`):
  - Singly-linked list with an inner private `Node` class; `head` reference points to first node and `size` is maintained as an int.
  - `addFirst` inserts at head; `addLast` traverses to append (or maintain tail if you choose, but don't change public behavior).
  - `removeLast` must handle the single-element list and empty-list exception correctly.
  - `reverse` (challenge) should reverse the list in-place and preserve `size`.

- Tests are authoritative: do not modify `LinkedListTester.java` unless you are intentionally updating the test expectations. Use it to validate behavior locally before committing.

Small contract for edits to `LinkedList.java`:
- Inputs: calls coming from `LinkedListTester` and potential external callers; generics `E` must be preserved.
- Outputs: methods must return types and throw exceptions exactly as the tester expects.
- Error modes: throw the specified exceptions (NoSuchElementException / IndexOutOfBoundsException).

Edge cases to check locally before committing:
- Operations on empty lists (all remove/get methods)
- Single-element lists for removeLast/getLast
- indexOf/contains with null values
- add at index 0 and at index == size (append)

When adding or fixing code, keep changes minimal and focused. Prefer targeted small commits with descriptive messages such as "Implement addFirst/addLast and tests pass".

If you need broader repository context (multiple assignments or different languages), search for similar folders under `white/` to find other student assignments and test drivers; they follow the same single-file, driver-based pattern.

If any part of the tests seems inconsistent with your change, open a PR and include the failing test output and a short note explaining the intended behavior.

Questions or missing details? Ask a human maintainer and include the failing test output and the file you edited.
