---
title: "ArrayList Operations Lab"
---


In this lab, you'll explore the fundamental operations of ArrayLists through a series of coding exercises. Complete each task and observe how ArrayLists behave differently from regular arrays.

## Setup
Create a new class called `ArrayListLab` with a main method. Use this structure for your code:

```java
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListLab {
    public static void main(String[] args) {
        // Your code will go here

        // Print results after each task
        System.out.println("Task 1 results:");
        // etc...
    }
}
```

## Tasks

### Task 1: Creating and Adding Elements
Create an ArrayList of Strings called `fruits`. Add these fruits in order: "apple", "banana", "orange".
Then insert "mango" at index 1.

Expected output:
```
[apple, mango, banana, orange]
```

### Task 2: Exploring ArrayList Size
1. Create an ArrayList of Integers
2. Add the numbers 1 through 5
3. Print the size
4. Remove two elements
5. Print the size again
6. Add three more elements
7. Print final size

This task demonstrates how ArrayLists automatically resize.

### Task 3: Finding and Replacing
Create an ArrayList with the following names: ["Alice", "Bob", "Charlie", "Bob", "David"]
1. Find the first index of "Bob"
2. Find the last index of "Bob"
3. Replace the first "Bob" with "Robert"
4. Print the modified list

### Task 4: Removing Elements
Create an ArrayList with numbers 1-5. Write code to:
1. Remove the element at index 2
2. Remove the first occurrence of the number 4
3. Print the list after each removal

### Task 5: Working with Subsets
Create an ArrayList with the numbers 1 through 10. Then:
1. Get a subList from index 3 to 7
2. Print both the original list and the subList
3. Modify an element in the subList
4. Print both lists again to see how they're connected

### Challenge Task: Filtering
Create an ArrayList of integers with values [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Write code to:
1. Remove all odd numbers
2. Print the resulting list
3. Try to do this using a loop (Hint: be careful with indexes when removing elements!)

## Example Solution for Task 1
```java
ArrayList<String> fruits = new ArrayList<>();
fruits.add("apple");
fruits.add("banana");
fruits.add("orange");
fruits.add(1, "mango");
System.out.println(fruits);
```
