import javafx.geometry.Point2D;

class Player extends ActiveEntity
{
    Player()
    {
        setHealthMax(100);
        setHealth(100);
        setArmor(0);
        setDamage(1);
        setDamageMult(1);
        setName("Player");
        setTeam("player");
    }

    /**
     * Handles controls for the player. Actual actions are passed off
     * to logic classes.
     * @param input String inputted.
     */
    void Controls(String input)
    {
        switch(input.toLowerCase())
        {
            default:
            {
                normalizeVelocity();
            }
            case "w":
            {
                addVelocity(0, 1);
                break;
            }
            case "s":
            {
                addVelocity(0, -1);
                break;
            }
            case "d":
            {
                addVelocity(1, 0);
                break;
            }
            case "a":
            {
                addVelocity(-1, 0);
                break;
            }
            case "attack":
            {
                AttackLogic(0);
                break;
            }
        }
    }


    /**
     * Handles movement of the player.
     */
    @Override
    protected void MovementLogic()
    {
        setLocation(new Point2D(getLocation().getX() + getVelocityX(), getLocation().getY() + getVelocityY()));
    }

    /**
     * Handles attacking.
     * @param direction Direction of attack.
     */
    @Override
    protected void AttackLogic(int direction)
    {
        //Attacks
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
        if(initiator instanceof Enemy)
        {
            ModifyHealth(initiator.getDamage());
        }
    }

    /**
     * Handles changing rooms.
     * @param room Coordinates of room to change to.
     */
    private void ChangeRoom(Point2D room)
    {
        setCurrentRoom(room);
    }
}
