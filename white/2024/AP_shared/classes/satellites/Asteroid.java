package satellites;

// Concrete subclass for asteroids
public class Asteroid extends Satellite {
	private String composition;

	public Asteroid(String name, double mass, double orbitalPeriod, String composition) {
			super(name, mass, orbitalPeriod);
			this.composition = composition;
	}

	@Override
	public String getClassification() {
			return "Asteroid (" + composition + ")";
	}

	@Override
	protected double getOrbitalRadius() {
			return 2.7 * 1.496e11;  // Average asteroid belt distance, simplified
	}
}
