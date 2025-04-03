/**
 * Main class for the Calculator application.
 * Initializes the MVC components and starts the application.
 */
public class Calculator {

    /**
     * Application entry point.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Launch the application
        javax.swing.SwingUtilities.invokeLater(() -> {
            CalculatorModel model = new CalculatorModel();
            CalculatorUI view = new CalculatorUI(2.0); // adjust the scale to 1.0 or 2.0 etc. as needed
            @SuppressWarnings("unused")
						CalculatorController controller = new CalculatorController(model, view);
        });
    }
}
