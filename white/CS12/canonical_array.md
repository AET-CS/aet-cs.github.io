# Canonical Array Algorithms

Given a 1D array of unbounded real numbers (they can be any size allowed by the machine) you
should be able to code the following

```java
public static double max_value(double[] numbers) {
	// return the maximum value in the array
}

public static double min_value(double[] numbers) {
	// return the minimum value in the array
}

public static double find_first(double[] numbers, double goal) {
	// return the index of the first occurrence of goal in the array
	// or -1 if not present
}

public static double find_last(double[] numbers, double goal) {
	// return the index of the last occurrence of goal in the array
	// or -1 if not present
}

public static double find_kth_occurrences(double[] numbers, double goal, double k) {
	// return the index of the kth occurrence of goal in the array
  // or -1 if not present
}

public static double sum_of_numbers(double[] numbers) {
  // return the sum of all numbers in the array
}

public static boolean contains(double[] numbers, double goal) {
	// return true if the array contains goal, false otherwise
}

public static double[] remove_duplicates(double[] numbers) {
  // return a new array with all duplicates removed
  // the order of the remaining elements should be maintained
}
```

## Key

```java
public static double max_value(double[] numbers) {
	// return the maximum value in the array
	if numbers.length == 0 {
		return null;
	}

	double max_value = numbers[0];
	for (int i = 0; i < numbers.length; i++) {
		if (numbers[i] > max_value) {
			max_value = numbers[i]
		}
	}
	return max_value;
}

public static double min_value(double[] numbers) {
	// return the maximum value in the array
	if numbers.length == 0 {
		return null;
	}

	double min_value = numbers[0];
	for (int i = 0; i < numbers.length; i++) {
		min_value = Math.min(min_value, numbers[i]);
	}
	return min_value;
}

public static double find_first(double[] numbers, double goal) {
	// return the index of the first occurrence of goal in the array
	// or -1 if not present
	if numbers.length == 0 {
		return null;
	}
	for (int i = 0; i < numbers.length, i++) {
		if (numbers[i] == goal) {
			return i;
		}
	}
	return -1;
}

public static double find_last(double[] numbers, double goal) {
	// return the index of the last occurrence of goal in the array
	// or -1 if not present
	if numbers.length == 0 {
		return null;
	}
	for (int i = numbers.length - 1; i >= 0, i--) {
		if (numbers[i] == goal) {
			return i;
		}
	}
	return -1;
}

public static double find_kth_occurrences(double[] numbers, double goal, double k) {
	// return the index of the kth occurrence of goal in the array
  // or -1 if not present
	if numbers.length == 0 {
		return null;
	}
	int count = 0;
	for (int i = 0; i < numbers.length, i++) {
		if (numbers[i] == goal) {
			count++;
		}
		if (count == goal) {
			return i;
		}
	return -1;
}

public static double sum_of_numbers(double[] numbers) {
  // return the sum of all numbers in the array
	if numbers.length == 0 {
		return null;
	}
	int sum = 0;
	for (int i = 0; i < numbers.length; i++) {
		sum += numbers[i];
	}
	return sum;
}

public static boolean contains(double[] numbers, double goal) {
	// return true if the array contains goal, false otherwise
	if numbers.length == 0 {
		return false;
	}
	for (int i = 0; i < numbers.length; i++) {
		if numbers[i] == goal {
			return true;
		}
	}
	return false;
}

public static double[] remove_duplicates(double[] numbers) {
  // return a new array with all duplicates removed
  // the order of the remaining elements should be maintained
	double[] result = new double[numbers.length];
	for (int i = 0; i < numbers.length; i++) {

	}
}
```