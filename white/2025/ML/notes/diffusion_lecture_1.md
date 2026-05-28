---
title: "Diffusion Models — Lecture 1"
subtitle: "Forward noising and the reverse expectation, in the simplest possible setting"
---

# 1. The big picture

Generative models learn to turn noise into data. We have already seen variational
autoencoders (VAEs), which **compress** an input down to a small latent code and then
**decode** that code back into a sample. Diffusion models take a very different route.

A diffusion model does two things:

- **Forward process.** Start with a real data point $x_0$ (think: a photograph). Add a
  tiny bit of Gaussian noise. Repeat about a thousand times. After enough steps the data
  is indistinguishable from pure random noise — every trace of the original is gone.
- **Reverse process.** Learn to *undo* one small noising step at a time. If we can reverse
  a single step, we can chain a thousand reversals together: start from pure noise and walk
  backwards, one small denoising step at a time, until a brand-new data point appears.

The key difference from a VAE is that the forward process is **fixed and requires no
learning** — it is just "add a little noise." All the learning happens in the reverse
direction, and because each forward step is *small*, each reverse step turns out to be
easy to approximate.

The whole course will build up to images. But everything important about diffusion can be
understood in a setting with no images at all — a single number that is either $+1$ or
$-1$. That is where we start.

---

# 2. Start out walking randomly

Let $X_0$ be our data. For now it takes only two values:

$$X_0 = +1 \text{ or } -1, \qquad P(X_0 = +1) = P(X_0 = -1) = \tfrac{1}{2}.$$

**Question.** If I show you $X_0$, can you tell me $X_0$?

Obviously yes — you are looking right at it. There is no noise, no ambiguity, nothing to
infer. This is the trivial baseline. The moment we add noise, the question stops being
trivial, and that question is the entire subject of the course.

---

# 3. One step of *discrete* noise

Let $Y_0$ be a noise variable, also $\pm 1$ with probability $\tfrac{1}{2}$ each,
independent of $X_0$. Define the first noised value:

$$X_1 = X_0 + Y_0.$$

Since each of $X_0, Y_0$ is $\pm 1$, the sum $X_1$ can be $-2$, $0$, or $+2$:

| $X_0$ | $Y_0$ | $X_1$ | probability |
|:-----:|:-----:|:-----:|:-----------:|
| $+1$  | $+1$  | $+2$  | $1/4$ |
| $+1$  | $-1$  | $0$   | $1/4$ |
| $-1$  | $+1$  | $0$   | $1/4$ |
| $-1$  | $-1$  | $-2$  | $1/4$ |

So $X_1 \in \{-2, 0, +2\}$ with probabilities $\{\tfrac14, \tfrac12, \tfrac14\}$.

**Question.** Given $X_1$, can you recover $X_0$?

- If $X_1 = +2$: the only way is $X_0 = +1, Y_0 = +1$. So $X_0 = +1$ for certain.
- If $X_1 = -2$: similarly $X_0 = -1$ for certain.
- If $X_1 = 0$: it could be $(X_0=+1, Y_0=-1)$ **or** $(X_0=-1, Y_0=+1)$, each equally
  likely. **You cannot tell.**

This is the heart of the matter. Adding noise is a *many-to-one* operation: different
$(X_0, Y_0)$ pairs collapse to the same $X_1$. Once that happens you can no longer invert
it with certainty.

## The best you can do: the conditional expectation

If you cannot recover $X_0$ exactly, the next best thing is the *average* value of $X_0$
over all the ways it could have produced the $X_1$ you observed. That is the conditional
expectation $\mathbb{E}[X_0 \mid X_1]$:

$$\mathbb{E}[X_0 \mid X_1 = +2] = +1, \qquad
  \mathbb{E}[X_0 \mid X_1 = 0] = 0, \qquad
  \mathbb{E}[X_0 \mid X_1 = -2] = -1.$$

Notice these three values are exactly $X_1 / 2$. On its support,

$$\boxed{\;\mathbb{E}[X_0 \mid X_1] = \tfrac{1}{2}\, X_1.\;}$$

If you plot $\mathbb{E}[X_0 \mid X_1]$ against $X_1$, you get three points on a line of
slope $\tfrac12$ through the origin: $(-2, -1), (0, 0), (+2, +1)$.

---

# 4. Two steps — and a pattern emerges

Now add another independent $\pm 1$ noise $Y_1$ and take a second step:

$$X_2 = X_1 + Y_1 = X_0 + Y_0 + Y_1.$$

$X_2$ is now a sum of **three** independent $\pm1$ variables, so it ranges over
$\{-3, -1, +1, +3\}$. Working out the conditional expectations (try it as an exercise,
or count the cases) gives:

$$\mathbb{E}[X_0 \mid X_2 = \pm 3] = \pm 1, \qquad
  \mathbb{E}[X_0 \mid X_2 = \pm 1] = \pm \tfrac{1}{3}.$$

These four values are exactly $X_2 / 3$. So now the slope is $\tfrac13$:

$$\mathbb{E}[X_0 \mid X_2] = \tfrac{1}{3}\, X_2.$$

## Asisde: Why is the slope is $1/(t+1)$?

Here is the elegant reason. After $t$ steps,

$$X_t = \underbrace{X_0 + Y_0 + Y_1 + \cdots + Y_{t-1}}_{t+1 \text{ independent } \pm1 \text{ variables}}.$$

All $t+1$ of these variables have the *same* distribution and are independent — they are
**exchangeable**. So given their sum, no one of them is special: each has the same
conditional expectation. Since there are $t+1$ of them and they must add up to $X_t$,

$$(t+1)\,\mathbb{E}[X_0 \mid X_t] = \mathbb{E}\!\left[\textstyle\sum (\cdots) \,\middle|\, X_t\right] = X_t
\quad\Longrightarrow\quad
\boxed{\;\mathbb{E}[X_0 \mid X_t] = \frac{X_t}{t+1}.\;}$$

**The flattening.** As $t$ grows, the slope $1/(t+1)$ shrinks toward zero. The line of best
guesses gets flatter and flatter, until in the limit $\mathbb{E}[X_0 \mid X_t] \to 0$ — the
prior mean — for every observed value. In words: *after enough noising, the observation
tells you nothing about the original, and your best guess collapses to the average over
the prior.* That is exactly the "signal is destroyed" endpoint of the forward process.

---

# 5. Level up: switching to *Gaussian* noise

Real diffusion models use continuous Gaussian noise, not $\pm1$ steps. So let us redo the
single-step analysis with

$$Y \sim \mathcal{N}(0, \sigma^2), \qquad X_1 = X_0 + Y.$$

Recall that the Gaussian pdf with mean $\mu$ and variance $\sigma^2$ is

$$\mathcal{N}(x;\,\mu,\sigma^2) = \frac{1}{\sigma\sqrt{2\pi}}\,e^{-(x-\mu)^2/(2\sigma^2)}.$$

Conditioned on $X_0$, the noised value $X_1$ is just $X_0$ shifted by a Gaussian, so

$$X_1 \mid X_0 \sim \mathcal{N}(X_0,\; \sigma^2).$$

The unconditional distribution of $X_1$ is a 50/50 blend of the two cases $$X_0 = \pm 1$$
a **mixture of two Gaussians**, one centered at $+1$ and one at $-1$:

$$f_{X_1}(x) = \tfrac{1}{2}\,\mathcal{N}(x;\, +1, \sigma^2)
            + \tfrac{1}{2}\,\mathcal{N}(x;\, -1, \sigma^2).$$

When $\sigma$ is small the two bumps are clearly separated; when $\sigma$ is large they
merge into a single hump and the origin of any sample becomes ambiguous — exactly the
continuous analogue of the discrete picture above.

---

# 6. Posterior probability that $X_0 = +1$

The **posterior probability** means: after observing $X_1 = x$, how likely is each possible
original value of $X_0$?

Given an observed $X_1 = x$, what is the probability $X_0$ was $+1$? Apply Bayes' rule:

$$P(A\mid B) = \frac{P(B\mid A)P(A)}{P(B)}.$$

Here $A$ is the event $X_0 = +1$ and $B$ is the event $X_1 = x$, so

$$P(X_0 = +1 \mid X_1 = x)
= \frac{P(X_1 = x \mid X_0 = +1)P(X_0 = +1)}{P(X_1 = x)}.$$

Now expand the denominator using the law of total probability. Since $X_0$ can only be
$+1$ or $-1$,

$$P(X_1 = x)
= P(X_1 = x \mid X_0 = +1)P(X_0 = +1)
 + P(X_1 = x \mid X_0 = -1)P(X_0 = -1).$$

Substituting this into Bayes' rule gives

$$P(X_0 = +1 \mid X_1 = x)
= \frac{P(X_1 = x \mid X_0 = +1)P(X_0 = +1)}
  {P(X_1 = x \mid X_0 = +1)P(X_0 = +1) + P(X_1 = x \mid X_0 = -1)P(X_0 = -1)}.$$

Because $X_1\mid( X_0=+1 )$ is normal  $\sim \mathcal{N}(1,\sigma^2)$ and
$X_1\mid (X_0=-1)$ is also normal $\sim \mathcal{N}(-1,\sigma^2)$, *and* because $P(X_0 = \pm1) = \frac12$, this becomes

$$P(X_0 = +1 \mid X_1 = x)
= \frac{\tfrac12\,\mathcal{N}(x;\,1,\sigma^2)}
  {\tfrac12\,\mathcal{N}(x;\,1,\sigma^2) + \tfrac12\,\mathcal{N}(x;\,-1,\sigma^2)}
= \frac{\mathcal{N}(x;\,1,\sigma^2)}
       {\mathcal{N}(x;\,1,\sigma^2) + \mathcal{N}(x;\,-1,\sigma^2)}.$$

Writing out the Gaussian densities explicitly gives

$$P(X_0 = +1 \mid X_1 = x)
= \frac{e^{-(x-1)^2/(2\sigma^2)}}{e^{-(x-1)^2/(2\sigma^2)} + e^{-(x+1)^2/(2\sigma^2)}}.$$

---

# 7. Posterior probability that $X_0 = -1$

This is a simple modification of the previous derivation.

$$P(X_0 = -1 \mid X_1 = x) = 1 - P(X_0 = +1 \mid X_1 = x)
  = \frac{\mathcal{N}(x;\,-1,\sigma^2)}
         {\mathcal{N}(x;\,1,\sigma^2) + \mathcal{N}(x;\,-1,\sigma^2)}.$$

Writing out the Gaussian densities explicitly gives

$$P(X_0 = -1 \mid X_1 = x)
= \frac{e^{-(x+1)^2/(2\sigma^2)}}{e^{-(x-1)^2/(2\sigma^2)} + e^{-(x+1)^2/(2\sigma^2)}}.$$

---

# 8. The reverse expectation is a $\tanh$

Now combine the two posteriors into the conditional expectation. Since $X_0$ takes only the
values $\pm 1$:

$$\mathbb{E}[X_0 \mid X_1 = x]
= (+1)\,P(X_0{=}{+}1\mid x) + (-1)\,P(X_0{=}{-}1\mid x)
= P(X_0{=}{+}1\mid x) - P(X_0{=}{-}1\mid x).$$

Substitute the formulas from sections 6 and 7:

$$\mathbb{E}[X_0 \mid X_1 = x]
= \frac{e^{-(x-1)^2/(2\sigma^2)}}{e^{-(x-1)^2/(2\sigma^2)} + e^{-(x+1)^2/(2\sigma^2)}}
 - \frac{e^{-(x+1)^2/(2\sigma^2)}}{e^{-(x-1)^2/(2\sigma^2)} + e^{-(x+1)^2/(2\sigma^2)}}.$$

Since the denominators are the same, combine the numerators:

$$\mathbb{E}[X_0 \mid X_1 = x]
= \frac{e^{-(x-1)^2/(2\sigma^2)} - e^{-(x+1)^2/(2\sigma^2)}}
  {e^{-(x-1)^2/(2\sigma^2)} + e^{-(x+1)^2/(2\sigma^2)}}.$$

Now divide the numerator and denominator by $e^{-(x-1)^2/(2\sigma^2)}$:

$$\mathbb{E}[X_0 \mid X_1 = x]
= \frac{1 - e^{\left(-(x+1)^2 + (x-1)^2\right)/(2\sigma^2)}}
  {1 + e^{\left(-(x+1)^2 + (x-1)^2\right)/(2\sigma^2)}}.$$

Expand the quadratics in the remaining exponent:

$$\begin{aligned}
-(x+1)^2 + (x-1)^2
&= -(x^2 + 2x + 1) + (x^2 - 2x + 1) \\
&= -4x.
\end{aligned}$$

So

$$\mathbb{E}[X_0 \mid X_1 = x]
= \frac{1 - e^{-2x/\sigma^2}}{1 + e^{-2x/\sigma^2}}.$$

Now compare with the identity

$$\tanh(z) = \frac{1 - e^{-2z}}{1 + e^{-2z}}.$$

With $z = x/\sigma^2$, this gives


$$\boxed{\;\mathbb{E}[X_0 \mid X_1 = x] = \tanh\!\left(\frac{x}{\sigma^2}\right).\;}$$

## Comparing to the discrete case

This is the continuous cousin of the result from §3. There the best guess was the **linear**
function $X_1/2$; here it is the **smooth, saturating** $\tanh(x/\sigma^2)$. Both pass
through the origin, both saturate toward $\pm 1$ for large $|x|$. The discrete version was
linear because $X_0$ and the noise had the *same* $\pm 1$ distribution (exchangeability);
the Gaussian version curves because the data is $\pm 1$ but the noise is Gaussian — they are
no longer interchangeable.

## Behaviour as $\sigma \to \infty$

For any fixed $x$, as the noise grows without bound,

$$\tanh\!\left(\frac{x}{\sigma^2}\right) \longrightarrow \tanh(0) = 0.$$

The best guess collapses to $0$, the prior mean — exactly the flattening we saw in the
discrete case. When you have added so much noise that $X_1$ carries no information about
$X_0$, the optimal estimate is just the average of the prior. Conversely, as
$\sigma \to 0$, $\tanh(x/\sigma^2) \to \mathrm{sign}(x)$ and you recover $X_0$ perfectly.

## Why this matters

We have just computed, *exactly*, the optimal reverse step for this toy problem. Notice what
it depends on: the noise level $\sigma$, the observation $x$, and the **prior** (here baked
in as the symmetric $\tfrac12/\tfrac12$). In a real problem the prior is the distribution of
natural images — which we cannot write down. That is the gap a neural network will fill: it
*learns* this reverse expectation from data, without ever being handed the prior. Holding
onto this $\tanh$ as a known ground truth will let us check, later, whether the network
learned the right thing.

---

# 9. Homework: an unequal prior

Everything above assumed $P(X_0 = +1) = P(X_0 = -1) = \tfrac12$. Redo the Gaussian
single-step analysis (sections 5–8) for a **biased** prior:

$$P(X_0 = -1) = a, \qquad P(X_0 = +1) = 1 - a.$$

**Tasks.**

1. Write down the new mixture density $f_{X_1}(x)$.
2. Recompute $P(X_0 = +1 \mid X_1 = x)$. Where does the prior enter, and what happens to the
   cancellation that worked so nicely when the priors were equal?
3. Show that the conditional expectation becomes a **shifted** $\tanh$, and identify the
   shift.
4. Interpret the shift: which way does the decision boundary (the value of $x$ where
   $\mathbb{E}[X_0 \mid X_1] = 0$) move when $a > \tfrac12$, and why does that make sense?

**Hint.** The prior no longer cancels; instead it contributes an additive constant inside
the $\tanh$. You should arrive at

$$\mathbb{E}[X_0 \mid X_1 = x]
  = \tanh\!\left(\frac{x}{\sigma^2} - \tfrac{1}{2}\log\frac{a}{1-a}\right).$$

Check that this reduces to the symmetric result when $a = \tfrac12$, and confirm the $\sigma \to \infty$ limit equals the prior mean $1 - 2a$.
