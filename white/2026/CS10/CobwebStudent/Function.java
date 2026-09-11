/**
 * One function to study, together with the things the interface needs to know
 * about it: what to call it, what window to open on, and whether it has an
 * adjustable parameter r.
 * <p>
 * To add a function, write a subclass and override apply. If your function
 * has no parameter, ignore r.
 */
public abstract class Function {

    private final String name;
    // Every function opens on the same modest window; zoom out if you need
    // more room.  (Making this per-function is a natural extension.)
    private static final double WINDOW = 2;
    private final boolean hasR;
    private final double rMin, rMax, rStart;

    // A function with no adjustable parameter.
    public Function(String name) {
        this(name, false, 0, 0, 0);
    }

    // A function with a parameter r that the slider can change.
    public Function(String name, double rMin, double rMax, double rStart) {
        this(name, true, rMin, rMax, rStart);
    }

    private Function(String name, boolean hasR,
            double rMin, double rMax, double rStart) {
        if (hasR && (!Double.isFinite(rMin) || !Double.isFinite(rMax)
                || !Double.isFinite(rStart) || rMin >= rMax
                || rStart < rMin || rStart > rMax))
            throw new IllegalArgumentException("rStart must be within a non-empty finite r range");
        this.name = name;
        this.hasR = hasR;
        this.rMin = rMin;
        this.rMax = rMax;
        this.rStart = rStart;
    }

    // The function itself. Ignore r if this function does not use one.
    public abstract double apply(double x, double r);

    public String name() {
        return name;
    }

    public double window() {
        return WINDOW;
    }

    public boolean hasR() {
        return hasR;
    }

    public double rMin() {
        return rMin;
    }

    public double rMax() {
        return rMax;
    }

    public double rStart() {
        return rStart;
    }

    public String toString() {
        return name;
    }
}
