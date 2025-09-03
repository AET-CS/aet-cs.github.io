# Bisection Search



```python
min = 1
max = 2

def f(x):
    return x^3 - x^2 - x - 1

while(max > min):
    x = (min + max) / 2
    if f(x) > 0:
        min = x
    else:
        max = x
```