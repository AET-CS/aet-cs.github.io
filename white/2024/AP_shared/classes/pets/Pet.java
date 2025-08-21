public abstract class Pet {
	private String name;
	private int age;

	public Pet(String name, int age) {
			this.name = name;
			this.age = age;
	}

	public String getName() { return name; }
	public int getAge() { return age; }

	public abstract String makeSound();
	public abstract String getFavoriteActivity();

	public String getInfo() {
			return name + " is a " + this.getClass().getSimpleName() +
						 " who is " + age + " years old.";
	}
}