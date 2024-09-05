## Classical ML topics

1. prereqs: python, matplotlib, pandas, numpy. expose students with various projects in class
2. Model evaluation
	1. Categorical model evaluation - accuracy, precision, recall
	2. Quantitative model evaluation - $r^2$, sum of squared errors
3. Linear Regression
	1. Develop math
	2. Use scikit learn for 1D data and multi-dimensional data
	3. non-linear regression (data preprocessing)
	4. subset selection (forward and backward)
	5. lasso and ridge regression (reducing variance and overfitting)
	6. during this unit talk about test and training data. under and overfitting. maybe validation data?
4. Logistic Regression
	1. Classification problem
	2. short unit
	3. Introduce the math of the logistic curve and its derivative (which will be used with backpropagation)
5. Data Preprocessing
	1. One hot encoding
	2. Text? TF-IDF? Text embeddings? (would introduce vector cosine distance)
	3. Image formats? (RGB, BGR, conversion) Processing? (sharpen, blur, resample)
	4. Principal Component Analysis
		1. uses some linear algebra concepts
		2. reduces dimensionality of data
		3. important for large datasets
6. Decision Trees:
	1. Classification problem
	2. Categorical data
	3. Pruning methods (reducing variance and overfitting)
	4. Connect to subset selection and PCA (reducing input dimension)
	5. Random Forests? (here or later)
7. Support Vector Machines
	1. Quick overview of math interpretation (splitting data with a plane that maximizes distance between points)
	2. Talk about different kernel functions (provide math for kids who care)
8. Naive Bayes Classifier?
	1. Easy to understand
	2. Gives good baseline on learnability of concepts
9. Things to talk about now or sprinkle in
	1. Ensemble methods (combining models)
	2. Bootstrapping and Boosting
	3. Cross-validation
	4. Hyperparameter grid search techniques

This is a good 2 quarters maybe more. Then we do neural net stuff in the spring.

Notice once we get going with unit 3, there's not much real math. Mainly knowing how to interpret results. Some techniques will have optional math explanations. Some (like lasso and ridge regression) are very easy to explain if they understand normal regression, for example.

some recurring ideas:
- selecting a subset of your features that matter most
- preprocessing data and feature sets
- model analysis!
- hyperparameters
