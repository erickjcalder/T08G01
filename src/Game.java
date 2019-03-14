import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

/**
 * Class adapted from: https://www.youtube.com/watch?v=1gir2R7G9ws Date of
 * retrieval: 28/02/2019
 */

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static String OS = System.getProperty("os.name").toLowerCase();
	private int WIDTH, HEIGHT;
	private Thread thread;
	private Handler handler;
	private LevelHandler levelhandler;
	private boolean running = false;
	private KeyInput keyInput;

	// when a new game object is created a new window is first created
	// a new Handler is created and assigned to the Game object's handler instance
	// variable
	// a new Player entity object is added to the handler
	// a KeyInput is created and added to the Game object
	public Game() {
		if (OS.contains("windows")) {
			WIDTH = 1030;
			HEIGHT = 768;
		} else {
			WIDTH = 1027;
			HEIGHT = 776;
		}

		new Window(WIDTH, HEIGHT, "Game", this);
		handler = new Handler();
		levelhandler = new LevelHandler(handler);
		handler.addObject(new Player(490, 350, levelhandler));
		keyInput = new KeyInput(handler);
		this.addKeyListener(keyInput);

	}

	// start creates a new Thread using Game object
	// is only called once in the Window class once the window has been set up
	// sets the running boolean to true
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	// stop method called only once in run() if game loop is left
	// waits for thread to die
	// sets running boolean to false
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// the run method is part of the Runnable interface
	// Game loop, runs tick method and render method, should never be changed
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
				tick();
				delta--;
			}
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
		stop();
	}

	// Tick method, ticks the handler tick method and the keyinput tick method
	// handler tick method is responsible for ticking all Entities in the game
	// keyinput tick method just responsible for key inputs
	// this method should never really have anything added to it unless there is
	// good reason
	public void tick() {
		handler.tick();
		keyInput.tick();
	}

	// first creates a buffer strategy, then draws a background, then calls
	// individual render methods
	// handler.renderRoom() draws the room first
	// handler.render() draws all entities
	// handler.renderMap() draws the minimap in the top right
	// after calling all the render methods the graphics object is disposed, the
	// buffer strategy is shown and
	// finally the sync method is called which just makes sure the program can run
	// properly on Linux
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(188, 143, 143));
		g.fillRect(0, 0, 1024, 768);

		levelhandler.renderRoom(g);
		handler.render(g);
		levelhandler.renderMap(g);

		g.dispose();
		bs.show();

		if (!OS.contains("windows")) {
			Toolkit.getDefaultToolkit().sync();
		}
	}

	// A new game object is created
	public static void main(String[] args) {
		new Game();
	}
}
