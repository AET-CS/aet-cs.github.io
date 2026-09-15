---
title: MyString Assignment
---

## Overview:
You will create a MyString class that replicates some of the functionality of Java's built-in String class. Your class will store a string as a char array and implement several methods to work with it.

Use the [Testing File here](./TestMyString.java) to test your new class. You will add this class to your new String project (two files in the same project, like with Circle). Run the main method and check the output matches the expected. There are optional testing methods for the extensions if you try any of those!

Please submit to Javadrop before next class. Send me a Remind with any questions!

### Constructor:
`public MyString(String s)`
- Takes a Java String as a parameter and converts it to a char array, storing it in a private field.

### Required Methods:

`public int length()`
- Return the number of characters in the string.

`public char charAt(int index)`
- Return the character at the given index.

`public int indexOf(char c)`
- Return the index of the first occurrence of the character c. Return -1 if the character is not found.

`public void toUpperCase()`
- Convert all the characters in the string to uppercase. Leave non-characters alone.

`public void toLowerCase()`
- Convert all the characters in the string to lowercase. Leave non-characters alone.

`public boolean equals(MyString s)`
- Return true if this string object is the same length as `s` and every character is the same. Otherwise return false.

`public String toString()`
- Return a regular Java String representation of this MyString.

### Extension Methods:

Optional methods for a greater challenge!

`public void prepend(MyString prefix)`
- Modify the string by adding  `prefix` to the beginning

`public void append(MyString suffix)`
- Modify the string by adding  `suffix` to the end

`public boolean contains(MyString sub)`
- Return true if this MyString contains the given substring, false otherwise.

`public MyString substring(int start, int end)`
- Return a new MyString containing characters from start (inclusive) to end (exclusive). Throw an IndexOutOfBoundsException if the indices are invalid.

`public int compareTo(MyString s)`
- Return 1 if `s` is lexically greater than the string, 0, if they are identical, and -1 if `s` is lexically smaller. (i.e. compare based on dictionary sort).


### Hints:
- Use `toCharArray` in the constructor to convert a String to a char array.
- Use loops to iterate through the char array.
- Use Character.toUpperCase() and Character.toLowerCase() for case conversion.
- For substring matching, use nested loops to compare characters.
- Do **not** use any built in String methods in your code other than `toCharArray`