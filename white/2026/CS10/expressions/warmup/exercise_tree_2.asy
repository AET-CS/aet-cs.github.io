settings.outformat = "svg";
import "exprtree.asy" as et;
size(8cm, 0);
node t = rpntree("20 4 / 3 * 8 2 - 1 + -");
drawtree(currentpicture, t);