// exprtree.asy -- draw an expression parse tree with arbitrary string labels.
// Build the tree from an RPN token string, so the shape is explicit.

real dx = 1.1;        // horizontal gap between adjacent leaves
real dy = 1.3;        // vertical gap between levels
real r  = 0.30;       // node radius
pen opfill  = paleblue;
pen numfill = white;
pen nodeline = black + 0.7bp;
pen edgepen  = gray(0.35) + 0.7bp;

struct node {
  string label;
  node left, right;
  pair pos;
}

bool isleaf(node n) { return n.left == null && n.right == null; }

node leaf(string s) {
  node n = new node;
  n.label = s;
  return n;
}

node op(string s, node l, node r) {
  node n = new node;
  n.label = s; n.left = l; n.right = r;
  return n;
}

bool isop(string t) {
  return t == "+" || t == "-" || t == "*" || t == "/" || t == "^";
}

// Build a tree from RPN, e.g. "3 4 2 * +"
node rpntree(string s) {
  string[] tok = split(s, " ");
  node[] stack;
  for (int i = 0; i < tok.length; ++i) {
    string t = tok[i];
    if (t == "") continue;
    if (isop(t)) {
      if (stack.length < 2) abort("not enough operands for '" + t + "'");
      node rt = stack[stack.length-1]; stack.delete(stack.length-1);
      node lt = stack[stack.length-1]; stack.delete(stack.length-1);
      stack.push(op(t, lt, rt));
    } else {
      stack.push(leaf(t));
    }
  }
  if (stack.length != 1)
    abort("expression left " + string(stack.length) + " values on the stack");
  return stack[0];
}

int depth(node n) {
  if (isleaf(n)) return 0;
  return 1 + max(depth(n.left), depth(n.right));
}

int leaves(node n) {
  if (isleaf(n)) return 1;
  return leaves(n.left) + leaves(n.right);
}

// Leaves get consecutive slots; parents sit centred over their children.
int slot = 0;
void place(node n, int depth = 0) {
  if (isleaf(n)) {
    n.pos = (slot*dx, -depth*dy);
    ++slot;
  } else {
    place(n.left,  depth+1);
    place(n.right, depth+1);
    n.pos = (0.5*(n.left.pos.x + n.right.pos.x), -depth*dy);
  }
}

void drawedges(picture pic = currentpicture, node n) {
  if (isleaf(n)) return;
  node[] kids = {n.left, n.right};
  for (node k : kids) {
    pair d = unit(k.pos - n.pos);
    draw(pic, (n.pos + r*d) -- (k.pos - r*d), edgepen);
    drawedges(pic, k);
  }
}

void drawnodes(picture pic = currentpicture, node n) {
  path c = circle(n.pos, r);
  filldraw(pic, c, isleaf(n) ? numfill : opfill, nodeline);
  label(pic, "$" + n.label + "$", n.pos);
  if (isleaf(n)) return;
  drawnodes(pic, n.left);
  drawnodes(pic, n.right);
}

// Draws root with its leftmost leaf in column `start`.
// Returns the first free column to the right, so trees can be laid out
// in a row sharing one coordinate system (and therefore one scale).
int drawtree(picture pic = currentpicture, node root, int start = 0) {
  slot = start;
  place(root);
  drawedges(pic, root);
  drawnodes(pic, root);
  return slot;
}
