public class Cat extends Pet {
	private boolean indoor;

	public Cat(String name, int age, boolean indoor) {
			super(name, age);
			this.indoor = indoor;
	}

	@Override
	public String makeSound() {
			return "Meow!";
	}

	@Override
	public String getFavoriteActivity() {
			return "chasing laser pointers";
	}

	public void scratch() {
			System.out.println(getName() + " is scratching the furniture!");
	}
}