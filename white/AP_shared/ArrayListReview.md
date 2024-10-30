# Overview of ArrayList

1. **Declare an ArrayList**
   Declare an `ArrayList` with a specified type.
   ```java
   ArrayList<String> list; // Declaration only
   ```

2. **Initialize an ArrayList**
   Use `new ArrayList<String>()` to create an instance.
   ```java
   list = new ArrayList<String>(); // Now list is an empty ArrayList
   ```

3. **Add Elements to an ArrayList**
   Use `add()` to insert elements.
   ```java
   list.add("A"); // Adds "A" to the list
   ```

4. **Remove Elements from an ArrayList**
   Use `remove()` by index or by value.
   ```java
   list.remove(0); // Removes element at index 0
   list.remove("A"); // Removes the first occurrence of "A"
   ```

5. **Clear All Elements from an ArrayList**
   Use `clear()` to remove all elements.
   ```java
   list.clear(); // Empties the list
   ```

6. **Copy an ArrayList**
   Use `List.copyOf()` to make an immutable copy.
   ```java
   List<String> immutableList = List.copyOf(list);
   ```

7. **Get the Size of an ArrayList**
   Use `size()` to get the number of elements.
   ```java
   int size = list.size(); // Returns the size of the list
   ```

8. **Check for Element Containment**
   Use `contains()` to check if an element is in the list.
   ```java
   boolean hasA = list.contains("A"); // Returns true if "A" is in the list
   ```

9. **Access an Element by Index**
   Use `get()` to access elements.
   ```java
   String first = list.get(0); // Accesses the first element
   ```

10. **Convert an ArrayList to an Array**
    Use `toArray()` to convert the list to an array.
    ```java
    String[] array = list.toArray(new String[0]); // Converts list to array
    ```

11. **Sort an ArrayList**
    Use `Collections.sort()` to sort elements in ascending order.
    ```java
    Collections.sort(list); // Sorts list in ascending order
    ```

12. **Iterate Over an ArrayList**
    Use a for-each loop to iterate over elements.
    ```java
    for (String item : list) {
        System.out.println(item); // Prints each item in the list
    }
    ```
