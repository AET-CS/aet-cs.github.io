# Calculator Assignment Instructions

## Overview of Architecture

This calculator application follows the Model-View-Controller (MVC) design pattern:

1. **Model (`CalculatorModel.java`)**: Manages the data and calculation logic, including operand and operator stacks.
2. **View (`CalculatorUI.java`)**: Handles the user interface elements including display and buttons.
3. **Controller (`CalculatorController.java`)**: Processes user input and coordinates between the model and view.
4. **Main Application (`Calculator.java`)**: Entry point that initializes the MVC components.

Supporting classes include:
- `Operator.java`: Constants for calculator operations
- `DebugStack.java`: Extended Stack implementation for debugging

![Calculator Interface](calculator.png){ width=25% }

## Operand and Operator Stacks

The calculator uses two stacks to manage calculations:
- **Operand Stack**: Stores numeric values (operands) for calculations
- **Operator Stack**: Stores operators (+, -, ×, ÷, etc.)

These stacks allow for sequential processing of operations. When operations are performed, values are popped from the stacks, calculations are made, and results are pushed back.

## Methods to Complete

### In `CalculatorController.java`:

1. **`handleClear()`**: Reset the calculator state, clear display and stacks.
2. **`handleBackspace()`**: Remove the last character from the display.

### In `CalculatorModel.java`:

1. **`appendDecimalPoint()`**: Add a decimal point to the current display value if not already present.
2. **`backspace()`**: Remove the last character from the display value.
3. **`calculate()`**: Perform a calculation using operands and operators from the stacks.
4. **`reduce(String operator)`**: Perform one calculation using the top elements of the stacks.
5. **`precedence(String operator)`**: Return the precedence level of an operator (for Phase 2).

### In `DebugStack.java`:

Extend the Stack class to print stack contents after push, pop, and clear operations. For example:
```java
@Override
public E push(E item) {
    E result = super.push(item);
    System.out.println("Push: " + item + ", Stack: " + this);
    return result;
}
```

## Implementation Phases

### Phase 1: Basic Functionality
- Implement all the required methods
- Support basic operations (+, -, ×, ÷)
- Add support for constants π and e (hint: handle in the controller similar to digits)
- Process calculations left-to-right without operator precedence
- Implement DebugStack for debugging

### Phase 2: Operator Precedence
- Implement the `precedence()` method to assign priorities to operators
- Modify `reduce()` and related methods to respect operator precedence
- Ensure that multiplication/division are performed before addition/subtraction

### Phase 3: Extensions (Pick one or more)
- Implement parentheses support
- Add scientific functions (sin, cos, tan, sqrt, etc.)
- Add a 2nd button for arcsin, arccos, etc.
- Implement memory functions (M+, M-, MR, MC)
- Create a graphing feature for simple functions
- Support different number systems (binary, octal, hex)
- Add unit conversions
- Your idea!

## Notes

- The `DebugStack` implementation should be helpful for visualizing how the stacks change during operations
- Remember that in Phase 1, operations are performed left-to-right (no operator precedence)
- For constants like π and e, you'll need to handle these specially in the controller
