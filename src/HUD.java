import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	private Handler handler;

	public HUD(Handler handler) {
		this.handler = handler;
	}

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
			
			g.fillRect(10, 7, 200, 3);
			g.fillRect(7, 7, 3, 26);
			g.fillRect(10, 30, 200, 3);
			g.fillRect(210, 7, 3, 26);
			
			g.drawString("Health: ", 10, 45);
			g.drawString((((ActiveEntity) player).getHealth()) + "", 63, 45);
		}

	}

    /**
     * Sets handler.
     * @param handler Instance of handler.
     */
	public void setHandler(Handler handler)
	{
		this.handler = handler;
	}
}
