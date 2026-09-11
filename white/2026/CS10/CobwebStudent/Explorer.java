/**
 * Explores fixed points by drawing cobweb diagrams. Pick a function from the
 * menu, drag the r slider, click to start an orbit.
 * <p>
 * Fill in each INSERT LOCATION. The file compiles as given, so you can run it
 * at any point to see how far you have got.
 */
public class Explorer {

    public static final int CURVE_POINTS = 800;

    // ---- the catalog -------------------------------------------------------
    // To add a function, write one more class here and add it to the array.

    public static class Cos extends Function {
        public Cos() {
            super("cos x");
        }

        public double apply(double x, double r) {
            return Math.cos(x);
        }
    }

    /// INSERT LOCATION 1
    /// 
    /// 
    /// 
    /// 
    /// 
    /// 
    ///

    public static Function[] catalog() {
        return new Function[] { new Cos(),
                /// INSERT LOCATION 2
        };
    }

    // ---- building arrays ---------------------------------------------------

    public static double[] sampleX(double lo, double hi, int n) {
        // INSERT LOCATION 3
        return null;
    }

    public static double[] applyF(double[] xs, Function f, double r) {
        // INSERT LOCATION 3
        return null;
    }

    public static double[] orbit(double x0, int steps, Function f, double r) {
        // INSERT LOCATION 3
        return null;
    }

    public static double min(double[] a) {
        // INSERT LOCATION 3
        return 0;
    }

    public static double max(double[] a) {
        // INSERT LOCATION 3
        return 0;
    }

    // ---- putting it together -----------------------------------------------

    public static void main(String[] args) {
        Cobweb.open(catalog());
        Cobweb.initAnimation(25);

        boolean haveStart = false;
        double x0 = 0;

        while (true) {
            String what = Cobweb.nextEvent();

            Function f = Cobweb.getFunction();
            double r = Cobweb.getParameter();

            // The curve always gets rebuilt: the view, the function or r may have
            // changed, and we only sample the part that is on screen.
            double[] xs = sampleX(Cobweb.viewLo(), Cobweb.viewHi(), CURVE_POINTS);
            Cobweb.drawCurve(xs, applyF(xs, f, r));

            if (what.equals("click")) {
                x0 = Cobweb.getClickX();
                haveStart = true;
            }
            if (what.equals("function")) {
                haveStart = false;
            }

            // While the slider is moving we leave the old web alone -- it would just
            // thrash. Everything else rebuilds it.
            if (haveStart && !what.equals("parameter")) {
                double[] o = orbit(x0, Cobweb.getOrbitLength(), f, r);
                if (what.equals("click"))
                    Cobweb.animateCobweb(o);
                else
                    Cobweb.drawCobweb(o);

                // How far does this orbit roam? Once min and max work, this
                // tells you whether it settled down or wandered off.
                if (o != null)
                    System.out.printf("start %.4f   orbit stays within [%.4f, %.4f]%n", x0, min(o), max(o));
            }
        }
    }
}
