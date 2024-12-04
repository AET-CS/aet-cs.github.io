public class PetDemo {
	public static void main(String[] args) {
		Pet perry = new Dog("perry", 10, "doberman");
		System.out.println(perry.makeSound());
		System.out.println(perry.getInfo());
		((Dog)perry).fetch();
	}
}
