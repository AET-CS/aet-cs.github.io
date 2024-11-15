# Teaching Calendar 2024-2025
[Quarter 1 Archive](./calendar-q1.md)
## Quarter 2
- 11/6/2024 (Wednesday)
  - Topic: k Nearest Neighbor
  - Devise algorithm in class
  - [Implement in Jupyter](./lessons/knn-Student.ipynb) [(html)](./lessons/knn-Student.html)
  - Also complete extension(s)
- 11/8/2024 (Friday)
  - Continue k-NN. Finish notebook from last class
  - Work on [Digits dataset](./lessons/digits-student.ipynb) [(html)](./lessons/digits-student.html)
  - Here's a [nice writeup](https://towardsdatascience.com/comprehensive-guide-to-approximate-nearest-neighbors-algorithms-8b94f057d6b6) of the approximate nearest neighbor problem with an eye towards ML applications like Spotify and Netflix recommendations (may be a paywall I'm not sure)
- 11/12/2024 (Tuesday)
  - Topic: Intro to decision trees
  - Work on the [Decision Tree Notebook](./lessons/Restaurant.ipynb)
  - Note: Oops I forgot sklearn can't handle categorical features. Ugh. The fix is to one-hot or ordinal encode everything, but that makes for ugly trees.
  - Some [notes on entropy](./ai-notes-decision-trees.pdf) are contained in this chapter. I go through the ABCDEFGH example from class here, starting on p. 63.
  - The wikipedia on [Huffman Trees](https://en.wikipedia.org/wiki/Huffman_coding) is pretty good to if you want notes on that algorithm
- 11/14/2024 (Thursday)
	- A [Decision Tree learning algorithm](./lessons/Restaurant_Tree.html) based on notes from last class. See how it handles the slightly modified [restaurant dataset](./lessons/restaurant2.csv). The original notebook is [here](./lessons/Restaurant_Tree.ipynb) but running it requires installing `graphiviz`, the graphviz development package, and `pygraphviz`.
	- Work on this [Decision Tree Lab](./lessons/Decision_Tree_Student.ipynb) using income data.
	- Here's a [LightGBM Example](./lessons/LightGBM_Example.ipynb) for your viewing pleasure.
	- This [thread](https://github.com/microsoft/WSL/issues/11022) may solve the "can't ftp to grumpy inside WSL" problem. Can't test it because both my laptops seem to work fine?