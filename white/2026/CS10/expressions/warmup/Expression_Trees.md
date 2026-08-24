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

{% capture question1 %}
![Tree 1]({{ '/white/2026/CS10/expressions/warmup/exercise_tree_1.svg' | relative_url }})
What is the evaluation of this tree?
{% endcapture %}
{% capture answer1 %}
**13**
{% endcapture %}
{% include answer-box.html question=question1 content=answer1 %}

{% capture question2 %}
![Tree 2]({{ '/white/2026/CS10/expressions/warmup/exercise_tree_2.svg' | relative_url }})
What is the evaluation of this tree?
{% endcapture %}
{% capture answer2 %}
**8**
{% endcapture %}
{% include answer-box.html question=question2 content=answer2 %}

{% capture question3 %}
![Tree 3]({{ '/white/2026/CS10/expressions/warmup/exercise_tree_3.svg' | relative_url }})
What is the evaluation of this tree?
{% endcapture %}
{% capture answer3 %}
**14**
{% endcapture %}
{% include answer-box.html question=question3 content=answer3 %}

{% capture question4 %}
![Tree 4]({{ '/white/2026/CS10/expressions/warmup/exercise_tree_4.svg' | relative_url }})
What is the evaluation of this tree?
{% endcapture %}
{% capture answer4 %}
**38**
{% endcapture %}
{% include answer-box.html question=question4 content=answer4 %}

## Write the Postfix Expressions

Write a post-fix (RPN) expression for each tree.

{% capture question5 %}
![Tree 1]( {{ '/white/2026/CS10/expressions/warmup/exercise_tree_1.svg' | relative_url }} )
Write the post-fix expression for this tree.
{% endcapture %}
{% capture answer5 %}`7 2 * 5 3 - / 6 +`{% endcapture %}
{% include answer-box.html question=question5 content=answer5 %}

{% capture question6 %}
![Tree 2]( {{ '/white/2026/CS10/expressions/warmup/exercise_tree_2.svg' | relative_url }} )
Write the post-fix expression for this tree.
{% endcapture %}
{% capture answer6 %}`20 4 / 3 * 8 2 - 1 + -`{% endcapture %}
{% include answer-box.html question=question6 content=answer6 %}

{% capture question7 %}
![Tree 3]( {{ '/white/2026/CS10/expressions/warmup/exercise_tree_3.svg' | relative_url }} )
Write the post-fix expression for this tree.
{% endcapture %}
{% capture answer7 %}`60 5 / 4 - 2 / 3 + 2 *`{% endcapture %}
{% include answer-box.html question=question7 content=answer7 %}

{% capture question8 %}
![Tree 4]( {{ '/white/2026/CS10/expressions/warmup/exercise_tree_4.svg' | relative_url }} )
Write the post-fix expression for this tree.
{% endcapture %}
{% capture answer8 %}`12 2 / 3 + 4 * 20 5 / 2 - +`{% endcapture %}
{% include answer-box.html question=question8 content=answer8 %}

## Write the Infix Expressions

Write an infix (normal math) expression for each tree. Include parentheses
where they make the tree structure clear.

{% capture question9 %}
![Tree 1]( {{ '/white/2026/CS10/expressions/warmup/exercise_tree_1.svg' | relative_url }} )
Write the infix expression for this tree.
{% endcapture %}
{% capture answer9 %}`(7 * 2) / (5 - 3) + 6`{% endcapture %}
{% include answer-box.html question=question9 content=answer9 %}

{% capture question10 %}
![Tree 2]( {{ '/white/2026/CS10/expressions/warmup/exercise_tree_2.svg' | relative_url }} )
Write the infix expression for this tree.
{% endcapture %}
{% capture answer10 %}`(20 / 4 * 3) - ((8 - 2) + 1)`{% endcapture %}
{% include answer-box.html question=question10 content=answer10 %}

{% capture question11 %}
![Tree 3]( {{ '/white/2026/CS10/expressions/warmup/exercise_tree_3.svg' | relative_url }} )
Write the infix expression for this tree.
{% endcapture %}
{% capture answer11 %}`((60 / 5 - 4) / 2 + 3) * 2`{% endcapture %}
{% include answer-box.html question=question11 content=answer11 %}

{% capture question12 %}
![Tree 4]( {{ '/white/2026/CS10/expressions/warmup/exercise_tree_4.svg' | relative_url }} )
Write the infix expression for this tree.
{% endcapture %}
{% capture answer12 %}`(12 / 2 + 3) * 4 + (20 / 5 - 2)`{% endcapture %}
{% include answer-box.html question=question12 content=answer12 %}

## Draw the Expression Trees

Draw the tree corresponding to each RPN expression. Work from left to right,
making each operator the parent of the two values immediately below it.

### 1. `1 2 3 4 + - *`

{% capture question13 %}
Draw the expression tree for `1 2 3 4 + - *`.
{% endcapture %}
{% capture answer13 %}
![Answer tree 1]({{ '/white/2026/CS10/expressions/warmup/tree_question_1.svg' | relative_url }})
{% endcapture %}
{% include answer-box.html question=question13 content=answer13 %}

### 2. `1 2 + 3 4 - 5 6 * * /`

{% capture question14 %}
Draw the expression tree for `1 2 + 3 4 - 5 6 * * /`.
{% endcapture %}
{% capture answer14 %}
![Answer tree 2]({{ '/white/2026/CS10/expressions/warmup/tree_question_2.svg' | relative_url }})
{% endcapture %}
{% include answer-box.html question=question14 content=answer14 %}