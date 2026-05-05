---
title: Transformer NMT Notebook — Comprehension Questions
---

These are reading-check questions to help you work through the code. They're not tricky — if you read the section, you should be able to answer them. Copy and paste into a doc/google doc/etc.

---

## Section 1: Positional Encoding

1. Why does the Transformer need positional encoding but our RNN did not?

2. In the code, `pe` is registered with `self.register_buffer('pe', pe)` instead of as an `nn.Parameter`. What's the difference? (Hint: does the positional encoding change during training?)

3. The positional encoding is *added* to the embedding, not concatenated. What does this mean for the output shape?

---

## Section 2: Scaled Dot-Product Attention

1. What are the shapes of Q, K, and V if we have a batch of 4 sequences, 8 attention heads, sequence length 10, and d_k = 32?

2. Why do we divide the dot product by √d_k? What would happen if we skipped this step?

3. When we apply a mask with `masked_fill(mask == 0, float('-inf'))`, what effect does this have after the softmax? Why negative infinity specifically?

---

## Section 3: Multi-Head Attention

1. With d_model = 256 and 8 heads, what is d_k (the dimension per head)?

2. The code uses `W_q = nn.Linear(d_model, d_model)` — a single large matrix rather than 8 separate smaller ones. Why is this equivalent? What's the advantage?

3. In the Transformer, this same `MultiHeadAttention` class is used three different ways. What are they, and how does the choice of what you pass as Q, K, V change the behavior?

---

## Section 4: Position-wise Feed-Forward Network

1. The FFN applies the same linear transformation to every position independently. Why is this "position-wise"? Could it mix information between positions?

2. The inner dimension `d_ff` is typically 4× `d_model`. In our model, d_model=256 and d_ff=512. How many parameters does one FeedForward module have? (Count both linear layers and their biases.)

---

## Section 5: Encoder Layer

1. What does "Add & Norm" mean? Write the operation for the self-attention sub-layer in one line using `x`, `self_attn(x)`, and `LayerNorm`.

2. The encoder uses self-attention where Q=K=V=x. In plain English, what is each source token doing?

---

## Section 6: Decoder Layer

1. The decoder layer has three sub-layers while the encoder has two. What's the extra one, and what role does it play?

2. In the masked self-attention, why do we need a causal mask? What would go wrong during training without it?

3. Which sub-layer of the decoder is the direct replacement for Bahdanau attention in our RNN model?

---

## Section 7: Full Transformer Model

1. Look at `make_tgt_mask`. It combines two masks with `pad_mask & causal_mask`. What does each mask prevent?

2. In the `encode` method, the embeddings are multiplied by `math.sqrt(self.d_model)`. Why? (Hint: look at Section 3.4 of the Vaswani paper — it has to do with the relative scale of embeddings vs. positional encodings.)

3. The encoder output is computed once and passed to every decoder layer. How does this compare to our RNN, where the encoder hidden states were also computed once?

---

## Section 8: Smoke Test

1. If the source sequence has 10 tokens and the target has 12 tokens, what is the shape of the output logits? What does each dimension represent?

---

## Section 9: Causal Mask

1. Look at row 0 of the causal mask matrix. What does it mean that position 0 can "only see itself"?

2. How does the causal mask enforce the same constraint that the RNN decoder got automatically from sequential processing?

---

## Section 10: Training Loop

1. The target is split into `tgt_input = tgt[:, :-1]` and `tgt_labels = tgt[:, 1:]`. Why this shift-by-one?

2. The code uses `torch.nn.utils.clip_grad_norm_` with max_norm=1.0. What does gradient clipping do, and why is it important for Transformers?

---

## Section 11: Greedy Inference

1. During training, the decoder processes all target positions in parallel. During inference, it processes them one at a time. Why the difference?

2. The inference loop re-runs the decoder on the entire generated sequence so far at each step. This is inefficient. What optimization (mentioned in the notebook comments) could speed this up?

---

## Section 14: Vocabulary

1. The `encode` method adds `<sos>` at the start and `<eos>` at the end. Why does the model need both of these special tokens?

2. What happens when the model encounters a word during translation that wasn't in the training vocabulary?

---

## Section 16: Learning Rate Schedule

1. The Vaswani LR schedule increases linearly for `warmup_steps`, then decreases. Why not just use a constant learning rate? What could go wrong early in training with a high LR?

2. The optimizer is initialized with `lr=0`. Why is this OK?

---

## Section 17: Training Loop

1. The notebook says "No teacher forcing toggle — the Transformer always uses teacher forcing during training." But doesn't the causal mask prevent cheating? Explain how both of these statements can be true.

2. Sample translations are printed after every epoch. Why is this useful beyond just watching the loss number decrease?

---

## Results

Discuss what you see in the weight matrix examples at the end of the notebook.