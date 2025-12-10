---
title: word2vec
---

Here's some code that might work for doing a word embedding.

```java
import gensim.downloader as api
import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score, confusion_matrix
import seaborn as sns
import matplotlib.pyplot as plt

# Load pre-trained Word2Vec model (Google's Word2Vec)
# this connects to the network to download the model

word2vec = api.load("word2vec-google-news-300")  # 300-dimensional vectors


# LOAD SOME DATA
url = "../data/spam.csv"
data = pd.read_csv(
    url, sep=",", header=None, names=["label", "message", "x1", "x2", "x3"]
)
data = data.drop(["x1", "x2", "x3"], axis=1)

# Create DataFrame
df = pd.DataFrame(data)

# Encode labels using OrdinalEncoder
from sklearn.preprocessing import OrdinalEncoder
encoder = OrdinalEncoder()
df['label'] = encoder.fit_transform(df[['label']])

# Split data into features and target
X = df['message']
y = df['label']

# Split into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)


# Function to average Word2Vec embeddings for each document
# so you get an average vector for the whole document

def average_word2vec(doc):
    word_vectors = [word2vec[word] for word in doc.split() if word in word2vec]
    if word_vectors:
        return np.mean(word_vectors, axis=0)
    else:
        return np.zeros(word2vec.vector_size)  # Return a vector of zeros if no words are found
A
# Transform the text data into Word2Vec embeddings
X_train_vec = np.array([average_word2vec(text) for text in X_train])
X_test_vec = np.array([average_word2vec(text) for text in X_test])
```