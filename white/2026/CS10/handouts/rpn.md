---
title: "Reverse Polish Notation"
layout: single
excerpt: "Evaluating expressions with a stack"
classes:
  - wide
---

A calculator has to read `3 + 4 * 2` and decide what happens first. That takes
precedence rules, and parentheses to override them, and a parser that knows both.

There's another way to write the same expression that needs none of that. Put each
operator *after* its two operands:

```
3 4 2 * +
```

No precedence. No parentheses. Ever. The order you read it in *is* the order it
happens in, and one simple machine can evaluate anything written this way.

That machine is a stack.

## The rules

Read left to right, one token at a time:

- **A number?** Push it onto the stack.
- **An operator?** Pop two values, apply the operator, push the result.

When you run out of tokens, the answer is the single value left on the stack.

That's the whole algorithm. Two rules.

## Try it

Type an expression and step through it. Watch what the stack holds after each token —
the presets at the bottom are the problems from the worksheet.

<iframe src="./rpn_stack.html" width="100%" height="720"
        style="border:1px solid #d0e3f8; border-radius:4px"
        title="RPN stack machine"></iframe>

Two things worth noticing while you play:

**Operators always take the top two values**, in the order they're sitting there. The
second-from-top is the left operand. For `+` and `*` that doesn't matter. For `-` and
`/` it decides everything.

**Watch how deep the stack gets.** Some expressions never hold more than two values.
Others hold seven. That depth is a real cost — it's memory the calculator has to have.

## Evaluate these

Work them out on paper first, then check with the machine above.

{% capture question1 %}
`3 7 +`
{% endcapture %}
{% capture answer1 %}
**10**
{% endcapture %}
{% include answer-box.html question=question1 content=answer1 %}

{% capture question2 %}
`12 4 -`
{% endcapture %}
{% capture answer2 %}
**8**

Order matters here. The stack holds 12 then 4, so `-` computes 12 − 4, not 4 − 12.
{% endcapture %}
{% include answer-box.html question=question2 content=answer2 %}

{% capture question3 %}
`10 3 2 + +`
{% endcapture %}
{% capture answer3 %}
**15**

Both operators come at the end, so all three numbers are on the stack before any
arithmetic happens. Stack depth reaches 3.
{% endcapture %}
{% include answer-box.html question=question3 content=answer3 %}

{% capture question4 %}
`10 2 / 5 2 + +`
{% endcapture %}
{% capture answer4 %}
**12**

The stack goes: `10` → `10, 2` → `5` → `5, 5` → `5, 5, 2` → `5, 7` → `12`.

Notice the 5 from `10 2 /` just sits at the bottom, waiting, while `5 2 +` is
computed on top of it.
{% endcapture %}
{% include answer-box.html question=question4 content=answer4 %}

{% capture question5 %}
`9 8 7 6 5 4 3 - - - - - -`

Before you compute it: how deep does the stack get?
{% endcapture %}
{% capture answer5 %}
**6**, and the stack gets **7 deep** — every operand is pushed before a single
operator runs.

It unwinds from the inside out: 9 − (8 − (7 − (6 − (5 − (4 − 3))))).

In algebraic notation you'd need six sets of parentheses to say that. Here you need
none.
{% endcapture %}
{% include answer-box.html question=question5 content=answer5 %}

{% capture question6 %}
`12 4 2 + / 8 * 10 - 3 2 + /`
{% endcapture %}
{% capture answer6 %}
**6/5**, or **1.2**.

Every step before the last one lands on a whole number: `4+2` is 6, `12/6` is 2,
`2*8` is 16, `16-10` is 6, `3+2` is 5. Only the final division doesn't divide evenly.

Now the interesting part. If you wrote this in Java with `int` variables, `6/5` is
**1** — the fraction is thrown away. With `double` variables it's **1.2**.

Look at the expression. **Nowhere in it does it say which one you want.** The notation
says *divide* and stops there. The types decide the rest. Try the division dropdown in
the machine above and watch the last step change.
{% endcapture %}
{% include answer-box.html question=question6 content=answer6 %}

## Read these as algebra

The stack doesn't care whether it's holding numbers. Same two rules.

{% capture question7 %}
`3 x x * *`
{% endcapture %}
{% capture answer7 %}
**3x²**

`x x *` builds x · x first, then the 3 waiting underneath multiplies into it.
{% endcapture %}
{% include answer-box.html question=question7 content=answer7 %}

{% capture question8 %}
`4 x x * * 3 x * + 1 -`
{% endcapture %}
{% capture answer8 %}
**4x² + 3x − 1**
{% endcapture %}
{% include answer-box.html question=question8 content=answer8 %}

{% capture question9 %}
`12 x * 3 y * + 5 x * 2 y * - /`
{% endcapture %}
{% capture answer9 %}
**(12x + 3y) / (5x − 2y)**

Watch this one in the machine. The entire numerator is built and then parked at the
bottom of the stack while the whole denominator gets assembled on top of it. The `/`
doesn't fire until both halves are finished.

The parentheses in the answer aren't in the RPN anywhere — they only appear when you
translate *back* into algebra.
{% endcapture %}
{% include answer-box.html question=question9 content=answer9 %}

## Now go the other way

Convert each of these to RPN. Check your answer by pasting it into the machine.

{% capture question10 %}
`(3 + 4) * (2 + 3)`
{% endcapture %}
{% capture answer10 %}
`3 4 + 2 3 + *` → 35
{% endcapture %}
{% include answer-box.html question=question10 content=answer10 %}

{% capture question11 %}
`1 + 2 + 3 + 4`
{% endcapture %}
{% capture answer11 %}
`1 2 + 3 + 4 +` → 10

`1 2 + 3 4 + +` also works and also gives 10. Addition doesn't care how you group it,
so both are fine. Compare that to the next problem.
{% endcapture %}
{% include answer-box.html question=question11 content=answer11 %}

{% capture question12 %}
`5 - 4 - 3 - 2`
{% endcapture %}
{% capture answer12 %}
`5 4 - 3 - 2 -` → −4

Only one right answer this time. Subtraction groups left to right, so this means
((5 − 4) − 3) − 2.

If you wrote `5 4 - 3 2 - -` you got 0 instead, because that means (5 − 4) − (3 − 2).
Both are valid RPN. Only one is the expression we asked for.
{% endcapture %}
{% include answer-box.html question=question12 content=answer12 %}

{% capture question13 %}
`1 / (1 + 2)`
{% endcapture %}
{% capture answer13 %}
`1 1 2 + /` → 1/3

The first `1` gets pushed and then waits through the entire `1 2 +` before the
division happens.
{% endcapture %}
{% include answer-box.html question=question13 content=answer13 %}

{% capture question14 %}
`1 / (1 + (1 / (1 + 1 / (1 + 1))))`
{% endcapture %}
{% capture answer14 %}
`1 1 1 1 1 1 1 + / + / + /` → 3/5

Build it from the inside out:

| piece | RPN |
|---|---|
| `1 + 1` | `1 1 +` |
| `1 / (1+1)` | `1 1 1 + /` |
| `1 + 1/(1+1)` | `1 1 1 1 + / +` |
| `1 / (1 + 1/(1+1))` | `1 1 1 1 1 + / + /` |
| `1 + (that)` | `1 1 1 1 1 1 + / + / +` |
| `1 / (that)` | `1 1 1 1 1 1 1 + / + / + /` |

Seven 1s and six operators — which is exactly the check below.
{% endcapture %}
{% include answer-box.html question=question14 content=answer14 %}

## One check that catches most mistakes

Count. An RPN expression with **n** operands needs exactly **n − 1** operators.

Seven numbers, six operators. Three numbers, two operators. Always.

So look at this one:

```
5 6 * 2 3 +
```

Four numbers, two operators. It needs three. Run it in the machine and you'll see what
goes wrong — it finishes reading with **30 and 5 both still on the stack** and no
operator left to combine them. That isn't a complete expression, it's two expressions
sitting next to each other.

A finished RPN expression leaves exactly one value on the stack. Not two, not zero.

There's a second half to the rule: reading left to right, you must never hit an
operator when there are fewer than two values available. `1 2 + +` passes the count
test in the wrong direction and still fails — the second `+` has only one value to
work with.

## Why this matters

You just hand-executed a **stack machine**. Push a value, pop a value, and the top of
the stack is the only place you can reach.

This isn't a curiosity. It's how the Java Virtual Machine actually runs your code.
When you compile `int x = 3 + 4 * 2;`, javac converts it to something very close to
`3 4 2 * +` and the JVM evaluates it by pushing and popping on a stack, exactly the way
you did on paper.

The stack is also how Java keeps track of method calls — which is where we're headed
next.
