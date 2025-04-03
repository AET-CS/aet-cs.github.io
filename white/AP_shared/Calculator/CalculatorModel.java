import java.util.Stack;

/**
 * Model class for the Calculator application.
 * Handles the data and calculations for the calculator.
 */
public class CalculatorModel {

    // Current display value as a string
    private String displayValue;

		// *****************************************************
    // Stacks for operators and operands
		// define operatorStack and operandStack
		// YOU SHOULD change to DebugStack once that is running.
		// *****************************************************
		// YOUR CODE HERE
		private Stack<Double> operandStack;
		private Stack<String> operatorStack;

    // Current calculated result
    private double currentResult;

    // Store for memory functions (can be expanded later)
    @SuppressWarnings("unused")
		private double memoryStore;

    /**
     * Constructor initializes the calculator model.
     */
    public CalculatorModel() {
        displayValue = "0";
        operatorStack = new DebugStack<>();
        operandStack = new DebugStack<>();
        currentResult = 0.0;
        memoryStore = 0.0;
    }

    /**
     * Clears the calculator state.
     */
    public void clear() {
        displayValue = "0";
        operatorStack.clear();
        operandStack.clear();
        currentResult = 0.0;
    }

		/**
     * Appends a digit to the current display value.
     *
     * @param digit The digit to append
     */
    public void appendDigit(String digit) {
        if (displayValue.equals("0")) {
            displayValue = digit;
        } else {
            displayValue += digit;
        }
    }

    /**
     * Appends a decimal point to the current display value if not already present.
     */
    public void appendDecimalPoint() {
			// YOUR CODE
    }

    /**
     * Removes the last character from the display value.
     */
    public void backspace() {
			// YOUR CODE
    }

		public void calculate() {
			// use recent operands and operators and the stacks
			// to perform one calculation
			// update the display with the result
			// and the stacks accordingly

			// YOUR CODE
		}

		public void reduce(String operator) {
			// called by operator() when there are enough operands and operators
			// to perform a calculation
			// gets the top two operands and the top operator
			// performs the calculation

			// YOUR CODE
		}


		public void operator(String operator) {
			// based on the current operator just pressed

			// get the current display value and push it to the operand stack
			double operand = Double.parseDouble(displayValue);
			operandStack.push(operand);

			// reduce the stacks if there are enough operands and operators
			// to perform a calculation
			if (operandStack.size() >= 2 && operatorStack.size() >= 1) {
				reduce(operator);
			}
			// push the operator to the operator stack
			if (operator != Operator.EQUALS) {
				operatorStack.push(operator);
			}
		}

		// replace the top operator on the operator stack
		public void replaceOperator(String operator) {
			if (operatorStack.size() > 0) {
				operatorStack.pop();
			}
			operatorStack.push(operator);
		}

		// return the precedence of an operator
		// (will be used when you implement order of operations)
		public int precedence(String operator) {
			// YOUR CODE
			return 0;
		}

    /**
     * Gets the current display value.
     *
     * @return The current display value
     */
    public String getDisplayValue() {
        return displayValue;
    }

    /**
     * Sets the display value.
     *
     * @param value The new display value
     */
    public void setDisplayValue(String value) {
        this.displayValue = value;
    }

    /**
     * Gets the current calculated result.
     *
     * @return The current result
     */
    public double getCurrentResult() {
        return currentResult;
    }

    /**
     * Sets the current result.
     *
     * @param result The new result
     */
    public void setCurrentResult(double result) {
        this.currentResult = result;
    }

    /**
     * Pushes a value to the operands stack.
     *
     * @param value The value to push
     */
    public void pushOperand(double value) {
        operandStack.push(value);
    }

    /**
     * Pushes an operator to the operators stack.
     *
     * @param operator The operator to push
     */
    public void pushOperator(String operator) {
        operatorStack.push(operator);
    }

    /**
     * Gets the operands stack.
     *
     * @return The operands stack
     */
    public Stack<Double> getOperandStack() {
        return operandStack;
    }

    /**
     * Gets the operators stack.
     *
     * @return The operators stack
     */
    public Stack<String> getOperatorStack() {
        return operatorStack;
    }
}
