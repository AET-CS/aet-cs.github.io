# Quick Jupyter Intro and Python Loops

Jupyter notebook is arranged into cells. Cells can contain Input (computations/code), Output and Markdown (text). A cell can be *selected* or *active*. To *select* a cell, click to the left of it and it should highlight with a colored border. To activate a cell, click inside of it so the cursor is visible. To run a code cell, or render a text cell, type `shift-return`.

When a cell is selected you can do cell operations by typing single letters such as

- `A` add a cell above this one
- `B` add a cell below this one
- `X` cut the cell to the clipboard
- `C` copy the cell to the clipboard
- `V` paste the cell on the clipboard
- `D` delete the cell
- `M` convert a cell to markdown style
- `Y` convert a markdown cell back to Input style

You can also drag a selected cell around the notebook with the mouse. You should also get familiar with the menu bar and toolbars. There are several useful operations hidden there. For example, *rerun all cells* is handy as is *Clear Outputs of all cells*

## Python Loops

I'll show some basic examples of how to do things in python. You may want to run these in Jupyter to verify.

- Print the numbers 0-99 on separate lines
	```python
	for i in range(100):
		print(i)
	```

- Print the numbers 10-99 on separate lines
	```python
	for i in range(10,100):
		print(i)
	```

- Print the *even* numbers 10-19 on separate lines
	```python
	for i in range(10,20,2):
		print(i)
	```

- Print all characters in "hello computer", all on one line
	```python
	for c in "the word":
		print(c, end="")
	```

- Compute the largest Fibonacci number less than 1000 (note the use of parallel assignment)
	```python
	a,b = 1,0
	while a<1000:
			a,b = a+b,a
	```

## Python Lists

Python's regular list data type is not a true array, such as you would find in C or Java. An array is, properly, fixed length and single type. Python uses *lists* which are dynamic (auto-resizing) and can contain *any* type. For example

```python
l1 = [1,2,3]
l2 = ["bird",2,-10.3,"cow"]
l3 = [l1,l2,3]
print(l1)
print(l2[2])
print(l3[1][1])
```
```
[1, 2, 3]
-10.3
2
```
You see lists can even contain lists. Here are some familiar tropes in Python

```python
l = [0]*10 # a list of 10 zeros
for i in range(len(l)):
	l[i] = i  # replace the zeros with the index
for i in range(10,20):
	l.append(i) # add more elements to l
for i in range(20,30):
	l = l + [i] # another way to add
print(l)
```
This prints the integers from 0 to 29 inclusive.

Lists are very flexible and there are many operations that are easy to do such as sort, find, replace, merge, delete. If you want to perform a list operation, look it up in online and see if it is already a built-in operation

## Comprehensions

Comprehensions are beautiful and lovely ways to build lists quickly. I'll just give a couple examples here

This code
```python
l = []
for i in range(10):
	l.append(i**2)
```

can be replaced with this comprehension

```python
l = [i**2 for i in range(10)]
```

It is quite lovely, isn't it. It reads like a math set definition

$$ L = \{i^2 \; | \;  0 \leq i < 10\}$$

And make great tools for plotting, say

```python
from math import sin
from matplotlib import pyplot as plt
X = [i/100 for i in range(628)]
Y = [sin(i/100) for i in range(628)]
plt.plot(X,Y);
```
will plot one period of a sine curve.

In practice, numerical algorithms will use `numpy arrays` instead of python lists because they are much much faster and work like traditional C arrays. But lists still come in extremely handy when you need to collect data and speed is not a priority.