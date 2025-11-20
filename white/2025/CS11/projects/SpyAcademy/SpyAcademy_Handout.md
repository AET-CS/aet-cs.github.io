# SPY ACADEMY: CODE BREAKER TRAINING
**AP Computer Science A - Arrays and Strings Lab**

---

## INTRODUCTION: WHAT IS A CAESAR CIPHER?

A Caesar cipher is one of the oldest and simplest encryption techniques. It works by shifting each letter in a message by a fixed number of positions in the alphabet.

**Example with shift of 3:**
```
A → D    B → E    C → F    ...    X → A    Y → B    Z → C
```

So the word "HELLO" becomes "KHOOR":
```
H → K    E → H    L → O    L → O    O → R
```

To decode, you shift backwards by the same amount:
```
K → H    H → E    O → L    O → L    R → O
```

This cipher is named after Julius Caesar, who used it to protect military messages!

---

## JAVA CONCEPT: CHARACTERS AND NUMBERS

In Java, every character has a corresponding integer value. This means you can do math with characters!

**Key facts:**
- `'A'` corresponds to the number 65
- `'B'` corresponds to 66, `'C'` to 67, and so on...
- `'Z'` corresponds to 90

**You can convert between characters and numbers:**

```java
char letter = 'A';
int num = letter;           // num is now 65

int value = 67;
char ch = (char) value;     // ch is now 'C'
```

**Useful trick** - if you want A=0, B=1, C=2, etc:

```java
char letter = 'C';
int position = letter - 'A';    // position is 2 (because C is the 3rd letter)

int position = 2;
char newLetter = (char)('A' + position);    // newLetter is 'C'
```

This is perfect for Caesar cipher! You can convert letters to numbers (0-25), shift them, and convert back.

---

## THE LAB: TWO-PLAYER SPY GAME

### THE GAME:
You'll implement methods that power a two-player spy game:

1. **Player 1 (The Spy)** enters a secret message and chooses a shift amount
2. The computer encodes the message using YOUR `caesarShift` method
3. **Player 2 (The Code Breaker)** sees the encoded message
4. Player 2 has a limited number of guesses to crack the shift amount
5. The computer decodes their guess using YOUR `caesarDecrypt` method
6. Players switch roles and play again!

**Example gameplay:**
```
PLAYER 1: Types "AGENT" with shift 5
COMPUTER: Encodes to "FLMJS"
PLAYER 2: Sees "FLMJS" and must guess the shift
PLAYER 2: Guesses shift of 3 → decodes to "CEBKQ" ❌
PLAYER 2: Guesses shift of 5 → decodes to "AGENT" ✅ SUCCESS!
```

### THE LAB HAS TWO PHASES:

**Phase 1: Single Word Operations**
Practice with simple words (no spaces or punctuation)
- `reverseString()` - warmup
- `caesarShift()` - encode a word
- `caesarDecrypt()` - decode a word
- `countVowels()` - helper method

**Phase 2: Full Phrase Operations**
Level up to messages with spaces and punctuation!
- `caesarShiftPhrase()` - encode "MEET AT NOON!" → "PHHW DW QRRQ!"
- `caesarDecryptPhrase()` - decode the phrase back
- `isValidDecode()` - check if decoded message contains keywords (optional)

---

## YOUR TASK: IMPLEMENT THESE METHODS

> **⚠️ IMPORTANT: TEST AS YOU GO!**
> Don't wait until you've written all the methods to test. After you complete each method, test it with the examples below. This will help you catch bugs early and make debugging much easier!

### PHASE 1 METHODS:

#### 1. `String reverseString(String word)`
**Purpose:** Return the word spelled backwards

**Example:**
```java
reverseString("AGENT")  // returns "TNEGА"
```

**Test cases to try:**
```java
reverseString("HELLO")     // should return "OLLEH"
reverseString("CODE")      // should return "EDOC"
reverseString("A")         // should return "A"
reverseString("RACECAR")   // should return "RACECAR" (palindrome!)
```

**Hint:** Loop through the string from end to beginning

**✅ Test this method before moving on!** You can add a few print statements in the main method to verify it works.

---

#### 2. `String caesarShift(String word, int shift)`
**Purpose:** Encode a word by shifting each letter. You shift a letter by converting to a number (A=0), then adding 26. Then
you take that number MOD 26 (x % 26). The mod operation is how you can "wrap around".

**Example:**
```java
caesarShift("HELLO", 3)  // returns "KHOOR"
```

**Test cases to try:**
```java
caesarShift("ABC", 1)      // should return "BCD"
caesarShift("XYZ", 3)      // should return "ABC" (wraparound!)
caesarShift("AGENT", 5)    // should return "FLMJS"
caesarShift("HELLO", 0)    // should return "HELLO" (no shift)
caesarShift("ZEBRA", 1)    // should return "AFCSB" (Z wraps to A)
caesarShift("AAA", 25)     // should return "ZZZ"
```

**Hints:**
- Convert each letter to a number (A=0...Z=25)
- Add the shift
- Use `% 26` to wrap around (this is crucial!)
- Convert back to a letter

**Algorithm:**
1. Get character: `word.charAt(i)`
2. Convert to 0-25: `letter - 'A'`
3. Add shift and wrap: `(number + shift) % 26`
4. Convert back to letter: `(char)('A' + number)`

**✅ Test this method thoroughly!** The wraparound is tricky. Make sure "Z" with shift 1 gives you "A"!

---

#### 3. `String caesarDecrypt(String encoded, int shift)`
**Purpose:** Decode an encoded word by shifting backwards. IN JAVA, when you take negative number mod 26, you will get a negative answer. To fix this, you can add 26 to make the negative number positive.

**Example:**
```java
caesarDecrypt("KHOOR", 3)  // returns "HELLO"
```

**Test cases to try:**
```java
caesarDecrypt("BCD", 1)       // should return "ABC"
caesarDecrypt("ABC", 3)       // should return "XYZ" (wraparound backwards!)
caesarDecrypt("FLMJS", 5)     // should return "AGENT"
caesarDecrypt("HELLO", 0)     // should return "HELLO"
caesarDecrypt("AAA", 1)       // should return "ZZZ" (backward wrap)
```

**Hint:** This is the opposite of `caesarShift`
**Important:** When subtracting, you might get negative numbers. Use `(number - shift + 26) % 26` to handle this!

**✅ Test both encoding and decoding together:**
```java
String original = "HELLO";
String encoded = caesarShift(original, 7);
String decoded = caesarDecrypt(encoded, 7);
// decoded should equal original!
```

---

#### 4. `int countVowels(String text)`
**Purpose:** Count how many vowels (A, E, I, O, U) are in the text

**Example:**
```java
countVowels("HELLO")  // returns 2 (E and O)
```

**Test cases to try:**
```java
countVowels("AEIOU")      // should return 5
countVowels("BCDFG")      // should return 0
countVowels("PROGRAMMING") // should return 3
countVowels("AGENT")      // should return 2
countVowels("WHY")        // should return 0 (Y is not a vowel)
```

**Hint:** Loop through and check if each character equals 'A', 'E', 'I', 'O', or 'U'

**✅ Quick test!** This one is simpler, but still verify it counts correctly.

---

### 🎮 TEST PHASE 1 BY PLAYING THE GAME!

Once you've implemented all Phase 1 methods, run your program and play Phase 1 with a partner:
- If the encoded message looks like random letters ✅ Good!
- If Player 2 correctly guesses the shift but gets told it's wrong ❌ Bug in your code!
- If decoding gives weird characters or errors ❌ Check your modulo logic!

---

### PHASE 2 METHODS:

#### 5. `String caesarShiftPhrase(String message, int shift)`
**Purpose:** Encode a full phrase, but KEEP spaces and punctuation unchanged

**Example:**
```java
caesarShiftPhrase("MEET AT NOON!", 3)  // returns "PHHW DW QRRQ!"
```

**Test cases to try:**
```java
caesarShiftPhrase("HELLO WORLD", 5)    // should return "MJQQT BTWQI"
caesarShiftPhrase("THE MISSION IS GO!", 13)  // should return "GUR ZVFFVBA VF TB!"
caesarShiftPhrase("A B C", 1)          // should return "B C D"
caesarShiftPhrase("TOP SECRET!!", 7)   // should return "AVW ZJJYLA!!"
caesarShiftPhrase("CODE 123", 2)       // should return "EQFG 123" (numbers unchanged)
caesarShiftPhrase("YES-OR-NO?", 10)    // should return "IOC-BE-XY?" (hyphens stay)
```

**Hints:**
- Use `Character.isLetter(ch)` to check if a character is a letter
- Only shift letters; keep spaces, punctuation, and numbers the same
- You can still use your Caesar shift logic from Phase 1, just wrap it in a condition!

**✅ Test with different punctuation!** Make sure exclamation marks, periods, and numbers stay unchanged.

---

#### 6. `String caesarDecryptPhrase(String encoded, int shift)`
**Purpose:** Decode a phrase (opposite of `caesarShiftPhrase`)

**Example:**
```java
caesarDecryptPhrase("PHHW DW QRRQ!", 3)  // returns "MEET AT NOON!"
```

**Test cases to try:**
```java
caesarDecryptPhrase("MJQQT BTWQI", 5)   // should return "HELLO WORLD"
caesarDecryptPhrase("GUR ZVFFVBA VF TB!", 13)  // should return "THE MISSION IS GO!"
caesarDecryptPhrase("B C D", 1)         // should return "A B C"
caesarDecryptPhrase("AVW ZJJYLA!!", 7)  // should return "TOP SECRET!!"
```

**✅ Test encoding and decoding together:**
```java
String original = "THE MISSION IS GO!";
String encoded = caesarShiftPhrase(original, 10);
String decoded = caesarDecryptPhrase(encoded, 10);
// decoded should equal original!
```

---

#### 7. `boolean isValidDecode(String decoded, String[] keywords)` [OPTIONAL]
**Purpose:** Check if the decoded message contains any word from the keywords array

**Example:**
```java
String[] keywords = {"MISSION", "AGENT", "SECRET"};
isValidDecode("THE MISSION IS GO", keywords)  // returns true (contains "MISSION")
isValidDecode("HELLO THERE", keywords)        // returns false
```

**Test cases to try:**
```java
String[] spyWords = {"AGENT", "MISSION", "SECRET", "CODE"};

isValidDecode("AGENT SMITH", spyWords)        // should return true
isValidDecode("TOP SECRET FILES", spyWords)   // should return true
isValidDecode("THE CODE IS READY", spyWords)  // should return true
isValidDecode("NOTHING HERE", spyWords)       // should return false
isValidDecode("HELLO WORLD", spyWords)        // should return false
```

**Hint:** Loop through the keywords array and use `.contains()` or `.indexOf()`

---

### 🎮 TEST PHASE 2 BY PLAYING THE GAME!

Once Phase 2 methods work, play the full game with phrases:
- Try messages with lots of punctuation
- Try messages with numbers
- Make sure spaces are preserved in the right places!

---

## TESTING STRATEGY: BUILD INCREMENTALLY

**The best approach:**

1. ✅ Write `reverseString()` → test it → move on
2. ✅ Write `caesarShift()` → test it thoroughly → move on
3. ✅ Write `caesarDecrypt()` → test it AND test encode/decode together
4. ✅ Write `countVowels()` → test it
5. ✅ **Play Phase 1 game** → if it works, great! If not, debug.
6. ✅ Write `caesarShiftPhrase()` → test it with the examples above
7. ✅ Write `caesarDecryptPhrase()` → test it thoroughly
8. ✅ **Play Phase 2 game** → your reward for completing the lab!

**Don't skip testing!** It's tempting to write all the code and test at the end, but you'll save time by testing each method as you complete it.

---

## COMMON MISTAKES TO AVOID

❌ **Forgetting to use % 26** → Letters will go past Z and become weird characters!
❌ **Not adding 26 when subtracting** → You'll get negative numbers and errors
❌ **In Phase 2, shifting spaces/punctuation** → They should stay exactly the same!
❌ **Off-by-one errors** → Remember: A=0, not A=1
❌ **Not testing wraparound** → Make sure Z+1=A and A-1=Z!

---

## DEBUGGING TIPS

**If your encoding looks weird:**
- Print out the number values you're getting
- Check: Does 'A' - 'A' give you 0?
- Check: Does 'Z' - 'A' give you 25?
- Are you using `% 26` correctly?

**If decoding doesn't work:**
- Try encoding "ABC" with shift 1, then decode "BCD" with shift 1
- Should get "ABC" back!
- If not, check your subtraction and modulo logic

**If spaces disappear:**
- Are you checking `Character.isLetter()` in Phase 2?
- Are you including non-letters in your result string?

---

## EXTENSIONS FOR FAST FINISHERS

If you complete the lab early, try these challenges:

1. **Hint System**: Modify the game to tell Player 2 if their guess is too high or too low
2. **Auto-Cracker**: Write `findBestShift(String encoded, String[] keywords)` that tries all 26 shifts and returns the one that produces a valid message
3. **Alternating Case Cipher**: Create a new cipher that alternates between uppercase and lowercase
4. **Frequency Analysis**: Write a method that counts how often each letter appears (useful for breaking Caesar ciphers!)
5. **Multi-Shift Cipher**: Instead of one shift value, use a different shift for each letter position

---

**Good luck, Agent! 🕵️**

Remember: Test often, debug early, and have fun cracking codes!
