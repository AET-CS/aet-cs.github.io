settings.outformat = "svg";
import "exprtree.asy" as et;
size(8cm, 0);
node t = rpntree("60 5 / 4 - 2 / 3 + 2 *");
drawtree(currentpicture, t);