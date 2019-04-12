import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.File;

/**
 * Class adapted from: https://www.youtube.com/watch?v=1gir2R7G9ws 
 * Date of retrieval: 28/02/2019
 */

/**
 * The main backend of the game
 *
 * @author Parker
 * @version Demo 2
 */

public class Game extends Canvas {

	private static final long serialVersionUID = 1L;

	private static String OS = System.getProperty("os.name").toLowerCase();
	private int WIDTH, HEIGHT;
	private Handler handler;
	private LevelHandler levelhandler;
	private boolean running = false;
	private KeyInput keyInput;
	public String gameState;
	private Menu menu;
	private HUD hud;

	/**
	 * Creates a new Game object and also creates a new window
	 */
	public Game() {
		if (OS.contains("windows")) {
			WIDTH = 1030;
			HEIGHT = 768;
		} else {
			WIDTH = 1026;
			HEIGHT = 776;
		}

		new Window(WIDTH, HEIGHT, "Game", this);
		handler = new Handler();
		levelhandler = new LevelHandler(handler);
		handler.addObject(new Player(490, 350, levelhandler));
		keyInput = new KeyInput(handler, this);
		menu = new Menu(this);
		this.addKeyListener(keyInput);
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		this.hud = new HUD(handler);

		gameState = "menu";

	}

	public void save(String filename) {
		Save.saveGame(this.levelhandler.getMap(), this.handler, filename);
	}

	public void load(String filename) {
		Save.loadGame(this, filename);
	}

	/**
	 * Calls tick method 60 times per second as well as calling the render method as
	 * many times as possible
	 */
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				if (levelhandler.gameState.equals("lose")) {
					gameOver();
				} else if (levelhandler.gameState.equals("won")) {
					winGame();
				}
				tick();
				delta--;
			}

			if (!gameState.equals("file select") && !gameState.equals("warning")) {
				if (running) {
					render();
				}

				frames++;

				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println("FPS: " + frames);
					frames = 0;
				}
			}
		}
	}

	/**
	 * Call Handler and KeyInput tick methods which are used to update the game
	 */
	public void tick() {
		if (gameState.equals("game")) {
			handler.tick();
			keyInput.tick();
			levelhandler.tick();
		} else if (gameState.equals("menu") || gameState.equals("gameover")) {
			menu.tick();
		}
	}

	/**
	 * Creates a BufferStrategy, which is used to create a Graphics object, which is
	 * passed to individual render methods that cause every object to be drawn to
	 * the screen
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(118, 87, 44));
		g.fillRect(0, 0, 1024, 768);

		if (gameState.equals("game")) {
			levelhandler.renderRoom(g);
			handler.render(g);
			levelhandler.renderMap(g);
			hud.render(g);
		} else if (gameState.equals("menu") || gameState.equals("gameover")) {
			menu.render(g);

		}

		g.dispose();
		bs.show();

		if (!OS.contains("windows")) {
			Toolkit.getDefaultToolkit().sync();
		}
	}

	/**
	 * Creates a new Game object
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.running = true;
		game.run();
		System.exit(0);
	}

	public String getGameState() {
		return new String(this.gameState);
	}

	public void setGameState(String gameState) {
		this.gameState = new String(gameState);
	}

	public void setMenuState(String menuState) {
		this.menu.setMenuState(menuState);
	}

	public void stop() {
		running = false;
	}

	public void gameOver() {
		gameState = "gameover";
		menu.setMenuState("gameover");
	}
	
	public void winGame() {
		gameState = "gameover";
		menu.setMenuState("winscreen");
	}

	public void resetGame() {
		handler.clearEntities();
		levelhandler = new LevelHandler(handler);
		handler.addObject(new Player(490, 350, levelhandler));
		menu.setMenuState("main");
		setGameState("menu");
	}

	/**
	 * Used to create a new game with load function.
	 * 
	 * @param newLevelHandler
	 *            LevelHandler created by load function.
	 */
	public void newGame(LevelHandler newLevelHandler) {
		this.levelhandler = newLevelHandler;

		// Pointing various variables to new version of handlers.
		this.handler = levelhandler.getHandler();
		this.hud.setHandler(handler);
		this.keyInput.setHandler(handler);

		// Starts game.
		this.menu.setMenuState("");
		setGameState("game");
	}

}
