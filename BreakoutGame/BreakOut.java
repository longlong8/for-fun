package breakOut;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BreakOut extends JFrame {
	JPanel BreakOutPanel;
	
	public BreakOut() {
		super();
        JFrame frame = new JFrame("Break Out");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BreakOutPanel());
        frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
        // run main application 
        JFrame game = new BreakOut();
    }

}
