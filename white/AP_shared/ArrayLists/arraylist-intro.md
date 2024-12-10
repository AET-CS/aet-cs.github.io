# Introduction to ArrayLists in Java

ArrayLists are one of the most commonly used data structures in Java. Unlike regular arrays, ArrayLists can grow and shrink dynamically, making them more flexible for many programming tasks.

## Creating ArrayLists

```java
// Creating an empty ArrayList
ArrayList<String> names = new ArrayList<>();

// Creating with initial elements
ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
```

## Basic Operations

### Adding Elements
```java
names.add("Alice");               // Adds to end
names.add(0, "Bob");             // Adds at index 0
names.addAll(otherArrayList);    // Adds all elements from another list
```

### Accessing Elements
```java
String first = names.get(0);     // Gets element at index
int size = names.size();         // Gets number of elements
boolean exists = names.contains("Alice");  // Checks if element exists
```

### Modifying Elements
```java
names.set(0, "Charlie");         // Changes element at index
```

### Removing Elements
```java
names.remove(0);                 // Removes by index
names.remove("Alice");           // Removes first matching object
names.clear();                   // Removes all elements
```

## Important Tips

1. **Type Safety**: ArrayLists use generics (`<Type>`) to ensure type safety. Once you specify a type, you can only store that type of object in the ArrayList.

2. **Auto-boxing**: For primitive types, use their wrapper classes:
   - `int` → `ArrayList<Integer>`
   - `double` → `ArrayList<Double>`
   - `boolean` → `ArrayList<Boolean>`

3. **Common Mistakes to Avoid**:
   - Don't try to access an index that doesn't exist
   - Remember that indices start at 0
   - Be careful when removing elements while looping (loop backwards or use iterators)

## Comparing Arrays vs ArrayLists

| Feature | Array | ArrayList |
|---------|-------|-----------|
| Size | Fixed | Dynamic |
| Syntax | `String[] arr` | `ArrayList<String> list` |
| Adding/Removing | Not possible | Easy and dynamic |
| Direct Access | `arr[0]` | `list.get(0)` |
| Memory | Less overhead | More overhead |

## When to Use ArrayLists

Choose ArrayLists when you:
- Don't know the exact size needed in advance
- Need to frequently add or remove elements
- Want built-in methods for common operations
- Need to resize the collection dynamically

Choose arrays when you:
- Know the exact size needed
- Want slightly better performance
- Are working with primitive types and memory is crucial
