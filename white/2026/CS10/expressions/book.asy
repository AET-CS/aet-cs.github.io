settings.outformat="pdf";
import "exprtree.asy" as et;
string[] exprs = {"3 2 +", "3 4 2 * +", "12 4 2 + / 8 * 10 - 3 2 + /"};
for (int i = 0; i < exprs.length; ++i) {
  if (i > 0) newpage();
  size(12cm, 0);
  drawtree(rpntree(exprs[i]));
}
