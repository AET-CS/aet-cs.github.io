---
title: Code up tracing answers
---

**TL/DR:** Create one java class file with methods for each of the tracing examples on your worksheet. Write a `main` method that calls each of the tracing methods in turn and prints the result. Verify the result is as expected. (If you miss any, try to figure out why).

**In depth:** In IntelliJ, create a New Project. Call it "Tracing" and save it in a folder. (You probably want to make a CS11 folder in your Documents folder, and this will make a new folder Tracing, inside of that). Click on `add sample code` (this is a good way to start until you're used to making your own project from scratch.)

You should probably open Tracing in "this window" when it asks (so it closes anything else open)

A single file, `Main.java` will open. Delete its contents and replace with the code below. (If you're doing the AP level problems, then the method names are `mystery`).

```java
public class Main {
    public static void trace1() {
        int x = 5;
        int y = 3;
        x = x + y;
        y = x - y;
        System.out.println(x + " " + y);
    }
    public static void main(String[] args) {
        trace1();
    }
}
```

Save the file and run it (click on green triangle). You should see the output.

Once this is confirmed running, you can add more `trace` methods and call them in `main`. Your final file will look like this

```java
public class Main {
    public static void trace1() {
        int x = 5;
        int y = 3;
        x = x + y;
        y = x - y;
        System.out.println(x + " " + y);
    }

    public static void trace2() {
    // trace2 stuff
    }

    public static void trace3() {
    // trace3 stuff
    }

    // more methods here

public static void main(String[] args) {
        trace1();
        trace2();
        trace3();
        // etc
    }
}
```

You should only add **one** trace method at a time and run it. In Java, all the punctuation matters, and the semi-colons at the end of the lines. Spaces do NOT matter, nor does indentation (they are spaced the way they are to facilitate legibility only). The line starting with `//` is a comment and it can say anything. The computer ignores it.

**About classes** We will study 'classes' eventually but for now see you have a file called "Main.java" and it contains the line `public class main`. In Java, one file contains one *class* and they must have the same name. If you rename `Main` to, for example, `Practice`, then you need to rename the file. (IntelliJ will do it for you if you hover over the redline error you see when you change the classname!)