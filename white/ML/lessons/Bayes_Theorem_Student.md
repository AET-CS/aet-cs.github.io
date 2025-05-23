---
title: "Bayes' Theorem"
---


Bayes' Theorem gives us a way to *invert* conditional probabilities. The formula comes from the definition of conditional probability

$$P(A|B) = \dfrac{P(A \cap B)}{P(B)}$$

this implies the following

$$P(A \cap B) = P(A|B)P(B) = P(B|A)P(A)$$

Solving for $P(A|B)$ we get

$$P(A|B) = \dfrac{P(B|A)P(A)}{P(B)}$$

Though this is the final form, in practice you will need to compute $P(B)$ using the following

$$P(B) = P(B|A)P(A) + P(B|\overline{A})P(\overline{A})$$

which says the probability of $B$ is the sum of the probability of $B$ given $A$ and $B$ given not $A$. ($A$ is either true or false so these are the only two options)

## Exercise 1: Plot a Venn Diagram

Using matplotlib, draw a simple Venn diagram representing two sets $A$, $B$ with a non-null intersection.


```python
## Code here. Add cells as needed
```

## Exercise 2: Compute Bayes' Probabilities

We want to replicate the computation carried out in class. If a doctor performs a test that has a given accuracy, for a disease with a given incidence rate, determine the probability that a randomly selected person with a positive test result has the disease. You are given *accuracy* and *incidence* as input, both in the range $(0,1]$


```python
def get_bayes_probability(acc, inc):
    ## code here
    return bayes
```

Check some results below. The first one comes from class


```python
get_bayes_probability(0.97,0.001)
```


```python
get_bayes_probability(0.97,0.01)
```


```python
get_bayes_probability(0.97,0.1)
```


```python
get_bayes_probability(0.99,0.001)
```


```python
get_bayes_probability(0.50,0.001)
```

## Exercise 3: Plot

You will create two plots in the section. For a fixed incidence rate, plot the bayes probability as the accuracy of the test ranges from 0 to 100%.

Then, for a fixed accuracy, plot the bayes probability as the incidence rate increases.

**Note**, to avoid 1/0 errors you'll probably want to *not* go all the way to 0 or 1.

State a conclusion about the results. What's the correlation? What do you observe? What do you think about accuracy measures for tests now?

Hint: create two arrays `X,Y` (python lists) of the same length containing the X values in one array and the Y values in another. List comprehensions are the best way to do this in python, though a for loop is fine too (append to an initially empty list)

then use `plt.plot(X,Y)`


```python
from matplotlib import pyplot as plt
```


```python
# code here. add as needed
```

## Exercise 4: Beautify plots

Now go back and beautify your plots. Add a title and a legend. Some axis labels. Maybe read about matplotlib styles and change up the colors. Try a different type of  plot. Just experiment some. Results below.


```python

```
