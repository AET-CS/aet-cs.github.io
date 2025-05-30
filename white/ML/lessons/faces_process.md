---
title: "Copy faces into folders"
---



```python
import sys, os, random, glob, shutil
```


```python
files = glob.glob('faces/*.png')
files = list(files)
random.shuffle(files)
```


```python
if not os.path.exists('faces-data/train/1'): os.makedirs('faces-data/train/1')
if not os.path.exists('faces-data/test/1'): os.makedirs('faces-data/test/1')
```


```python
train_size = 10000
test_size = 10

train_set = files[:train_size]
test_set = files[train_size:train_size+test_size]

for f in train_set:
    basename = os.path.basename(f)
    from_path = os.path.join('faces', basename)
    to_path = os.path.join('faces-data/train/1', basename)
    shutil.copyfile(from_path, to_path)

for f in test_set:
    basename = os.path.basename(f)
    from_path = os.path.join('faces', basename)
    to_path = os.path.join('faces-data/test/1', basename)
    shutil.copyfile(from_path, to_path)
```

# Load folder into X,y arrays

```python
def load_image_folder_data(folder_path, image_size=(256, 256), split=0.8, seed=123):
    """
    Load images from folder structure into train/test NumPy arrays.
    Expects a folder structure with class subfolders.

    Returns:
        (x_train, y_train), (x_test, y_test)
    """
    # Create a dataset
    dataset = tf.keras.preprocessing.image_dataset_from_directory(
        folder_path,
        image_size=image_size,
        batch_size=None,  # Load one image at a time
        shuffle=True,
        seed=seed
    )

    # Convert to numpy arrays
    x_data = np.array([img.numpy() for img, _ in dataset])
    y_data = np.array([label.numpy() for _, label in dataset])

    # Create train/test split
    split_idx = int(len(x_data) * split)
    x_train, x_test = x_data[:split_idx], x_data[split_idx:]
    y_train, y_test = y_data[:split_idx], y_data[split_idx:]

    return (x_train, y_train), (x_test, y_test)
```