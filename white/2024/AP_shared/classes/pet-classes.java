// The abstract base class Pet defines common properties and behaviors for all pets
abstract class Pet {
    // Instance variables that all pets have
    private String name;
    private int age;
    
    // Constructor to initialize a pet with a name and age
    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Getter methods to access private instance variables
    public String getName() { return name; }
    public int getAge() { return age; }
    
    // Abstract methods that all pet types must implement
    // These ensure each type of pet provides its own specific behaviors
    public abstract String makeSound();      // Each pet makes a different sound
    public abstract String getFavoriteActivity();  // Each pet has a different favorite activity
    
    // A concrete method that all pets inherit and can use as-is
    // Uses getClass().getSimpleName() to get the actual type of pet (Dog, Cat, etc.)
    public String getInfo() {
        return name + " is a " + this.getClass().getSimpleName() + 
               " who is " + age + " years old.";
    }
}

// Dog class - a specific type of Pet
class Dog extends Pet {
    // Dogs have an additional breed property
    private String breed;
    
    // Constructor calls the parent (super) constructor and sets the breed
    public Dog(String name, int age, String breed) {
        super(name, age);  // Call Pet constructor first
        this.breed = breed;
    }
    
    // Implementation of abstract methods from Pet class
    @Override
    public String makeSound() {
        return "Woof! Woof!";
    }
    
    @Override
    public String getFavoriteActivity() {
        return "going for walks";
    }
    
    // A method specific to dogs only
    public void fetch() {
        System.out.println(getName() + " is playing fetch! Good dog!");
    }
}

// Cat class - another type of Pet
class Cat extends Pet {
    // Cats have a property to track if they're indoor or outdoor
    private boolean indoor;
    
    // Constructor calls the parent constructor and sets indoor status
    public Cat(String name, int age, boolean indoor) {
        super(name, age);
        this.indoor = indoor;
    }
    
    // Implementation of abstract methods from Pet class
    @Override
    public String makeSound() {
        return "Meow!";
    }
    
    @Override
    public String getFavoriteActivity() {
        return "chasing laser pointers";
    }
    
    // A method specific to cats only
    public void scratch() {
        System.out.println(getName() + " is scratching the furniture!");
    }
}

// Bird class - another type of Pet
class Bird extends Pet {
    // Birds have an additional species property
    private String species;
    
    // Constructor calls the parent constructor and sets the species
    public Bird(String name, int age, String species) {
        super(name, age);
        this.species = species;
    }
    
    // Implementation of abstract methods from Pet class
    @Override
    public String makeSound() {
        return "Tweet! Tweet!";
    }
    
    @Override
    public String getFavoriteActivity() {
        return "singing songs";
    }
    
    // A method specific to birds only
    public void fly() {
        System.out.println(getName() + " is flying around the room!");
    }
}
