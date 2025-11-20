# Binary Trees Lab

**Goal** Analyze running time of "insert" and "find" operations in binary trees of various sizes.

**Method** Write java methods to create lists of various sizes and structures. Insert these lists into binary trees and time the result.

## One: Sorted List

Write a method that takes a list of integers sorted (1,...,n) and inserts them in order into a binary tree (using your Binary tree class). Record the total amount of time taken (using System.nanoTime())
for each list size and write the result to stdout, or a file. You can create a comma separated list like this and import the data straight into a spreadsheet as a comma separated file, or paste it and choose "convert
text to columns" (or something like that)

size,time
100,1.2
300,2.4
500,6.7
1000,9.2

Choose a range of sizes from 100 to 1,000,000 (or whatever size you can compute on your laptop). Make sure you have at least 10 datapoints.

Once you have your data, plot a best fit line. Is the growth rate linear? If not, make a new table using size squared (n^2) vs time. Is this plot linear? It could also be (n log n) so you could
do a best fit line with (size * log(size)) vs time. Which has the best correlation?

## Two: Reverse sorted list

Do the same as above but reverse the list. What do you notice? Perform a data analysis

## Random list

Make a list of n random numbers. To avoid repeats you'll want to choose numbers with a large maximum size (read about java nextInt). Perform the same analysis as before

## Optimal list

This one is trickier. Can you make a list that is "optimal". We talked about the best order for a list in class. Can you write an algorithm to create a list in precisely the best order and
analyze it. How does it compare?

## Turn in

When you are finished, create a brief report with the 4 list types. Discuss the best fit line. Is it linear, quadratic, n log n, or something else? Include graphs.
