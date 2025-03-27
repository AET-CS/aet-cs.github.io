/**
 * A Stack implementation that extends LinkedList.
 * This class follows the Last-In-First-Out (LIFO) principle.
 *
 * @param <E> the type of elements held in this stack
 */
public class Stack<E> extends LinkedList<E> {

    /**
     * Constructs an empty stack.
     */
    public Stack() {
        super();
    }

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param item the item to be pushed onto this stack
     * @return void
     */
    public void push(E item) {
			addFirst(item);
    }

    /**
     * Removes the object at the top of this stack and returns it.
     *
     * @return the object at the top of this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public E pop() {
			E item = removeFirst();
			return item;
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     *
     * @return the object at the top of this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public E peek() {
        return getFirst();
		}

    /**
     * Returns true if this stack contains no elements.
     *
     * @return true if this stack contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack
     */
    public int size() {
        return super.size();
    }

    /**
     * Searches for an element in this stack.
     *
     * @param o the element to search for
     * @return the 1-based position of the element from the top of the stack,
     *         or -1 if the element is not found
     */
    public int search(E e) {
			return indexOf(e);
    }

    /**
     * Removes all elements from this stack.
     */
    public void clear() {
			super.clear();
		}

    /**
     * Returns a string representation of this stack.
     *
     * @return a string representation of this stack
     */
    @Override
    public String toString() {
			return super.toString();
		}
}
