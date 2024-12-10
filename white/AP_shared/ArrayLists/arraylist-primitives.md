# ArrayLists with Primitive Types in Java

Java ArrayLists can only store objects, not primitive types. To store primitives, we use special wrapper classes that convert primitives to objects. Here's what you need to know:

## Wrapper Classes

Each primitive type has a corresponding wrapper class:
- `int` → `Integer`
- `double` → `Double`
- `boolean` → `Boolean`
- `char` → `Character`
- `long` → `Long`

## Creating ArrayLists of Primitives

```java
// Creating ArrayLists of primitive types
ArrayList<Integer> numbers = new ArrayList<>();
ArrayList<Double> prices = new ArrayList<>();
ArrayList<Boolean> flags = new ArrayList<>();
```

## Auto-boxing and Auto-unboxing

Java automatically converts between primitives and their wrapper classes:

```java
ArrayList<Integer> numbers = new ArrayList<>();

// Adding values - Java automatically "boxes" the primitives
numbers.add(42);          // int -> Integer
numbers.add(7);          // another automatic boxing

// Getting values - Java automatically "unboxes"
int firstNum = numbers.get(0);     // Integer -> int
int secondNum = numbers.get(1);    // another automatic unboxing
```

## What's Happening Behind the Scenes

When you write:
```java
numbers.add(42);
```

Java secretly does this:
```java
numbers.add(Integer.valueOf(42));
```

And when you write:
```java
int x = numbers.get(0);
```

Java secretly does this:
```java
int x = numbers.get(0).intValue();
```

## Using in Loops

Auto-unboxing happens in loops too:

```java
ArrayList<Integer> nums = new ArrayList<>();
nums.add(1);
nums.add(2);
nums.add(3);

// Each iteration involves unboxing
int sum = 0;
for(Integer num : nums) {  // num is wrapper type
    sum += num;            // num gets unboxed to int here
}

// Traditional for loop works too
for(int i = 0; i < nums.size(); i++) {
    sum += nums.get(i);    // unboxing happens here
}
```

## Common Pitfalls

### Null Values
Wrapper classes can be null, but primitives can't:

```java
ArrayList<Integer> nums = new ArrayList<>();
nums.add(null);  // This is legal!
int x = nums.get(0);  // This will crash (NullPointerException)
```

### Performance
Wrapper classes use more memory than primitives and involve extra conversion steps. For very large lists where performance is critical, consider using regular arrays of primitives instead.

### Comparing Values
Remember to use `.equals()` when comparing wrapper objects:

```java
ArrayList<Integer> nums = new ArrayList<>();
nums.add(128);

// This might not work as expected
if (nums.get(0) == 128) {  // Don't do this!
    System.out.println("Found it!");
}

// Do this instead
if (nums.get(0).equals(128)) {
    System.out.println("Found it!");
}
```

## Best Practices

1. Always check for null when working with wrapper classes if there's any chance they could contain null values
2. Use the primitive type in for-each loops unless you specifically need the wrapper object
3. Remember that auto-boxing and unboxing have a small performance cost
4. Use `.equals()` for comparing values, not `==`

## Common Methods with Primitives

```java
ArrayList<Integer> numbers = new ArrayList<>();

// Adding elements
numbers.add(42);                  // Adds to end
numbers.add(0, 7);               // Adds at index 0

// Getting elements
int first = numbers.get(0);      // Gets element at index

// Updating elements
numbers.set(0, 10);              // Changes element at index

// Removing elements
numbers.remove(0);               // Removes by index
numbers.remove(Integer.valueOf(42));  // Removes first occurrence of 42
```
