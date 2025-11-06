# Sierpinski Assignment

You will complete a program to draw an equilateral Sierpinksi Triangle and then complete some extensions.

The [java code](./SierpinskiTriangle.java) should run without error and draw a blank screen. You should read through the code and complete the `drawSierpinski` method to
recursively draw a Sierpinski triangle. Once it is working, increase the recursion depth to a suitable amount to draw as much details as your screen will allow.

## Follow-up questions and extensions

1. For a different effect, change `drawPolygon` to `fillPolygon`. Choose your favorite color, or try alternating colors on each level.
2. Using screen coordinates and the original width and height given in the sample code, what is the limit of the shaded area drawn by infinitely many calls to `fillPolygon`
3. Modify the code to draw a right triangle
4. Modify the code to draw a square, or any other shape you want. You are free to define how the "inner levels" get drawn.
5. For the shape you choose to draw, if the unit length of the largest edge is 1, what is the total area of the shaded region?
