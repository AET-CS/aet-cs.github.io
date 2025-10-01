### **Plain English Rules for an Infix Expression (With Parentheses)**

Here is a step-by-step guide for processing an expression.

**Start Here: Initialize**

1. Create an empty operandStack for numbers.
2. Create an empty operatorStack for operators.
3. Read the expression tokens one by one. For each token, follow the rules below.

#### **Rule 1: If the token is an OPERAND (a number)...**

* Push it directly onto the operandStack.

#### **Rule 2: If the token is an OPERATOR (+, \-, \*, /)...**

1. Call your reduce(operator) method.
   * **Inside reduce**, a WHILE loop should run as long as:
     1. The operatorStack is not empty, AND
     2. The operator on top of the stack is NOT an opening parenthesis (, AND
     3. The operator on top of the operatorStack has a precedence *greater than or equal to* the current operator's precedence.
   * **Inside this WHILE loop**, you should call a method like calculate() which performs one operation: it pops two operands and one operator, does the math, and pushes the result back onto the operandStack.
2. After the reduce method is finished, push the current operator onto the operatorStack.

#### **Rule 3: If the token is an OPENING PARENTHESIS (...**

* Push it directly onto the operatorStack.

#### **Rule 4: If the token is a CLOSING PARENTHESIS )...**

1. **WHILE** the operator at the top of the operatorStack is NOT an opening parenthesis (:
   * Call your calculate() method.
2. After the loop finishes, pop the ( from the operatorStack and discard it.

#### **Rule 5: After you've read all the tokens... (Cleanup)**

This phase is typically triggered when the user presses the "equals" button.

1. **WHILE** the operatorStack is not empty:
   * Repeatedly call your calculate() method.
2. The final answer is the single number left in the operandStack.