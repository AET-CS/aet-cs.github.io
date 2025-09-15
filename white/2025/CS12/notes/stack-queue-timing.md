# Timing Stacks and Queues

**Overview: You are writing code to test your Stack and Queue classes and turning in a document explaining your results**

The purpose of this assignment is to determine the running time of `push` and `pop` for your `Stack` and `Queue` implementations. Ideally, both data structures will support **constant time** push and pop. You will test this by pushing (or popping) $n$ items to a data structure and timing it. By varying the size of $n$, you can infer running times.

## Stack

You should start with stack. Modify the current StackDriver.java file as so
* rename `main` to something like `stackTest`
* create a new `main` that does nothing right now
* define `pushItems(int n, Stack<Integer> s)` that takes a Stack, clears it, and pushes exactly $n$ integers. It should time this operation and return the time required for the pushes
* define `testPush()` which calls `push` in a loop for $n = 1000, 2000, 4000, \ldots$, doubling each time. It should print the size of the Stack $n$ and the required time to push $n$ items, as rows in a table. The loop should stop whenever the time required is at least 1000ms.
* call `testPush()` from `main()` and observe the output
* repeat the above steps for `pop`. In this case, you should clear the queue and `push` $n$ items **before** you pop (this ensures every test is fair). Create `popItems` and `testPop` and call `testPop` from main
* make a scatter plot of your results in software (excel, sheets, python, java, whatever). Discuss the results!
* Add the Table, the graph and your discussion to a file (docs, word, etc, whatever) and save as a .pdf

## Queue

Repeat all the above analyses with your Queue data structure. Add your results to the same .pdf file above. Discuss any differences between the running times.

## Sample Output

Here's a sample of what your output should look like. (You will have many more rows).

### Stack Push Performance

| Stack size | Time to push |
| ---------: | -----------: |
|       1000 |            1 |
|       2000 |            0 |
|       4000 |            1 |
|       8000 |            1 |
|      16000 |            3 |


### Stack Pop Performance

| Stack size | Time to pop |
| ---------: | ----------: |
|       1000 |           1 |
|       2000 |           1 |
|       4000 |           1 |
|       8000 |           1 |


## Formatted output

Here's some handy java code to produce such a table

```java
System.out.print("    Stack size     Time to pop\n");
System.out.print("---------------|---------------\n");
while (result < 1000) {
    result = timePop(size, stack);
    System.out.printf("%15d\t%15d\n", size,  result);
    size *= 2;
}
```

The key is the line `System.out.printf("%15d\t%15d\n", size,  result)`. This prints two numbers in a formatted, aligned table-like output.

#### Format string breakdown: `"%15d\t%15d\n"`

- **`%15d`** - Print an integer (`d` = decimal) in a field that's 15 characters wide, right-aligned
- **`\t`** - Insert a tab character
- **`%15d`** - Print another integer in a 15-character wide field
- **`\n`** - Insert a newline (move to next line)

#### Arguments: `size, result`
- First `%15d` gets the value of `size`
- Second `%15d` gets the value of `result`

## Example output:
```
           1000              1
           2000              0
           4000              1
```

## Timing code

The simplest way to time code in `java` is with `currentTimeMillis()`. See an example below. Due to various quirks of modern OSes and java, it may only be accurate to 10-15 ms but that's fine for us.

```java
long startTime = System.currentTimeMillis();
for (int i = 0; i < n; i++) {
    stack.push(i);
}
long endTime = System.currentTimeMillis();
return endTime - startTime;
```