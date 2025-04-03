import javax.swing.*;
import java.awt.*;

public class StackView extends JFrame {
	private JList<String> operandsDisplay;
	private JList<String> operatorsDisplay;

	public StackView()
	{
		setTitle("Two Lists");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Create a panel to hold the two lists
		JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns, 10px gap

		// Create the lists
		operandsDisplay = new JList<>(new String[]{"Item 1", "Item 2", "Item 3"});
		operatorsDisplay = new JList<>(new String[]{"Item A", "Item B", "Item C"});

		// Add scrolling capability to each list
		JScrollPane leftScroll = new JScrollPane(operandsDisplay);
		JScrollPane rightScroll = new JScrollPane(operatorsDisplay);

		// Add the scrollable lists to the panel
		listsPanel.add(leftScroll);
		listsPanel.add(rightScroll);

		// Add the panel to the frame
		add(listsPanel, BorderLayout.CENTER);

		// Set size and display
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
}

	public JList<String> getOperandsDisplay() {
		return operandsDisplay;
	}
	public JList<String> getOperatorsDisplay() {
		return operatorsDisplay;
	}
}