public class Spaceship {
    // Instance variables
    private String name;
    private double fuelLevel;
    private int crewCapacity;
    private boolean shieldsActive;
    private String[] cargo;
    private int cargoCount;

    // Constants
    public static final double MAX_FUEL = 100.0;
    public static final int MAX_CARGO = 10;

    // Constructor
    public Spaceship(String name, int crewCapacity) {
        this.name = name;
        this.crewCapacity = crewCapacity;
        this.fuelLevel = MAX_FUEL;
        this.shieldsActive = false;
        this.cargo = new String[MAX_CARGO];
        this.cargoCount = 0;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getFuelLevel() { return fuelLevel; }
    public int getCrewCapacity() { return crewCapacity; }
    public boolean areShieldsActive() { return shieldsActive; }

    // Methods
    public void activateShields() {
        if (fuelLevel >= 10) {
            shieldsActive = true;
            fuelLevel -= 10;
        }
    }

    public void deactivateShields() {
        shieldsActive = false;
    }

    public boolean addCargo(String item) {
        if (cargoCount < MAX_CARGO) {
            cargo[cargoCount] = item;
            cargoCount++;
            return true;
        }
        return false;
    }

    public String removeCargo() {
        if (cargoCount > 0) {
            String item = cargo[cargoCount - 1];
            cargo[cargoCount - 1] = null;
            cargoCount--;
            return item;
        }
        return null;
    }

    public void refuel(double amount) {
        fuelLevel = Math.min(fuelLevel + amount, MAX_FUEL);
    }

    // toString method
    public String toString() {
        return String.format("Spaceship %s: [Fuel: %.1f, Crew: %d, Shields: %s, Cargo: %d/%d]",
            name, fuelLevel, crewCapacity, shieldsActive ? "On" : "Off", cargoCount, MAX_CARGO);
    }
}
