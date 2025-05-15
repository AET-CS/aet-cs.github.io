---
title: "Calculus Notes"
---

# Introduction to Derivatives

The derivative of a function represents its rate of change at a specific point.

$$f'(x) = \lim_{h \to 0} \frac{f(x+h) - f(x)}{h}$$

## Question 1

Find the derivative of $f(x) = x^2 + 3x + 5$.

{% capture answer1 %}
To find the derivative, we use the power rule and the sum rule:

$$\begin{align*}
f'(x) &= \frac{d}{dx}(x^2 + 3x + 5) \\
&= \frac{d}{dx}(x^2) + \frac{d}{dx}(3x) + \frac{d}{dx}(5) \\
&= 2x + 3 + 0 \\
&= 2x + 3
\end{align*}$$
{% endcapture %}
{% include answer.html content=answer1 %}

## Question 2

Find the derivative of $g(x) = e^x \sin(x)$ using the product rule.

{% capture answer2 %}
Using the product rule: $(f \cdot g)' = f' \cdot g + f \cdot g'$

$$\begin{align*}
\frac{d}{dx}[e^x \sin(x)] &= \frac{d}{dx}(e^x) \cdot \sin(x) + e^x \cdot \frac{d}{dx}(\sin(x)) \\
&= e^x \cdot \sin(x) + e^x \cdot \cos(x) \\
&= e^x[\sin(x) + \cos(x)]
\end{align*}$$
{% endcapture %}
{% include answer.html content=answer2 %}