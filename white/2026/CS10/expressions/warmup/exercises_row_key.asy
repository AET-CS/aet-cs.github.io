// Five trees, 2 -> 4 operators, captioned only with their value.
settings.outformat = "svg";
import "exprtree.asy" as et;

size(32cm, 0);

string[] exprs = {
  "7 2 * 5 3 - / 6 +",           // 4 operators
  "20 4 / 3 * 8 2 - 1 + -",      // 5 operators
  "60 5 / 4 - 2 / 3 + 2 *",      // 5 operators
  "12 2 / 3 + 4 * 20 5 / 2 - +"  // 6 operators
};

string[] vals = {"13", "8", "14", "38"};

int gap = 2;   // blank leaf-columns between trees

int s = 0;
for (int i = 0; i < exprs.length; ++i) {
  node t = rpntree(exprs[i]);
  int start = s;
  s = drawtree(currentpicture, t, start);
  real cx = 0.5*(start + s - 1)*dx;
  label("$" + vals[i] + "$", (cx, -(depth(t) + 0.85)*dy), fontsize(13pt));
  s += gap;
}
