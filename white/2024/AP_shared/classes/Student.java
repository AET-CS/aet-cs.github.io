/* Assignment: Student Class Implementation
 *
 * Create a Student class that manages student course schedules.
 * Requirements:
 * 1. Private instance variables for:
 *    - name (String)
 *    - id (int)
 *    - courses (array of 8 Strings)
 *
 * 2. Implement:
 *    - Two constructors
 *    - Methods for managing courses (add, drop, swap)
 *    - Getters and setters for name and id
 *    - toString method
 */

public class Student {
    // TODO: Declare private instance variables

    // TODO: Create constructor that takes name and id
    // Initialize courses array with size 8 (periods 1-8)

    // TODO: Create constructor that takes only name
    // Should call first constructor with id = -1

    // TODO: Implement getName method
    // Should return student's name

    // TODO: Implement setName method
    // Parameter: String name
    // Should update student's name

    // TODO: Implement getId method
    // Should return student's id

    // TODO: Implement setId method
    // Parameter: int id
    // Should update student's id

    // TODO: Implement addCourse method
    // Parameters: int periodNumber, String courseName
    // Should validate period number (1-8)
    // Should store course name in appropriate array index

    // TODO: Implement dropCourse method
    // Parameter: int periodNumber
    // Should validate period number (1-8)
    // Should set course name of specified period to null
    // Should return dropped course name or null

    // TODO: Implement swapCourses method
    // Parameters: int period1, int period2
    // Should validate both period numbers (1-8)
    // Should swap courses in the specified periods
    // Should return true if swap successful, false if either period invalid

    // TODO: Implement toString method
    // Should return name, id, and list of courses by period
}
