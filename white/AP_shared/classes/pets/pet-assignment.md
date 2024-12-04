# Pet Management System Assignment
AP Computer Science A - Working with Inheritance

## Overview
In this assignment, you'll work with a pre-built pet management system and extend it by adding your own pet type. This will help you understand how inheritance works in Java and how to use different classes together.

## Files Provided (Download each)
- `Pet.java` - The abstract base class for all [pets](./Pet.java)
- `Dog.java` - A concrete class for [dogs](./Dog.java)
- `Cat.java` - A concrete class for [cats](./Cat.java)
- `Bird.java` - A concrete class for [birds](./Bird.java)

## Your Tasks


### 1. Create PetDemo.java
Write a main program that demonstrates all pet functionality. See the *sample output* below.


```java
public class PetDemo {
    public static void main(String[] args) {
        // Your code here
    }
}
```

Your program must:

1. Create Pets
   - Create at least one instance of each pet type (Dog, Cat, Bird, and your new pet)
   - Use different names, ages, and specific attributes for each pet

2. Display Pet Information
   - For each Pet you create:
     * Print each pet's information using getInfo()
     * Make each pet produce their sound
     * Print each pet's favorite activity
		 * You do NOT need to use an array

3. Demonstrate Special Actions
   - Show each pet's unique action (fetch for Dog, scratch for Cat, etc.)

4. (OPTIONAL)
   - You can create a Pet array to simplify your code. It will require casting based on type and understanding polymorphism.

### 2. Create Your Own Pet Class
Create a new file for your own type of pet (e.g., `Hamster.java`, `Fish.java`, etc.). Your pet class must:
- Extend the Pet class
- Include at least one unique instance variable (like breed for Dog)
- Override all required abstract methods
- Include one unique method specific to your pet type
- Use appropriate access modifiers (public/private)

## Example Output
```
=== Pet Information ===
Buddy is a Dog who is 3 years old.
Whiskers is a Cat who is 5 years old.
Tweety is a Bird who is 1 years old.
Nibbles is a Hamster who is 2 years old.

=== Pet Sounds ===
Buddy says: Woof! Woof!
Whiskers says: Meow!
Tweety says: Tweet! Tweet!
Nibbles says: Squeak! Squeak!

=== Favorite Activities ===
Buddy loves going for walks
Whiskers loves chasing laser pointers
Tweety loves singing songs
Nibbles loves running on the wheel

=== Special Actions ===
Buddy is playing fetch! Good dog!
Whiskers is scratching the furniture!
Tweety is flying around the room!
Nibbles is stuffing food in their cheeks!
```

## Tips for Success
1. Look at the existing pet classes as examples for creating your own
2. Remember to test all functionality for each pet type
3. (**Only if you're using an optional Pet array**) When using special methods, you'll need to use casting:
   ```java
   if (pet instanceof Dog) {
       ((Dog)pet).fetch();
   }
   ```

## Submission Requirements
Submit the following files:
1. Create a folder called "pets" in your project folder on the server
1. In the folder put your new pet class file (e.g., `Hamster.java`)
2. Add your completed `PetDemo.java` and all other `java` pet files
3. A screenshot of your program output
4. You *must* not change the class names!

## Due Date
Today in class
