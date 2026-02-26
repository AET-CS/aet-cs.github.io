---
title: resize
---

```python
import numpy as np

def resize_in_batches(images, size=(224, 224), batch_size=256):
    result = []
    for i in range(0, len(images), batch_size):
        batch = tf.image.resize(images[i : i + batch_size], size)
        result.append(batch.numpy())
    return np.concatenate(result, axis=0)


x_train = resize_in_batches(x_train)
x_test = tf.image.resize(x_test, [224, 224])
```