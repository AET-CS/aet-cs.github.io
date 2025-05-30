
public abstract class Person {
	
	public static final int OTHER = 0;
	public static final int FEMALE = 1;
	public static final int MALE = 2;
	
	private String name;
	private int birthYear;
	private int gender;
	
	public Person (String name, int birthYear, int gender) {
		
		this.name = name;
		this.birthYear = birthYear;
		this.gender = gender;
	}
	
	public abstract void doWork();
	public abstract int getExperience(int currentYear);
	
	/**
	 * Gets the name of the person.
	 * @return the name of the person.
	 */
	public String getName() {
		return name;
	}
	
	public int getBirthYear() {
		return birthYear;
	}
	
	public int getGender() {
		return gender;
	}
	
	public String getGenderAsString() {
		if (gender == OTHER) {
			return "Other";
		} else if (gender == FEMALE) {
			return "Female";
		} else {
			return "Male";
		}
	}
	
	public String toString() {
		return name + " " + birthYear + " " + getGenderAsString();
	}
}
