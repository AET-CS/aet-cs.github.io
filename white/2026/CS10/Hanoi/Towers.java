import java.util.ArrayList;

/**
 * Towers of Hanoi.
 *
 * Fill in the three TODOs: move, then isLegal, then hanoi.
 */
public class Towers {

    // pegs.get(p) is peg p; bottom disk at index 0, top disk at the end
    static ArrayList<ArrayList<Integer>> pegs = new ArrayList<ArrayList<Integer>>();
    static HanoiView view;
    static int selected = -1; // peg picked up in manual play, or -1

    /** Put n disks on peg 0, largest at the bottom; pegs 1 and 2 empty. */
    static void setup(int n) {
        pegs.clear();
        for (int p = 0; p < 3; p++) {
            pegs.add(new ArrayList<Integer>());
        }
        for (int d = n; d >= 1; d--) {
            pegs.get(0).add(d);
        }
    }

    /** Can the top disk of 'from' go on top of 'to'? */
    static boolean isLegal(int from, int to) {
        ArrayList<Integer> a = pegs.get(from);
        ArrayList<Integer> b = pegs.get(to);
        // YOU
        return false;
    }

    /** Move the top disk of 'from' onto 'to', then show it. */
    static void move(int from, int to) {
        // YOU
        view.show(from, to);
    }

    /** Move n disks from 'a' to 'b', using 'c' as storage. */
    static void hanoi(int n, int a, int b, int c) {
        // YOU
    }

    public static void main(String[] args) {
        int n = 4;
        setup(n);
        view = new HanoiView(pegs);

        while (true) {
            String event = view.nextEvent();

            if (event.equals("size")) {
                n = view.size();
                setup(n);
                selected = -1;
                view.select(-1);
                view.refresh();
            } else if (event.equals("reset")) {
                setup(n);
                selected = -1;
                view.select(-1);
                view.refresh();
            } else if (event.equals("solve")) {
                setup(n);
                selected = -1;
                view.select(-1);
                view.refresh();
                hanoi(n, 0, 2, 1);
            } else if (event.equals("click")) {
                int peg = view.clickedPeg();
                if (selected == -1) {
                    if (!pegs.get(peg).isEmpty()) {
                        selected = peg;
                        view.select(peg);
                    }
                } else {
                    if (peg != selected && isLegal(selected, peg)) {
                        move(selected, peg);
                    }
                    selected = -1;
                    view.select(-1);
                }
            }
        }
    }
}
