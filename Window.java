import java.awt.Canvas;
import javax.swing.JFrame;

public class Window extends Canvas {
	private static final long serialVersionUID = 1L;

	//Creates a JFrame for the game to be displayed on
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setSize(width, height);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
		
	}
}
