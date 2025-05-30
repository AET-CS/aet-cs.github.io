import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

public class MyPointPanelSolution extends JPanel implements MouseListener {
	// constant
	private static final Color BACKGROUND = new Color(242, 43, 43);
	// fields
	private ArrayList<Point> myPoints = new ArrayList<Point>();
	private Color myColor;
	private Timer t;

	public MyPointPanelSolution(JFrame frame, int maxX, int maxY) {
		myColor = BACKGROUND;
		t = new Timer(20, new Listener());
		// t.start();
		addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		g.setColor(myColor);
		g.fillRect(0, 0, getWidth(), getHeight());

		// TODO (all below this)
		for (int k = 0; k < myPoints.size(); k++) {
			Point pt1 = myPoints.get(k);
			// get the x and y coordinates of pt1
			int x1 = (int) pt1.getX();
			int y1 = (int) pt1.getY();
			// draw the point
			g.setColor(Color.blue);
			g.fillOval(x1 - 3, y1 - 3, 6, 6);

			// connect all points below the current point to that point
			for (int j = k + 1; j < myPoints.size(); j++) {
				Point pt2 = myPoints.get(j);

				// todo: get the coordinates of pt2
				int x2 = (int) pt2.getX();
				int y2 = (int) pt2.getY();

				// draw the point
				g.setColor(Color.blue);
				g.fillOval(x2 - 3, y2 - 3, 6, 6);
				double distance = computeDistance(x1, y1, x2, y2);
				int colorVal = (int) (distance * 255 / getWidth());
				if (colorVal > 255) {
					colorVal = 255;
				}

				// draw the line between pt1 and pt2
				Color cc = new Color(colorVal, colorVal, colorVal);
				g.setColor(cc);

				g.drawLine(x1, y1, x2, y2);
			}
		}
	}

	// TODO (whole method)
	public double computeDistance(int x1, int y1, int x2, int y2) {
		int deltaX = x2 - x1;
		int deltaY = y2 - y1;

		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
	}

	public void mouseClicked(MouseEvent e) {
		saySomething("Mouse pressed; # of clicks: " + e.getClickCount(), e);
	}

	public void mouseReleased(MouseEvent e) {
		saySomething("Mouse released; # of clicks: " + e.getClickCount(), e);
	}

	public void mouseEntered(MouseEvent e) {
		saySomething("Mouse entered", e);
		myPoints.clear(); // TODO
		myColor = BACKGROUND;
		repaint();
	}

	public void mouseExited(MouseEvent e) {

		saySomething("Mouse exited", e);
		myColor = Color.white; // TODO
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		saySomething("Mouse pressed (# of clicks: " + e.getClickCount() + ")", e);

		int x = e.getX();
		int y = e.getY();
		myPoints.add(new Point(x, y)); // TODO

		// redraw the frame
		repaint();

		System.out.println("x = " + x + "  y = " + y);
	}

	public void saySomething(String eventDescription, MouseEvent e) {
		System.out.println(eventDescription + " detected on " + e.getComponent().getClass().getName() + "." + "\n");
	}

	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("What is the Point");
		frame.setSize(1000, 800);
		frame.setLocation(50, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new MyPointPanelSolution(frame, 800, 400));
		frame.setVisible(true);
	}
}