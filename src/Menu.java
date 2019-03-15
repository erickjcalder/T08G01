import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

	private Game game;
	private String menuState;

	public Menu(Game game) {
		this.game = game;
		this.menuState = "main";
	}

	public void render(Graphics g) {
		if (menuState.equals("main")) {
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("Helvetica", 1, 85));
			g.drawString("Game", 410, 100);

			g.setFont(new Font("Helvetica", 1, 65));
			g.drawRect(405, 300, 250, 100);
			g.drawString("Start", 455, 370);
		} else {
			g.setColor(new Color(255, 255, 255));
			
			g.setFont(new Font("Helvetica", 1, 85));
			g.drawString("Pause", 410, 100);
			
			g.setFont(new Font("Helvetica", 1, 65));
			g.drawRect(405, 300, 250, 100);
			g.drawString("Back", 455, 370);
		}
	}

	public void tick() {

	}

	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (mouseOver(mouseX, mouseY, 405, 300, 250, 100)) {
			game.gameState = "game";
			menuState = "pause";
		}
	}

	public void mouseReleased() {

	}

	private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
		if (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height) {
			return true;
		}
		return false;
	}

}
