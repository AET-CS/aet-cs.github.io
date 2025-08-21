package satellites;

// Abstract base class
public abstract class Satellite {
	private String name;
	private double mass;  // in kg
	private double orbitalPeriod;  // in days

	public Satellite(String name, double mass, double orbitalPeriod) {
			this.name = name;
			this.mass = mass;
			this.orbitalPeriod = orbitalPeriod;
	}

	// Abstract method that subclasses must implement
	public abstract String getClassification();

	// Common method for all satellites
	public double getOrbitalVelocity() {
			return 2 * Math.PI * getOrbitalRadius() / (orbitalPeriod * 24 * 3600);  // in m/s
	}

	// Abstract method - each type will calculate this differently
	protected abstract double getOrbitalRadius();

	public String getName() {
			return name;
	}

	@Override
	public String toString() {
			return String.format("%s (%s) - Period: %.1f days",
					name, getClassification(), orbitalPeriod);
	}
}