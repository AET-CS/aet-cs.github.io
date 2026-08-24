settings.outformat = "svg";
import "exprtree.asy" as et;
size(8cm, 0);
node t = rpntree("12 2 / 3 + 4 * 20 5 / 2 - +");
drawtree(currentpicture, t);