public class Dog extends Pet {
	private String breed;

	public Dog(String name, int age, String breed) {
			super(name, age);
			this.breed = breed;
	}

	@Override
	public String makeSound() {
			return "Woof! Woof!";
	}

	@Override
	public String getFavoriteActivity() {
			return "going for walks";
	}

	public void fetch() {
			System.out.println(getName() + " is playing fetch! Good dog!");
	}
}