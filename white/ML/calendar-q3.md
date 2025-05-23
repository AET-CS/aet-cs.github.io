---
title: "ML Calendar Archive"
layout: single
classes:
  - wide
---
- [Quarter 1 Archive](./calendar-q1.md)
- [Quarter 2 Archive](./calendar-q2.md)

## Daily Review

- 1/30/2025 (Thursday)
	- Research brainstorm [this file](https://docs.google.com/document/d/1ME4WEPHl4WExeAzCSBX33trG_vVQVpxoO0uZJl7lxv4/edit?usp=sharing)
- 2/3/2025 (Monday)
  - Continue Research Brainstorm. Add to [this file](https://docs.google.com/document/d/1ME4WEPHl4WExeAzCSBX33trG_vVQVpxoO0uZJl7lxv4/edit?usp=sharing).
  - Wow OK I just checked that file and it looks great. Please review and see if you get even more ideas
  - Today's goal, at the end of class, add YOUR NAME to the **same** file and three specific project ideas:
    - They should be specific enough that I can understand them, but don't over specify too early
    - They should have some clear path forward -- things that look like they can be done
    - It's OK if they have parts that you can't see how to do yet.
    - They should each be a research idea, or an invention, or a product (hardware, software, etc).
	- Finally if you had to pick a Sr research direction (including but not limited to: AI/ML, App Dev, game dev, software dev, compilers, OS, computer vision, networking, cybersecurity, really ANYTHING). what would what area be? This is not a commitment, just a survey. Add that below your name.
- 2/5/2025 (Wednesday)
  - Research: Take one of your research ideas, and do some background research. Find 3 peer reviewed papers or articles that relate in some way to a piece of the puzzle. It's fine if the articles are for different ideas -- you don't have to overspecify at this point. For each article, read it carefully and be able to answer: "What relevant question about your project does this article answer?" and "What new question does it raise."
  - Create a Google Doc (for now, even though I hate Google Docs) and cite your 3 articles, link directly to them, and answer the two questions. Do this in class today please. Share the Google Doc with me.
  - Hardware: If you haven't yet, finish setting up tf-gpu on a laptop you can use. Please crowdsource this until it works.
  - Cloud: You should probably get a cloud computing account to use if you need some heavy duty computing. You should try any/all of the following
	and see what you think: Google Colab (free but slow) AWS Sagemaker Studio Lab, Lightning.ai, Paperspace, lambda labs, something else? IDK if these
	are blocked by the firewall or what exactly you have to do to sign up but they all have some kind of free tier. Try them out and see what you think.
	- I'm hoping to get us some cloud computing accounts somewhere but for now no clue if that will happen.
	-  Bonus activity for AP Stats: If you have a few minutes please complete [this form:](https://forms.gle/x7oVwdDntgPyeZJt8)
- 2/7/2025 (Friday)
  - Perceptron Learning
  - Work on [this notebook](./lessons/Perceptron_Learn_Student)
- 2/11/2025 (Tuesday)
  - Linearly separable functions
  - multi-perceptron learning
  - introduction to backpropagation
- 2/13/2025 (Thursday)
  - More backpropagation
  - Deep dive into TF with MNIST
  - Work on [this notebook](./lessons/MNIST-Model-Student.ipynb)
- 2/18/2025 (Tuesday)
  - Fashion MNIST [notebook](./lessons/Fashion-Model.ipynb)
  - Submit to server when finished
- 2/20/2025 (Thursday)
  - Parameters for DNNs
  - hyperparameter search for [Fashion](./lessons/Fashion-Tune.ipynb)
- 2/24/2025 (Monday)
	- IMDB Challenge!
	- `from keras.datasets import imdb`
	- create an MLP that classifies the reviews in this dataset as positive or negative.
- 2/26/2025 (Wednesday)
  - Intro to CNNs
  - Image convolutions [exploratory notebook](./lessons/ImageConvolutions.ipynb)
  - Visualization of [MNIST](https://adamharley.com/nn_vis/cnn/3d.html)
  - Implement a LeNet and an AlexNet in Tensorflow
    - Consult [Dive into Deep Learning](https://d2l.ai), chapters 7.6 and 8.1
    - Do *not* use the `dl` package -- modify the code provided
    - `model.fit` will look like previous ones you've made
- 2/28/2005 (Friday)
  - Image Processing and Transfer learning
  - Read about Inception Blocks and [GoogLE Net](https://d2l.ai/chapter_convolutional-modern/googlenet.html)
  - [Image Processing Notebook](https://colab.research.google.com/gist/paderevski/d15f41fb5cdd67a85bcff077b52f7409/images.ipynb). Run this notebook up to the "tf.data" section.
  - [Data Augmentation Notebook](https://colab.research.google.com/gist/paderevski/4d027105a4f2608c8989ebe75b17869b/data_augmentation.ipynb).
  - Task: Following [the example code here](https://keras.io/api/applications/), implement transfer learning on an InceptionV3 notework to recognize images from the flower dataset (or any other TF dataset or batch of labeled imaged). Use Colab GPU for this (or a similar service) if you don't have a personal GPU.
  - Unmoderated! My [alexnet](./alexnet.ipynb) and [googLenet](./googLenet.ipynb) notebooks (for reference but have not been cleaned up).
  - Tensorflow Datasets is a good place to [get data](https://www.tensorflow.org/datasets)
- 3/4/2025 (Tuesday)
  - Time series Data
  - Audio processing: FFTs and MFCCs
  - CNN approaches to Audio classification
  - Work through [Bird Call Notebook](https://colab.research.google.com/drive/1lBWmGmouU7feyEasJBCNUB7pB_e5uPxN?usp=sharing) in Colab. (Does this link make your own copy?). Note there is some data to download first.
  - Find your own audio data to classify. One possibility is the [music genre database](https://huggingface.co/datasets/marsyas/gtzan)
- 3/6/2025 (Thursday)
  - Predict [AAPL stock prices](./data/AAPL.csv)
  - Here's my [Apple prediction](./data/apple-prediction.png) using a history of 64 and a 1 node NN
- 3/10/2025 (Monday)
  - Train an RNN to write Shakespeare
  - First read this famous [blog post about RNNs](https://karpathy.github.io/2015/05/21/rnn-effectiveness/)
  - Learn about the details of [softmax and temperature](./lessons/Softmax.ipynb)
  - Code your own RNN to [write like Shakespeare](./lessons/Shakespeare_Student.ipynb)
  - Homework: train a deep RNN model for as long as possible, try to maximize validation accuracy. Be sure to use checkpoint saving callbacks. Use Shakespeare or any other dataset you'd like to imitate.
  - Here's a [sample of my network](https://colab.research.google.com/gist/paderevski/41b323cc232e306a4573689d83c62ebb/shakespeare.ipynb) running on Colab. It's a deep RNN network with dropout and an encoding layer. Sample output is at the end. This was trained on an A100.
- 3/12/2025 (Wednesday)
  - Finish Shakespeare from last class, share some results
  - Google Drive mapping
  - Write a Bach chorale! Get files [here](https://homl.info/bach). Train your model and then have it output a csv file in the same format as the input
  - Use this [csv to midi converter](./lessons/convert-back.py) to generate midi files
  - Try this [gist](https://gist.github.com/korakot/a0c6b0120bb75d48588aec27030b3325) to play your files in Colab (or download and play them locally)
- 3/14/2025 (Friday)
  - Finish Shakespeare from last class if you haven't already
  - Work on Bach
    - Start with one voice only (e.g. soprano, voice 0) and drop the others
    - Use an Embedding layer at the start of your RNN
    - Midi note ranges are approx 40-70 in this dataset. You might want to shift that down before you train (and back up when you test)
		- Categorical is probably the best way to classify this data, but you could try a regression based loss if you want.
		- Use the converter above to create midi/wav files from your output
		- Be careful with array and tensor dimensions. It's annoying. Sequence input should look like [[10,12,10,15]] not [10,12,10,15]
		- When you're ready for four voices, I recommend weaving the data into a 1D stream: like this: SATBSATBSATB where each group of 4 notes is played simultaneously. You can try keeping it as a 4-vector in a 3D tensor but that get complicated.
		- Here's  [sample output](./data/bach-201.wav) from a 3-layer deep GRU I trained. The training data is the first 4 measures or so and then the model takes over.
- 3/18/2025 (Tuesday)
	- Continue to refine your Bach RNN model. Here is a [very nicely constructed example](https://gist.github.com/paderevski/ae7fd776fab0da3a560642279460273c) of my Bach model training.
	- New topic: Sentiment Analysis. This is a text-to-categorical model. Work through [the notebook here](https://gist.github.com/paderevski/e8f4983afc7e7ae1ca6627941ee9ab6f).
	- Assignment: Classify another similar dataset of text data. I recommend the Amazon Review dataset, but you can find your own if you prefer.
	- For next class: Pick your best Shakespeare and Bach examples to share!
	- Errata: The original Shakespeare_Student notebook had a critical error in the generation cell. (The later example notebook did not). Please see the fix [here](./bad-shake.md)
	- Fun: Here are some of my [generated Bach chorales](https://drive.google.com/drive/folders/1--pfsv9gRr_lawLdik5C95uz8ZvCw_T0?usp=sharing). Each one starts with 64 samples (about 4 measures or 7 seconds) of seed from an unseen Bach chorale.
- 3/20/2025 (Thursday)
	- Submit your best Bach [here to dropbox](https://www.dropbox.com/request/B8aU2W4sVxJTOWHDgi2m)
	- Train your own sentiment analysis model on data of your choice
- 3/24/2025 (Monday)
  - Try logging into the 4090 @ 192.168.1.243
  - Submit Bach to Dropbox if you haven't already
  - Finish Sentiment Analysis on your own dataset and upload notebook to the server (don't upload data files unless they're very small)
- 3/26/2025 (Wednesday)
  - Neural Machine Translation
  - Complete [this notebook](https://colab.research.google.com/gist/paderevski/cee31b4cc313729864c06cc824de509b/neural-machine-translation-student.ipynb)
  - Here is a diagram of [the seq2seq model](./spanish-english-A.png) you will be buildling
  - A nice [schematic](image.png) of a translation model
  - To Do: Get the model working then add your own customizations to improve performance.
  - A detailed overview of [Google's NMT system from 2016](https://arxiv.org/pdf/1609.08144)
- 3/28/2025 (Friday)
	- Continue working on seq2seq model
- 4/2/2025 (Wednesday)
	- Finish Spanish/English seq2seq model
	- Notes on Attention Layers and Beam Search
	- Implement both using these [helper functions](./lessons/seq2seq-Resources.ipynb), explore the model parameters
	- See some [examples](./lessons/Translation-Examples.html) of my translation
- 4/4 2025 (Friday)
	- No school
- 4/8/2025 (Tuesday)
	- Auto-Encoders!
	- Side quest: Check out my app [here](https://javadrop-io-019d03a56736.herokuapp.com/) for turning in Java code. Log in with your credentials for class and submit to the Binary Tree assignment. If you need a file to
	submit, [here's one that should work](./lessons/BinaryTree.java)
	- Assignment: Create an auto-encoder for the Fashion MNIST dataset. If you get that working, add noise or dropout to the input images and see how well it reconstructs.
- 4/10/2025 (Thursday)
	- Notes on genetic algorithms and symbolic regression
	- Social Hour
- Spring Break