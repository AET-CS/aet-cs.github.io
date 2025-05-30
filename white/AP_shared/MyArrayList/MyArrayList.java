public class MyArrayList<E> {
    // Default capacity for internal array
    private static final int DEFAULT_CAPACITY = 2;

    // Internal array to store elements
    private Object[] elements;

    // Number of elements in the ArrayList
    private int size;

    // Default constructor
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // Constructor with initial capacity
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        elements = new Object[initialCapacity];
        size = 0;
    }

    // Add an element to the end of the list
    public boolean append(E element) {
        // TODO: Ensure capacity, add element, increment size
        return true;
    }

    // Insert element at specific index
    public void add(int index, E element) {
        // TODO: Check index bounds, ensure capacity, shift elements, insert new element
    }

    // Get element at index
    @SuppressWarnings("unchecked")
    public E get(int index) {
        // TODO: Check index bounds, return element
        return null;
    }

    // Replace element at index
    public E set(int index, E element) {
        // TODO: Check index bounds, replace element, return old value
        return null;
    }

    // Remove element at index
    public E remove(int index) {
        // TODO: Check index bounds, remove element, shift elements, return removed element
        return null;
    }

    // Remove first occurrence of element
    public boolean remove(Object o) {
        // TODO: Find element, remove it if found
        return false;
    }

    // Get number of elements
    public int size() {
        return size;
    }

    // Check if list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Clear the list
    public void clear() {
        // TODO: Clear array, reset size
    }

    // Check if list contains element
    public boolean contains(Object o) {
        // TODO: Search for element
        return false;
    }

    // Find index of first occurrence of element
    public int indexOf(Object o) {
        // TODO: Find index of element
        return -1;
    }

    // Ensure internal array has enough capacity
    private void ensureCapacity(int minCapacity) {
        // TODO: Resize array if current size is less than minCapacity
        // The new size will be double the old size
        // Array elements are copied to the new array
    }
}