settings.outformat = "svg";
import "exprtree.asy" as et;
size(8cm, 0);
node t = rpntree("1 2 3 4 + - *");
drawtree(currentpicture, t);