## GAN problem fixer

The code provided in class, taken from *Hands On Machine Learning v3* is not compatible with Keras 3. I didn't realize the issue because I was able to compile it in both versions -- I assumed it would work in both version. But in V3 it does not converge.

The main issue seems to be that `discriminator.trainable` needs to be toggled within the train step. (This makes sense and I'm not sure why it self-toggles in the Keras 2 version). So `train gan` looks like this

```python
def train_gan(gan, dataset, batch_size, codings_size, n_epochs):
	generator, discriminator = gan.layers
	for epoch in range(1, n_epochs+1):
		print(f"Epoch {epoch}/{n_epochs}")  # extra code
		for X_batch in dataset:
			# phase 1 - training the discriminator
			noise = tf.random.normal(shape=[batch_size, codings_size])
			generated_images = generator(noise)
			X_fake_and_real = tf.concat([generated_images, X_batch], axis=0)
			y1 = tf.constant([[0.]] * batch_size + [[1.]] * batch_size)
			discriminator.trainable = True
			d_loss = discriminator.train_on_batch(X_fake_and_real, y1)
			discriminator.trainable = False
			# phase 2 - training the generator
			noise = tf.random.normal(shape=[batch_size, codings_size])
			y2 = tf.constant([[1.]] * batch_size)
			g_loss = gan.train_on_batch(noise, y2)
```

The secondary issue may be with the optimizer. I had better luck defining the optimizer manually instead of just saying `rmsprop`. The code headers for Keras 2 and Keras 3 are included below. The headers are similar but maybe something changed in the implementation. Anyway, this is what I'm using

```
discriminator_optimizer = tf.keras.optimizers.RMSprop(learning_rate=0.002)
gan_optimizer = tf.keras.optimizers.RMSprop(learning_rate=0.001)

discriminator.compile(loss="binary_crossentropy", optimizer=discriminator_optimizer)
discriminator.trainable = False
gan.compile(loss="binary_crossentropy", optimizer=gan_optimizer)
```

Finally, these GAN networks seem to be hard to "reset." I have taken to adding

```python
tf.keras.backend.clear_session()
```

to the first line of my model and making sure I re-run *all* the model code whenever I make any change (other than things like batch size and number of epochs)

## Keras 2
```python
tf_keras.optimizers.RMSprop(
    learning_rate=0.001,
    rho=0.9,
    momentum=0.0,
    epsilon=1e-07,
    centered=False,
    weight_decay=None,
    clipnorm=None,
    clipvalue=None,
    global_clipnorm=None,
    use_ema=False,
    ema_momentum=0.99,
    ema_overwrite_frequency=100,
    jit_compile=True,
    name="RMSprop",
    **kwargs
)
```

## Keras 3

```python
keras.optimizers.RMSprop(
    learning_rate=0.001,
    rho=0.9,
    momentum=0.0,
    epsilon=1e-07,
    centered=False,
    weight_decay=None,
    clipnorm=None,
    clipvalue=None,
    global_clipnorm=None,
    use_ema=False,
    ema_momentum=0.99,
    ema_overwrite_frequency=None,
    loss_scale_factor=None,
    gradient_accumulation_steps=None,
    name="rmsprop",
    **kwargs
)
```