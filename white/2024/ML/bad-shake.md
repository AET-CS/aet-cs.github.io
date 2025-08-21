---
title: "Shakespeare Errata"
---


The following is a correct implementation of next_char using temperature. In the published version, I renamed `probs` to `logits` but did so inconsistently. The output of the softmax layer is first logarithm-ed (to undo the softmax exponentiation), divided by temp (to rescale) and then fed into tf.random.categorical (which expects logits, i.e. PRE-softmax data) to generate a random sample.

```python
import tensorflow as tf

def next_char(seed, temp = 1):
  seed = label_encoder.transform([c for c in seed])
  probs = model.predict(seed.reshape(1,-1,1), verbose=0)
  probs = np.log(probs)/temp
  char_id = tf.random.categorical(probs, num_samples=1)
  return label_encoder.inverse_transform(char_id.numpy().flatten())[0]
```