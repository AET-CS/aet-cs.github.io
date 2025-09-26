# %%
# Bayes Error Rate Assignment: Understanding Irreducible Error
# ML Introduction Course

import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import multivariate_normal

# Set random seed for reproducible results
np.random.seed(42)

print("=== Understanding Bayes Error Rate ===")
print("In this assignment, you'll explore why some classification errors are unavoidable!")
print()

# %%
# =============================================================================
# PART 1: Well-Separated Distributions (Easy Problem)
# =============================================================================

print("PART 1: Two well-separated groups")
print("-" * 40)

# Create two 2D normal distributions that are far apart
mean_A = [-3, 0]  # Center of group A
mean_B = [3, 0]   # Center of group B
cov = [[1, 0], [0, 1]]  # Same circular variance for both

# Generate labeled data
n_samples = 500
X_A = np.random.multivariate_normal(mean_A, cov, n_samples)
X_B = np.random.multivariate_normal(mean_B, cov, n_samples)

# Combine data and create labels
X_combined = np.vstack([X_A, X_B])
y_combined = np.hstack([np.zeros(n_samples), np.ones(n_samples)])  # 0=A, 1=B

# %%

# Visualize the data
plt.figure(figsize=(10, 6))
plt.scatter(X_A[:, 0], X_A[:, 1], c='blue', alpha=0.6, label='Group A', s=20)
plt.scatter(X_B[:, 0], X_B[:, 1], c='red', alpha=0.6, label='Group B', s=20)
plt.xlabel('X-coordinate')
plt.ylabel('Y-coordinate')
plt.title('Part 1: Well-Separated Distributions')
plt.legend()
plt.grid(True, alpha=0.3)

# Draw a decision boundary at x = 0
plt.axvline(x=0, color='black', linestyle='--', linewidth=2, label='Decision Boundary (x=0)')
plt.legend()
plt.show()

# %%
# TODO 1: Count classification errors with decision boundary at x = 0
# INSTRUCTIONS: 
# - If x-coordinate > 0, predict Group B (label = 1)
# - If x-coordinate <= 0, predict Group A (label = 0)
# - Count how many predictions are wrong

# YOUR CODE HERE:
predictions = (X_combined[:, 0] > 0).astype(int)
errors = np.sum(predictions != y_combined)
error_rate = errors / len(y_combined)

print(f"Decision rule: If x > 0, predict Group B; else predict Group A")
print(f"Number of errors: {errors} out of {len(y_combined)} samples")
print(f"Error rate: {error_rate:.3f} ({error_rate*100:.1f}%)")
print()

# %%
# =============================================================================
# PART 2: Closer Distributions (Harder Problem)
# =============================================================================

print("PART 2: Moving the groups closer together")
print("-" * 40)

# Create distributions that are closer together
mean_A_close = [-1, 0]  # Closer to center
mean_B_close = [1, 0]   # Closer to center

# Generate new data
X_A_close = np.random.multivariate_normal(mean_A_close, cov, n_samples)
X_B_close = np.random.multivariate_normal(mean_B_close, cov, n_samples)

X_combined_close = np.vstack([X_A_close, X_B_close])
y_combined_close = np.hstack([np.zeros(n_samples), np.ones(n_samples)])

# %%# Visualize
plt.figure(figsize=(10, 6))
plt.scatter(X_A_close[:, 0], X_A_close[:, 1], c='blue', alpha=0.6, label='Group A', s=20)
plt.scatter(X_B_close[:, 0], X_B_close[:, 1], c='red', alpha=0.6, label='Group B', s=20)
plt.xlabel('X-coordinate')
plt.ylabel('Y-coordinate')
plt.title('Part 2: Closer Distributions - More Overlap!')
plt.axvline(x=0, color='black', linestyle='--', linewidth=2, label='Decision Boundary (x=0)')
plt.legend()
plt.grid(True, alpha=0.3)
plt.show()

# %%
# TODO 2: Count errors with the same decision boundary (x = 0)
# YOUR CODE HERE:
predictions_close = (X_combined_close[:, 0] > 0).astype(int)
errors_close = np.sum(predictions_close != y_combined_close)
error_rate_close = errors_close / len(y_combined_close)

print(f"Same decision rule on closer distributions:")
print(f"Number of errors: {errors_close} out of {len(y_combined_close)} samples")
print(f"Error rate: {error_rate_close:.3f} ({error_rate_close*100:.1f}%)")
print(f"Error increased by: {error_rate_close - error_rate:.3f}")
print()

# %%
# =============================================================================
# PART 3: Finding the Optimal Split Point
# =============================================================================

print("PART 3: Can we do better? Finding the optimal decision boundary")
print("-" * 40)

# TODO 3: Try different split points and find the best one
# INSTRUCTIONS: Test split points from -2 to 2 and find the one with lowest error

split_points = np.linspace(-2, 2, 41)  # Test 41 different points
error_rates = []

for split in split_points:
    # YOUR CODE HERE: 
    # 1. Make predictions using this split point
    # 2. Calculate error rate
    # 3. Store in error_rates list
    
    predictions = (X_combined_close[:, 0] > split).astype(int)
    errors = np.sum(predictions != y_combined_close)
    error_rate = errors / len(y_combined_close)
    error_rates.append(error_rate)

# Find the best split point
best_idx = np.argmin(error_rates)
best_split = split_points[best_idx]
best_error_rate = error_rates[best_idx]

print(f"Best split point: x = {best_split:.2f}")
print(f"Best error rate: {best_error_rate:.3f} ({best_error_rate*100:.1f}%)")
print()

# %%# Plot error rates vs split points
plt.figure(figsize=(10, 6))
plt.plot(split_points, error_rates, 'b-', linewidth=2)
plt.axvline(x=best_split, color='red', linestyle='--', linewidth=2, 
           label=f'Best split: x={best_split:.2f}')
plt.xlabel('Split Point (x-coordinate)')
plt.ylabel('Error Rate')
plt.title('Error Rate vs. Decision Boundary Location')
plt.legend()
plt.grid(True, alpha=0.3)
plt.show()

# Visualize the optimal boundary
plt.figure(figsize=(10, 6))
plt.scatter(X_A_close[:, 0], X_A_close[:, 1], c='blue', alpha=0.6, label='Group A', s=20)
plt.scatter(X_B_close[:, 0], X_B_close[:, 1], c='red', alpha=0.6, label='Group B', s=20)
plt.axvline(x=best_split, color='green', linestyle='-', linewidth=3, 
           label=f'Optimal Boundary (x={best_split:.2f})')
plt.xlabel('X-coordinate')
plt.ylabel('Y-coordinate')
plt.title('Optimal Decision Boundary')
plt.legend()
plt.grid(True, alpha=0.3)
plt.show()

# %%
# =============================================================================
# PART 4: Different Variances (Extension)
# =============================================================================

print("PART 4: What if one group is more spread out?")
print("-" * 40)

# TODO 4: Create distributions with different variances
# Group A: more concentrated, Group B: more spread out
cov_A = [[0.5, 0], [0, 0.5]]  # Smaller variance
cov_B = [[2.0, 0], [0, 2.0]]  # Larger variance

# YOUR CODE HERE:
# 1. Generate new data with these different covariances
# 2. Find the optimal split point
# 3. Compare error rates

X_A_diff = np.random.multivariate_normal(mean_A_close, cov_A, n_samples)
X_B_diff = np.random.multivariate_normal(mean_B_close, cov_B, n_samples)

X_combined_diff = np.vstack([X_A_diff, X_B_diff])
y_combined_diff = np.hstack([np.zeros(n_samples), np.ones(n_samples)])

# %%# Find optimal split for different variances
error_rates_diff = []
for split in split_points:
    predictions = (X_combined_diff[:, 0] > split).astype(int)
    errors = np.sum(predictions != y_combined_diff)
    error_rate = errors / len(y_combined_diff)
    error_rates_diff.append(error_rate)

best_idx_diff = np.argmin(error_rates_diff)
best_split_diff = split_points[best_idx_diff]
best_error_rate_diff = error_rates_diff[best_idx_diff]

# Visualize
plt.figure(figsize=(10, 6))
plt.scatter(X_A_diff[:, 0], X_A_diff[:, 1], c='blue', alpha=0.6, label='Group A (concentrated)', s=20)
plt.scatter(X_B_diff[:, 0], X_B_diff[:, 1], c='red', alpha=0.6, label='Group B (spread out)', s=20)
plt.axvline(x=best_split_diff, color='green', linestyle='-', linewidth=3, 
           label=f'Optimal Boundary (x={best_split_diff:.2f})')
plt.xlabel('X-coordinate')
plt.ylabel('Y-coordinate')
plt.title('Different Variances: Concentrated vs. Spread Out')
plt.legend()
plt.grid(True, alpha=0.3)
plt.show()

print(f"Optimal split with different variances: x = {best_split_diff:.2f}")
print(f"Error rate: {best_error_rate_diff:.3f} ({best_error_rate_diff*100:.1f}%)")
print()

# %%
# =============================================================================
# REFLECTION QUESTIONS
# =============================================================================

print("=== REFLECTION QUESTIONS ===")
print("1. Why did the error rate increase when we moved the distributions closer?")
print("2. Even with the optimal split point, why couldn't we achieve 0% error?")
print("3. How did different variances affect the optimal boundary and error rate?")
print("4. In real ML problems, how might this relate to irreducible error?")
print()

print("=== SUMMARY ===")
print(f"Part 1 (far apart):     {error_rate:.3f} error rate")
print(f"Part 2 (closer):        {error_rate_close:.3f} error rate") 
print(f"Part 3 (optimal):       {best_error_rate:.3f} error rate")
print(f"Part 4 (diff variance): {best_error_rate_diff:.3f} error rate")
print()
print("Key insight: When distributions overlap, some errors are unavoidable!")
print("This is the Bayes error rate - the theoretical minimum error rate.")
