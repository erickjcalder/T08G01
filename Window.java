import java.awt.Canvas;
import javax.swing.JFrame;

/**
 * Creates a window to be drawn on
 *
 * @author Parker
 * @version Demo 2
 */
public class Window extends Canvas {
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a window that the game will be displayed on
	 * 
	 * @param int the width of the window
	 * @param int the height of the window
	 * @param String the title of the window
	 * @param Game the Game object that will be displayed
	 */
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);

		frame.setSize(width, height);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);

	}
}
