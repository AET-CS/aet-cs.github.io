//Programmer:  Pete (modified by Nate) 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class HeatMapSolution extends JPanel implements MouseListener {

	private static final Color BACKGROUND = new Color(204, 204, 204);

	private double[][] tempGrid = new double[50][50];
	private double maxTemp = 150;
	private double minTemp = -150;

	// for part 3, leave them alone 
	private int clickedRow = -1;
	private int clickedCol = -1;
	private int clickTemp = 0;

	public HeatMapSolution() {
		// TODO: initialize the heat map, half cold, half hot
		for (int r = 0; r < tempGrid.length; r++) {
			for (int c = 0; c < tempGrid[r].length; c++) {
				if (c < tempGrid[r].length / 2) {
					tempGrid[r][c] = minTemp;
				} else {
					tempGrid[r][c] = maxTemp;
				}
			}
		}

		// TODO: uncomment timer start once you get the color methods to work.
		Timer t = new Timer(40, new Listener());
		t.start();

		addMouseListener(this);

		// Tests get colors methods. Prints results to console.
		for (int temp = (int) minTemp; temp < maxTemp; temp += 10) {
			System.out.printf("%5d: %4d, %4d, %4d\n", temp, getRed(temp), getGreen(temp), getBlue(temp));
		}
	}

	// TODO: complete methods - given a temp value return proper R G or B value.
	public int getRed(double temp) {
		if (temp > 0)
			return 255;
		else if (temp < -74)
			return 0;

		temp += 75;
		temp = temp / 75 * 255;
		temp = Math.min(Math.max(0, temp), 255);
		return (int) temp;
	}

	public int getGreen(double temp) {

		if (temp > -75 && temp < 76)
			return 255;
		else if (temp <= -75) {
			temp += maxTemp;
			temp = temp / 75 * 255;
		} else {
			temp -= 76;
			temp = 255 - temp / 75 * 255;

		}
		temp = Math.min(Math.max(0, temp), 255);
		return (int) temp;
	}

	public int getBlue(double temp) {

		if (temp > 75) {
			return 0;
		}
		else if (temp < 1) {
			return 255;
		}

		temp += 1;
		temp = 255 - temp / 75 * 255;
		temp = Math.min(Math.max(0, temp), 255);
		return (int) temp;
	}

	// draws squares representing the temp in each cell
	// method completed for you, nothing to do here
	public void paintComponent(Graphics g) {
		g.setColor(BACKGROUND);
		g.fillRect(0, 0, getWidth(), getHeight());

		int blockHeight = getHeight() / tempGrid.length + 1;
		int blockWidth = getWidth() / tempGrid[0].length + 1;
		for (int r = 0; r < tempGrid.length; r++) {
			for (int c = 0; c < tempGrid[r].length; c++) {

				double tVal = tempGrid[r][c];
				g.setColor(new Color(getRed(tVal), getGreen(tVal), getBlue(tVal)));

				int x = c * blockWidth; // (x,y) is the upper left hand corner of the rectangle
				int y = r * blockHeight;
				g.fillRect(x, y, blockWidth, blockHeight);
			}
		}
		// Display temperatures of both sides
		String avgLeftTempStr = String.format("%7.2f", tempGrid[tempGrid.length / 2][0]);
		String avgRightTempStr = String.format("%7.2f", tempGrid[tempGrid.length / 2][tempGrid[0].length - 1]);

		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 60));
		g.drawString("Left Side Temp", 10, 60);
		g.drawString(avgLeftTempStr, 100, 120);
		g.drawString("Right Side Temp", getWidth() - 450, 60); // TODO: you might need to adjust location
		g.drawString(avgRightTempStr, getWidth() - 350, 120);
	}

	@Override
	public void mousePressed(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();

		int blockHeight = getHeight() / tempGrid.length + 1;
		int blockWidth = getWidth() / tempGrid[0].length + 1;

		clickedRow = y / blockHeight;
		clickedCol = x / blockWidth;

		int button = event.getButton();
		if (button == MouseEvent.BUTTON1) {
			clickTemp = -1750;
		}
		else if (button == MouseEvent.BUTTON3) {
			clickTemp = 1750;
		}

		tempGrid[clickedRow][clickedCol] = clickTemp; //just added
		System.out.printf("r=%d, c=%d  button=%d, temp=%d\n", clickedRow, clickedRow, button, clickTemp);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO: part 3, reset clickedRow and clickedCol
		clickedRow = -1;
		clickedCol = -1;
	}

	// these mouse methods are unneeded for this lab
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO: recalculate every element of the 2D array
			// update the temperature values in tempGrid
			for (int r = 0; r < tempGrid.length; r++) {
				for (int c = 0; c < tempGrid[0].length; c++) {
					int count = 0;
					double sum = 0;
					// add left
					if (c > 0) {
						sum += tempGrid[r][c - 1];
						count++;
					}
					// add above
					if (r > 0) {
						sum += tempGrid[r - 1][c];
						count++;
					}
					// check right
					if (c < tempGrid[0].length - 1) {
						sum += tempGrid[r][c + 1];
						count++;
					}
					// check below
					if (r < tempGrid.length - 1) {
						sum += tempGrid[r + 1][c];
						count++;
					}
					// check self
					sum += tempGrid[r][c];
					count++;

					// update temp
					tempGrid[r][c] = sum / count;
				}
			}

			// TODO: in part 3 you will add an if statement here before repaint
			if (clickedRow > -1) {
				tempGrid[clickedRow][clickedCol] = clickTemp;
			}
			
			repaint();
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Heat Map");
		frame.setSize(1400, 1005);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new HeatMapSolution());
		frame.setVisible(true);
	}
}