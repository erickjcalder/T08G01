import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Wasp extends Enemy {

	private Image left[] = new Image[5];
	private Image right[] = new Image[5];
	private Image back[] = new Image[5];
	private Image front[] = new Image[5];
	private Random r = new Random();

	/**
	 * Creates a new Wasp Enemy
	 * 
	 * @param int
	 *            the x coordinate of the wasp
	 * @param int
	 *            the y coordinate of the wasp
	 * @param LevelHandler
	 *            the LevelHandler used to keep track of the Wasp Enemy within the
	 *            Game
	 */
	Wasp(int x, int y, LevelHandler levelHandler) {
		super(x, y, levelHandler);

		setVelocityX(2);
		setVelocityY(2);

		left[0] = Toolkit.getDefaultToolkit().getImage("resources/Wasp Left Side.png");
		left[1] = Toolkit.getDefaultToolkit().getImage("resources/Wasp Left Side Flying.png");

		right[0] = Toolkit.getDefaultToolkit().getImage("resources/Wasp Right Side.png");
		right[1] = Toolkit.getDefaultToolkit().getImage("resources/Wasp Right Side Flying.png");

		back[0] = Toolkit.getDefaultToolkit().getImage("resources/Wasp Back.png");
		back[1] = Toolkit.getDefaultToolkit().getImage("resources/Wasp Back Flying.png");

		front[0] = Toolkit.getDefaultToolkit().getImage("resources/Wasp Front.png");
		front[1] = Toolkit.getDefaultToolkit().getImage("resources/Wasp Front Flying.png");

		setAnimTimer(0);
		setAnimFrame(0);
		setAnimState("walking front");

		setWidth(120);
		setHeight(120);
	}

	/**
	 * The logic that decides general aspects of the Wasp's behaviour
	 */
	@Override
	public void logicInterface(String input) {
		// TODO Auto-generated method stub

	}

	/**
	 * The logic that decides how the Wasp Enemy attacks
	 */
	@Override
	protected void attackLogic(int direction) {
		// TODO Auto-generated method stub

	}

	/**
	 * The logic that decides how the Wasp Enemy moves around the screen
	 */
	@Override
	protected void movementLogic() {
		int rand = r.nextInt(100);
		if (rand > 10 && rand < 12) {
			setVelocityX(-4);
		}

		if (rand > 20 && rand < 22) {
			setVelocityY(-4);
		}

		if (rand > 30 && rand < 32) {
			setVelocityX(4);
		}

		if (rand > 40 && rand < 42) {
			setVelocityY(4);
		}

		if (getX() + getVelocityX() + getWidth() > 950 || getX() + getVelocityX() < 80) {
			setVelocityX(getVelocityX() * -1);
		}

		if (getY() + getVelocityY() + getHeight() > 520 || getY() + getVelocityY() < 20) {
			setVelocityY(getVelocityY() * -1);
		}

		setX(getX() + (int) getVelocityX());
		setY(getY() + (int) getVelocityY());

	}

	/**
	 * Handles how the Wasp Enemy is animated
	 */
	@Override
	public void animationHandler() {
		setAnimTimer(getAnimTimer() + 1);

		if (getAnimTimer() > 3) {
			setAnimTimer(0);
		}

		if (getAnimTimer() == 3) {
			setAnimFrame(getAnimFrame() + 1);
			if (getAnimFrame() >= 2) {
				setAnimFrame(0);
			}
		}
	}

	/**
	 * Renders the Wasp to the screen
	 * 
	 * @param Graphics
	 *            the Graphics object that is used to draw the Wasp to the screen
	 */
	@Override
	public void render(Graphics g) {
		switch (getAnimState()) {
		case "walking left":
			g.drawImage(left[getAnimFrame()], getX(), getY(), 120, 120, null);
			break;

		case "walking right":
			g.drawImage(right[getAnimFrame()], getX(), getY(), 120, 120, null);
			break;

		case "walking back":
			g.drawImage(back[getAnimFrame()], getX(), getY(), 120, 120, null);
			break;

		case "walking front":
			g.drawImage(front[getAnimFrame()], getX(), getY(), 120, 120, null);
			break;
		}
	}

	/**
	 * Handles the changes that the Wasp undergoes on every tick of the game running
	 */
	@Override
	protected void tick() {
		movementLogic();
		animationHandler();
		healthThresholdEvents();
	}

}
