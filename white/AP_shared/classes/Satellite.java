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

// Main class demonstrating usage with ArrayList
public class SolarSystemDemo {
    public static void main(String[] args) {
        ArrayList<Satellite> solarSystem = new ArrayList<>();

        // Create some planets
        Planet earth = new Planet("Earth", 5.97e24, 365.26, 3);
        Planet mars = new Planet("Mars", 6.42e23, 687, 4);

        // Create some moons
        Moon luna = new Moon("Luna", 7.34e22, 27.3, earth);
        Moon phobos = new Moon("Phobos", 1.06e16, 0.32, mars);

        // Create some asteroids
        Asteroid ceres = new Asteroid("Ceres", 9.38e20, 1683, "rocky");
        Asteroid vesta = new Asteroid("Vesta", 2.59e20, 1325, "metallic");

        // Add all objects to the ArrayList
        solarSystem.add(earth);
        solarSystem.add(mars);
        solarSystem.add(luna);
        solarSystem.add(phobos);
        solarSystem.add(ceres);
        solarSystem.add(vesta);

        // Demonstrate polymorphism
        System.out.println("Solar System Objects:");
        for(Satellite obj : solarSystem) {
            System.out.println(obj.toString());  // Uses polymorphic toString()
            System.out.printf("Orbital velocity: %.2f km/s%n",
                obj.getOrbitalVelocity() / 1000);
            System.out.println();
        }

        // Demonstrate filtering by type
        System.out.println("Moons only:");
        for(Satellite obj : solarSystem) {
            if(obj instanceof Moon) {
                System.out.println(obj.getName());
            }
        }
    }
}
