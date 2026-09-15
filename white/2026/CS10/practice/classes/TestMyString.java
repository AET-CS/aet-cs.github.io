public class TestMyString {
    public static void main(String[] args) {
        testBasicMethods();
        
        // Uncomment the following if you do any extension methods
        // testExtensionMethods();
    }

    public static void testBasicMethods() {
        System.out.println("=== Testing Basic Methods ===\n");

        String testString = "Hello";

        MyString str = new MyString(testString);
        System.out.println("Testing with String " + testString);

        // Test length
        System.out.println("Test: length()");
        System.out.println("Expected: 5");
        System.out.println("Actual: " + str.length());
        System.out.println();

        // Test charAt
        System.out.println("Test: charAt(0)");
        System.out.println("Expected: H");
        System.out.println("Actual: " + str.charAt(0));
        System.out.println();

        System.out.println("Test: charAt(4)");
        System.out.println("Expected: o");
        System.out.println("Actual: " + str.charAt(4));
        System.out.println();

        // Test indexOf
        System.out.println("Test: indexOf('l')");
        System.out.println("Expected: 2");
        System.out.println("Actual: " + str.indexOf('l'));
        System.out.println();

        System.out.println("Test: indexOf('z')");
        System.out.println("Expected: -1");
        System.out.println("Actual: " + str.indexOf('z'));
        System.out.println();

        // Test toUpperCase
        System.out.println("Test: toUpperCase()");
        System.out.println("Expected: HELLO");
        str.toUpperCase();
        System.out.println("Actual: " + str);
        System.out.println();

        // Test toLowerCase
        MyString str2 = new MyString("World");
        System.out.println("Test converting `World` toLowerCase()");
        System.out.println("Expected: world");
        str2.toLowerCase();
        System.out.println("Actual: " + str2);
        System.out.println();

        // Test toLowerCase
        MyString str2b = new MyString("Hello WORLD!! :-) ");
        System.out.println("Test converting `Hello WORLD!! :-) ` toLowerCase()");
        System.out.println("Expected: hello world!! :-) ");
        str2b.toLowerCase();
        System.out.println("Actual: " + str2b);
        System.out.println();

        // Test equals
        MyString str3 = new MyString("HELLO");
        System.out.println("Test: equals(str2b) where str3 = \"HELLO\"");
        System.out.println("Expected: true");
        System.out.println("Actual: " + str.equals(str3));
        System.out.println();

        MyString str4 = new MyString("GOODBYE");
        System.out.println("Test: \"HELLO\" == \"GOODBYE\"");
        System.out.println("Expected: false");
        System.out.println("Actual: " + str.equals(str4));
        System.out.println();
    }

    public static void testExtensionMethods() {
        System.out.println("=================== Testing Extension Methods =======================\n");

        // Test contains
        MyString str = new MyString("Java Programming");
        MyString sub1 = new MyString("Java");
        MyString sub2 = new MyString("Python");

        System.out.println("Test: " + str + " contains(\"Java\")");
        System.out.println("Expected: true");
        System.out.println("Actual: " + str.contains(sub1));
        System.out.println();

        System.out.println("Test: " + str + " contains(\"Python\")");
        System.out.println("Expected: false");
        System.out.println("Actual: " + str.contains(sub2));
        System.out.println();

        // Test substring
        System.out.println("Test: " + str + ".substring(0, 4)");
        System.out.println("Expected: Java");
        System.out.println("Actual: " + str.substring(0, 4));
        System.out.println();

        System.out.println("Test: " + str + ".substring(5, 10)");
        System.out.println("Expected: Progr");
        System.out.println("Actual: " + str.substring(5, 10));
        System.out.println();

        // Test prepend
        MyString str2 = new MyString("World");
        MyString prefix = new MyString("Hello ");

        System.out.println("Test: prepend(\"Hello \") to \"World\"");
        System.out.println("Expected: Hello World");
        str2.prepend(prefix);
        System.out.println("Actual: " + str2);
        System.out.println();

        // Test append
        MyString str3 = new MyString("Hello");
        MyString suffix = new MyString(" World");

        System.out.println("Test: append(\" World\") to \"Hello\"");
        System.out.println("Expected: Hello World");
        str3.append(suffix);
        System.out.println("Actual: " + str3);
        System.out.println();

        // Test compareTo
        MyString s1 = new MyString("apple");
        MyString s2 = new MyString("banana");
        MyString s3 = new MyString("apple");

        System.out.println("Test: apple.compareTo(banana)");
        System.out.println("Expected: -1");
        System.out.println("Actual: " + s1.compareTo(s2));
        System.out.println();

        System.out.println("Test: banana.compareTo(apple)");
        System.out.println("Expected: 1");
        System.out.println("Actual: " + s2.compareTo(s1));
        System.out.println();

        System.out.println("Test: apple.compareTo(apple)");
        System.out.println("Expected: 0");
        System.out.println("Actual: " + s1.compareTo(s3));
    }
}
