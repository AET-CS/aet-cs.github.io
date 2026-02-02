# TensorFlow + Metal on Apple Silicon (Mac, M4)

This documents the working setup as of 2026-01-22 on macOS for running TensorFlow with Metal GPU acceleration.

## Environment
- Python: 3.12.x (conda env)
- TensorFlow: 2.16.2
- tensorflow-metal: 1.2.0
- macOS: 12+ on Apple Silicon (tested on M4)

## Create the env (conda)
```bash
conda create -y -n tfmetal-pip python=3.12
conda activate tfmetal-pip
python -m pip install --no-cache-dir tensorflow==2.16.2 tensorflow-metal==1.2.0
```

## Verify GPU is detected
```bash
conda activate tfmetal-pip
python - <<'PY'
import tensorflow as tf
print('TF', tf.__version__)
print('GPUs', tf.config.list_physical_devices('GPU'))
PY
```
Expected output includes a Metal GPU device, e.g. `GPUs [PhysicalDevice(name='/physical_device:GPU:0', device_type='GPU')]`.

## Why this version pin
- The latest TF 2.20.0 + tensorflow-metal 1.2.0 combo currently fails to load on macOS/arm with a missing `_pywrap_tensorflow_internal.so` symbol. TF 2.16.2 with tensorflow-metal 1.2.0 loads cleanly and exposes the GPU.

## Using in notebooks
- Activate the env before launching Jupyter: `conda activate tfmetal-pip`
- Start your notebook server from the project root so the kernel sees this env. If multiple Python kernels show up, pick the one for `tfmetal-pip`.

## Troubleshooting
- If import still fails, ensure you are using Python 3.10–3.12 (3.12 recommended) and that `pip show tensorflow tensorflow-metal` reports the pinned versions above.
- Avoid `conda install tensorflow`; use `pip` inside the conda env.
