# Linear Regression

Given $n$ points $\left(x_{1}, y_{1}\right) \ldots\left(x_{n}, y_{n}\right)$
and an assumed relation $y=f(x)+\epsilon, \epsilon \sim N(\mu, \sigma)$
we want to find a model $\tilde{y_i}=a x_i+b$
such that the residual squared error

$$
\operatorname{RSS}(a, b)=\sum_{i=1}^n \left(\tilde{y}_{i}-y_{i}\right)^{2}
$$

is minimized.

$RSS$ is a function of the line parameters $a$ and $b$. To minimize
it we set both partial derivatives to zero. (This could technically
find a maximum -- but it's reasonably clear this function has no maximum value because the error can always be increased.)

Take partial derivatives

$$
\begin{aligned}
\frac{\partial RSS}{\partial a} & =2 \sum\left(\tilde{y}_{c}-y_{i}\right) \frac{\partial}{\partial a}\left(\tilde{y}_{i}-y_{i}\right) \\
& =2 \sum\left(\tilde{y}_{i}-y_{i}\right)\left(x_{i}\right) \\
\frac{\partial R S S}{\partial b} & =2 \sum\left(\tilde{y}_{i}-y_{i}\right)\frac{\partial}{\partial b}\left(\tilde{y}_{i}-y_{i}\right) \\
& =2 \sum\left(\tilde{y}_{i}-y_{i}\right)(1)
\end{aligned}
$$

Since

$$\frac{\partial}{\partial a} \tilde{y}_{i}=\frac{\partial}{\partial a}\left(a x_{i}+b\right)=x_{i}$$

$$
\frac{\partial}{\partial b} \tilde{y}_{c}=\frac{\partial}{\partial b}\left(a x_{c}+b\right)=1
$$

And solve

$$\left\{\begin{array}{l}\dfrac{\partial R S S}{\partial a}=0 \\[20pt] \dfrac{\partial R S S}{\partial b}=0\end{array} \Rightarrow\left\{\begin{array}{l}\left.\sum (\tilde{y}_{i}-y_{i}\right) x_{i}=0 \\[10pt] \sum\left(\tilde{y}_{i}-y_{i}\right)=0\end{array}\right.\right.$$

Since $\tilde{y}_{i} = ax_i+b$
$$\sum\left(a x_{i}+b-y_{i}\right) x_{i}=0 \Rightarrow a \sum x_{i}^{2}+b \sum x_{i}=\sum y_{i} x_{j}$$
and
$$\sum\left(a x_{i}+b-{y}_{i}\right)=0 \Rightarrow a \sum x_{i}+b \sum 1=\sum y_{i}$$

by Cramer's rule

$$
\begin{aligned}
a & =\left|\begin{array}{ll}
\sum x_{i} y_{i} & \sum x_{i} \\
\sum y_{i} & n
\end{array}\right| /\left|\begin{array}{ll}
\sum x_{i}^{2} & \sum x_{i} \\
\sum x_{i} & n
\end{array}\right| \\
b & =\left|\begin{array}{ll}
\sum x_{i}^{2} & \sum x_{i} y_{i} \\
\sum x_{i} & \sum y_{i}
\end{array}\right| /\left|\begin{array}{ll}
\sum x_{i}^{2} & \sum x_{i} \\
\sum x_{i} & n
\end{array}\right|
\end{aligned}
$$

since $\sum_{i=1}^{n} 1=n$

Taking determinants,
$$a=\frac{n \sum x_{i} y_{i}-\left(\sum x_{i}\right)\left(\sum y_{i}\right)}{n \sum x_{i}^{2}-\left(\sum x_{i}\right)^{2}}$$

$$
b=\frac{\sum y_{i} \sum x_{i}^{2}-\sum x_{i} \sum x_{i} y_{i}}{n \sum x_{i}^{2}-\left(\sum x_{i}\right)^{2}}
$$

## Interpretation as a ratio of variances

Students of statistics may appreciate the following manipulations

*Definition* of covariance
$$E(x y)-E(x) E(y)=\operatorname{Cov}(x, y)$$

*Definition* of variance

$$\operatorname{Var}(x)=E\left[(x-\mu)^{2}\right]$$

*Lemma*

$$
\begin{aligned}
\operatorname{Var}(x)&=E\left[(x-\mu)^{2}\right] \\
&=E\left(x^{2}\right)-2 \mu E[x]+E[\mu]^{2} \\
&=E\left[x^{2}\right]-2 E[x]^{2}+\mu^{2} \\
&=E\left[x^{2}\right]-E[x]^{2}
\end{aligned}
$$


Manipulating the denominator of the equation for $a$ on the previous page,
$$
\begin{aligned}
n \sum x_{i}^{2}-\left(\sum x_{i}\right)^{2} & =n^{2}\left(\frac{1}{n} \sum x_{i}^{2}-\left(\frac{\sum x_{i}}{n}\right)^{2}\right) \\
& =n^{2}\left(E\left[x^{2}\right]-E[x]^{2}\right) \\
& =n^{2} \operatorname{Var}(x)
\end{aligned}
$$

And the numerator
$$
\begin{aligned}
n \sum x_{i} y_{i}-\sum x_{i} \sum y_{i} \\
& =n^{2}\left(\frac{1}{n} \sum x_{i} y_{i}-\frac{1}{n} \sum x_{i} \cdot\frac1n \sum y_{i}\right) \\
& =n^{2}\left(E\left[x y\right]-E[x] E[y]\right) \\
& =n^{2}\left(E[x y]-\mu_{y} \mu_{y}\right) \\
& = n^2 \operatorname{Cov}(x,y)
\end{aligned}
$$

so

$$a=\frac{E[x y]-\mu_{x} \mu_{y}}{E[x]-\mu_{x}^{2}}=\frac{\operatorname{Cov}(x, y)}{\operatorname{Vav}(x)}$$
