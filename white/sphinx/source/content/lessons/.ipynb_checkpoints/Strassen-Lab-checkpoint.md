# Strassen's Algorithm (Optional Extension)

Matrix multiplication in the naive implementation requires $O(n^3)$ floating point
operations on a matrix with $n^2$ entries. In fact it seems unlikely one could do better.
Each of the $n^2$ entries in the product requires a dot product of two length $n$ vectors.
Yet in 1969, Volken Strassen shocked numerical analysists with his algorithm requiring
$O(n^{\log_2{7}} \approx O(n^2.73$ floating point operations. The

*Goal*: You will implement Strassen's algorithm using python and numpy. You will then
perform tests to verify the achieved asymptotic running time of Strassen is actually lower than
the running time of your previously implemented (non-numpy) matrix multiplication.

