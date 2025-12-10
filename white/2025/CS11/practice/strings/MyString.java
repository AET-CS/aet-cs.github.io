public class MyString {
    private char[] chars;

    // Constructor: converts String to char array
    public MyString(String s) {
        chars = s.toCharArray();
    }

    // Returns the number of characters
    public int length() {
        return chars.length;
    }

    // Returns the character at the given index
    public char charAt(int index) {
        if (index < 0 || index >= chars.length) {
					// you don't have to know this yet
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return chars[index];
    }

    // Returns the index of the first occurrence of c, or -1 if not found
    public int indexOf(char c) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                return i;
            }
        }
        return -1;
    }

    // Converts all characters to uppercase
    public void toUpperCase() {
        for (int i = 0; i < chars.length; i++) {
            chars[i] = Character.toUpperCase(chars[i]);
        }
    }

    // Converts all characters to lowercase
    public void toLowerCase() {
        for (int i = 0; i < chars.length; i++) {
            chars[i] = Character.toLowerCase(chars[i]);
        }
    }

    // Returns true if this string equals s
    public boolean equals(MyString s) {
        if (this.length() != s.length()) {
            return false;
        }
        for (int i = 0; i < chars.length; i++) {
            if (this.chars[i] != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Returns the string representation
    public String toString() {
        String result = "";
        for (char c : chars) {
            result += c;
        }
        return result;
    }

    // EXTENSION METHODS

    // Adds prefix to the beginning
    public void prepend(MyString prefix) {
        char[] newChars = new char[prefix.length() + this.length()];
        for (int i = 0; i < prefix.length(); i++) {
            newChars[i] = prefix.charAt(i);
        }
        for (int i = 0; i < this.length(); i++) {
            newChars[prefix.length() + i] = this.chars[i];
        }
        this.chars = newChars;
    }

    // Adds suffix to the end
    public void append(MyString suffix) {
        char[] newChars = new char[this.length() + suffix.length()];
        for (int i = 0; i < this.length(); i++) {
            newChars[i] = this.chars[i];
        }
        for (int i = 0; i < suffix.length(); i++) {
            newChars[this.length() + i] = suffix.charAt(i);
        }
        this.chars = newChars;
    }

    // Returns true if this MyString contains the substring
    public boolean contains(MyString sub) {
        if (sub.length() > this.length()) {
            return false;
        }
        for (int i = 0; i <= this.length() - sub.length(); i++) {
            boolean match = true;
            for (int j = 0; j < sub.length(); j++) {
                if (this.chars[i + j] != sub.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }

    // Returns a substring from start (inclusive) to end (exclusive)
    public MyString substring(int start, int end) {
        if (start < 0 || end > chars.length || start > end) {
            throw new IndexOutOfBoundsException("Invalid indices");
        }
        char[] sub = new char[end - start];
        for (int i = 0; i < sub.length; i++) {
            sub[i] = chars[start + i];
        }
        String result = "";
        for (char c : sub) {
            result += c;
        }
        return new MyString(result);
    }

    // Returns 1 if s is lexically greater, 0 if equal, -1 if s is lexically smaller
    public int compareTo(MyString s) {
        int minLength = Math.min(this.length(), s.length());
        for (int i = 0; i < minLength; i++) {
            if (this.chars[i] < s.charAt(i)) {
                return -1;
            } else if (this.chars[i] > s.charAt(i)) {
                return 1;
            }
        }
        if (this.length() < s.length()) {
            return -1;
        } else if (this.length() > s.length()) {
            return 1;
        }
        return 0;
    }
}
