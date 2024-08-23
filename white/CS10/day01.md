# Day 1 Coding Challenges



## Reverse Integer

Given an unsigned 32-bit integer x, return x with its digits reversed. Do *not* use string operations.

```
Example 1:

Input: x = 123
Output: 321

Example 2:

Input: x = 120
Output: 21
```

----------
## String to Integer (atoi)

Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.

The algorithm for myAtoi(string s) is as follows:

- Whitespace: Ignore any leading whitespace (" ").
- Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither is present.
- Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
- Return the integer as the final result.


## Roman Numeral

 Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

|Symbol  |     Value  |
|--------|------------|
I       |      1
V        |     5
X         |    10
L       |      50
C      |       100
D     |        500
M    |         1000

For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.


```
Example 1:

Input: s = "III"
Output: 3
Explanation: III = 3.

Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.

Example 3:

Input: s = "MCMXCIV"
Output: 1994

Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```

## Longest Common Prefix

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".


```
Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```

Constraints:

```
1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lowercase English letters.
```