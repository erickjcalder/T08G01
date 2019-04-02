import java.awt.*;

public class Mosquito extends Enemy {

    private Image front[] = new Image[5];

    public Mosquito(int x, int y, LevelHandler levelHandler) {
        super(x, y, levelHandler);

        setVelocityX(3);
        setVelocityY(3);

        front[0] = Toolkit.getDefaultToolkit().getImage("resources/Mosquito Front.png");
        front[1] = Toolkit.getDefaultToolkit().getImage("resources/Mosquito Front Flying.png");

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
        int playerX = this.levelHandler.handler.object.getFirst().getX();
        int playerY = this.levelHandler.handler.object.getFirst().getY();
        boolean xFurtherThanY = (Math.abs(playerX)-Math.abs(this.getX()) > Math.abs(playerY)-Math.abs(this.getY()));

        if (playerX > this.getX()) {
            //Player to the right of mosquito
            if (xFurtherThanY) {
                setVelocityX(3);
            } else {
                setVelocityX(2);
            }
        } else if (playerX == this.getX()) {
            //Player above/below mosquito
            setVelocityX(0);
        } else {
            //Player to the left of mosquito
            if (xFurtherThanY) {
                setVelocityX(-3);
            } else {
                setVelocityX(-2);
            }
        }


        if(playerY > this.getY()) {
            //Player above mosquito
            if (xFurtherThanY) {
                setVelocityY(2);
            } else {
                setVelocityY(3);
            }
        } else if (playerY == this.getY()) {
            //Player beside mosquito
            setVelocityY(0);
        } else {
            //Player below mosquito
            if (xFurtherThanY) {
                setVelocityY(-2);
            } else {
                setVelocityY(-3);
            }
        }


        setX(getX() + (int)getVelocityX());
        setY(getY() + (int)getVelocityY());
    }

    @Override
    public void animationHandler() {

        setAnimState("walking front");

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
