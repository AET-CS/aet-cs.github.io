settings.outformat="svg";
settings.render=8;
import "exprtree.asy" as et;

string[] exprs = {"3 2 +", "3 4 2 * +", "3 4 + 2 *"};
string[] names = {"multi_a", "multi_b", "multi_c"};

for (int i = 0; i < exprs.length; ++i) {
  picture p;
  size(p, 6cm, 0);
  drawtree(p, rpntree(exprs[i]));
  shipout(names[i], p);
}
