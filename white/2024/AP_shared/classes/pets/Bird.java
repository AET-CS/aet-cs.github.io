public class Bird extends Pet {
	private String species;

	public Bird(String name, int age, String species) {
			super(name, age);
			this.species = species;
	}

	@Override
	public String makeSound() {
			return "Tweet! Tweet!";
	}

	@Override
	public String getFavoriteActivity() {
			return "singing songs";
	}

	public void fly() {
			System.out.println(getName() + " is flying around the room!");
	}
}