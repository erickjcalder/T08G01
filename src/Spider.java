import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Spider extends Enemy {

	private Image jump[] = new Image[5];
	private Image front[] = new Image[5];
	private Random r = new Random();

	private int battleTimer = 0;

	private int jumpX = 0;
	private int jumpY = 0;

	public Spider(int x, int y, LevelHandler levelHandler) {
		super(x, y, levelHandler);

		setVelocityX(-5);
		setVelocityY(-2);

		jump[0] = Toolkit.getDefaultToolkit().getImage("resources/Spider Front Full Bend.png");
		jump[1] = Toolkit.getDefaultToolkit().getImage("resources/Spider Front Half Bend and Landing.png");
		jump[2] = Toolkit.getDefaultToolkit().getImage("resources/Spider Front Jumping.png");

		front[0] = Toolkit.getDefaultToolkit().getImage("resources/Spider Front Right Step.png");
		front[1] = Toolkit.getDefaultToolkit().getImage("resources/Spider Front.png");
		front[2] = Toolkit.getDefaultToolkit().getImage("resources/Spider Front Left Step.png");
		front[3] = Toolkit.getDefaultToolkit().getImage("resources/Spider Front.png");

		setAnimTimer(0);
		setAnimFrame(0);
		setAnimState("walking");

		setWidth(160);
		setHeight(120);
	}

	@Override
	public void logicInterface(String input) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void attackLogic(int direction) {
		switch (direction) {
		case 0:
			handler.addObject(new Projectile(getX() - 10, getY(), -10, 0, handler, "web"));
			break;
		case 1:
			handler.addObject(new Projectile(getX() + 150, getY(), 10, 0, handler, "web"));
			break;
		case 2:
			handler.addObject(new Projectile(getX() + 150, getY(), 0, -10, handler, "web"));
			break;
		case 3:
			handler.addObject(new Projectile(getX() + 150, getY(), -5, 5, handler, "web"));
			break;
		case 4:
			handler.addObject(new Projectile(getX() + 150, getY(), 5, 5, handler, "web"));
			break;

		}

	}

	@Override
	protected void movementLogic() {
		if (battleTimer == 0) {
			setVelocityX(-2);
		}

		if (battleTimer < 500) {
			setAnimState("walking");
			setVelocityY(-2);
			if (getY() > 100) {
				setY(getY() + (int) getVelocityY());
			} else {
				setX(getX() + (int) getVelocityX());
			}
			if (getX() + getVelocityX() < 100 || getX() + getVelocityX() > 750) {
				setVelocityX(getVelocityX() * -1);
			}
		}

		if (battleTimer > 500) {
			setAnimState("jump");
		}

		if (battleTimer == 550) {
			setAnimTimer(0);
			setAnimFrame(0);
			jumpX = handler.getPlayerInstance().getX();
			jumpY = handler.getPlayerInstance().getY();

			setVelocityX((jumpX - getX()) / 15);
			setVelocityY((jumpY - getY()) / 15);

			if (Math.abs(getVelocityX()) < 1) {
				setVelocityX(1);
			}

			if (Math.abs(getVelocityY()) < 1) {
				setVelocityY(1);
			}
		}

		if (battleTimer > 550 && getX() <= 750 && getX() >= 100 && getY() <= 500 && getY() >= 100) {
			setX(getX() + (int) getVelocityX());
			setY(getY() + (int) getVelocityY());
		}

		if (getY() > 500) {
			setY(500);
			setVelocityY(0);
			setVelocityX(0);
		}

		if (getY() < 100) {
			setY(100);
			setVelocityY(0);
			setVelocityX(0);
		}

		if (getX() > 750) {
			setX(750);
			setVelocityY(0);
			setVelocityX(0);
		}

		if (getX() < 100) {
			setX(100);
			setVelocityY(0);
			setVelocityX(0);
		}

		battleTimer++;

		if (battleTimer == 650) {
			battleTimer = 0;
		}

	}

	@Override
	public void animationHandler() {

		if (getAnimState().equals("walking")) {
			setAnimTimer(getAnimTimer() + 1);
			if (getAnimTimer() > 3) {
				setAnimTimer(0);
			}

			if (getAnimTimer() == 3) {
				setAnimFrame(getAnimFrame() + 1);
				if (getAnimFrame() >= 4) {
					setAnimFrame(0);
				}
			}
		}

		if (getAnimState().equals("jump")) {
			setAnimTimer(getAnimTimer() + 1);

			if (getAnimTimer() == 50) {
				setAnimFrame(1);
			}

			if (getAnimTimer() == 100) {
				setAnimFrame(0);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		switch (getAnimState()) {

		case "jump":
			g.drawImage(jump[getAnimFrame()], getX(), getY(), 180, 180, null);
			break;

		case "walking":
			g.drawImage(front[getAnimFrame()], getX(), getY(), 180, 180, null);
			break;
		}
	}

	public void healthThresholdEvents() {
		if (getHealth() <= 0) {
			int x = getX();
			int y = getY();
			handler.removeObject(this);
			levelHandler.removeEnemy(this);
			handler.addObject(new Pickups(levelHandler.getHandler(), levelHandler, 66, x, y));

		}
	}

	@Override
	protected void tick() {
		int rand = r.nextInt(100);

		attackLogic(rand);
		movementLogic();
		animationHandler();
		healthThresholdEvents();
		this.checkCollision();
	}

}
