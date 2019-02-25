import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Represents any entity.
 */
abstract class Entity
{
    /**
     * Represents the name of the entity.
     */
    String name = "";

    /**
     * Represents the "attack" stat of the entity. Negative values act as healing.
     */
    int damage = 0;

    /**
     * Represents the (x,y) coordinates of the entity.
     */
    Point2D location = null;

    /**
     * Represents the (x,y) coordinates of the room the entity is in.
     */
    Point2D currentRoom = null;

    /**
     * The image displayed.
     */
    Image graphics = null;

    /**
     * Entities cannot affect other entities on the same team.
     */
    String team;

    /**
     * Represents the computation of the entity's movement.
     */
    abstract void MovementLogic();

    /**
     * Represents the computation of the entity's attacks.
     */
    abstract void AttackLogic(int direction);
}
