package satellites;
import java.util.*;

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
