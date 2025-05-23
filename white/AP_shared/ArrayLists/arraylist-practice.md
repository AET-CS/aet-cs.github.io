---
title: "ArrayList Practice Worksheet - Insert and Remove Operations"
---


For each question, show what the ArrayList contains after performing all the given operations in order. Write out each step to get full credit.

## Question 1

Start with an empty ArrayList<String> called `words`.
Perform these operations:
1. `words.add("cat")`
2. `words.add("dog")`
3. `words.add(1, "bird")`
4. `words.remove(0)`
5. `words.add("fish")`
6. `words.remove("bird")`

Show the final contents of the ArrayList:
```
Final answer: [______, ______]
```

## Question 2

Start with an ArrayList<Integer> called `nums` containing: `[5, 10, 15, 20]`
Perform these operations:
1. `nums.add(2, 12)`
2. `nums.remove(Integer.valueOf(10))`
3. `nums.add(0, 3)`
4. `nums.remove(nums.size()-1)`
5. `nums.add(nums.get(1))`

Show the final contents of the ArrayList:
```
Final answer: [______, ______, ______, ______, ______]
```

## Question 3

Start with an ArrayList<String> called `colors` containing: `["red", "blue", "green"]`
Perform these operations:
1. `colors.add("yellow")`
2. `colors.set(1, "purple")`
3. `colors.remove(2)`
4. `colors.add(1, "orange")`
5. `colors.remove("red")`
6. `colors.add(colors.get(0))`

Show the final contents of the ArrayList:
```
Final answer: [______, ______, ______, ______]
```

## Question 4

Start with an ArrayList<Integer> called `nums` containing: `[2, 4, 6, 8, 10, 12, 14]`
Perform these operations:
1. `nums.add(3)`
2. For loop that runs forward through the list:
```java
for(int i = 0; i < nums.size(); i++) {
    if(nums.get(i) % 4 == 0) {
        nums.remove(i);
    }
}
```
3. `nums.add(0, 1)`
4. For loop that doubles each number:
```java
for(int i = 0; i < nums.size(); i++) {
    nums.set(i, nums.get(i) * 2);
}
```

Show the final contents of the ArrayList:
```
Final answer: [______, ______, ______, ______, ______, ______]
```
