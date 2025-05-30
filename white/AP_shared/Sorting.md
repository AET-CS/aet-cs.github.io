---
title: "Sorting Assignment"
---


*Goal* You will analyze the running time of various sorting algorithms on lists on increasing size. You will chart the results.

# Steps:

1. Download and run [sample code](./SortChart.zip).
2. Inside the SortChart folder, create a new class SortChart with a `main` method.
3. Implement a method to generate a random array of integers of a given size, with each integer in a given range [min, max].
4. Implement insertionSort, selectionSort, mergeSort. These methods will take an input array and sort that array  in increasing order.
5. Write a method checkSort(a) that returns true if and only if 'a' is sorted. Use it to check your sort algorithms.
6. Graph each sort algorithm this way:
	1. For several arrays sizes (say 5000 to 100,000), make a random array of that size,
	2. sort it,
	3. and add the the time required to sort to a new arrayList.
	4. Plot that array list.
	5.  Do the same thing with each sorting algorithm. Plot all results on one chart
7. Make sure your chart has a title and labels on the axes
8. Now, for increased accuracy, do not plot the results of ONE sorting call, but average ten sorts for each list. You will need to make a new list each time (sorting a sorted list may be easier than a random list)
9. Make a new chart with the averaged results.
10. Before submitting, make sure the main method of SortChart creates your graph
11. There should be no package statement in your java files for this project.
12. Submit your project folder and your chart (graphics file)

You can find information about the sorting library at [the XChart website](https://knowm.org/open-source/xchart/)

Here's an example of timing Insertion Sort
```{java}
double tic = System.nanoTime();
insertionSort(a);
double toc = System.nanoTime()
insertionTimes.add((toc - tic)/1000000);
```

To get a more accurate result, repeat the above **each time with a new array** and average the resulting times.
