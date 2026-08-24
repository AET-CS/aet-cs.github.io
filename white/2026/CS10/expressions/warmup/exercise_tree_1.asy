settings.outformat = "svg";
import "exprtree.asy" as et;
size(8cm, 0);
node t = rpntree("7 2 * 5 3 - / 6 +");
drawtree(currentpicture, t);