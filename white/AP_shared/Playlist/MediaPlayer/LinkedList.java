package MediaPlayer;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList class
 * A implementation of a singly linked list data structure
 * with a head pointer.
 */
public class LinkedList<E> {

    /**
     * Node class for the LinkedList
     * Contains data and a reference to the next node
     */
    protected class Node {
        protected E data;         // Data stored in this node
        protected Node next;      // Reference to the next node in the list

        /**
         * Constructor for the Node class
         * @param data The data to store in this node
         */
        public Node(E data) {
            // Implement this constructor
            this.data = data;
            this.next = null;
        }

        /**
         * Constructor for the Node class
         * @param data The data to store in this node
         * @param next The reference to the next node
         */
        public Node(E data, Node next) {
            // Implement this constructor
            this.data = data;
            this.next = next;
        }
    }

    // Instance variables for the LinkedList
    protected Node head;  // Reference to the first node in the list
    protected int size;   // Number of elements in the list

    /**
     * Default constructor for the LinkedList class
     * Creates an empty list
     */
    public LinkedList() {
        // Implement this constructor
        head = null;
        size = 0;
    }

    /**
     * Returns the number of elements in the list
     * @return The number of elements in the list
     */
    public int size() {
        // Implement this method
        return size; // Placeholder return value
    }

    /**
     * Checks if the list is empty
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        // Implement this method
        if (head==null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the first element in the list without removing it
     * @return The first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public E getFirst() {
        // Implement this method
        if( isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return head.data;
        }
    }

    /**
     * Returns the last element in the list without removing it
     * @return The last element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public E getLast() {
        if (isEmpty()) {
           throw new NoSuchElementException();
        } else {
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            return last.data;
        }
    }

    /**
     * Adds an element to the beginning of the list
     * @param element The element to add
     */
    public void addFirst(E element) {
        Node newNode = new Node(element, head);
        head = newNode;
        size++;
    }

    /**
     * Adds an element to the end of the list
     * @param element The element to add
     */
    public void addLast(E element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
        } else {
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the first element in the list
     * @return The first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public E removeFirst() {
       if (isEmpty()) {
           throw new NoSuchElementException();
       } else {
           E data = head.data;
           head = head.next;
           size--;
           return data;
       }
    }

    /**
     * Removes and returns the last element in the list
     * @return The last element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public E removeLast() {
       if (isEmpty()) {
          throw new NoSuchElementException();
       } else {
          Node last = head;
          Node previous = null;
           while (last.next != null) {
               previous = last;
               last = last.next;
           }
           E data = last.data;
           previous.next = null;
           size--;
           return data;
       }
    }

    /**
     * Adds an element at the specified index
     * @param index The index at which to add the element
     * @param element The element to add
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(element, head);
        size++;
        if (index == 0) {
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    /**
     * Returns the element at the specified index without removing it
     * @param index The index of the element to return
     * @return The element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        return current.data;
    }

    /**
     * Removes and returns the element at the specified index
     * @param index The index of the element to remove
     * @return The element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            E data = head.data;
            head = head.next;
            size--;
            return data;
        }
        Node current = head;
        Node previous = null;
        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }
        E data = current.data;
        previous.next = current.next;
        size--;
        return data;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * @param element The element to search for
     * @return The index of the first occurrence of the element, or -1 if not found
     */
    public int indexOf(E element) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if ((element == null && current.data == null) || (element != null && element.equals(current.data))) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    /**
     * Checks if the list contains the specified element
     * @param element The element to search for
     * @return true if the list contains the element, false otherwise
     */
    public boolean contains(E element) {
        Node current = head;
        while (current != null) {
            if (( element == null && current.data == null) || (element != null && element.equals(current.data))) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns a string representation of the list
     * @return A string representation of the list
     */
    @Override
    public String toString() {
       if (isEmpty()) {
           return "[]";
       }
        Node current = head;
        String result = "[" + current.data;
        while (current.next != null) {
           current = current.next;
           result += ", " + current.data;
       }
        result += "]";
        return result; // Placeholder return value
    }

    /**
     * Clears the list, removing all elements
     */
    public void clear() {
       Node current = head;
       while (current != null) {
           Node here = current;
           current = current.next;
           here.next = null;
           size--;
       }
       head = null;
    }

    /**
     * Extra challenge: Reverses the list in place
     */
    public void reverse() {
       if (isEmpty()) {
           return;
       }
       Node current = head;
       Node previous = null;
       while (current != null) {
           Node next = current.next;
           current.next = previous;
           previous = current;
           current = next;
       }
       head = previous;
    }
}
