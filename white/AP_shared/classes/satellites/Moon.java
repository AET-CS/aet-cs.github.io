package satellites;

// Concrete subclass for moons
public class Moon extends Satellite {
    private Planet parentPlanet;

    public Moon(String name, double mass, double orbitalPeriod, Planet parentPlanet) {
        super(name, mass, orbitalPeriod);
        this.parentPlanet = parentPlanet;
    }

    @Override
    public String getClassification() {
        return "Moon of " + parentPlanet.getName();
    }

    @Override
    protected double getOrbitalRadius() {
        return parentPlanet.getOrbitalRadius() * 0.1;  // Simplified
    }
}
