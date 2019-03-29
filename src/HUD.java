import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	private Handler handler;

	/**
	 * Creates a new HUD object
	 * 
	 * @param Handler
	 *            the handler being used by the Game
	 */
	public HUD(Handler handler) {
		this.handler = handler;
	}

	/**
	 * Renders every aspect of the HUD to the screen
	 */
	public void render(Graphics g) {
		Entity player = handler.object.get(0);
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i) instanceof Player) {
				player = handler.object.get(i);
			}
		}

		if (player instanceof Player) {
			g.setColor(new Color(200 - ((ActiveEntity) player).getHealth() * 2, ((ActiveEntity) player).getHealth() * 2,
					0));
			g.fillRect(10, 10, ((ActiveEntity) player).getHealth() * 2, 20);
			g.setFont(new Font("Helvetica", 1, 12));
			g.setColor(new Color(255, 255, 255));
			g.drawString("Health", 10, 43);
		}

	}

}
