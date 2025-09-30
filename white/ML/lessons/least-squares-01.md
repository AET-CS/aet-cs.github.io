---
title: "Linear Least Squares"
---


In class we derived the formula for linear least squares of one variable. In this notebook you will learn a bit of the numerical library numpy, use numpy to compute linear regression, and then compute it yourself using formulas from class

Begin by running the cell below. Then go back and carefully read through all the code. There is a lot of new stuff here. Note how to create numpy arrays/matrices and how to compute a linear least squares regression.


```python
import numpy as np
import matplotlib.pyplot as plt

# Step 1: Prepare your data
# x: Independent variable (input)
# y: Dependent variable (output)
x = np.array([1, 2, 3, 4, 5])
y = np.array([2, 4, 5, 4, 5])

# Step 2: Perform linear regression using the least squares method

# Add a column of ones to the input data for the intercept (bias term)
X = np.vstack([x, np.ones(len(x))]).T

# Calculate the slope (m) and intercept (b)
a, b = np.linalg.lstsq(X, y, rcond=None)[0]

print(f"Slope (a): {a:.4f}")
print(f"Intercept (b): {b:.4f}")

# Step 3: Predict y values using the regression line
y_pred = a * x + b

# Optional: Plot the data and the regression line
plt.scatter(x, y, color='blue', label='Data points')
plt.plot(x, y_pred, color='red', label='Regression line')
plt.xlabel('x')
plt.ylabel('y')
plt.legend()
plt.show()

```

## An aside about numpy matrices

What happened to x? Here's the original $x$, which is an array


```python
x
```

We add a row of 1s after it and take the transpose to get the input matrix $X$


```python
X
```

Breaking this down into pieces, first let's make a python list that contains $x$ and an array of ones


```python
[x,np.ones(len(x))]
```

Now let's use numpy to make a vertical stack. The first element in the list becomes the first row


```python
np.vstack([x, np.ones(len(x))])
```

And now take the transpose


```python
np.vstack([x, np.ones(len(x))]).T
```

### Practice with matrices

Make a numpy matrix that is a row of 5 zeros followed by a row of 5 ones, then 5 zeros, then 5 ones again. Use built in functions and `vstack` (don't just type a bunch of 0 and 1 -- can you guess the name of a function that makes an array of zeros?)


```python

```

Now make a similar matrix that is a row of all 1s followed by all 2s in the second row, then 3s then 4s. Again use built in function `np.ones`. Name this matrix `M`. Hint: $[2,2,2,2,2] = 2\cdot[1,1,1,1,1]$


```python

```

compute M times M transpose and M transpose times M ($MM^T$ and $M^TM$). In `numpy` $AB$ can be computed with `A @ B` for matrices `A` and `B`


```python

```

A matrix $M$ is *symmetric* if $M = M^T$. This also implies $M_{ij} = M_{ji}$ for all indices $(i,j)$. Write a python function `is_symmetric(M)` which returns `true` if and only if $M$ is symmetric


```python

```

Test your function. Make a 5 by 5 random integer matrix (see `np.random.randint`) called $M$. It is a fact that $MM^T$ is always symmetric. Check that your function return `true` for $MM^T$ and `false` for $M$. Repeat this trial 100 times and verify all 100 are correct.


```python

```

## Linear Least Squares Regression

You can create a vector of normally distributed samples with mean $\mu$ and standard deviation $\sigma$ by using the numpy function `np.random.normal(mu, sigma, n)`. Try creating a vector with 10 random samples, with a mean of 100 and a standard deviation of 5.


```python

```

Now create some data for linear regression. Make a vector $x$ of ints over the range $[0,9]$ and let $y$ be a linear function of $x$, $y = 3x+2+\epsilon(x)$ where $\epsilon(x)$ is a random Gaussian noise function $\epsilon(x) \sim N(0,1)$. Make a scatter plot of $y$ vs. $x$ and label it


```python

```

Compute the correct linear regression coefficients using numpy as above. Check they are resonable.


```python

```

Now compute the regression coefficients using the formulas from class. Begin by defining some very helpful variables: `Sx, Sy` will be $\sum_i {x_i}$ and $\sum_i {y_i}$ respectively. Next `Sxx` and `Syy` are the sum of squares: $\sum_i {x_i}^2$ and $\sum_i {y_i}^2$. Finally the inner product `Sxy` = $\sum_i x_iy_i$. The quickest way to do this involves using comprehensions and the `sum` function, but you can use loops for now if you need to.


```python

```


```python
# Print your results
Sx, Sy, Sxx, Syy, Sxy
```

Finally determine $a,b$ as in class. Display the absolute errors between your calculations and the ones numpy returned. (They should be close to machine precision, which is $10^{-15}$ give or take.


```python

```

## Least squares function

Did you know python can return two values? Here's an example.


```python
def two_numbers():
    a = 1
    b = 10
    return a,b
```


```python
A, B = two_numbers()
print(A,B)
```

Write a function `linear_least_squares(x,y)` which takes input vectors x,y and returns a,b as above. (


```python
def linear_least_squares(x,y):

```

## Application

Now, using $a=5, b =-15$, run linear least squares 100 times on 100 vector pairs $(x,y)$, where each of the 100 $x$ are the same but the $y=ax+b+\epsilon$ each have different amounts of Gaussian noise.  Plot the resulting best fit lines all on the same graph.
- Use `np.arange` to make your input vector $x$ cover the domain $[-5,5]$ with a step-size of 0.01
- Create arrays to store all the computed a and b values (you'll use this later)
- If you call `plt.plot()` in a loop, it will keep adding to the same plot
- Give your plot a title!


```python

```

Determine the average of the $a$s and $b$s returned above. Compare these to the true $a,b$. Explain your result.(There is an `np.mean` function)


```python

```

Make two histogram plots of the calculated $a$ and $b$ values `plt.hist` works nicely and adding a semicolon suppressed the nasty text output (you'll see)


```python

```
