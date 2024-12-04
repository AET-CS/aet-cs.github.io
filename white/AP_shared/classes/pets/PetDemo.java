public class PetDemo {
	public static void main(String[] args) {
		Pet perry = new Dog("perry", 10, "doberman");
		perry.makeSound();
		System.out.println(perry.getInfo());
		((Dog)perry).fetch();
	}
}
