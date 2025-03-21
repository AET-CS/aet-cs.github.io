package MediaPlayer;

/**
 * LinkedList class
 * A implementation of a singly linked list data structure
 * with a head pointer.
 *
 * AP CS students: Implement all methods in this file
 */
public class LinkedList<E> {

	/**
	 * Node class for the LinkedList
	 * Contains data and a reference to the next node
	 */
	private class Node {
			private E data;         // Data stored in this node
			private Node next;      // Reference to the next node in the list

			/**
			 * Constructor for the Node class
			 * @param data The data to store in this node
			 */
			public Node(E data) {
					// Implement this constructor
			}

			/**
			 * Constructor for the Node class
			 * @param data The data to store in this node
			 * @param next The reference to the next node
			 */
			public Node(E data, Node next) {
					// Implement this constructor
			}
	}

	// Instance variables for the LinkedList
	private Node head;  // Reference to the first node in the list
	private int size;   // Number of elements in the list

	/**
	 * Default constructor for the LinkedList class
	 * Creates an empty list
	 */
	public LinkedList() {
			// Implement this constructor
	}

	/**
	 * Returns the number of elements in the list
	 * @return The number of elements in the list
	 */
	public int size() {
			// Implement this method
			return -1; // Placeholder return value
	}

	/**
	 * Checks if the list is empty
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty() {
			// Implement this method
			return false; // Placeholder return value
	}

	/**
	 * Returns the first element in the list without removing it
	 * @return The first element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	public E getFirst() {
			// Implement this method
			return null; // Placeholder return value
	}

	/**
	 * Returns the last element in the list without removing it
	 * @return The last element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	public E getLast() {
			// Implement this method
			return null; // Placeholder return value
	}

	/**
	 * Adds an element to the beginning of the list
	 * @param element The element to add
	 */
	public void addFirst(E element) {
			// Implement this method
	}

	/**
	 * Adds an element to the end of the list
	 * @param element The element to add
	 */
	public void addLast(E element) {
			// Implement this method
	}

	/**
	 * Removes and returns the first element in the list
	 * @return The first element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	public E removeFirst() {
			// Implement this method
			return null; // Placeholder return value
	}

	/**
	 * Removes and returns the last element in the list
	 * @return The last element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	public E removeLast() {
			// Implement this method
			return null; // Placeholder return value
	}

	/**
	 * Adds an element at the specified index
	 * @param index The index at which to add the element
	 * @param element The element to add
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	public void add(int index, E element) {
			// Implement this method
	}

	/**
	 * Returns the element at the specified index without removing it
	 * @param index The index of the element to return
	 * @return The element at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	public E get(int index) {
			// Implement this method
			return null; // Placeholder return value
	}

	/**
	 * Removes and returns the element at the specified index
	 * @param index The index of the element to remove
	 * @return The element at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	public E remove(int index) {
			// Implement this method
			return null; // Placeholder return value
	}

	/**
	 * Returns the index of the first occurrence of the specified element
	 * @param element The element to search for
	 * @return The index of the first occurrence of the element, or -1 if not found
	 */
	public int indexOf(E element) {
			// Implement this method
			return -1; // Placeholder return value
	}

	/**
	 * Checks if the list contains the specified element
	 * @param element The element to search for
	 * @return true if the list contains the element, false otherwise
	 */
	public boolean contains(E element) {
			// Implement this method
			return false; // Placeholder return value
	}

	/**
	 * Returns a string representation of the list
	 * @return A string representation of the list
	 */
	@Override
	public String toString() {
			// Implement this method
			return ""; // Placeholder return value
	}

	/**
	 * Clears the list, removing all elements
	 */
	public void clear() {
			// Implement this method
	}

	/**
	 * Extra challenge: Reverses the list in place
	 */
	public void reverse() {
			// Implement this method (optional challenge)
	}
}
