<h1 id="avl-trees">AVL Trees</h1>
<p>Now that you’ve had an introduction to AVL trees, let’s take a step
back and look at where this operation called rotation even comes
from.</p>
<h2 id="the-canonical-trees">The Canonical Trees</h2>
<p>Let’s start by taking the numbers 1,2,3 and creating every possible
valid binary search tree from these three numbers.</p>
<div class="question">
<p>Draw your search trees in the space below.</p>
<div class="answer">
<figure>
<img src="./AP_shared/BinaryTree/canonical-trees.png"
alt="Five Trees" />
<figcaption aria-hidden="true">Five Trees</figcaption>
</figure>
<p>These are the five possible arrangements of 1, 2, and 3 in a BST.</p>
</div>
</div>
<h2 id="code-examples">Code Examples</h2>
<div class="codesolution">
<div class="skeleton">
<div id="cb1" class="sourceCode">
<div class="sourceCode" id="cb1"><pre
class="sourceCode java"><code class="sourceCode java"><span id="cb1-1"><a href="#cb1-1" aria-hidden="true" tabindex="-1"></a><span class="bu">Node</span> <span class="fu">leftRotate</span><span class="op">(</span><span class="bu">Node</span> one<span class="op">)</span> <span class="op">{</span></span>
<span id="cb1-2"><a href="#cb1-2" aria-hidden="true" tabindex="-1"></a>    <span class="co">// </span><span class="al">TODO</span><span class="co">: Implement left rotation</span></span>
<span id="cb1-3"><a href="#cb1-3" aria-hidden="true" tabindex="-1"></a></span>
<span id="cb1-4"><a href="#cb1-4" aria-hidden="true" tabindex="-1"></a><span class="op">}</span></span></code></pre></div>
</div>
</div>
<div class="solution">
<div id="cb2" class="sourceCode">
<div class="sourceCode" id="cb2"><pre
class="sourceCode java"><code class="sourceCode java"><span id="cb2-1"><a href="#cb2-1" aria-hidden="true" tabindex="-1"></a><span class="bu">Node</span> <span class="fu">leftRotate</span><span class="op">(</span><span class="bu">Node</span> one<span class="op">)</span> <span class="op">{</span></span>
<span id="cb2-2"><a href="#cb2-2" aria-hidden="true" tabindex="-1"></a>    <span class="bu">Node</span> two <span class="op">=</span> one<span class="op">.</span><span class="fu">right</span><span class="op">;</span></span>
<span id="cb2-3"><a href="#cb2-3" aria-hidden="true" tabindex="-1"></a>    one<span class="op">.</span><span class="fu">right</span> <span class="op">=</span> two<span class="op">.</span><span class="fu">left</span><span class="op">;</span></span>
<span id="cb2-4"><a href="#cb2-4" aria-hidden="true" tabindex="-1"></a>    two<span class="op">.</span><span class="fu">left</span> <span class="op">=</span> one<span class="op">;</span></span>
<span id="cb2-5"><a href="#cb2-5" aria-hidden="true" tabindex="-1"></a>    <span class="cf">return</span> two<span class="op">;</span></span>
<span id="cb2-6"><a href="#cb2-6" aria-hidden="true" tabindex="-1"></a><span class="op">}</span></span></code></pre></div>
</div>
</div>
</div>
