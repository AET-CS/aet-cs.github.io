---
title: "Strings in Java"
---


Here's a quick overview of some of the most important and commonly used `String` methods in Java (mostly written by ChatGpt 4-O)

#### 1. **`length()`**
   - **Description**: Returns the length of the string (number of characters).
   - **Usage**:
     ```java
     String str = "Hello";
     int len = str.length(); // 5
     ```

#### 2. **`charAt(int index)`**
   - **Description**: Returns the character at the specified index.
   - **Usage**:
     ```java
     char ch = str.charAt(0); // 'H'
     ```

#### 3. **`substring(int beginIndex)` and `substring(int beginIndex, int endIndex)`**
   - **Description**: Extracts a substring starting from `beginIndex` (inclusive) and optionally up to `endIndex` (exclusive).
   - **Usage**:
     ```java
     String sub = str.substring(1); // "ello"
     String sub2 = str.substring(0, 2); // "He"
     ```

#### 4. **`indexOf(String str)` and `indexOf(char ch)`**
   - **Description**: Returns the index of the **first** occurrence of the specified character or substring. Returns `-1` if not found.
   - **Usage**:
     ```java
     int index = str.indexOf('e'); // 1
     int index2 = str.indexOf("lo"); // 3
     ```

#### 5. **`lastIndexOf(String str)` and `lastIndexOf(char ch)`**
   - **Description**: Returns the index of the last occurrence of the specified character or substring.
   - **Usage**:
     ```java
     int lastIndex = str.lastIndexOf('l'); // 3
     ```

#### 6. **`equals(Object obj)` and `equalsIgnoreCase(String anotherString)`**
   - **Description**: Compares two strings for equality. `equalsIgnoreCase()` ignores case.
   - **Usage**:
     ```java
     boolean isEqual = str.equals("Hello"); // true
     boolean isEqualIgnoreCase = str.equalsIgnoreCase("hello"); // true
     ```

#### 7. **`compareTo(String anotherString)` and `compareToIgnoreCase(String anotherString)`**
   - **Description**: Compares two strings lexicographically. Returns a negative, zero, or positive integer depending on whether the string is less than, equal to, or greater than the compared string.
   - **Usage**:
     ```java
     int result = str.compareTo("Hello"); // 0 (equal)
     int resultIgnoreCase = str.compareToIgnoreCase("hello"); // 0 (equal ignoring case)
     ```

#### 8. **`contains(CharSequence s)`**
   - **Description**: Returns `true` if the string contains the specified sequence of characters.
   - **Usage**:
     ```java
     boolean containsSub = str.contains("ell"); // true
     ```

#### 9. **`toUpperCase()` and `toLowerCase()`**
   - **Description**: Converts all characters in the string to uppercase or lowercase.
   - **Usage**:
     ```java
     String upper = str.toUpperCase(); // "HELLO"
     String lower = str.toLowerCase(); // "hello"
     ```

#### 10. **`trim()`**
   - **Description**: Removes leading and trailing whitespace from the string.
   - **Usage**:
     ```java
     String trimmed = "  Hello  ".trim(); // "Hello"
     ```

#### 11. **`replace(char oldChar, char newChar)` and `replaceAll(String regex, String replacement)`**
   - **Description**: Replaces all occurrences of a specified character or substring. `replaceAll()` allows regex replacement. (A regex is a regular expression. Feel free to read about them if you're interested! The regex '\d' matches any digit. The extra '\')
   - **Usage**:
     ```java
     String replaced = str.replace('l', 'x'); // "Hexxo"
     String replacedAll = "foo123bar".replaceAll("\\d", ""); // "foobar" (removes digits)
     ```

#### 12. **`split(String regex)`**
   - **Description**: Splits the string into an array based on a regular expression delimiter.
   - **Usage**:
     ```java
     String[] parts = "apple,orange,banana".split(","); // ["apple", "orange", "banana"]
     ```

#### 13. **`join(CharSequence delimiter, CharSequence... elements)`**
   - **Description**: Joins multiple strings with the specified delimiter.
   - **Usage**:
     ```java
     String joined = String.join(", ", "apple", "orange", "banana"); // "apple, orange, banana"
     ```

#### 14. **`startsWith(String prefix)` and `endsWith(String suffix)`**
   - **Description**: Checks if the string starts or ends with the specified prefix or suffix.
   - **Usage**:
     ```java
     boolean starts = str.startsWith("He"); // true
     boolean ends = str.endsWith("lo"); // true
     ```

#### 15. **`isEmpty()` and `isBlank()`**
   - **Description**: `isEmpty()` returns `true` if the string's length is 0. `isBlank()` returns `true` if the string is empty or contains only whitespace.
   - **Usage**:
     ```java
     boolean empty = "".isEmpty(); // true
     boolean blank = "   ".isBlank(); // true
     ```

#### 16. **`valueOf()`**
   - **Description**: Converts various data types (int, float, boolean, etc.) to a string.
   - **Usage**:
     ```java
     String numStr = String.valueOf(123); // "123"
     ```

---
