# Singular Value Decomposition (SVD)

## The Big Idea

SVD takes any matrix and breaks it down into three simpler matrices that represent **rotation**, **scaling**, and **rotation** again.

For any matrix **A** (size m × n), we can write:

**A = U Σ V^T**

where:
- **U** is an m × m rotation matrix (rotates in the output space)
- **Σ** (Sigma) is an m × n diagonal matrix (scales along different directions)
- **V^T** is an n × n rotation matrix (rotates in the input space)

## What Each Matrix Does

### V^T: First Rotation
This matrix rotates your data into a new coordinate system. Think of it as finding the "best" axes for your data.

### Σ: The Scaling Matrix
This is the **key part** for compression! It's a diagonal matrix that looks like:

```
[σ₁  0   0   0 ]
[0   σ₂  0   0 ]
[0   0   σ₃  0 ]
[0   0   0  σ₄ ]
```

The values σ₁, σ₂, σ₃, ... are called **singular values**, and they're always arranged from largest to smallest: σ₁ ≥ σ₂ ≥ σ₃ ≥ ...

**These values tell us how much the matrix stretches space in each direction.**

### U: Second Rotation
This matrix rotates the scaled data into the final output space.

## The Punch Line: Compression!

Here's why SVD is powerful for data compression:

**Large singular values = important information**  
**Small singular values = less important information**

If some singular values are very small, we can **drop them** (set them to zero) without losing much information. This gives us a compressed approximation of the original matrix.

For example, if Σ looks like:
```
[10.5,  0,    0,   0  ]
[0,     8.2,  0,   0  ]
[0,     0,    0.3, 0  ]
[0,     0,    0,   0.05]
```

We might keep only the first two values (10.5 and 8.2) and drop the rest, since they're much smaller. This gives us a compressed version that captures most of the important patterns in the data.

## Why This Matters for PCA

When we use SVD on our data matrix:
- The singular values tell us how much variance each direction captures
- We can keep only the top k singular values and their corresponding directions
- This gives us a lower-dimensional representation that preserves the most important structure

**Bottom line:** SVD finds the best way to compress your data by identifying which directions matter most and how much they matter.