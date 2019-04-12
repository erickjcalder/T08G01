import java.awt.*;

public class Worm extends Enemy {

    private Image emerging[] = new Image[5];
    private Image front[] = new Image[5];
    private Image left[] = new Image[5];
    private Image right[] = new Image[5];
    private Image back[] = new Image[5];
    private int timer = 0;
    private int playerX;
    private int playerY;

    public Worm(int x, int y, LevelHandler levelHandler) {
        super(x, y, levelHandler);

        setVelocityX(0);
        setVelocityY(0);

        emerging[0] = Toolkit.getDefaultToolkit().getImage("resources/Worm Head Coming Out.png");
        emerging[1] = Toolkit.getDefaultToolkit().getImage("resources/Worm Head Coming Out.png");

        front[0] = Toolkit.getDefaultToolkit().getImage("resources/Worm Front Full Out.png");
        front[1] = Toolkit.getDefaultToolkit().getImage("resources/Worm Half Out.png");

        left[0] = Toolkit.getDefaultToolkit().getImage("resources/Worm Left Down.png");
        left[1] = Toolkit.getDefaultToolkit().getImage("resources/Worm Left Up.png");

        right[0] = Toolkit.getDefaultToolkit().getImage("resources/Worm Right Down.png");
        right[1] = Toolkit.getDefaultToolkit().getImage("resources/Worm Right Up.png");

        back[0] = Toolkit.getDefaultToolkit().getImage("resources/Worm Back Down.png");
        back[1] = Toolkit.getDefaultToolkit().getImage("resources/Worm Back Up.png");

        setAnimTimer(0);
        setAnimFrame(0);
        setAnimState("emerging");

        super.setHealth(70);

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
        if (getAnimState().equals("emerging")) {
            this.playerX = this.handler.getPlayerInstance().getX();
            this.playerY = this.handler.getPlayerInstance().getY();
        } else {
            //UPPER
            if (this.getX() < playerX) {
                int distanceX = playerX - this.getX();
                if (distanceX >= 200) {
                    setVelocityX(7);
                } else if (distanceX >= 150) {
                    setVelocityX(6);
                } else if (distanceX >= 100) {
                    setVelocityX(5);
                } else if (distanceX >= 50) {
                    setVelocityX(4);
                } else if (distanceX < 4) {
                    setVelocityX(0);
                }
            } else {
                int distanceX = this.getX() - playerX;
                if (distanceX >= 200) {
                    setVelocityX(-7);
                } else if (distanceX >= 150) {
                    setVelocityX(-6);
                } else if (distanceX >= 100) {
                    setVelocityX(-5);
                } else if (distanceX >= 50) {
                    setVelocityX(-4);
                } else if (distanceX < 4) {
                    setVelocityX(0);
                }
            }

            if (this.getY() < playerY) {
                int distanceY = playerY - this.getY();
                if (distanceY >= 200) {
                    setVelocityY(7);
                } else if (distanceY >= 150) {
                    setVelocityY(6);
                } else if (distanceY >= 100) {
                    setVelocityY(5);
                } else if (distanceY >= 50) {
                    setVelocityY(4);
                } else if (distanceY < 4) {
                    setVelocityY(0);
                }
            } else {
                int distanceY = this.getY() - playerY;
                if (distanceY >= 200) {
                    setVelocityY(-7);
                } else if (distanceY >= 150) {
                    setVelocityY(-6);
                } else if (distanceY >= 100) {
                    setVelocityY(-5);
                } else if (distanceY >= 50) {
                    setVelocityY(-4);
                } else if (distanceY < 4) {
                    setVelocityY(0);
                }
            }


            if (getVelocityX() == 0 && getVelocityY() == 0) {
                setAnimState("emerging");
                timer = 0;
            }

            setX(getX() + (int) getVelocityX());
            setY(getY() + (int) getVelocityY());
        }
    }

    @Override
    public void animationHandler() {

        if (getAnimState().equals("emerging") && timer < 100) {
            timer++;
        } else {
            if (getVelocityY() == getVelocityX()) {
                setAnimState("walking front");
            } else if (Math.abs(getVelocityX()) > Math.abs(getVelocityY())) {
                if (getVelocityX() < 0) {
                    setAnimState("walking left");
                } else {
                    setAnimState("walking right");
                }
            } else {
                if (getVelocityY() < 0) {
                    setAnimState("walking back");
                } else {
                    setAnimState("walking front");
                }
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
            case "emerging":
                g.drawImage(emerging[0],getX(),getY(),80,80,null);
                break;

            case "walking left":
                g.drawImage(left[getAnimFrame()], getX(), getY(), 60, 100, null);
                break;

            case "walking right":
                g.drawImage(right[getAnimFrame()], getX(), getY(), 60, 100, null);
                break;

            case "walking back":
                g.drawImage(back[getAnimFrame()], getX(), getY(), 60, 100, null);
                break;

            case "walking front":
                g.drawImage(front[getAnimFrame()], getX(), getY(), 80, 100, null);
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
