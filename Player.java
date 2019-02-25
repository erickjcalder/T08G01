import javafx.geometry.Point2D;

class Player extends ActiveEntity
{
    /**
     * Band-aid to make retreat function work; will be removed for GUI ver.
     * Represents the previous room.
     */
    private Point2D previousRoom;  //PREPRODUCTION

    Player(int baseDamage, int baseHealth)
    {
        healthMax = baseHealth;
        health = healthMax;
        damage = baseDamage;
        name = "Player";
        team = "player";
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
            case "w":
            {
                velocityY = 1;
                break;
            }
            case "s":
            {
                velocityY = -1;
                break;
            }
            case "d":
            {
                velocityX = 1;
                break;
            }
            case "a":
            {
                velocityX = -1;
                break;
            }
            case "attack":
            {
                AttackLogic(0);
            }

            case "retreat":  //PREPRODUCTION
            {
                if(currentRoom.getX() > previousRoom.getX())
                {
                    velocityX = -1;
                }
                else if(currentRoom.getX() < previousRoom.getX())
                {
                    velocityX = 1;
                }
                else if(currentRoom.getY() > previousRoom.getY())
                {
                    velocityY = -1;
                }
                else if(currentRoom.getY() < previousRoom.getY())
                {
                    velocityY = 1;
                }
            }
        }
        MovementLogic();  //PREPRODUCTION
                          //Put in mainloop instead.
    }


    /**
     * Handles movement of the player.
     */
    @Override
    void MovementLogic()
    {
        if(velocityX == 1)
        {
            ChangeRoom(new Point2D(currentRoom.getX() + 1, currentRoom.getY()));
        }
        else if(velocityX == -1)
        {
            ChangeRoom(new Point2D(currentRoom.getX() - 1, currentRoom.getY()));
        }
        else if(velocityY == 1)
        {
            ChangeRoom(new Point2D(currentRoom.getX(), currentRoom.getY() + 1));
        }
        else if(velocityY == -1)
        {
            ChangeRoom(new Point2D(currentRoom.getX(), currentRoom.getY() - 1));
        }

    }

    /**
     * Handles attacking.
     * @param direction Direction of attack.
     */
    @Override
    void AttackLogic(int direction)
    {
        //Attacks
    }

    /**
     * Handles events based on health.
     */
    @Override
    void HealthThresholdEvents()
    {
        if(health <= 0)
        {
            //Game over
        }
    }

    /**
     * Handles interactions with other entities.
     * @param initiator Entity that initiates interactions.
     */
    @Override
    void checkInteraction(Entity initiator)
    {
        // if initator is enemy:
        health -= initiator.damage;
    }

    /**
     * Handles changing rooms.
     * @param room Coordinates of room to change to.
     */
    private void ChangeRoom(Point2D room)
    {
        previousRoom = currentRoom;
        currentRoom = room;
    }
}
