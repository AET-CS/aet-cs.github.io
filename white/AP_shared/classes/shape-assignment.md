# Shape Calculator Assignment
AP Computer Science A - Inheritance and Polymorphism

## Overview
In this assignment, you will create a shape hierarchy to demonstrate your understanding of inheritance and polymorphism in Java. You'll implement different shapes that inherit from a common base class and create a program that can work with these shapes polymorphically.

## Learning Objectives
- Implement inheritance using abstract and concrete classes
- Demonstrate polymorphism through method overriding
- Work with arrays/lists containing objects of different types
- Practice good documentation and code organization

## Requirements

### Base Class
Create an abstract class called `Shape` with:
- An abstract method `double area()`
- An abstract method `String getDescription()`
- A concrete method `void printInfo()` that uses getDescription() and area()

### Derived Classes
Implement the following shape classes that inherit from Shape:

1. `Circle`
   - Private field: radius (double)
   - Constructor that takes radius as parameter
   - Override area() method using πr²
   - Override getDescription() to return "Circle with radius [r]"

2. `Rectangle`
   - Private fields: width, height (double)
   - Constructor that takes width and height as parameters
   - Override area() method using width × height
   - Override getDescription() to return "Rectangle with width [w] and height [h]"

3. `Triangle`
   - Private fields: base, height (double)
   - Constructor that takes base and height as parameters
   - Override area() method using ½ × base × height
   - Override getDescription() to return "Triangle with base [b] and height [h]"

### Main Program
Create a class called `ShapeCalculator` with a main method that:
1. Creates an array of at least 5 Shape objects with different types and dimensions
2. Implements a method called `printAllShapes` that:
   - Takes an array of Shape objects as a parameter
   - Loops through the array
   - Calls printInfo() on each shape
3. Implements a method called `getTotalArea` that:
   - Takes an array of Shape objects as a parameter
   - Returns the sum of all shapes' areas

## Example Output
```
Shape Information:
Circle with radius 5.0
Area: 78.54 square units

Rectangle with width 4.0 and height 6.0
Area: 24.00 square units

Triangle with base 8.0 and height 3.0
Area: 12.00 square units

Total area of all shapes: 114.54 square units
```

## Submission Requirements
1. Create a folder 'shapes' and put all your java code in it
2. Add 'package shapes;' to each java file in the first line.
3. Add a screenshot of the program output to the shapes folder
4. Submit the entire 'shapes' folder to the server (you can drag a whole folder in)
5. Files should be named: Shape.java, Circle.java, Rectangle.java, Triangle.java, ShapeCalculator.java, screenshot.png

## Due Date
Friday 12/6/2024 in class
