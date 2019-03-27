import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Represents any player character.
 *
 * @author Justin
 * @version Demo 2
 */
class Player extends ActiveEntity {
	/**
	 * Instance of the current map.
	 */
	private Map map;
	private Handler handler;
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
		setMapX(levelHandler.map.getStartX());
		setMapY(levelHandler.map.getStartY());
		setHealthMax(100);
		setHealth(100);
		setArmor(0);
		setDamage(1);
		setDamageMult(1);
		setName("Player");
		setTeam("player");

		setWidth(56);
		setHeight(84);

		setAnimState("walking front");

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
	public void logicInterface(String input) {
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
				attackLogic(270);
			}
			break;

		case "shootdown":
			if (getShotTimer() == getShotCooldown()) {
				attackLogic(90);
			}
			break;

		case "shootleft":
			if (getShotTimer() == getShotCooldown()) {
				attackLogic(180);
			}
			break;

		case "shootright":
			if (getShotTimer() == getShotCooldown()) {
				attackLogic(0);
			}
			break;
		}
	}

	/**
	 * Handles movement of the player.
	 */
	@Override
	protected void movementLogic() {
		if (getY() + getHeight() + getVelocityY() > 640 && !this.map.roomCheck(getMapX(), getMapY() + 1)) {
			setY(640 - getHeight());
			setVelocityY(0);
		} else if (getY() + getHeight() + getVelocityY() > 640 && this.map.roomCheck(getMapX(), getMapY() + 1)
				&& (getX() > 515 || getX() < 452)) {
			setY(640 - getHeight());
			setVelocityY(0);
		}

		if (getY() + getVelocityY() < 55 && !this.map.roomCheck(getMapX(), getMapY() - 1)) {
			setY(55);
			setVelocityY(0);
		} else if (getY() + getVelocityY() < 55 && this.map.roomCheck(getMapX(), getMapY() - 1)
				&& (getX() > 515 || getX() < 452)) {
			setY(55);
			setVelocityY(0);
		}

		if (getX() + getWidth() + getVelocityX() > 936 && !this.map.roomCheck(getMapX() + 1, getMapY())) {
			setX(936 - getWidth());
			setVelocityX(0);
		} else if (getX() + getWidth() + getVelocityX() > 936 && this.map.roomCheck(getMapX() + 1, getMapY())
				&& (getY() > 335 || getY() < 265)) {
			setX(936 - getWidth());
			setVelocityX(0);
		}

		if (getX() + getVelocityX() < 90 && !this.map.roomCheck(getMapX() - 1, getMapY())) {
			setX(90);
			setVelocityX(0);
		} else if (getX() + getVelocityX() < 90 && this.map.roomCheck(getMapX() - 1, getMapY())
				&& (getY() > 335 || getY() < 265)) {
			setX(90);
			setVelocityX(0);
		}

		setX(getX() + (int) getVelocityX());
		setY(getY() + (int) getVelocityY());
	}

	/**
	 * Handles attacking.
	 * 
	 * @param direction Direction of attack.
	 */
	@Override
	protected void attackLogic(int direction) {

		if (getShotTimer() >= getShotCooldown()) {

			setShotTimer(0);

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
	}

	/**
	 * Handles events based on health.
	 */
	@Override
	protected void healthThresholdEvents() {
		if (getHealth() <= 0) {
			// Game over
		}
	}

	/**
	 * Handles interactions with other entities.
	 * 
	 * @param initiator Entity that initiates interactions.
	 */
	@Override
	protected void checkInteraction(Entity initiator) {
		if (initiator instanceof Projectile) {
			modifyHealth(initiator.getDamage());
		}
	}

	/**
	 * Handles changing rooms.
	 */
	private void changeRoom() {
		if (getX() > 940 && this.map.roomCheck(getMapX() + 1, getMapY())) {
			setX(40);
			handler.clearProjectiles();
			handler.clearPickups();
			handler.clearEnemies();
			levelHandler.addPickups(getMapX() + 1, getMapY());
			levelHandler.addEnemies(getMapX() + 1, getMapY());
			setMapX(getMapX() + 1);
			levelHandler.setCurrentRoomX(getMapX());
		}

		if (getX() < 40 && this.map.roomCheck(getMapX() - 1, getMapY())) {
			setX(940);
			handler.clearProjectiles();
			handler.clearPickups();
			handler.clearEnemies();
			levelHandler.addPickups(getMapX() - 1, getMapY());
			levelHandler.addEnemies(getMapX() - 1, getMapY());
			setMapX(getMapX() - 1);
			levelHandler.setCurrentRoomX(getMapX());
		}

		if (getY() < 40 && this.map.roomCheck(getMapX(), getMapY() - 1)) {
			setY(600);
			handler.clearProjectiles();
			handler.clearPickups();
			handler.clearEnemies();
			levelHandler.addPickups(getMapX(), getMapY() - 1);
			levelHandler.addEnemies(getMapX(), getMapY() - 1);
			setMapY(getMapY() - 1);
			levelHandler.setCurrentRoomY(getMapY());
		}

		if (getY() > 640 && this.map.roomCheck(getMapX(), getMapY() + 1)) {
			setY(40);
			handler.clearProjectiles();
			handler.clearPickups();
			handler.clearEnemies();
			levelHandler.addPickups(getMapX(), getMapY() + 1);
			levelHandler.addEnemies(getMapX(), getMapY() + 1);
			setMapY(getMapY() + 1);
			levelHandler.setCurrentRoomY(getMapY());
		}
	}

	public void animationHandler() {
		setAnimTimer(getAnimTimer() + 1);

		if (getAnimState().contains("throw")) {
			if (getAnimTimer() > 6) {
				setAnimTimer(0);
			}

			if (getAnimTimer() == 5) {
				setAnimFrame(getAnimFrame() + 1);
				if (getAnimFrame() >= 5) {
					setAnimFrame(3);
				}
			}
		}

		if (getAnimState().contains("walking")) {
			if (getAnimTimer() > 6) {
				setAnimTimer(0);
			}

			if (getAnimTimer() == 5) {
				setAnimFrame(getAnimFrame() + 1);
				if (getAnimFrame() >= 3) {
					setAnimFrame(0);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		switch (getAnimState()) {
		case "walking left":
			g.drawImage(left[getAnimFrame()], getX(), getY(), 56, 84, null);
			break;

		case "walking right":
			g.drawImage(right[getAnimFrame()], getX(), getY(), 56, 84, null);
			break;

		case "walking back":
			g.drawImage(back[getAnimFrame()], getX(), getY(), 56, 84, null);
			break;

		case "walking front":
			g.drawImage(front[getAnimFrame()], getX(), getY(), 56, 84, null);
			break;

		case "throw front":
			g.drawImage(front[getAnimFrame()], getX(), getY(), 56, 84, null);
			break;

		case "throw back":
			g.drawImage(back[getAnimFrame()], getX(), getY(), 56, 84, null);
			break;

		case "throw left":
			g.drawImage(left[getAnimFrame()], getX(), getY(), 56, 84, null);
			break;

		case "throw right":
			g.drawImage(right[getAnimFrame()], getX(), getY(), 56, 84, null);
			break;
		}
	}

	@Override
	protected void tick() {
		movementLogic();
		changeRoom();
		updateShotDelay();
		animationHandler();
	}
}
