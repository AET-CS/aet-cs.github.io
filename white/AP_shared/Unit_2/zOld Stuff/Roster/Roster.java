import java.util.ArrayList;
import java.util.List;

public class Roster {

	private List<Student> roster;
	private String className;
	
	/**
	 * Creates a <code>Roster</code>
	 * @param roster
	 * @param className
	 */
	public Roster(List<Student> roster, String className) {
		this.roster = roster;
		this.className = className;
	}
	
	/**
	 * Returns the name of the class.
	 * @return the name of the class.
	 */
	public String getClassName() {
		return className;
	}
	
	
	/**
	 * Returns a list of Strings of all the names of students in the roster
	 * @return a list of Strings of all the names of students in the roster
	 */
	public List<String> getNames() {
		List<String> names = new ArrayList<String>();
		
		for (Student student : roster) {
			names.add(student.getName());
		}
		
		return names;
	} 
	
	/**
	 * Returns a list of unique majors as a <code>String</code>
	 * @return a list of unique majors 
	 */
	public List<String> getMajors() {
		
		List<String> majors = new ArrayList<String>();
		
		for (Student student : roster) {
			if (!majors.contains(student.getMajor())) {
				majors.add(student.getMajor());
			}
		}
		
		return majors;
	}
	
	
	/**
	 * Returns a list of all <code>Student</code> objects whose name has the 
	 * given sequence of characters of <code>str</code> somewhere in the 
	 * name of the student.
	 * @param str the target string of characters
	 * @return a list of all <code>Student</code> objects whose name contains the
	 * given target string 
	 */
	public List<Student> nameContains(String str) {
		List<Student> students = new ArrayList<Student>();
		
		for (Student student : roster) {
			if (student.getName().contains(str)) {
				students.add(student);
			}
		}
		
		return students;
	}
	
	
	/**
	 * Returns a new <code>Roster</code> of students occurring lexicographically before
	 * the target <code>name</code> while ignoring case-sensitivity 
	 * @param name the target string
	 * @return a new <code>Roster</code> of students occurring lexicographically before
	 * the target <code>name</code> while ignoring case-sensitivity
	 */
	public Roster getNamesBefore (String name) {
		List<Student> subRoster = new ArrayList<Student>();
		
		for (int i = 0; i < roster.size(); i++) {
			if (roster.get(i).getName().toLowerCase().compareTo(name.toLowerCase()) < 0) {
				subRoster.add(roster.get(i));
			}
		}
		
		Roster newRoster = new Roster(subRoster, className);
		return newRoster;
	}
	
	@Override
	public String toString() {
		String string = className + ": \n";
		
		for (Student student : roster) {
			string += student + "\n";
		}
		
		return string;
	}
	
}
