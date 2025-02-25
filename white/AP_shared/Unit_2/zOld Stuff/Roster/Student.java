
public class Student extends Person {

	private String major;
	
	/**
	 * Creates a student with the given name, birth year, gender, and major
	 * @param name the name of the student
	 * @param birthYear the birth year of the student
	 * @param gender the gender of the student
	 * @param major the major of the student
	 */
	public Student(String name, int birthYear, int gender, String major) {
		super(name, birthYear, gender);
		this.major = major;
	}
	
	/**
	 * Returns the major
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}

	public void doWork() {
		System.out.println(getName() + " goes to school.");
	}
	
	public int getExperience(int currentYear) {
		return currentYear - getBirthYear() - 6;
	}
	
	
	@Override
	/**
	 * Returns the first name of the student.
	 * @return the first name of the student.
	 */
	public String getName() {
		String firstName = "";
		int index = super.getName().indexOf(" ");
		firstName = super.getName().substring(0, index);
		return firstName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + " " + major;
	}
	
	
	
}
