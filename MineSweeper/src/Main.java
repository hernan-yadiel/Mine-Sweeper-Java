import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
<<<<<<< HEAD

	static JFrame myFrame = new JFrame("Mine Sweeper");
=======
>>>>>>> refs/heads/fix
	
	static JFrame myFrame = new JFrame("Minesweeper");

	public static void main(String[] args) {

		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(700, 700);
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);
<<<<<<< HEAD

=======
>>>>>>> refs/heads/fix
		startGame();
	}
	
	static void startGame() {
		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
		myFrame.setVisible(true);
	}
	
	static void playAgain() {
		myFrame.getContentPane().removeAll();
<<<<<<< HEAD
		myFrame.getContentPane().repaint();
		startGame();	 
=======
		Main.startGame();	 
>>>>>>> refs/heads/fix
	}
}