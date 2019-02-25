/**
 * Represents any 'active' entity. (e.g. enemies, player)
 */
abstract class ActiveEntity extends Entity
{
    /**
     * Represents maximum health of the entity.
     */
    int healthMax;

    /**
     * Represents current health of the entity.
     */
    int health;

    /**
     * Represents velocity of movement.
     */
    float velocityX;
    float velocityY;

    /**
     * Represents events caused by health.
     */
    abstract void HealthThresholdEvents();

    /**
     * Checks interaction with entity and acts based on type.
     * @param initiator Entity that initiates interactions.
     */
    abstract void checkInteraction(Entity initiator);
}
