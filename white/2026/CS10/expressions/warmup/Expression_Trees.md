---
title: "Expression Trees"
layout: single
classes:
  - wide
---

Expression trees show the structure of an arithmetic expression. Operators are
internal nodes, and numbers are leaves.

## Examples

The following trees are shown with their evaluations.

![Five example expression trees]({{ '/white/2026/CS10/expressions/warmup/examples_row.svg' | relative_url }})

## Evaluate the Trees

Evaluate each tree. The answer below each tree is the value of the entire
expression.

![Four expression trees to evaluate]({{ '/white/2026/CS10/expressions/warmup/exercises_row.svg' | relative_url }})

{% capture question1 %}
What are the values of the four trees, from left to right?
{% endcapture %}
{% capture answer1 %}
**13, 8, 14, 38**
{% endcapture %}
{% include answer-box.html question=question1 content=answer1 %}

## Write the Postfix Expressions

Write a post-fix (RPN) expression for each of the four trees above.

{% capture question2 %}
Write the four post-fix expressions in left-to-right order.
{% endcapture %}
{% capture answer2 %}
1. `7 2 * 5 3 - / 6 +`
2. `20 4 / 3 * 8 2 - 1 + -`
3. `60 5 / 4 - 2 / 3 + 2 *`
4. `12 2 / 3 + 4 * 20 5 / 2 - +`
{% endcapture %}
{% include answer-box.html question=question2 content=answer2 %}

## Write the Infix Expressions

Write an infix (normal math) expression for each of the same four trees.

{% capture question3 %}
Write the four infix expressions in left-to-right order. Include parentheses
where they make the tree structure clear.
{% endcapture %}
{% capture answer3 %}
1. `(7 * 2) / (5 - 3) + 6`
2. `(20 / 4 * 3) - ((8 - 2) + 1)`
3. `((60 / 5 - 4) / 2 + 3) * 2`
4. `(12 / 2 + 3) * 4 + (20 / 5 - 2)`
{% endcapture %}
{% include answer-box.html question=question3 content=answer3 %}

## Draw the Expression Trees

Draw the tree corresponding to each RPN expression. Work from left to right,
making each operator the parent of the two values immediately below it.

### 1. `1 2 3 4 + - *`

{% capture question4 %}
Draw the expression tree for `1 2 3 4 + - *`.
{% endcapture %}
{% capture answer4 %}
The answer is shown in the left half of the graphic below.
{% endcapture %}
{% include answer-box.html question=question4 content=answer4 %}

### 2. `1 2 + 3 4 - 5 6 * * /`

{% capture question5 %}
Draw the expression tree for `1 2 + 3 4 - 5 6 * * /`.
{% endcapture %}
{% capture answer5 %}
The answer is shown in the right half of the graphic below.
{% endcapture %}
{% include answer-box.html question=question5 content=answer5 %}

![Answer trees for the two drawing problems]({{ '/white/2026/CS10/expressions/warmup/tree_questions_key.svg' | relative_url }})