import java.awt.Canvas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

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
	 * @param        int the width of the window
	 * @param        int the height of the window
	 * @param String the title of the window
	 * @param Game   the Game object that will be displayed
	 */
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				game.setGameState("warning");
				String ObjButtons[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null,
						"Any unsaved progress will be lost, are you sure you want to quit?", "WARNING",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
				if (PromptResult == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				game.setGameState("game");
			}
		});

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);

	}
}
