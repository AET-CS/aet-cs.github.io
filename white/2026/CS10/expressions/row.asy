settings.outformat="png";
settings.render=4;
import "exprtree.asy" as et;

string[] exprs = {"3 4 2 * +", "3 4 + 2 *"};
string[] caps  = {"3 + 4 \cdot 2", "(3 + 4) \cdot 2"};

real x = 0;
for (int i = 0; i < exprs.length; ++i) {
  picture p;
  drawtree(p, rpntree(exprs[i]));
  frame f = p.fit(5.5cm);
  label(f, "$" + caps[i] + "$",
        (0.5*(min(f).x + max(f).x), min(f).y), S, fontsize(11pt));
  add(f, (x, 0));
  x += max(f).x - min(f).x + 0.9cm;
}
