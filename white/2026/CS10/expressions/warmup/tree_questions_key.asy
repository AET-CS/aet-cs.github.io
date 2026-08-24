// Answer trees for the two RPN expressions at the end of the worksheet.
settings.outformat = "svg";
import "exprtree.asy" as et;

size(18cm, 0);

string[] exprs = {
  "1 2 3 4 + - *",
  "1 2 + 3 4 - 5 6 * * /"
};

int gap = 3;
int s = 0;
for (int i = 0; i < exprs.length; ++i) {
  node t = rpntree(exprs[i]);
  int start = s;
  s = drawtree(currentpicture, t, start);
  real cx = 0.5*(start + s - 1)*dx;
  label("$" + string(i + 1) + "$", (cx, -(depth(t) + 0.85)*dy), fontsize(13pt));
  s += gap;
}