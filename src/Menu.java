import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;

public class Menu extends MouseAdapter implements MouseMotionListener {

	private Game game;
	private String menuState;
	private Image mainMenu = Toolkit.getDefaultToolkit().getImage("resources/menu.png");
	private Image pauseMenu = Toolkit.getDefaultToolkit().getImage("resources/menupause.png");
	private Image startButton = Toolkit.getDefaultToolkit().getImage("resources/start_button.png");
	private Image quitButton = Toolkit.getDefaultToolkit().getImage("resources/quit_button.png");
	private Image loadButton = Toolkit.getDefaultToolkit().getImage("resources/load_button.png");
	private Image backButton = Toolkit.getDefaultToolkit().getImage("resources/back_button.png");
	private Image saveButton = Toolkit.getDefaultToolkit().getImage("resources/save_button.png");
	private Image[] birds = new Image[3];

	private int animFrame;
	private int animTimer;
	private int birdX;
	private int birdY;

	private boolean hoverStart;
	private boolean hoverQuit;
	private boolean hoverLoad;
	private boolean hoverBack;
	private boolean hoverSave;

	public Menu(Game game) {
		this.game = game;
		this.menuState = "main";
		this.hoverStart = false;
		this.hoverQuit = false;

		birds[0] = Toolkit.getDefaultToolkit().getImage("resources/BirdsAnim1.png");
		birds[1] = Toolkit.getDefaultToolkit().getImage("resources/BirdsAnim2.png");
		birds[2] = Toolkit.getDefaultToolkit().getImage("resources/BirdsAnim3.png");

		animFrame = 0;
		animTimer = 0;

		birdX = -200;
		birdY = 400;
	}

	public void render(Graphics g) {
		if (menuState.equals("main")) {
			g.drawImage(mainMenu, 0, 0, 1030, 780, null);

			if (!hoverStart) {
				g.drawImage(startButton, 405, 290, 250, 100, null);
			} else {
				g.drawImage(startButton, 400, 285, 260, 110, null);
			}

			if (!hoverLoad) {
				g.drawImage(loadButton, 405, 400, 250, 100, null);
			} else {
				g.drawImage(loadButton, 400, 395, 260, 110, null);
			}

			if (!hoverQuit) {
				g.drawImage(quitButton, 405, 510, 250, 100, null);
			} else {
				g.drawImage(quitButton, 400, 505, 260, 110, null);
			}

			g.drawImage(birds[animFrame], birdX, birdY, 24, 18, null);
			g.drawImage(birds[animFrame], birdX + 20, birdY + 60, 24, 18, null);
			if (animFrame == 2) {
				g.drawImage(birds[0], birdX + 10, birdY + 30, 24, 18, null);
				g.drawImage(birds[0], birdX - 70, birdY + 30, 24, 18, null);
			} else {
				g.drawImage(birds[animFrame + 1], birdX + 12, birdY + 30, 24, 18, null);
				g.drawImage(birds[animFrame + 1], birdX - 70, birdY + 30, 24, 18, null);
			}

			if (animFrame == 0) {
				g.drawImage(birds[2], birdX - 30, birdY + 15, 24, 18, null);
			} else {
				g.drawImage(birds[animFrame - 1], birdX - 32, birdY + 15, 24, 18, null);
			}

		} else if (menuState.equals("pause")) {
			g.drawImage(pauseMenu, 0, 0, 1030, 780, null);
			g.setColor(new Color(255, 255, 255));

			if (!hoverBack) {
				g.drawImage(backButton, 405, 290, 250, 100, null);
			} else {
				g.drawImage(backButton, 400, 285, 260, 110, null);
			}

			if (!hoverSave) {
				g.drawImage(saveButton, 405, 400, 250, 100, null);
			} else {
				g.drawImage(saveButton, 400, 395, 260, 110, null);
			}

			if (!hoverQuit) {
				g.drawImage(quitButton, 405, 510, 250, 100, null);
			} else {
				g.drawImage(quitButton, 400, 505, 260, 110, null);
			}
		} else if (menuState.equals("gameover")) {
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("Helvetica", 1, 65));
			g.drawString("Game Over", 350, 200);

			if (!hoverStart) {
				g.drawImage(startButton, 405, 290, 250, 100, null);
			} else {
				g.drawImage(startButton, 400, 285, 260, 110, null);
			}

			if (!hoverQuit) {
				g.drawImage(quitButton, 405, 510, 250, 100, null);
			} else {
				g.drawImage(quitButton, 400, 505, 260, 110, null);
			}
		}
	}

	public void tick() {
		animTimer++;
		birdX += 2;
		birdY--;

		if (birdX > 3000) {
			birdX = -200;
			birdY = 400;
		}

		if (animTimer == 15) {
			animFrame++;
		} else if (animTimer == 25) {
			animFrame++;
		} else if (animTimer == 45) {
			animFrame = 0;
			animTimer = 0;
		}
	}

	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (menuState.equals("main")) {
			if (mouseOver(mouseX, mouseY, 405, 290, 250, 100)) {
				hoverStart = true;
			} else {
				hoverStart = false;
			}

			if (mouseOver(mouseX, mouseY, 405, 400, 250, 100)) {
				hoverLoad = true;
			} else {
				hoverLoad = false;
			}

			if (mouseOver(mouseX, mouseY, 405, 510, 250, 100)) {
				hoverQuit = true;
			} else {
				hoverQuit = false;
			}
		}

		if (menuState.equals("pause")) {
			if (mouseOver(mouseX, mouseY, 405, 290, 250, 100)) {
				hoverBack = true;
			} else {
				hoverBack = false;
			}

			if (mouseOver(mouseX, mouseY, 405, 400, 250, 100)) {
				hoverSave = true;
			} else {
				hoverSave = false;
			}

			if (mouseOver(mouseX, mouseY, 405, 510, 250, 100)) {
				hoverQuit = true;
			} else {
				hoverQuit = false;
			}
		}

		if (menuState.equals("gameover")) {
			if (mouseOver(mouseX, mouseY, 405, 290, 250, 100)) {
				hoverStart = true;
			} else {
				hoverStart = false;
			}

			if (mouseOver(mouseX, mouseY, 405, 510, 250, 100)) {
				hoverQuit = true;
			} else {
				hoverQuit = false;
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (menuState.equals("main")) {
			if (mouseOver(mouseX, mouseY, 405, 290, 250, 100)) {
				game.gameState = "game";
				menuState = "pause";
			}

			if (mouseOver(mouseX, mouseY, 405, 400, 250, 100)) {
				game.setGameState("file select");
				FileExplorer fe = new FileExplorer("Select save");

				File file = fe.getSelectedFile();
				System.out.println(file);

				game.setGameState("menu");
			}

			if (mouseOver(mouseX, mouseY, 405, 510, 250, 100)) {
				game.stop();
			}
		}

		if (menuState.equals("pause")) {
			if (mouseOver(mouseX, mouseY, 405, 290, 250, 100)) {
				game.gameState = "game";
			}

			if (mouseOver(mouseX, mouseY, 405, 400, 250, 100)) {
				game.setGameState("file select");
				FileExplorer fe = new FileExplorer("Select save");

				File file = fe.getSelectedFile();
				System.out.println(file);

				game.setGameState("menu");
			}

			if (mouseOver(mouseX, mouseY, 405, 510, 250, 100)) {
				game.stop();
			}
		}

		if (menuState.equals("gameover")) {
			if (mouseOver(mouseX, mouseY, 405, 290, 250, 100)) {
				game.resetGame();
			}

			if (mouseOver(mouseX, mouseY, 405, 510, 250, 100)) {
				game.stop();
			}
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

	public void setMenuState(String menuState) {
		this.menuState = new String(menuState);
	}

}
