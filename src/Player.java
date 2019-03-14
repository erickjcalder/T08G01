import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

class Player extends ActiveEntity {
	/**
	 * Instance of the current map.
	 */
	private Map map;
	private Handler handler;
	private int animFrame;
	private int animTimer;
	public String animState;
	private Image left[] = new Image[5];
	private Image right[] = new Image[5];
	private Image back[] = new Image[5];
	private Image front[] = new Image[5];
	private LevelHandler levelHandler;

	Player(int X, int Y, LevelHandler levelHandler) {
		super(X, Y);
		this.map = levelHandler.getMap();
		this.handler = levelHandler.getHandler();
		this.levelHandler = levelHandler;
		setMapX(2);
		setMapY(1);
		setHealthMax(100);
		setHealth(100);
		setArmor(0);
		setDamage(1);
		setDamageMult(1);
		setName("Player");
		setTeam("player");

		setWidth(40);
		setHeight(40);

		animState = "walking left";

		left[0] = Toolkit.getDefaultToolkit().getImage("resources/left_side.png");
		left[1] = Toolkit.getDefaultToolkit().getImage("resources/left_left_step.png");
		left[2] = Toolkit.getDefaultToolkit().getImage("resources/left_right_step.png");
		left[3] = Toolkit.getDefaultToolkit().getImage("resources/left_hold.png");
		left[4] = Toolkit.getDefaultToolkit().getImage("resources/left_throw.png");

		right[0] = Toolkit.getDefaultToolkit().getImage("resources/right_side.png");
		right[1] = Toolkit.getDefaultToolkit().getImage("resources/right_left_step.png");
		right[2] = Toolkit.getDefaultToolkit().getImage("resources/right_right_step.png");
		right[3] = Toolkit.getDefaultToolkit().getImage("resources/right_hold.png");
		right[4] = Toolkit.getDefaultToolkit().getImage("resources/right_throw.png");

		back[0] = Toolkit.getDefaultToolkit().getImage("resources/back.png");
		back[1] = Toolkit.getDefaultToolkit().getImage("resources/back_left.png");
		back[2] = Toolkit.getDefaultToolkit().getImage("resources/back_right.png");
		back[3] = Toolkit.getDefaultToolkit().getImage("resources/back_hold.png");
		back[4] = Toolkit.getDefaultToolkit().getImage("resources/back_throw.png");

		front[0] = Toolkit.getDefaultToolkit().getImage("resources/front.png");
		front[1] = Toolkit.getDefaultToolkit().getImage("resources/right_step.png");
		front[2] = Toolkit.getDefaultToolkit().getImage("resources/left_step.png");
		front[3] = Toolkit.getDefaultToolkit().getImage("resources/front_hold.png");
		front[4] = Toolkit.getDefaultToolkit().getImage("resources/front_throw.png");

	}

	/**
	 * Handles conversion of keypresses to actions.
	 */
	@Override
	public void LogicInterface(String input) {
		switch (input) {
		case "up":
			if (getVelocityY() > -7) {
				setVelocityY(getVelocityY() - 2);
			} else if (getVelocityY() < 0) {
				setVelocityY(getVelocityY() + 1);
			}
			break;

		case "down":
			if (getVelocityY() < 7) {
				setVelocityY(getVelocityY() + 2);
			} else if (getVelocityY() > 0) {
				setVelocityY(getVelocityY() - 1);
			}
			break;

		case "left":
			if (getVelocityX() > -7) {
				setVelocityX(getVelocityX() - 2);
			} else if (getVelocityX() < 0) {
				setVelocityX(getVelocityX() + 1);
			}
			break;

		case "right":
			if (getVelocityX() < 7) {
				setVelocityX(getVelocityX() + 2);
			} else if (getVelocityX() > 0) {
				setVelocityX(getVelocityX() - 1);
			}
			break;

		case "updown":
			if (getVelocityY() > 0) {
				setVelocityY(getVelocityY() - 1);
			} else if (getVelocityY() < 0) {
				setVelocityY(getVelocityY() + 1);
			}
			break;

		case "leftright":
			if (getVelocityX() > 0) {
				setVelocityX(getVelocityX() - 1);
			} else if (getVelocityX() < 0) {
				setVelocityX(getVelocityX() + 1);
			}
			break;

		case "shootup":
			if (getShotTimer() == getShotCooldown()) {
				AttackLogic(270);
			}
			break;

		case "shootdown":
			if (getShotTimer() == getShotCooldown()) {
				AttackLogic(90);
			}
			break;

		case "shootleft":
			if (getShotTimer() == getShotCooldown()) {
				AttackLogic(180);
			}
			break;

		case "shootright":
			if (getShotTimer() == getShotCooldown()) {
				AttackLogic(0);
			}
			break;
		}
	}

	/**
	 * Handles movement of the player.
	 */
	@Override
	protected void MovementLogic() {
		if (getY() + getVelocityY() > 599 && !this.map.roomCheck(getMapX(), getMapY() + 1)) {
			setY(599);
			setVelocityY(0);
		} else if (getY() + getVelocityY() > 599 && this.map.roomCheck(getMapX(), getMapY() + 1)
				&& (getX() > 557 || getX() < 467)) {
			setY(599);
			setVelocityY(0);
		}

		if (getY() + getVelocityY() < 100 && !this.map.roomCheck(getMapX(), getMapY() - 1)) {
			setY(100);
			setVelocityY(0);
		} else if (getY() + getVelocityY() < 100 && this.map.roomCheck(getMapX(), getMapY() - 1)
				&& (getX() > 557 || getX() < 467)) {
			setY(100);
			setVelocityY(0);
		}

		if (getX() + getVelocityX() > 885 && !this.map.roomCheck(getMapX() + 1, getMapY())) {
			setX(885);
			setVelocityX(0);
		} else if (getX() + getVelocityX() > 885 && this.map.roomCheck(getMapX() + 1, getMapY())
				&& (getY() > 375 || getY() < 325)) {
			setX(885);
			setVelocityX(0);
		}

		if (getX() + getVelocityX() < 100 && !this.map.roomCheck(getMapX() - 1, getMapY())) {
			setX(100);
			setVelocityX(0);
		} else if (getX() + getVelocityX() < 100 && this.map.roomCheck(getMapX() - 1, getMapY())
				&& (getY() > 375 || getY() < 325)) {
			setX(100);
			setVelocityX(0);
		}

		setX(getX() + (int) getVelocityX());
		setY(getY() + (int) getVelocityY());
	}

	/**
	 * Handles attacking.
	 * 
	 * @param direction
	 *            Direction of attack.
	 */
	@Override
	protected void AttackLogic(int direction) {
		switch (direction) {
		case 270:
			handler.addObject(new Projectile(getX() + 10, getY() - 25, 0, -10));
			break;

		case 90:
			handler.addObject(new Projectile(getX() + 10, getY() + 45, 0, 10));
			break;

		case 180:
			handler.addObject(new Projectile(getX() - 25, getY() + 10, -10, 0));
			break;

		case 0:
			handler.addObject(new Projectile(getX() + 45, getY() + 10, 10, 0));
			break;

		}

	}

	/**
	 * Handles events based on health.
	 */
	@Override
	protected void HealthThresholdEvents() {
		if (getHealth() <= 0) {
			// Game over
		}
	}

	/**
	 * Handles interactions with other entities.
	 * 
	 * @param initiator
	 *            Entity that initiates interactions.
	 */
	@Override
	protected void checkInteraction(Entity initiator) {
		if (initiator instanceof Projectile) {
			ModifyHealth(initiator.getDamage());
		}
	}

	/**
	 * Handles changing rooms.
	 */
	private void ChangeRoom() {
		if (getX() > 940 && this.map.roomCheck(getMapX() + 1, getMapY())) {
			setX(40);
			handler.clearProjectiles();
			handler.clearPickups();
			levelHandler.addPickups(getMapX() + 1, getMapY());
			setMapX(getMapX() + 1);
		}

		if (getX() < 40 && this.map.roomCheck(getMapX() - 1, getMapY())) {
			setX(940);
			handler.clearProjectiles();
			handler.clearPickups();
			levelHandler.addPickups(getMapX() - 1, getMapY());
			setMapX(getMapX() - 1);
		}

		if (getY() < 40 && this.map.roomCheck(getMapX(), getMapY() - 1)) {
			setY(600);
			handler.clearProjectiles();
			handler.clearPickups();
			levelHandler.addPickups(getMapX(), getMapY() - 1);
			setMapY(getMapY() - 1);
		}

		if (getY() > 640 && this.map.roomCheck(getMapX(), getMapY() + 1)) {
			setY(40);
			handler.clearProjectiles();
			handler.clearPickups();
			levelHandler.addPickups(getMapX(), getMapY() + 1);
			setMapY(getMapY() + 1);
		}
	}

	@Override
	public void render(Graphics g) {
		switch (animState) {
		case "walking left":
			g.drawImage(left[animFrame], getX(), getY(), null);
			break;

		case "walking right":
			g.drawImage(right[animFrame], getX(), getY(), null);
			break;

		case "walking back":
			g.drawImage(back[animFrame], getX(), getY(), null);
			break;

		case "walking front":
			g.drawImage(front[animFrame], getX(), getY(), null);
			break;
		}
	}

	@Override
	protected void tick() {
		MovementLogic();
		ChangeRoom();
		UpdateShotDelay();

		animTimer++;

		if (animTimer == 6) {
			animTimer = 0;
		}

		if (animTimer == 5) {
			animFrame++;
			if (animFrame == 3) {
				animFrame = 0;
			}
		}
	}
}
