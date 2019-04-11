import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Represents any 'active' entity. (e.g. enemies, player)
 *
 * @author Justin
 * @version Demo 2
 */
abstract class ActiveEntity extends Entity {
	/**
	 * Represents maximum health of the entity.
	 */
	private int healthMax = 100;

	/**
	 * Represents current health of the entity. Cannot exceed maximum health.
	 */
	private int health = 100;

	/**
	 * Represents armor, or damage reduction of entity. Cannot be less than 0 or
	 * greater than 100. Armor is an integer that is then converted to a percentage
	 * of damage reduction based on a formula.
	 */
	private int armor = 0;

	ActiveEntity(int x, int y, Handler handler) {
		super(x, y, handler);
	}

	/**
	 * Returns maximum health of entity.
	 * 
	 * @return maximum health of entity.
	 */
	public int getHealthMax() {
		return healthMax;
	}

	/**
	 * Sets maximum health of entity.
	 * 
	 * @param healthMax
	 *            maximum health of entity.
	 */
	protected void setHealthMax(int healthMax) {
		this.healthMax = healthMax;
	}

	/**
	 * Gets current health of entity.
	 * 
	 * @return current health of entity.
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets current health of entity, up to maximum health.
	 * 
	 * @param health
	 *            current health of entity.
	 */
	void setHealth(int health) {
		this.health = health;

		if (this.health > this.healthMax) {
			this.health = this.healthMax;
		}
	}

	/**
	 * Gets armor value of entity.
	 * 
	 * @return armor value of entity.
	 */
	public int getArmor() {
		return armor;
	}

	/**
	 * Sets armor value of entity, where armor is between 0 and 100.
	 * 
	 * @param armor
	 *            armor value of entity.
	 */
	void setArmor(int armor) {
		this.armor = armor;
		if (this.armor > 100) {
			this.armor = 100;
		} else if (this.armor < 0) {
			this.armor = 0;
		}
	}

	/**
	 * Represents events caused by health.
	 */
	protected abstract void healthThresholdEvents();

	/**
	 * Returns the damage mitigation multiplier of the entity.
	 * 
	 * @return damage mitigation multiplier of the entity.
	 */
	private double getArmorAbsorption() {
		if (this.armor >= 100) {
			return 0.7;
		} else if (this.armor <= 0) {
			return 0.0;
		} else {
			// Diminishing returns formula.
			// 0:0 25:.329 50:.509 75:.622 100:.70
			return ((1.12 * this.armor) / (this.armor + 60));
		}
	}

	/**
	 * Damages health of entity based on armor and damage.
	 * 
	 * @param baseDamage
	 *            damage taken.
	 */
	protected void modifyHealth(int baseDamage) {
		this.health -= baseDamage * (1 - getArmorAbsorption());
	}

	/**
	 * Damages health of entity based on armor and damage.
	 * 
	 * @param baseDamage
	 *            damage taken.
	 * @param bypassArmor
	 *            if the damage should ignore armor.
	 */
	protected void modifyHealth(int baseDamage, boolean bypassArmor) {
		if (bypassArmor) {
			this.health -= baseDamage;
		} else {
			modifyHealth(baseDamage);
		}
	}

	@Override
	Element Save(Document save)
	{
		Element e = null;

		Element rootElement = super.Save(save);

		e = save.createElement("healthMax");
		e.appendChild(save.createTextNode(Integer.toString(getHealthMax())));
		rootElement.appendChild(e);

		e = save.createElement("health");
		e.appendChild(save.createTextNode(Integer.toString(getHealth())));
		rootElement.appendChild(e);

		e = save.createElement("armor");
		e.appendChild(save.createTextNode(Integer.toString(getArmor())));
		rootElement.appendChild(e);

		return rootElement;
	}
}
