import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


/**
 * Controller class for the Calculator application.
 * Handles user input and updates the model and view accordingly.
 */
public class CalculatorController implements ActionListener {

    private CalculatorModel model;
    private CalculatorUI view;
		// Enum to track the last key pressed. Should be updated on each press
		private Keys lastKeyPress = Keys.NONE;
		private enum Keys {
			DIGIT, DECIMAL, OPERATOR, CLEAR, BACKSPACE, NONE
		}

    /**
     * Constructor initializes the controller with model and view references.
     *
     * @param model The calculator model
     * @param view The calculator UI view
     */
    public CalculatorController(CalculatorModel model, CalculatorUI view) {
        this.model = model;
        this.view = view;

        // Register this controller as an action listener for all buttons
        this.view.registerActionListener(this);

        // Initialize the display
        updateDisplay();
    }

    /**
     * Handles button click events.
     *
     * @param e The action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the source button and its text
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();


        // Handle different button types
        switch (buttonText) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                // Handle digit buttons
                handleDigit(buttonText);
                break;
            case ".":
                // Handle decimal point button
                handleDecimalPoint();
                break;
            case "C":
                // Handle clear button
                handleClear();
                break;
            case "←":
                // Handle backspace button
                handleBackspace();
                break;
						case Operator.PLUS: case Operator.MINUS: case Operator.TIMES: case Operator.DIVIDE:
						case Operator.EQUALS: case Operator.EXPONENT:
								// Handle operator buttons
								handleOperator(buttonText);
								break;
            default:
                // Other operations and key presses will be implemented here
								// YOUR CODE AS NEEDED
                break;
        }

        // Update the display
        updateDisplay();
    }

    /**
     * Handles digit button clicks.
     *
     * @param digit The digit that was clicked
     */
    private void handleDigit(String digit) {
				// Check if the last key press was a digit
				if (lastKeyPress == Keys.DIGIT || lastKeyPress == Keys.DECIMAL) {
					// If the last key was a digit or decimal, append the new digit
					model.appendDigit(digit);
				} else {
						// If the last key was not a digit, set the display to the new digit
						model.setDisplayValue(digit);
				}
				// Update the last key press
				lastKeyPress = Keys.DIGIT;
    		}

    /**
     * Handles decimal point button click.
     */
    private void handleDecimalPoint() {
				// Check if the last key press was a digit
				if (lastKeyPress == Keys.DIGIT || lastKeyPress == Keys.DECIMAL) {
					// If the last key was a digit or decimal, append the decimal point
        	model.appendDecimalPoint();
				} else {
					// If the last key was not a digit, set the display to "0."
					model.setDisplayValue("0.");
				}
				// Update the last key press
				lastKeyPress = Keys.DECIMAL;
    }

    /**
     * Handles clear button click.
     */
    private void handleClear() {
				// YOUR CODE
		}

    /**
     * Handles backspace button click.
     */
    private void handleBackspace() {
			// YOUR CODE
    }

		private void handleOperator(String operator) {
			model.operator(operator);
			// Update the last key press
			lastKeyPress = Keys.OPERATOR;
		}

    /**
     * Updates the calculator display with the current value from the model.
     */
    private void updateDisplay() {
        view.setDisplayText(model.getDisplayValue());
    }
}
