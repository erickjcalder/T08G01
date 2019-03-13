import java.awt.Color;
import java.awt.Graphics;
import java.math.*;

class Player extends ActiveEntity
{
    /**
     * Instance of the current map.
     */
    private Map map;
    private Handler handler;


    Player(int X, int Y, LevelHandler levelHandler)
    {
        super(X, Y);
        this.map = levelHandler.getMap();
        this.handler = levelHandler.getHandler();
        setMapX(2);
        setMapY(1);
        setHealthMax(100);
        setHealth(100);
        setArmor(0);
        setDamage(1);
        setDamageMult(1);
        setName("Player");
        setTeam("player");
    }

    /**
     * Handles conversion of keypresses to actions.
     */
    @Override
    public void LogicInterface(String input)
    {
        switch(input)
        {
            case "up":
            {
                if(getVelocityY() > -7)
                {
                    setVelocityY(getVelocityY() - 2);
                } else if(getVelocityY() < 0)
                {
                    setVelocityY(getVelocityY() + 1);
                }
            }

            case "down":
            {
                if(getVelocityY() < 7)
                {
                    setVelocityY(getVelocityY() + 2);
                } else if(getVelocityY() > 0)
                {
                    setVelocityY(getVelocityY() - 1);
                }
            }

            case "left":
            {
                if(getVelocityX() > -7)
                {
                    setVelocityX(getVelocityX() - 2);
                } else if(getVelocityX() < 0)
                {
                    setVelocityX(getVelocityX() + 1);
                }
            }

            case "right":
            {
                if(getVelocityY() < 7)
                {
                    setVelocityY(getVelocityY() + 2);
                } else if(getVelocityY() > 0)
                {
                    setVelocityY(getVelocityY() - 1);
                }
            }

            case "updown":
            {
                if (getVelocityY() > 0) {
                    setVelocityY(getVelocityY() - 1);
                } else if (getVelocityY() < 0) {
                    setVelocityY(getVelocityY() + 1);
                }
            }

            case "leftright":
            {
                if (getVelocityX() > 0) {
                    setVelocityX(getVelocityX() - 1);
                } else if (getVelocityX() < 0) {
                    setVelocityX(getVelocityX() + 1);
                }
            }

            case "shootup":
            {
                if(getShotTimer() == getShotCooldown())
                {
                    AttackLogic(270);
                }
            }

            case "shootdown":
            {
                if(getShotTimer() == getShotCooldown())
                {
                    AttackLogic(90);
                }
            }

            case "shootleft":
            {
                if(getShotTimer() == getShotCooldown())
                {
                    AttackLogic(180);
                }
            }

            case "shootright":
            {
                if(getShotTimer() == getShotCooldown())
                {
                    AttackLogic(0);
                }
            }
        }
    }


    /**
     * Handles movement of the player.
     */
    @Override
    protected void MovementLogic()
    {
        if (getY() + getVelocityY() > 599 && !this.map.roomCheck(getMapX(), getMapY() + 1)) {
            setY(599);
            setVelocityY(0);
        } else if (getY() + getVelocityY() > 599 && this.map.roomCheck(getMapX(), getMapY() + 1) && (getX() > 557 || getX() < 467)) {
            setY(599);
            setVelocityY(0);
        }

        if (getY() + getVelocityY() < 100 && !this.map.roomCheck(getMapX(), getMapY() - 1)) {
            setY(100);
            setVelocityY(0);
        } else if (getY() + getVelocityY() < 100 && this.map.roomCheck(getMapX(), getMapY() - 1) && (getX() > 557 || getX() < 467)) {
            setY(100);
            setVelocityY(0);
        }

        if (getX() + getVelocityX() > 885 && !this.map.roomCheck(getMapX() + 1, getMapY())) {
            setX(885);
            setVelocityX(0);
        } else if (getX() + getVelocityX() > 885 && this.map.roomCheck(getMapX() + 1, getMapY()) && (getY() > 375 || getY() < 325)) {
            setX(885);
            setVelocityX(0);
        }

        if (getX() + getVelocityX() < 100 && !this.map.roomCheck(getMapX() - 1, getMapY())) {
            setX(100);
            setVelocityX(0);
        } else if (getX() + getVelocityX() < 100 && this.map.roomCheck(getMapX() - 1, getMapY()) && (getY() > 375 || getY() < 325)) {
            setX(100);
            setVelocityX(0);
        }

        setX(getX() + (int) getVelocityX());
        setY(getY() + (int) getVelocityY());
    }

    /**
     * Handles attacking.
     * @param direction Direction of attack.
     */
    @Override
    protected void AttackLogic(int direction)
    {
        handler.addObject(new Projectile(getX(), getY(), 10 * (int) Math.sin(0), 10 * (int) Math.cos(0)));
    }

    /**
     * Handles events based on health.
     */
    @Override
    protected void HealthThresholdEvents()
    {
        if(getHealth() <= 0)
        {
            //Game over
        }
    }

    /**
     * Handles interactions with other entities.
     * @param initiator Entity that initiates interactions.
     */
    @Override
    protected void checkInteraction(Entity initiator)
    {
        if(initiator instanceof Projectile)
        {
            ModifyHealth(initiator.getDamage());
        }
    }

    /**
     * Handles changing rooms.
     */
    private void ChangeRoom()
    {
        if (getX() > 940 && this.map.roomCheck(getMapX() + 1, getMapY())) {
            setX(40);
            setMapX(getMapX() + 1);
        }

        if (getX() < 40 && this.map.roomCheck(getMapX() - 1, getMapY())) {
            setX(940);
            setMapX(getMapX() - 1);
        }

        if (getY() < 40 && this.map.roomCheck(getMapX(), getMapY() - 1)) {
            setY(600);
            setMapY(getMapY() - 1);
        }

        if (getY() > 640 && this.map.roomCheck(getMapX(), getMapY() + 1)) {
            setY(40);
            setMapY(getMapY() + 1);
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(new Color(255, 255, 255));
        g.fillRect(this.getX(), this.getY(), 40, 40);
    }

    @Override
    protected void tick()
    {
        MovementLogic();
        ChangeRoom();
        UpdateShotDelay();
    }
}
