public class Student {
    private String name;
    private int id;
    private String[] courses;
    
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.courses = new String[8];
    }
    
    public Student(String name) {
        this(name, -1);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public boolean addCourse(int periodNumber, String courseName) {
        if (periodNumber < 1 || periodNumber > 8) {
            return false;
        }
        courses[periodNumber - 1] = courseName;
        return true;
    }
    
    public String dropCourse(int periodNumber) {
        if (periodNumber < 1 || periodNumber > 8) {
            return null;
        }
        String dropped = courses[periodNumber - 1];
        courses[periodNumber - 1] = null;
        return dropped;
    }
    
    public boolean swapCourses(int period1, int period2) {
        if (period1 < 1 || period1 > 8 || period2 < 1 || period2 > 8) {
            return false;
        }
        String temp = courses[period1 - 1];
        courses[period1 - 1] = courses[period2 - 1];
        courses[period2 - 1] = temp;
        return true;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student: ").append(name);
        sb.append(" (ID: ").append(id).append(")\n");
        sb.append("Schedule:\n");
        
        for (int i = 0; i < courses.length; i++) {
            sb.append("Period ").append(i + 1).append(": ");
            sb.append(courses[i] == null ? "Empty" : courses[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
