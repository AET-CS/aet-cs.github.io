package satellites;

// Concrete subclass for planets
public class Planet extends Satellite {
	private int position;  // Position from the sun

	public Planet(String name, double mass, double orbitalPeriod, int position) {
			super(name, mass, orbitalPeriod);
			this.position = position;
	}

	@Override
	public String getClassification() {
			return "Planet";
	}

	@Override
	protected double getOrbitalRadius() {
			return position * 1.496e11;  // AU to meters, simplified
	}
}
