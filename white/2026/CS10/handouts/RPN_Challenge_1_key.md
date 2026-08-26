---
title: "RPN Challenge 1 - Answer Key"
layout: single
classes:
  - wide
---

# RPN Challenge 1 - Answer Key

## Problem 1

The largest expression is:

`2 3 4 5 ^ ^ ^`

This evaluates as $2^{(3^{(4^5)})}$. This seems right, but is it a proof? No. Let's consider one other possibly big number: `3 2 4 5 ^ ^ ^`

To compare it with `3 2 4 5 ^ ^ ^`, we will use logarithms. Let

$$
A = 2^{(3^{4^5})}, \qquad B = 3^{(2^{4^5})}.
$$

Both numbers are greater than $1$, so logarithms preserve their order.

Take $\ln$ of $A$:

$$
\ln A = \ln\left(2^{(3^{4^5})}\right) = 3^{4^5}\ln 2.
$$

Now take $\ln$ of that result:

$$
\ln(\ln A) = \ln\left(3^{4^5}\ln 2\right)
= \ln\left(3^{4^5}\right) + \ln(\ln 2)
= 4^5\ln 3 + \ln(\ln 2).
$$

The same two steps applied to $B$ give:

$$
\ln(\ln B) = 4^5\ln 2 + \ln(\ln 3).
$$

Now compute decimal values. We can ignore $\ln \ln 2$ and $\ln \ln 3$ because they are tiny. So we're just comparing $4^5 \ln 2$ against $4^5 \ln 3$. Obviously $\ln 3> \ln 2$:
$$
\ln(\ln A) \approx 1024(1.098612)  \approx 1124.97,
$$

$$
\ln(\ln B) \approx 1024(0.693147) \approx 709.78.
$$

Since $1124.97 > 709.78$, we have $\ln(\ln A) > \ln(\ln B)$, and since $\ln$ is
increasing, this means $A > B$:

`2 3 4 5 ^ ^ ^ > 3 2 4 5 ^ ^ ^`

## Problem 2

The smallest expression is:

`2 3 4 5 ^ ^ -`

This evaluates as $2 - 3^{(4^5)}$.

## Problem 3

The closest expression to $e$ is:

`2 5 3 4 + / +`

This evaluates as $2 + 5/7 = 19/7 \approx 2.7142857$.

## Problem 4

Using each number `2, 3, 4, 5` exactly once, and allowing the numbers to be reordered, the following RPN expressions construct each target value. The operators are `+`, `-`, `*`, `/`, and `^`.

1. `2 3 + 4 - 5 ^`
2. `5 2 3 - 4 + -`
3. `5 2 3 * 4 - -`
4. `2 3 + 4 + 5 -`
5. `2 3 + 4 - 5 *`
6. `2 3 + 4 - 5 +`
7. `2 3 * 4 - 5 +`
8. `2 3 - 4 + 5 +`
9. `5 2 3 - 4 * -`
10. `5 2 3 - 4 - -`
11. `4 2 3 / / 5 +`
12. `2 4 + 5 3 - *`
13. `3 2 4 + * 5 -`
14. `2 3 + 4 + 5 +`
15. `2 3 + 4 * 5 -`
16. `3 2 4 * + 5 +`
17. `2 3 ^ 4 + 5 +`
18. `3 2 ^ 4 + 5 +`
19. `2 3 * 4 * 5 -`
20. `5 4 2 3 - ^ /`
21. `3 4 + 5 2 - *`
22. `3 4 * 2 5 * +`
23. `3 2 4 + * 5 +`
24. `3 2 4 ^ + 5 +`
25. `2 3 + 4 * 5 +`
26. `5 2 ^ 3 4 - -`
27. `2 3 ^ 4 * 5 -`
28. `4 2 5 * 3 - *`
29. `2 3 * 4 * 5 +`
30. `5 2 3 / 4 / /`
31. `2 3 ^ 4 * 5 -`
32. `2 3 * 4 - 5 ^`
33. `2 5 ^ 3 4 - -`
34. `4 2 3 * 5 * +`
35. `3 2 4 - 5 ^ -`
36. `3 4 * 5 2 - *`
37. `2 3 ^ 4 * 5 +`
38. `3 4 ^ 5 - 2 /`
39. `3 4 + 2 5 ^ +`
40. `4 2 - 3 ^ 5 *`
41. `3 2 ^ 4 * 5 +`
42. `3 4 2 5 * + *`
43. `3 2 4 ^ * 5 -`
44. `3 4 + 2 ^ 5 -`
45. `2 3 + 4 + 5 *`
46. `2 3 4 5 * + *`
47. **Impossible**
48. `2 4 + 3 5 + *`
49. `3 4 + 2 5 + *`
