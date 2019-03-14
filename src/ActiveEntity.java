/**
 * Represents any 'active' entity. (e.g. enemies, player)
 */
abstract class ActiveEntity extends Entity
{
    /**
     * Represents maximum health of the entity.
     */
    private int healthMax = 100;

    /**
     * Represents current health of the entity. Cannot exceed maximum health.
     */
    private int health = 100;

    /**
     * Represents armor, or damage reduction of entity.
     * Cannot be less than 0 or greater than 100.
     * Armor is an integer that is then converted to a percentage
     * of damage reduction based on a formula.
     */
    private int armor = 0;


    ActiveEntity(int X, int Y)
    {
        super(X, Y);
    }


    /**
     * Returns maximum health of entity.
     * @return maximum health of entity.
     */
    public int getHealthMax()
    {
        return healthMax;
    }

    /**
     * Sets maximum health of entity.
     * @param healthMax maximum health of entity.
     */
    protected void setHealthMax(int healthMax)
    {
        this.healthMax = healthMax;
    }

    /**
     * Gets current health of entity.
     * @return current health of entity.
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Sets current health of entity, up to maximum health.
     * @param health current health of entity.
     */
    void setHealth(int health)
    {
        this.health = health;

        if(this.health > this.healthMax)
        {
            this.health = this.healthMax;
        }
    }

    /**
     * Gets armor value of entity.
     * @return armor value of entity.
     */
    public int getArmor()
    {
        return armor;
    }

    /**
     * Sets armor value of entity, where armor is between 0 and 100.
     * @param armor armor value of entity.
     */
    void setArmor(int armor)
    {
        this.armor = armor;
        if(this.armor > 100)
        {
            this.armor = 100;
        }
        else if(this.armor < 0)
        {
            this.armor = 0;
        }
    }

    /**
     * Represents events caused by health.
     */
    protected abstract void HealthThresholdEvents();

    /**
     * Checks interaction with entity and acts based on type.
     * @param initiator Entity that initiates interactions.
     */
    protected abstract void checkInteraction(Entity initiator);

    /**
     * Returns the damage mitigation multiplier of the entity.
     * @return damage mitigation multiplier of the entity.
     */
    private double getArmorAbsorption()
    {
        if(this.armor >= 100)
        {
            return 0.7;
        }
        else if(this.armor <= 0)
        {
            return 0.0;
        }
        else
        {
            // Diminishing returns formula.
            // 0:0  25:.329  50:.509  75:.622  100:.70
            return ((1.12 * this.armor) / (this.armor + 60));
        }
    }

    /**
     * Damages health of entity based on armor and damage.
     * @param baseDamage damage taken.
     */
    protected void ModifyHealth(int baseDamage)
    {
        this.health -= baseDamage * (1 - getArmorAbsorption());
    }

    /**
     * Damages health of entity based on armor and damage.
     * @param baseDamage damage taken.
     * @param bypassArmor if the damage should ignore armor.
     */
    protected void ModifyHealth(int baseDamage, boolean bypassArmor)
    {
        if(bypassArmor)
        {
            this.health -= baseDamage;
        }
        else
        {
            ModifyHealth(baseDamage);
        }
    }
}
