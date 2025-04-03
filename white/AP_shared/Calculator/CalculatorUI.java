import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * View class for the Calculator application.
 * Handles the user interface elements.
 */
public class CalculatorUI extends JFrame {

    private JTextField display;
    private Map<String, JButton> buttons;
		private double scale = 1;
    /**
     * Constructor initializes the calculator UI.
     */
    public CalculatorUI(double scale) {
        // Set up the frame
				this.scale = scale;
        setTitle("Scientific Calculator");
        setSize((int)(600 * scale), (int)(800 * scale));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.PLAIN, (int)(42*scale)));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Initialize button map
        buttons = new HashMap<>();

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 4, 5, 5));

        // Scientific functions (first row)
        buttonPanel.add(createButton("sin"));
        buttonPanel.add(createButton("cos"));
        buttonPanel.add(createButton("tan"));
        buttonPanel.add(createButton("C"));

        // Scientific functions (second row)
        buttonPanel.add(createButton(Operator.PI));
        buttonPanel.add(createButton(Operator.E));
        buttonPanel.add(createButton(Operator.EXPONENT));
        buttonPanel.add(createButton(Operator.BACKSPACE));

        // Scientific functions (third row)
        buttonPanel.add(createButton("x²"));
        buttonPanel.add(createButton("√"));
        buttonPanel.add(createButton("("));
        buttonPanel.add(createButton(")"));

        // Numbers and operators
        buttonPanel.add(createButton("7"));
        buttonPanel.add(createButton("8"));
        buttonPanel.add(createButton("9"));
        buttonPanel.add(createButton(Operator.DIVIDE));

        buttonPanel.add(createButton("4"));
        buttonPanel.add(createButton("5"));
        buttonPanel.add(createButton("6"));
        buttonPanel.add(createButton(Operator.TIMES));

        buttonPanel.add(createButton("1"));
        buttonPanel.add(createButton("2"));
        buttonPanel.add(createButton("3"));
        buttonPanel.add(createButton(Operator.MINUS));

        buttonPanel.add(createButton("0"));
        buttonPanel.add(createButton("."));
        buttonPanel.add(createButton(Operator.EQUALS));
        buttonPanel.add(createButton(Operator.PLUS));

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Display the frame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates a button with the given text and adds it to the button map.
     *
     * @param text The button text
     * @return The created button
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, (int)(scale*28)));
        buttons.put(text, button);
        return button;
    }

    /**
     * Registers an action listener for all buttons.
     *
     * @param listener The action listener
     */
    public void registerActionListener(ActionListener listener) {
        for (JButton button : buttons.values()) {
            button.addActionListener(listener);
        }
    }

    /**
     * Sets the text in the display.
     *
     * @param text The text to display
     */
    public void setDisplayText(String text) {
        display.setText(text);
    }

    /**
     * Gets the text from the display.
     *
     * @return The display text
     */
    public String getDisplayText() {
        return display.getText();
    }
	}
