import javafx.geometry.Point2D;
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
     * Represents the "attack" stat of the entity.
     */
    private int damage = 1;

    /**
     * Represents multiplier to damage.
     */
    private double damageMult = 1.0;


    /**
     * Time passed since last shot, up to value of shotCooldown.
     */
    private int shotTimer = 0;

    /**
     * Amount of time that must pass between shots.
     */
    private int shotCooldown = 30;


    /**
     * Represents the (x,y) coordinates of the entity.
     */
    private Point2D location = null;


    /**
     * Represents velocity of movement in X axis.
     */
    private float velocityX = 0;

    /**
     * Represents velocity of movement in Y axis.
     */
    private float velocityY = 0;


    /**
     * Represents X coordinate of room entity is in.
     */
    private int mapX = 0;

    /**
     * Represents Y coordinate of room entity is in.
     */
    private int mapY = 0;


    /**
     * Entities cannot damage other entities on the same team, nor buff other entities on other teams
     */
    private String team = "";


    Entity(int X, int Y)
    {
        this.location = new Point2D(X, Y);
    }


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
     * Returns the time that must pass between shots.
     * @return Time that must pass between shots
     */
    protected int getShotCooldown()
    {
        return shotCooldown;
    }

    /**
     * Returns the time since the last shot.
     * @return Time that passed since the last shot.
     */
    protected int getShotTimer()
    {
        return shotTimer;
    }

    /**
     * Sets the time that must pass between shots.
     * @param shotCooldown The time that must pass between shots.
     */
    protected void setShotCooldown(int shotCooldown)
    {
        this.shotCooldown = shotCooldown;
    }


    /**
     * Returns the location of the entity.
     * @return location of the entity.
     */
    protected Point2D getLocation()
    {
        return location;
    }

    /**
     * Returns the X-value of the entity's position.
     * @return X-value of entity's position.
     */
    protected int getX()
    {
        return (int) this.location.getX();
    }

    /**
     * Returns the Y-value of the entity's position.
     * @return Y-value of entity position.
     */
    protected int getY()
    {
        return (int) this.location.getY();
    }

    /**
     * Sets the location of the entity.
     * @param X X-value of entity's position.
     * @param Y Y-value of entity's position.
     */
    protected void setLocation(int X, int Y)
    {
        this.location = new Point2D(X, Y);
    }

    /**
     * Sets the X-value of the entity's position.
     * @param X X-value of entity's position.
     */
    protected void setX(int X)
    {
        this.location = new Point2D(X, this.location.getY());
    }

    /**
     * Sets the Y-value of the entity's position.
     * @param Y Y-value of entity's position.
     */
    protected void setY(int Y)
    {
        this.location = new Point2D(this.location.getX(), Y);
    }


    /**
     * Sets X-value of velocity.
     * @param velocityX X-value of velocity.
     */
    protected void setVelocityX(float velocityX)
    {
        this.velocityX = velocityX;
    }

    /**
     * Sets Y-value of velocity.
     * @param velocityY Y-value of velocity.
     */
    protected void setVelocityY(float velocityY)
    {
        this.velocityY = velocityY;
    }

    /**
     * Gets X-value of velocity.
     * @return X-value of velocity.
     */
    protected float getVelocityX()
    {
        return velocityX;
    }

    /**
     * Gets Y-value of velocity.
     * @return Y-value of velocity.
     */
    protected float getVelocityY()
    {
        return velocityY;
    }


    /**
     * Returns X position on the map.
     * @return X position on the map.
     */
    public int getMapX()
    {
        return mapX;
    }

    /**
     * Returns Y position on the map.
     * @return Y position on the map.
     */
    public int getMapY()
    {
        return mapY;
    }


    /**
     * Sets X position on map.
     * @param mapX X position on map.
     */
    protected void setMapX(int mapX)
    {
        this.mapX = mapX;
    }

    /**
     * Sets Y position on map.
     * @param mapY Y position on map.
     */
    protected void setMapY(int mapY)
    {
        this.mapY = mapY;
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
     * Handles all AI or controls.
     * @param input Action to be performed.
     */
    public abstract void LogicInterface(String input);

    /**
     * Represents the computation of the entity's movement.
     */
    protected abstract void MovementLogic();

    /**
     * Represents the computation of the entity's attacks.
     */
    protected abstract void AttackLogic(int direction);

    /**
     * Updates the delay on the shots.
     */
    protected void UpdateShotDelay() {
        if(this.shotTimer < this.shotCooldown)
        {
            shotTimer++;
        }
    }

    /**
     * Code for rendering the entity.
     * @param g Graphics object to be drawn to.
     */
    abstract public void render(Graphics g);

    /**
     * Handles updates.
     */
    protected abstract void tick();
}
