# Initializing and Comparing arrays Notes

In Java, declaring a 2D array and initializing it are distinct operations. Declaring an array simply creates a reference to an array, but it doesn't allocate memory or assign default values until the array is initialized.

### Key Concepts:

1. **Declaring a 2D Array**:
   ```java
   int[][] array;  // Declaration: no memory is allocated yet
   ```

   At this point, `array` is declared but uninitialized. Trying to access `array` (e.g., `array[0]`) before initializing it will result in a **NullPointerException**.

2. **Initializing a 2D Array**:
   ```java
   array = new int[3][4];  // Memory allocated, with default values
   ```

   When you initialize a 2D array, Java automatically fills it with default values. For example, an `int[][]` array is filled with `0`s, and an array of objects (like `String[][]`) is filled with `null`.

3. **Declaring and Initializing with Values**:
   ```java
   int[][] array = {
       {1, 2, 3},
       {4, 5, 6},
       {7, 8, 9}
   };
   ```

   Here, the array is both declared and initialized with specific values, avoiding the use of default values.

### Important Points:

- **Default values**: For primitive types like `int`, arrays are initialized with `0`. For object types, arrays are initialized with `null`.
- **Error from accessing uninitialized array**: If you declare a 2D array without initializing it and try to access it (e.g., `array[0][0]`), Java will throw a **NullPointerException** since the array reference points to `null` before initialization.

In summary, declaring a 2D array creates a reference, but the array must be initialized to allocate memory and set default values.

## Comparison

In Java, arrays are **reference types**, meaning that when you declare an array, you're creating a reference (or pointer) to the array's memory location. This has important implications when comparing arrays and understanding how references work.

### Key Concepts:

1. **References in Arrays**:
   When you declare an array, you are creating a reference to a block of memory that will hold the array's elements. For example:
   ```java
   int[][] array1 = new int[2][3];
   int[][] array2 = array1;  // array2 refers to the same memory as array1
   ```

   Here, both `array1` and `array2` refer to the same 2D array. Modifying `array2` will also affect `array1` because they point to the same memory.

2. **Comparing Arrays with `==`**:
   Using `==` to compare arrays checks whether the two arrays refer to the **same memory location**, not whether they contain the same elements.
   ```java
   int[][] array1 = new int[2][3];
   int[][] array2 = new int[2][3];

   System.out.println(array1 == array2);  // Output: false
   ```
   Even though `array1` and `array2` are of the same size and contain default values (`0`), they are stored in different memory locations. Thus, `==` returns `false`.

3. **Comparing Arrays with `Arrays.equals()`**:
   To compare whether two arrays contain the same values, you should use methods from `java.util.Arrays`. However, `Arrays.equals()` works only for **1D arrays**:
   ```java
   int[] arr1 = {1, 2, 3};
   int[] arr2 = {1, 2, 3};
   System.out.println(Arrays.equals(arr1, arr2));  // Output: true
   ```

   For **2D arrays**, you should use `Arrays.deepEquals()`:
   ```java
   int[][] array1 = {{1, 2}, {3, 4}};
   int[][] array2 = {{1, 2}, {3, 4}};

   System.out.println(Arrays.deepEquals(array1, array2));  // Output: true
   ```

   `Arrays.deepEquals()` compares both the references and the actual contents of multi-dimensional arrays recursively.

### Summary:

- Arrays are **reference types**. When you assign an array to another variable, both variables point to the same array in memory.
- Using `==` compares whether two array variables point to the **same memory location** (not the contents).
- Use `Arrays.equals()` for comparing **1D arrays** and `Arrays.deepEquals()` for comparing **multi-dimensional arrays** to check if they contain the same elements.