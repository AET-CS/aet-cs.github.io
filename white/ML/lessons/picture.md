# Picture Compression

Create a notebook "Gauss_Image.ipynb"

You will use an SVD to compress [this picture](./gauss.jpg). Learn how to use "PIL" to import the picture and resize it to a square 1024x1024. Convert it to an array and display it in grayscale. (Easy to google this bit.)

Now use the technique from class to compute the SVD of the picture. Plot the values of the diagonal matrix $\Sigma$. You will see a point at which the become very small.

You can compress the picture by setting all the diagonal values (they are called singular values) to 0 when they are below a certain level.

You can then reconstruct the image with

`new image = U @ np.diag(new_Sigma) @ VT`

Display this image. Pick a cutoff threshold that you think balances quality and size. What it the effective compression ratio (You can drop any rows and columns of $U$ and $V$ that correspond to 0 in the diagonal matrix).

Finally, the reconstruction error is the Frobenious norm of the difference between the reconstructed matrix and the original one. Construct a plot of this error as a function of the number of non-zero singular values in your compression.
