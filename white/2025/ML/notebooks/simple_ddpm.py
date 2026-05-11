# %% [markdown]
# # DDPM in 1D: Watching Distributions Move
#
# In the image DDPM notebook, the diffusion math gets buried under a 18M-parameter
# U-Net. The actual mathematical phenomenon — a probability distribution being
# transported through time, from data to noise and back — is happening, but it's
# invisible.
#
# In 1D, we can see it directly. We pick an arbitrary distribution over the real
# line, apply the same forward Markov process from the image notebook, and plot
# histograms at each timestep. We watch the distribution morph from data into
# N(0, 1). Then we train a tiny MLP (~30k parameters) to reverse the process,
# and watch N(0, 1) morph back into the original distribution.
#
# Same math, same algorithm, no architectural complexity. Everything below
# could be run on a laptop in a minute.

# %%
import math
import torch
import torch.nn as nn
import torch.nn.functional as F
import numpy as np
import matplotlib.pyplot as plt

torch.manual_seed(0)

if torch.cuda.is_available():
    device = torch.device("cuda")
elif torch.backends.mps.is_available():
    device = torch.device("mps")
else:
    device = torch.device("cpu")


def as_numpy(x):
    return x.detach().cpu().numpy()


print(f"Using device: {device}")

# %% [markdown]
# ## 1. The Target Distribution
#
# We need an arbitrary 1D distribution over (a, b). To make the experiment
# interesting, we pick something **bimodal** — a mixture of two Gaussians at
# x = -3 and x = +3. Bimodal is the right choice because:
#
# 1. It's clearly non-Gaussian, so we can see the forward process actually
#    *change* the distribution shape (not just scale it).
# 2. The reverse process has to *recover both modes*, which is a real test —
#    a broken model would collapse to a single mean, and that failure would
#    be visually obvious.
#
# Feel free to change `sample_target` to anything you like: three modes,
# uniform on an interval, log-normal, even hand-drawn via inverse-CDF
# sampling from a histogram. The framework doesn't care.


# %%
def sample_target(n, device=device):
    """Mixture of two Gaussians: 60% mass at x = -3, 40% mass at x = +3."""
    mask = torch.rand(n, device=device) < 0.6
    centers = torch.where(mask, -3.0, 3.0)
    return centers + 0.5 * torch.randn(n, device=device)


x0_full = sample_target(20000)

plt.figure(figsize=(8, 3))
plt.hist(as_numpy(x0_full), bins=100, density=True, alpha=0.7, color="steelblue")
plt.title("Target distribution (60% at -3, 40% at +3)")
plt.xlim(-5, 5)
plt.xlabel("x")
plt.ylabel("density")
plt.tight_layout()
plt.show()

# %% [markdown]
# ## 2. The Forward Process — Pure Math, No Learning
#
# Identical formula to the image DDPM, just on scalars:
#
# **x_t = √ᾱ_t · x_0 + √(1 - ᾱ_t) · ε**, where ε ~ N(0, 1)
#
# This is a deterministic transformation of x_0 plus scaled Gaussian noise.
# No neural network is involved. The forward process is completely known.


# %%
class NoiseSchedule1D:
    """Linear β schedule, identical to the image DDPM. Just 1D tensors instead of 4D."""

    def __init__(self, T=1000, beta_start=1e-4, beta_end=0.02, device=device):
        self.T = T
        betas = torch.linspace(beta_start, beta_end, T, device=device)
        alphas = 1.0 - betas
        alpha_bars = torch.cumprod(alphas, dim=0)

        self.betas = betas
        self.alphas = alphas
        self.alpha_bars = alpha_bars
        self.sqrt_alpha_bars = torch.sqrt(alpha_bars)
        self.sqrt_one_minus_alpha_bars = torch.sqrt(1 - alpha_bars)
        self.sqrt_recip_alphas = torch.sqrt(1.0 / alphas)

        alpha_bars_prev = torch.cat([torch.ones(1, device=device), alpha_bars[:-1]])
        self.posterior_variance = betas * (1 - alpha_bars_prev) / (1 - alpha_bars)

    def add_noise(self, x0, t, eps=None):
        if eps is None:
            eps = torch.randn_like(x0)
        sqrt_ab = self.sqrt_alpha_bars[t]
        sqrt_omab = self.sqrt_one_minus_alpha_bars[t]
        return sqrt_ab * x0 + sqrt_omab * eps, eps


schedule = NoiseSchedule1D(T=1000, device=device)

# %% [markdown]
# ### Forward histograms
#
# Sample 10,000 points from the target distribution, apply the forward process
# at six different timesteps, plot histograms. Watch the bimodal shape gradually
# smear into N(0, 1).
#
# At t=999 we overlay the analytical N(0, 1) density (red dashed line) to confirm
# that the math worked: the forward process really does converge to the standard
# normal.

# %%
x0 = sample_target(10000)
timesteps = [0, 100, 250, 500, 750, 999]

fig, axes = plt.subplots(2, 3, figsize=(13, 6))
for ax, t_val in zip(axes.flatten(), timesteps):
    if t_val == 0:
        x_t = x0
    else:
        t_tensor = torch.full((10000,), t_val, dtype=torch.long, device=device)
        x_t, _ = schedule.add_noise(x0, t_tensor)

    ax.hist(as_numpy(x_t), bins=80, density=True, alpha=0.7, color="steelblue")
    ax.set_xlim(-5, 5)
    ax.set_ylim(0, 0.55)
    ax.set_title(f"t = {t_val}")

    # At the final timestep, overlay N(0, 1) to confirm convergence
    if t_val == 999:
        xs = np.linspace(-5, 5, 200)
        ax.plot(
            xs,
            np.exp(-(xs**2) / 2) / np.sqrt(2 * np.pi),
            "r--",
            linewidth=2,
            label="N(0,1)",
        )
        ax.legend()

plt.suptitle(
    "Forward Process: Bimodal → N(0,1)  (top-left to bottom-right)", fontsize=12
)
plt.tight_layout()
plt.show()

# %% [markdown]
# **Discussion prompt for students:** at t = 250, the two modes are still visible
# but blurred together. At t = 500 they've merged into a single hump centered
# near zero. At t = 999 the distribution is indistinguishable from N(0, 1).
# The forward process is destroying information — there's no way, looking only
# at x_999, to recover whether the original sample came from the -3 mode or
# the +3 mode. The reverse process has to learn to undo this gradually,
# step by step.

# %% [markdown]
# ## 3. The Denoising Network
#
# In the image DDPM this was an 18M-parameter U-Net. Here it's a 4-layer MLP
# with about 30k parameters. The interface is identical:
#
# **Input:** noisy value `x_t` and timestep `t`.
# **Output:** predicted noise ε_pred.
#
# Time is encoded with the same sinusoidal embedding as the image DDPM's
# `TimeEmbedding` (and the same one as the NMT positional encoding) — this
# gives the network a smooth representation of t that it can interpolate over.


# %%
class TimeEmbedding(nn.Module):
    """Sinusoidal time embedding — same idea as positional encoding in NMT."""

    def __init__(self, dim):
        super().__init__()
        self.dim = dim

    def forward(self, t):
        half = self.dim // 2
        freqs = torch.exp(-math.log(10000) * torch.arange(half, device=t.device) / half)
        args = t.float()[:, None] * freqs[None]
        return torch.cat([torch.sin(args), torch.cos(args)], dim=-1)


class DenoisingMLP(nn.Module):
    """
    Tiny MLP. Takes (x, t) and predicts the noise that was added.
    Compare to the image U-Net: same job, vastly less machinery.
    """

    def __init__(self, time_dim=64, hidden=128):
        super().__init__()
        self.time_emb = TimeEmbedding(time_dim)
        self.net = nn.Sequential(
            nn.Linear(1 + time_dim, hidden),
            nn.SiLU(),
            nn.Linear(hidden, hidden),
            nn.SiLU(),
            nn.Linear(hidden, hidden),
            nn.SiLU(),
            nn.Linear(hidden, 1),
        )

    def forward(self, x, t):
        # x: (batch,)  t: (batch,)
        x_in = x.unsqueeze(-1) if x.dim() == 1 else x  # (batch, 1)
        t_in = self.time_emb(t)  # (batch, time_dim)
        return self.net(torch.cat([x_in, t_in], dim=-1)).squeeze(-1)


model = DenoisingMLP().to(device)
n_params = sum(p.numel() for p in model.parameters())
print(f"Parameters: {n_params:,}")

# %% [markdown]
# ## 4. Training
#
# The training loop is exactly the image DDPM loop, with everything in 1D:
#
# 1. Sample `x_0` from the target distribution
# 2. Sample a random timestep `t`
# 3. Add noise: `x_t, ε = forward(x_0, t)`
# 4. Predict: `ε_pred = model(x_t, t)`
# 5. MSE between predicted and true noise
# 6. Backprop
#
# Uses GPU automatically when available; otherwise runs on CPU.

# %%
optimizer = torch.optim.Adam(model.parameters(), lr=1e-3)
n_steps = 500
batch_size = 512
losses = []

for step in range(n_steps):
    x0 = sample_target(batch_size)
    t = torch.randint(0, schedule.T, (batch_size,), device=device)
    x_t, eps = schedule.add_noise(x0, t)
    eps_pred = model(x_t, t)
    loss = F.mse_loss(eps_pred, eps)

    optimizer.zero_grad()
    loss.backward()
    optimizer.step()

    losses.append(loss.item())
    if (step + 1) % 1000 == 0:
        recent = np.mean(losses[-100:])
        print(f"step {step + 1:5d}  |  loss (last 100) = {recent:.4f}")

plt.figure(figsize=(8, 3))
plt.plot(losses, alpha=0.4)
plt.plot(np.convolve(losses, np.ones(100) / 100, mode="valid"), color="red")
plt.xlabel("step")
plt.ylabel("loss")
plt.title("Training loss (red = 100-step moving average)")
plt.tight_layout()
plt.show()

# %% [markdown]
# ## 5. The Reverse Process
#
# Same iterative denoising step as the image DDPM. Start from x ~ N(0, 1),
# apply T reverse steps, end up at the data distribution.


# %%
@torch.no_grad()
def sample(model, schedule, n_samples=10000, snapshot_at=None):
    """Run the reverse process. Optionally save snapshots at specified timesteps."""
    model.eval()
    snapshot_at = snapshot_at or []
    snapshots = {}

    model_device = next(model.parameters()).device
    x = torch.randn(n_samples, device=model_device)
    if schedule.T - 1 in snapshot_at or "start" in snapshot_at:
        snapshots["start"] = x.clone()

    for t_val in reversed(range(schedule.T)):
        t = torch.full((n_samples,), t_val, dtype=torch.long, device=model_device)
        eps_pred = model(x, t)

        sqrt_recip_alpha = schedule.sqrt_recip_alphas[t_val]
        coeff = schedule.betas[t_val] / schedule.sqrt_one_minus_alpha_bars[t_val]
        mean = sqrt_recip_alpha * (x - coeff * eps_pred)

        if t_val > 0:
            noise = torch.randn_like(x)
            x = mean + torch.sqrt(schedule.posterior_variance[t_val]) * noise
        else:
            x = mean

        if t_val in snapshot_at:
            snapshots[t_val] = x.clone()

    return x, snapshots


snapshot_steps = [1000 - k * (1000 // 21) for k in range(21)]
snapshot_steps[0] = 999

x_final, snapshots = sample(model, schedule, snapshot_at=snapshot_steps)

# %% [markdown]
# ### Reverse histograms
#
# Same six timesteps as the forward process, but read in reverse order
# (start from t=999, end at t=0). The network is iteratively denoising —
# each step, the distribution gets a little more structured.

# %%
fig, axes = plt.subplots(3, int(len(snapshot_steps) / 3) + 1, figsize=(13, 6))
for ax, t_val in zip(axes.flatten(), snapshot_steps):
    samples_t = as_numpy(snapshots[t_val])
    ax.hist(samples_t, bins=80, density=True, alpha=0.7, color="coral")
    ax.set_xlim(-5, 5)
    ax.set_ylim(0, 0.55)
    ax.set_title(f"t = {t_val}")
    if t_val == 999:
        xs = np.linspace(-5, 5, 200)
        ax.plot(
            xs,
            np.exp(-(xs**2) / 2) / np.sqrt(2 * np.pi),
            "r--",
            linewidth=2,
            label="start: N(0,1)",
        )
        ax.legend()
plt.suptitle(
    "Reverse Process: N(0,1) → Bimodal  (top-left to bottom-right)", fontsize=12
)
plt.tight_layout()
plt.show()

# %% [markdown]
# ## 6. Overlap: Generated vs Original
#
# How well did we recover the original distribution? Plot both on the same axes.

# %%
plt.figure(figsize=(10, 4))
original = as_numpy(sample_target(10000))
plt.hist(
    original,
    bins=80,
    density=True,
    alpha=0.5,
    label="Original target",
    color="steelblue",
)
plt.hist(
    as_numpy(x_final),
    bins=80,
    density=True,
    alpha=0.5,
    label="Generated by reverse process",
    color="coral",
)
plt.xlim(-5, 5)
plt.xlabel("x")
plt.ylabel("density")
plt.legend()
plt.title("Original vs generated — should match closely")
plt.tight_layout()
plt.show()

# %% [markdown]
# ## 7. The Whole Function The Network Learned
#
# The U-Net in the image DDPM was incomprehensible — 18M parameters doing
# things to 64×64×3 tensors. Here the network is a function from (x, t) to
# predicted noise, both scalar-valued. We can plot the entire learned function
# as a 2D heatmap.
#
# **The x-axis is timestep, the y-axis is x-value, and color is predicted noise.**
# This is *literally everything the network has learned*. It's one image.

# %%
xs = torch.linspace(-5, 5, 200, device=device)
ts = torch.arange(0, schedule.T, 5, device=device)

X, T = torch.meshgrid(xs, ts.float(), indexing="ij")
x_flat = X.flatten()
t_flat = T.flatten().long()

with torch.no_grad():
    eps_pred_grid = model(x_flat, t_flat).reshape(X.shape)

plt.figure(figsize=(11, 5))
vmax = float(eps_pred_grid.abs().max())
plt.imshow(
    as_numpy(eps_pred_grid),
    aspect="auto",
    origin="lower",
    extent=(ts[0].item(), ts[-1].item(), xs[0].item(), xs[-1].item()),
    cmap="RdBu_r",
    vmin=-vmax,
    vmax=vmax,
)
plt.colorbar(label="predicted noise ε_θ(x, t)")
plt.xlabel("timestep t")
plt.ylabel("x")
plt.title("The entire learned function ε_θ(x, t)")
plt.tight_layout()
plt.show()

# %% [markdown]
# **What students should see in this heatmap:**
#
# - **Right side (large t):** the function is roughly linear in x. At high noise,
#   the optimal noise prediction is essentially `ε ≈ x` (because x is mostly
#   noise to begin with). The heatmap shows a smooth color gradient along x.
#
# - **Left side (small t):** the function has interesting structure. Near t=0,
#   the network has learned that values close to x = -3 or x = +3 should be
#   pushed *toward* those modes (noise prediction near zero), while values
#   *between* the modes (around x = 0) should be pushed *outward* (large
#   noise prediction in opposite directions on the two sides of zero).
#
# - **The transition between left and right** is where the interesting "denoising"
#   work happens — the function smoothly interpolates between "you're basically
#   noise, predict the noise as yourself" (high t) and "you're almost a sample,
#   nudge yourself toward the nearest mode" (low t).
#
# This single picture captures the entire generative process. Sampling from
# N(0, 1) and applying the reverse rule is just walking through this heatmap
# from right to left, using the predicted noise at each step to decide where
# to go next.

# %% [markdown]
# ## 8. Discussion / Extensions
#
# Some experiments worth running with students:
#
# 1. **Change the target distribution.** Three modes? Uniform? A Cauchy? The
#    framework just works. Some are easier to recover than others.
#
# 2. **Shrink the network.** Drop hidden size from 128 to 16. What fails first
#    — does it lose modes, smear the modes together, miss one mode entirely?
#
# 3. **Reduce training steps.** What does the recovered distribution look like
#    after only 500 training steps instead of 5000?
#
# 4. **Reduce T at training time.** Retrain with `T=10`. The forward process
#    no longer reaches anything close to N(0, 1) (compute `α_bar_T` and verify),
#    so the reverse process starting from N(0, 1) is starting from the wrong
#    distribution. Sample quality drops dramatically. Why does T need to be
#    large enough to reach near-pure noise?
#
# 5. **Compare to a flow / autoregressive baseline.** For a 1D distribution we
#    could also fit a normalizing flow or a kernel density estimate. How does
#    the diffusion recovery compare to those, and what's the value of going
#    through the full Markov chain when easier methods exist? (Answer: easier
#    methods don't generalize to high dimensions; diffusion does. But for 1D
#    they're competitive.)
