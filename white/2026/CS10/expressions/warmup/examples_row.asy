// Five trees, 2 -> 4 operators, captioned only with their value.
settings.outformat = "svg";
import "exprtree.asy" as et;

size(24cm, 0);

string[] exprs = {
  "3 4 2 * +",           // 2 operators
  "3 4 + 2 *",           // 2 operators, same leaves as above
  "20 4 / 3 1 - *",      // 3 operators, balanced
  "12 2 / 3 - 5 *",      // 3 operators, left-skewed
  "20 4 / 3 1 - * 2 +"   // 4 operators
};

string[] vals = {"11", "14", "10", "15", "12"};

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
