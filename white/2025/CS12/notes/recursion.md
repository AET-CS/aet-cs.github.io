# Recursion Notes: String Operations in Java

## Core Concept of Recursion
Recursion solves problems by:

1. Breaking down the problem into a smaller version of itself
2. Having a **base case** that stops the recursion
3. Having a **recursive case** that calls the function with a smaller input

## Problem 1: Reversing a String

### The Recursive Insight
To reverse a string, we can:
- Take the last character
- Add it to the reverse of everything except the last character
- Stop when we have an empty string (or single character)

### Pseudocode
```
reverse(s) =
    if s is empty → return ""
    else → lastChar(s) + reverse(s[0...length-2])
```

### Java Implementation
```java
public static String reverse(String s) {
    // BASE CASE: empty string or single character
    if (s.length() <= 1) {
        return s;
    }

    // RECURSIVE CASE:
    // Take the last character + reverse everything before it
    char lastChar = s.charAt(s.length() - 1);
    String remainingString = s.substring(0, s.length() - 1);

    return lastChar + reverse(remainingString);
}

// Alternative: Using first character instead of last
public static String reverseAlt(String s) {
    // BASE CASE
    if (s.length() <= 1) {
        return s;
    }

    // RECURSIVE CASE:
    // Reverse everything after first char + first char
    char firstChar = s.charAt(0);
    String remainingString = s.substring(1);

    return reverseAlt(remainingString) + firstChar;
}
```

### Trace Example: `reverse("CAT")`
```
reverse("CAT")
  → 'T' + reverse("CA")
  → 'T' + ('A' + reverse("C"))
  → 'T' + ('A' + "C")
  → 'T' + "AC"
  → "TAC"
```

## Problem 2: Checking if a String is a Palindrome

### The Recursive Insight
A string is a palindrome if:
- The first and last characters match, AND
- The middle part (without first and last) is also a palindrome
- An empty string or single character is a palindrome (base case)

### Pseudocode
```
isPalindrome(s) =
    if length ≤ 1 → return true
    if firstChar ≠ lastChar → return false
    else → isPalindrome(middle portion)
```

### Java Implementation
```java
public static boolean isPalindrome(String s) {
    // BASE CASE: empty string or single character
    if (s.length() <= 1) {
        return true;
    }

    // Check if first and last characters match
    char firstChar = s.charAt(0);
    char lastChar = s.charAt(s.length() - 1);

    if (firstChar != lastChar) {
        return false;  // Not a palindrome
    }

    // RECURSIVE CASE: Check the middle portion
    String middle = s.substring(1, s.length() - 1);
    return isPalindrome(middle);
}

```

### Trace Example: `isPalindrome("RACECAR")`
```
isPalindrome("RACECAR")
  → 'R' == 'R'? Yes → isPalindrome("ACECA")
  → 'A' == 'A'? Yes → isPalindrome("CEC")
  → 'C' == 'C'? Yes → isPalindrome("E")
  → length = 1 → return true
```

### Trace Example: `isPalindrome("HELLO")`
```
isPalindrome("HELLO")
  → 'H' == 'O'? No → return false
```

## Key Teaching Points

### 1. **Identifying the Base Case**
- Ask: "What's the simplest version of this problem?"
- For strings: empty string or single character
- The base case prevents infinite recursion

### 2. **Making the Problem Smaller**
- Each recursive call MUST work on a smaller input
- For strings: typically remove one or more characters
- This ensures we eventually reach the base case

### 3. **Trust the Recursion**
- When writing `reverse(remainingString)`, trust that it works
- Focus on: "If I have the solution to the smaller problem, how do I build the full solution?"

### 4. **Common Pitfalls**
- **Forgetting the base case** → Stack overflow
- **Not making the problem smaller** → Infinite recursion
- **Wrong base case** → Incorrect results


## Practice Exercises

1. **countVowels(String s)** - Recursively count vowels in a string
2. **removeChar(String s, char c)** - Remove all occurrences of character c
3. **isPalindromeIgnoreCase(String s)** - Case-insensitive palindrome check
4. **reverseWords(String s)** - Reverse word order: "hello world" → "world hello"

## Remember
The pattern for string recursion is almost always:

1. Check if base case (usually empty or length 1)
2. Process one character (first or last)
3. Recursively handle the rest
4. Combine the results