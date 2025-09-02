# Puzzles

## RPN
1. Using all the numbers `0 1 2 3 4 5 6 7 8 9 10` and all the operators `+ + + +` and `- - - - - -` exactly once, write an RPN expression with maximal value. (Example: using `1 2 3` and `+ -`, the largest expression value is `3 1 - 2 + = 4`)
2. Similar to problem #1, what is the *minimal* possible value?
3. Given a solution to #1, can you swap two numbers in your expression without changing the value? (e.g. `2 1 - 3 + = 4`)
4. Follow up to #3 -- if the answer is "yes", count how many different expressions have the same value, where the *order* of numbers and operators is the same, but the numbers' values can be rearranged. (e.g. `1 2 3 4 + + +` and `4 2 3 1 + + +` are the same)

## Binary Search
1. If you play the clock game (binary search) to guess an integer in the range [1,1023], what is the maximum number of guesses required? What are three numbers that require that many guesses?
2. Playing the clock game, over a min-max range of [1,1023], write a formula for $g(n)$ where $g(n)$ is the number of prices that require exactly $n$ guesses. *Follow-up* does the range effect the definition of $g()$?
3. (Follow up to #2) -- for the range [1,1023], what is the expected (average) number of guesses needed to win, assuming each price is equally likely?