# Install Tensorflow-GPU in WSL2

These instructions install Tensorflow on an AET laptop, assuming you already have WSL2 up and running. It also installs the required NVIDIA drivers.

For any other type of linux setup, these instructions should basically still work. Pick the right driver for your NVIDIA card, and the 'Install Tensorflow and CUDA libs' instructions below should work in any linux. We are assuming you have python 3.11 installed (3.12 is too new).

If you *really* want to do this right, you should use docker containers on a linux box. That's the best way to move between versions of software and packages. So if you have your own machine and know (or want to learn) docker, try that route.

If you have a silicon Mac, you can use tensorflow-metal to provide GPU support. You should create a new virtual environment for python and then follow [these instructions](https://developer.apple.com/metal/tensorflow-plugin/).

As of 1/23/2015, this instruction set installs Tensorflow 2.18 with GPU support

## Install NVIDIA Driver
* First go to [this webpage](https://www.tensorflow.org/install/pip#windows-wsl2_1)
* Scroll down to Step-by-step instructions
* Select the Windows WSL2 tab
* Click on [Setup NVIDIA GPU Support](https://docs.nvidia.com/cuda/wsl-user-guide/index.html) (yes, I did link it here as well)
* On NVIDIA's page, scroll down to 2.1: Install NVIDIA Driver
* Click on [https://www.nvidia.com/Download/index.aspx](https://www.nvidia.com/Download/index.aspx) in the first paragraph
* Search for your drive on the nvidia-page (AET laptop is T500)
* Direct link to the T500 driver is [here](https://us.download.nvidia.com/Windows/Quadro_Certified/553.62/553.62-quadro-rtx-desktop-notebook-win10-win11-64bit-international-dch-whql.exe)
* Run the NVIDIA Installer. Select "clean install" when prompted
* Restart your laptop!

## Install Tensorflow and CUDA libs
* Go back to [this webpage](https://www.tensorflow.org/install/pip#windows-wsl2_1) section 2.2 (the following instructions customize what you will find on that page)
* Open powershell and run `nvidia-smi`. It should work
* Open WSL2 and run `nvidia-smi`. It should work
* If they both work you can install tensorflow
* Create a NEW python venv:
  1. `cd` to your ML folder
  2.  run `python -m venv tf-env`. This creates a new tf-env folder
  3.  run `source tf-env/bin/activate`. This activates the new env
  4.  run `pip install tensorflow[and-cuda] numpy matplotlib jupyterlab scikit-learn pandas seaborn stats`
  5.  Wait patiently
  6. Run this in the terminal `python3 -c "import tensorflow as tf; print(tf.config.list_physical_devices('GPU'))"` and look for a GPU listed at or near the end of the output

## Try it out

If it worked, then invoke `jupyterlab` from this new environment. Download and run the new [MNIST Notebook](./lessons/MNIST-GPU.ipynb). Run the single cell. You will probably see LOTS of warnings and errors but it SHOULD mention a GPU and then run. Each epoch should take about 5 seconds (on an AET laptop).

To verify the GPU is running you can use `nvidia-smi`. First, bump up the number of epochs `model.fit(x_train, y_train, epochs=25)`, and re-run the cell. Open up powershell and run `nvidia-smi`. You should see GPU and memory usage are non-zero. Also try the same command in WSL2.

For more fun you can run a [larger test using CIFAR.](./lessons/CIFAR-cnn.ipynb).

## Keep it safe

Do NOT install any tensorflow packages in this environment again. Don't upgrade or downgrade. Do NOT update PYTHON! If you want to change anything related to tensorflow, create a new environment. 

BUT, even then, `pip instsall tensorflow` installs CUDA and CUDNN libraries. Your NVIDIA driver and CUDA and CUDNN and tensorflow versions all have to match. If you try to install an old tensorflow (like 2.3), it may try to replace all your CUDA and CUDNN libs, which will break *this* tensorflow.

If you try to update NVIDIA drivers in either windows or wsl, you will probably break everything.

*IF you break this* in the future, return to this instruction list and start over from scratch.


