import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.awt.*;

/**
 * Represents any entity.
 */
abstract class Entity
{
    /**
     * Represents the name of the entity.
     */
    private String name = "";

    /**
     * Represents the "attack" stat of the entity. Negative values act as healing.
     */
    private int damage = 1;

    /**
     * Represents multiplier to damage.
     */
    private double damageMult = 1.0;

    /**
     * Represents the (x,y) coordinates of the entity.
     */
    private Point2D location = null;

    /**
     * Represents the (x,y) coordinates of the room the entity is in.
     */
    private Point2D currentRoom = null;

    /**
     * The image displayed.
     */
    private Image graphics = null;

    /**
     * Entities cannot damage other entities on the same team, nor buff other entities on other teams
     */
    private String team = "";

    /**
     * Returns the name of the entity.
     * @return name of the entity.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Sets the name of the entity.
     * @param name name of the entity.
     */
    protected void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the damage of the entity.
     * @return damage of the entity.
     */
    public int getDamage()
    {
        return this.damage;
    }

    /**
     * Sets the damage of the entity.
     * @param damage damage of the entity.
     */
    protected void setDamage(int damage)
    {
        this.damage = damage;
    }

    /**
     * Returns the damage multiplier of the entity.
     * @return damage multiplier of the entity.
     */
    public double getDamageMult()
    {
        return this.damageMult;
    }

    /**
     * Sets the damage multiplier of the entity.
     * @param damageMult damage multiplier of the entity.
     */
    protected void setDamageMult(double damageMult)
    {
        this.damageMult = damageMult;
    }

    /**
     * Returns the location of the entity.
     * @return location of the entity.
     */
    public Point2D getLocation()
    {
        return this.location;
    }

    /**
     * Sets the location of the entity.
     * @param location location of the entity.
     */
    protected void setLocation(Point2D location)
    {
        this.location = location;
    }

    /**
     * Returns the current room of the entity.
     * @return current room of the entity.
     */
    public Point2D getCurrentRoom()
    {
        return this.currentRoom;
    }

    /**
     * Sets the current room of the entity.
     * @param currentRoom current room of the entity.
     */
    protected void setCurrentRoom(Point2D currentRoom)
    {
        this.currentRoom = currentRoom;
    }

    /**
     * Returns the graphical representation of the entity.
     * @return graphical representation of the entity.
     */
    public Image getGraphics()
    {
        return this.graphics;
    }

    /**
     * Sets the graphical representation of the entity.
     * @param graphics graphical representation of the entity.
     */
    protected void setGraphics(Image graphics)
    {
        this.graphics = graphics;
    }

    /**
     * Gets the team of the entity.
     * @return team of the entity.
     */
    public String getTeam()
    {
        return this.team;
    }

    /**
     * Sets the team of the entity.
     * @param team team of the entity.
     */
    protected void setTeam(String team)
    {
        this.team = team;
    }

    /**
     * Represents the computation of the entity's movement.
     */
    protected abstract void MovementLogic();

    /**
     * Represents the computation of the entity's attacks.
     */
    protected abstract void AttackLogic(int direction);
}
