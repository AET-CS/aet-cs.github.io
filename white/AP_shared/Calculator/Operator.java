/**
 * The Operator class defines a collection of constants representing
 * various mathematical and calculator-related operators and functions.
 * These constants can be used to standardize operator symbols and function
 * names across a calculator application.
 *
 * Supported operators and functions include:
 * - Basic arithmetic operations: addition, subtraction, multiplication, and division.
 * - Special operations: square root, square, percentage, and exponentiation.
 * - Trigonometric functions: sine, cosine, and tangent.
 * - Logarithmic function: natural logarithm (ln).
 * - Constants: pi (π) and Euler's number (e).
 * - Memory operations: store, recall, clear, add, and subtract.
 * - Other operations: factorial, decimal point, parentheses, and backspace.
 *
 * Each constant is represented as a String to ensure compatibility
 * with user input and display elements in a graphical user interface.
 *
 * Example usage:
 *     String additionOperator = Operator.PLUS; // "+"
 *     String sineFunction = Operator.SIN;     // "sin"
 *
 * @author Patrick White
 * @version 1.0
 */
public class Operator {
	public static final String PLUS = "+";
	public static final String MINUS = "-";
	public static final String TIMES = "×";
	public static final String DIVIDE = "÷";
	public static final String EQUALS = "=";
	public static final String CLEAR = "C";
	public static final String BACKSPACE = "←";
	public static final String DECIMAL = ".";
	public static final String PERCENT = "%";
	public static final String SQUARE_ROOT = "√";
	public static final String SQUARE = "x²";
	public static final String OPEN_PARENTHESIS = "(";
	public static final String CLOSE_PARENTHESIS = ")";
	public static final String SIN = "sin";
	public static final String COS = "cos";
	public static final String TAN = "tan";
	public static final String LOG = "ln";
	public static final String PI = "π";
	public static final String E = "e";
	public static final String FACTORIAL = "!";
	public static final String EXPONENT = "^";
	public static final String MEMORY_STORE = "M";
	public static final String MEMORY_RECALL = "MR";
	public static final String MEMORY_CLEAR = "MC";
	public static final String MEMORY_ADD = "M+";
	public static final String MEMORY_SUBTRACT = "M-";
}