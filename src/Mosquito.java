import java.awt.*;

public class Mosquito extends Enemy {

	private Image front[] = new Image[5];
	private Image left[] = new Image[5];
	private Image right[] = new Image[5];
	private Image back[] = new Image[5];

	public Mosquito(int x, int y, LevelHandler levelHandler) {
		super(x, y, levelHandler);

		setVelocityX(3);
		setVelocityY(3);

		front[0] = Toolkit.getDefaultToolkit().getImage("resources/Mosquito Front.png");
		front[1] = Toolkit.getDefaultToolkit().getImage("resources/Mosquito Front Flying.png");

		left[0] = Toolkit.getDefaultToolkit().getImage("resources/Mosquito Left Side.png");
        left[1] = Toolkit.getDefaultToolkit().getImage("resources/Mosquito Left Side Flying.png");

        right[0] = Toolkit.getDefaultToolkit().getImage("resources/Mosquito Right Side.png");
        right[1] = Toolkit.getDefaultToolkit().getImage("resources/Mosquito Right Side Flying.png");

        back[0] = Toolkit.getDefaultToolkit().getImage("resources/Mosquito Back.png");
        back[1] = Toolkit.getDefaultToolkit().getImage("resources/Mosquito Back Flying.png");

		setAnimTimer(0);
		setAnimFrame(0);
		setAnimState("walking front");

		super.setHealth(15);

		setWidth(80);
		setHeight(80);
	}

	@Override
	public void logicInterface(String input) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void attackLogic(int direction) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void movementLogic() {
		int playerX = this.handler.getPlayerInstance().getX();
		int playerY = this.handler.getPlayerInstance().getY();
		boolean xFurtherThanY = (Math.abs(playerX) - Math.abs(this.getX()) > Math.abs(playerY) - Math.abs(this.getY()));

		if (Math.abs(playerX - this.getX()) <= 3) {
            // Player above/below mosquito
            setVelocityX(0);
		} else if (playerX > this.getX()) {
		    // Player to the right of mosquito
            if (xFurtherThanY) {
                setVelocityX(3);
            } else {
                setVelocityX(2);
            }
		} else {
			// Player to the left of mosquito
			if (xFurtherThanY) {
				setVelocityX(-3);
			} else {
				setVelocityX(-2);
			}
		}

		if (Math.abs(playerY - this.getY()) <= 3) {
            // Player beside mosquito
            setVelocityY(0);
		} else if (playerY > this.getY()) {
            // Player above mosquito
            if (xFurtherThanY) {
                setVelocityY(2);
            } else {
                setVelocityY(3);
            }
		} else {
			// Player below mosquito
			if (xFurtherThanY) {
				setVelocityY(-2);
			} else {
				setVelocityY(-3);
			}
		}

		setX(getX() + (int) getVelocityX());
		setY(getY() + (int) getVelocityY());
	}

	@Override
	public void animationHandler() {

	    if (getVelocityY() > getVelocityX()) {
            if (getVelocityY() >= 0) {
                setAnimState("walking front");
            } else {
                setAnimState("walking back");
            }
        } else {
	        if (getVelocityX() <= 0) {
	            setAnimState("walking left");
            } else {
	            setAnimState("walking right");
            }
        }

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

	@Override
	protected void tick() {
		movementLogic();
		animationHandler();
		healthThresholdEvents();
		this.checkCollision();

	}
}
