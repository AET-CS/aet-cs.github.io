# ML Calendar Archive
## Quarter 1, 2024

- 8/22/2024. Introduction to class. See [more details](lessons/hw01.md)
- 8/26/2024. Didn't It Rain? [Exploring data](lessons/hw02.md) Also here's a [fix for WSL and Python](lessons/wsl.md)
- 8/28/2024. Present findings from weather data. [Class Notes](lessons/cw03.md). Discuss Bayes' Theorem.
	- HW: Finish [Bayes' Theorem notebook.](lessons/Bayes_Theorem_Student.ipynb) ([html version](./lessons/Bayes_Theorem_Student.html)). Please read [this intro](lessons/jupyter-python-intro.md) to jupyter and python lists if you need some pointers.
- 9/3/2024 (Tuesday) Turn in Bayes homework. Quick library orientation. New notes on Linear Regression -- [new notes provided](./lessons/Linear_regression_derivation.pdf).
	- HW: Complete the [linear regression notebook](./lessons/least-squares-01.ipynb), ([html version](./lessons/least-squares-01.html)) for classwork/homework. As an application, do a linear regression on the London weather dataset (this part is *not* HW yet but will be). For next class: pick some new ideas from your *Money List* to discuss.
- 9/5/2024 (Thursday) Turn in Linear Regression homework. Go over Bayes and Regression notebooks in class. [Bayes Key](lessons/Bayes_Theorem_Key.html) Discuss goodness of fit measures for categorical and quantitative data. Notes on [Pearson's Correlation Coefficient](./lessons/Correlation_Coefficient.pdf).
	- Classwork: perform a linear regression on the [London weather dataset](./data/london_weather.csv).
	- Then find your own dataset somewhere and perform a linear regression on it. In both cases use the LR algorithm you coded already; do not use built-in linear regression tools, please. Make your data analysis into a nice, brief write-up (emphasis on brief) and turn it in before class ends (as a jupyter notebook).
	- Classwork: Discuss some recent *money list* ideas
	- HW: Finish classwork, any other old notebooks that aren't done yet
- 9/9/2024 (Monday) Unit Topic: Matrices in Python and Numpy. Today we will code up standard algorithms for performing matrix arithmetic operations using Python lists. Please download and modify [this matrix notebook](./lessons/Matrices-student.ipynb) ([pdf](./lessons/Matrices-student.pdf)) and ftp it to the pi when you're finished. The next notebook on Gaussian elimination will be provided later.
- 9/11/2024 (Wednesday)  Gaussian elimination, on paper and using numpy.
	- [Intro to numpy arrays and operations](./lessons/Intro_to_Matrices_in_NumPy.ipynb) ([html](./lessons/Intro_to_Matrices_in_NumPy.html)).
	- [Matrix indices warmup](./lessons/Matrices_Index_Warmup-Student.ipynb) ([html](./lessons/Matrices_Index_Warmup-Student.html))
	- [Gaussian Elimination notebook](./lessons/Gaussian_Elimination-student.ipynb) ([pdf](./lessons/Gaussian_Elimination-student.pdf)). You'll need [this picture](./lessons/error-scatterplot.png) to display the Gaussian notebook properly, in the same folder. (only turn in this notebook when you finish Friday)
- 9/13/2024 (Friday) Complete regression analysis of error terms in Gaussian Elimination lab. Notes of [log-log regression](./lessons/LogLogRegression.html) ([notebook](./lessons/LogLogRegression.ipynb))
	- Turn in Matrix lab and Gauss lab today please
	- Extensions (optional):
		- [Strassen Multiplication](./lessons/Strassen-Lab.pdf)
		- [Gaussian Biography](./lessons/Gauss.pdf)
		- [Matrix inversion](./lessons/inversion.pdf) **This has been updated 9/18**
		- Custom idea (propose something!)
		- Note: the value of the extension is always its *uniqueness*. If everybody in class turns in essentially the same solution for an optional assignment, its value degrades to zero. These are a chance to explore something interesting and to be original and creative!
- 9/17/2024. Fire. A literal fire.
- 9/19/2024 (Thursday) Still closed for [fire](https://www.youtube.com/watch?v=XchwE9zVdnw). But there *is* homework
	- Please read an article about recent results in CS, and come to class ready to talk about it. [More information is here](./lessons/reading.md)
	- Finish all old labs and extensions and turn them in next class. I don't want to spend class time working on them anymore. Please email or send a Remind if you have any questions!
	- I'll probably go over some/most/all labs and extensions next class.
- 9/23/2024 (Monday) Closing thoughts on Gaussian Elimination, Matrix Inversion. Gaussian Biography presentation (awesome, Kyle)
	- Class activity: Work on [Running Time Analysis lab](./lessons/Running_Time_Analysis.ipynb), ([html](./lessons/Running_Time_Analysis.html)) in which running times of 4 different matrix multiply algorithms are considered. Discussion of pandas topics such as filtering and manipulating data and plotting using pandas.plot.
	- No new HW
	- If you are interested in the [C++ matrix code](./lessons/matrices.cpp) check it out, along with an example of using `pybind11` to call it from python.
- 9/25/2024 (Wednesday) Finish [Running Time Analysis lab](./lessons/Running_Time_Analysis.ipynb), ([html](./lessons/Running_Time_Analysis.html)). ~~Talk about confidence intervals on regression coefficients~~. Introduce new lab on data ingesting and visualization with the [HOBO data lab](./lessons/Hobo_Student.ipynb) ([html](./lessons/Hobo_Student.html))
- 9/27/2024 (Friday) Turn in Running Time Analysis (I think everybody finished?) Work in class on your part (1,2 or 3) of the HOBO data lab. I do want to go over Running Time Analysis results and confidence intervals (new topic).
	- Classwork: HOBO lab, hopefully finish your part
	- Homework: Finish HOBO if needed, prepare some Money List ideas for next class
	- Next class will be short for PSAT. I have a short group activity planned for our brief time together so if you know you won't be here, please let us know in advance if possible. Thanks!
- 10/1/2024 (Tuesday) PSAT day followed by a short class. We will get into groups and select articles for our next informal report. [Details here](./lessons/ML_Book_Club.md).
- 10/3/2024 (Thursday) Holiday
- 10/7/2024 (Monday) Look at the [Mushroom Dataset](./lessons/mushroom_student.ipynb) ([html version](./lessons/mushroom_student.html)).
  - classwork: find a dataset and model it using 'mushroom' as a guide
  - homework: finish what you started in class if you're not happy with it!
  - homework: we will do book club (article club) next class. be ready to discuss in groups
  - HOBO: submit what you have on HOBO -- I have some ideas to make this into a bigger project but we'll put it on the back-burner for a bit and bring it back soon. Each of the 3 groups has made great progress!
- 10/9/2024 (Wed) Plan to do book clubs and a proper treatment of multi-linear regression. Classwork: [Linear Regression](./lessons/Life_Expectancy_Student.ipynb) ([html version](./lessons/Life_Expectancy_Student.html)). Source for 10 good datasets for [Linear Regression](https://www.telusdigital.com/insights/ai-data/article/10-open-datasets-for-linear-regression)
- 10/11/2023 (Fri) More linear regression -- feature selection, regularization,  normalization. We learn about ridge regression and lasso regression which are enhancements to regular linear regression.
	- In-class warmup over optimization
	- Look at [this desmos graph](https://www.desmos.com/calculator/gqyuvs3iea) for a class activity
	- Work through [Life Expectancy 2](./lessons/Life_Part_2_Student.ipynb) notebook ([html version](./lessons/Life_Part_2_Student.html))
	- Due next week: your own notebook on linear regression. Use all or most of the techniques from class to discuss and analyze a dataset.
- 10/16/2023 (Wed). [Matrices as linear operators](./lessons/coordinates.pdf) The Singular Value Decomposition. Intro to PCA (Principal Component Analysis).
	- HW: [Image Processing](./lessons/picture.md)
- 10/18/2024 (Friday) Please take [these surveys] for the AP Stats class
- 10/22/2024 (Tuesday) Introduce Logistic Regression. Continue working with linear project models
	- upload your linear project and PCA picture to the pi
	- ALSO make a copy of your linear project
		- Add descriptive info at the beginning about the dataset, where to find it, what you're looking for, etc
		- Rename this copy as YourFirstName.ipynb
		- Upload it to the pi under /home/linear
	- SELECT one of your classmates Linear projects that SHOULD be logistic
		- Download it
		- Rewrite it to use logistic regression
		- Compare your results and claim your reward!
- 10/24/2024 (Thursday) Logistic Regression.
	- Download Alex's files from the pi if you haven't already
	- Download [Cancer_Logistic_Student](./lessons/Cancer_Logistic_Student.ipynb) from here [html](./lessons/Cancer_Logistic_Student.html)
	- Work through the notebook as indicated
	- Discuss your choices and results
	- Submit final version to pi
- 10/28/2024 (Monday) Last day of quarter
  - Please check gradebook -- anything BLANK is missing. Upload it today!