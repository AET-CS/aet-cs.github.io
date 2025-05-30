import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;


public class MyPointPanel extends JPanel implements MouseListener {
	// constant
	private static final Color BACKGROUND = new Color(242, 43, 43);
	
	// fields
	private ArrayList<Point> myPoints = new ArrayList<Point>();
	private Color myColor;
	private Timer t;

	public MyPointPanel(JFrame frame, int maxX, int maxY) {
		myColor = BACKGROUND;
		t = new Timer(20, new Listener());
		//t.start()
		addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		g.setColor(myColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// TODO: most of your code goes here (step 5 of lab handout) 

	}

	public void mouseClicked(MouseEvent e) {
		saySomething("Mouse pressed; # of clicks: " + e.getClickCount(), e);
	}

	public void mouseReleased(MouseEvent e) {
		saySomething("Mouse released; # of clicks: " + e.getClickCount(), e);
	}

	public void mouseEntered(MouseEvent e) {
		saySomething("Mouse entered", e);
		// TODO: set myColor back to red (the BACKGROUND constant) and empty the list
		
		repaint();
	}

	public void mouseExited(MouseEvent e) {
		saySomething("Mouse exited", e);
		// TODO: set the myColor field to white so the panel becomes white 
		
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		saySomething("Mouse pressed (# of clicks: " + e.getClickCount() + ")", e);

		//retrieves x and y location of the click 
		int x = e.getX();
		int y = e.getY();
		
		// TODO: create new point and add to the list
		
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
		frame.setContentPane(new MyPointPanel(frame, 800, 400));
		frame.setVisible(true);
	}
}