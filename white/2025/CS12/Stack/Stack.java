import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A generic Stack implementation that extends ArrayList. Follows
 * Last-In-First-Out (LIFO) principle. The "top" of the stack is at the end of
 * the ArrayList.
 *
 * @param <E> the type of elements stored in this stack
 */
public class Stack<E> extends ArrayList<E> {

  /**
   * Constructs an empty stack. Should call the parent ArrayList constructor.
   */
  public Stack() {
    // ADD CODE HERE
  }

  /**
   * Pushes an item onto the top of the stack. This should add the item to the end
   * of the ArrayList.
   *
   * @param item the item to be pushed onto the stack
   */
  public void push(E item) {
    // ADD CODE HERE
  }

  /**
   * Removes and returns the item at the top of the stack. This should remove and
   * return the last item in the ArrayList.
   *
   * @return the item at the top of the stack
   * @throws NoSuchElementException if the stack is empty
   */
  public E pop() {
    // Check if stack is empty first
    // If empty, throw NoSuchElementException
    // Otherwise, get the top item, remove it, and return it
    // HINT: Use peek() to get the item, then remove by index
    // ADD CODE HERE
  }

  /**
   * Returns the item at the top of the stack without removing it. This should
   * return the last item in the ArrayList without modifying the stack.
   *
   * @return the item at the top of the stack
   * @throws NoSuchElementException if the stack is empty
   */
  public E peek() {
    // Check if stack is empty first
    // If empty, throw NoSuchElementException
    // Otherwise, return the last item in the ArrayList
    // HINT: Use get() with the appropriate index
    // ADD CODE HERE
  }

  /**
   * Tests if the stack is empty.
   *
   * @return true if the stack contains no elements, false otherwise
   */
  public boolean isEmpty() {
    // Return true if size is 0, false otherwise
    // ADD CODE HERE
  }

  /**
   * Removes all elements from the stack. Should call the parent ArrayList's clear
   * method.
   */
  public void clear() {
    // Call the superclass clear method
    // ADD CODE HERE
  }

  /**
   * Searches for an item in the stack and returns its position. Position 0 is the
   * bottom of the stack, highest index is the top.
   *
   * @param item the item to search for
   * @return the index of the item if found, -1 if not found
   * @throws NoSuchElementException if the stack is empty
   */
  public int search(E item) {
    // Check if stack is empty first
    // If empty, throw NoSuchElementException
    // Loop through the ArrayList to find the item
    // Use .equals() method to compare items
    // Return the index if found, -1 if not found
    // ADD CODE HERE
  }

  /**
   * Returns a string representation of the stack. Should use the parent
   * ArrayList's toString method.
   *
   * @return a string representation of the stack
   */
  public String toString() {
    // Call and return the superclass toString method
    // ADD CODE HERE
  }
}