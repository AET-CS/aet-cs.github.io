# Interactive Introduction to NumPy
# A hands-on tutorial with examples and exercises

# 1. GETTING STARTED WITH NUMPY

# First, let's import NumPy (the standard convention is to use 'np')
import numpy as np

# Let's check the version
print("NumPy version:", np.__version__)

# ================================
# 2. CREATING ARRAYS
# ================================

# Example: Creating arrays from lists
arr1 = np.array([1, 2, 3, 4, 5])
print("1D array:", arr1)

arr2 = np.array([[1, 2, 3], [4, 5, 6]])
print("2D array:\n", arr2)

# Example: Creating arrays with built-in functions
zeros = np.zeros(5)
print("Zeros:", zeros)

ones = np.ones((2, 3))
print("Ones:\n", ones)

range_arr = np.arange(0, 10, 2)
print("Range array:", range_arr)

linspace_arr = np.linspace(0, 1, 5)
print("Linspace array:", linspace_arr)

# EXERCISE 1: Your turn!
# Create the following arrays:
# 1. A 1D array with numbers [10, 20, 30, 40, 50]
exercise1_1 = # Your code here

# 2. A 2D array filled with zeros, shape (3, 4)
exercise1_2 = # Your code here

# 3. An array with numbers from 5 to 15 (exclusive), step 2
exercise1_3 = # Your code here

# ================================
# 3. ARRAY ATTRIBUTES
# ================================

# Example: Exploring array properties
example_arr = np.array([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]])

print("Array:\n", example_arr)
print("Shape:", example_arr.shape)        # Dimensions
print("Size:", example_arr.size)          # Total number of elements
print("Data type:", example_arr.dtype)    # Data type
print("Number of dimensions:", example_arr.ndim)

# EXERCISE 2: Your turn!
# Create a 3D array with shape (2, 3, 4) filled with ones
exercise2_arr = # Your code here

# Print its shape, size, and number of dimensions
# Your code here

# ================================
# 4. INDEXING AND SLICING
# ================================

# Example: Accessing elements
arr = np.array([10, 20, 30, 40, 50])

print("Original array:", arr)
print("First element:", arr[0])
print("Last element:", arr[-1])
print("Elements 1 to 3:", arr[1:4])

# Example: 2D array indexing
arr_2d = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])

print("2D array:\n", arr_2d)
print("Element at row 1, col 2:", arr_2d[1, 2])
print("First row:", arr_2d[0, :])
print("Last column:", arr_2d[:, -1])

# EXERCISE 3: Your turn!
test_arr = np.array([[10, 20, 30, 40], [50, 60, 70, 80], [90, 100, 110, 120]])

# 1. Get the element in the middle row, middle column
exercise3_1 = # Your code here

# 2. Get the last row
exercise3_2 = # Your code here

# 3. Get the first two columns of all rows
exercise3_3 = # Your code here

# ================================
# 5. ARRAY OPERATIONS
# ================================

# Example: Element-wise operations
a = np.array([1, 2, 3, 4])
b = np.array([5, 6, 7, 8])

print("Array a:", a)
print("Array b:", b)
print("Addition:", a + b)
print("Multiplication:", a * b)
print("Power:", a ** 2)
print("Square root:", np.sqrt(a))

# Example: Operations with scalars
print("a + 10:", a + 10)
print("a * 3:", a * 3)

# EXERCISE 4: Your turn!
x = np.array([2, 4, 6, 8])
y = np.array([1, 3, 5, 7])

# 1. Calculate x - y
exercise4_1 = # Your code here

# 2. Calculate x divided by 2
exercise4_2 = # Your code here

# 3. Calculate the square of y
exercise4_3 = # Your code here

# ================================
# 6. USEFUL FUNCTIONS
# ================================

# Example: Statistical functions
data = np.array([1, 5, 3, 9, 2, 8, 4, 7, 6])

print("Data:", data)
print("Mean:", np.mean(data))
print("Median:", np.median(data))
print("Standard deviation:", np.std(data))
print("Min:", np.min(data))
print("Max:", np.max(data))
print("Sum:", np.sum(data))

# Example: Finding indices
print("Index of max value:", np.argmax(data))
print("Index of min value:", np.argmin(data))

# EXERCISE 5: Your turn!
scores = np.array([85, 92, 78, 96, 88, 79, 91, 84])

# 1. Calculate the average score
exercise5_1 = # Your code here

# 2. Find the highest score
exercise5_2 = # Your code here

# 3. Find how many scores are above 85
exercise5_3 = # Your code here (Hint: use np.sum() with a condition)

# ================================
# 7. RESHAPING ARRAYS
# ================================

# Example: Changing array shape
original = np.arange(12)
print("Original array:", original)

reshaped = original.reshape(3, 4)
print("Reshaped to 3x4:\n", reshaped)

flattened = reshaped.flatten()
print("Flattened:", flattened)

# Example: Adding dimensions
arr_1d = np.array([1, 2, 3])
arr_2d = arr_1d.reshape(-1, 1)  # -1 means "figure out this dimension"
print("1D to column vector:\n", arr_2d)

# EXERCISE 6: Your turn!
numbers = np.arange(24)

# 1. Reshape to a 4x6 array
exercise6_1 = # Your code here

# 2. Reshape to a 2x3x4 array
exercise6_2 = # Your code here

# ================================
# 8. BOOLEAN INDEXING
# ================================

# Example: Filtering with conditions
arr = np.array([1, 5, 3, 8, 2, 9, 4])

print("Original array:", arr)

# Create boolean mask
mask = arr > 4
print("Mask (elements > 4):", mask)

# Apply mask
filtered = arr[mask]
print("Filtered array:", filtered)

# One-liner
big_numbers = arr[arr > 4]
print("Numbers > 4:", big_numbers)

# EXERCISE 7: Your turn!
temperatures = np.array([20, 25, 18, 30, 22, 35, 28, 15])

# 1. Find temperatures above 25
exercise7_1 = # Your code here

# 2. Count how many temperatures are below 20
exercise7_2 = # Your code here

# ================================
# 9. PUTTING IT ALL TOGETHER
# ================================

# Example: A mini data analysis
# Let's analyze some fake test scores
np.random.seed(42)  # For reproducible results
class_scores = np.random.normal(78, 12, 30)  # 30 students, mean=78, std=12

print("Class scores (first 10):", class_scores[:10])
print(f"Class average: {np.mean(class_scores):.2f}")
print(f"Number of students who passed (≥70): {np.sum(class_scores >= 70)}")
print(f"Highest score: {np.max(class_scores):.2f}")
print(f"Lowest score: {np.min(class_scores):.2f}")

# FINAL EXERCISE: Your turn!
# Create your own mini analysis
np.random.seed(123)
sales_data = np.random.randint(100, 1000, 20)  # 20 days of sales

print("Daily sales:", sales_data)

# 1. Calculate total sales for the 20 days
total_sales = # Your code here

# 2. Find the average daily sales
avg_sales = # Your code here

# 3. Find days with sales above average
above_avg_days = # Your code here

# 4. What's the best sales day?
best_day = # Your code here (hint: use np.argmax() + 1 for day number)

print(f"Analysis Results:")
print(f"Total sales: ${total_sales}")
print(f"Average daily sales: ${avg_sales:.2f}")
print(f"Days with above-average sales: {len(above_avg_days)}")
print(f"Best sales day: Day {best_day}")

# ================================
# CONGRATULATIONS!
# ================================

print("\n🎉 Congratulations! You've completed the NumPy introduction!")
print("You now know the fundamentals of NumPy:")
print("✓ Creating arrays")
print("✓ Array attributes and indexing")
print("✓ Mathematical operations")
print("✓ Statistical functions")
print("✓ Reshaping and filtering")
print("\nKeep practicing and exploring more NumPy functions!")

# ================================
# BONUS: Quick Reference
# ================================

"""
NUMPY QUICK REFERENCE:

Creating Arrays:
- np.array([1,2,3])          # From list
- np.zeros(5)                # Array of zeros
- np.ones((2,3))             # Array of ones
- np.arange(0,10,2)          # Range with step
- np.linspace(0,1,5)         # Evenly spaced

Array Info:
- arr.shape                  # Dimensions
- arr.size                   # Total elements
- arr.dtype                  # Data type
- arr.ndim                   # Number of dimensions

Indexing:
- arr[0]                     # First element
- arr[-1]                    # Last element
- arr[1:4]                   # Slice
- arr[row, col]              # 2D indexing

Operations:
- arr1 + arr2                # Element-wise addition
- arr * 3                    # Scalar multiplication
- arr ** 2                   # Element-wise power
- np.sqrt(arr)               # Square root

Statistics:
- np.mean(arr)               # Average
- np.max(arr)                # Maximum
- np.min(arr)                # Minimum
- np.sum(arr)                # Sum
- np.std(arr)                # Standard deviation

Reshaping:
- arr.reshape(3,4)           # Change shape
- arr.flatten()              # Make 1D

Filtering:
- arr[arr > 5]               # Boolean indexing
- np.sum(arr > 5)            # Count elements
"""