import java.lang.Math;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Controls everything that has to do with items that can be picked up
 *
 * @author Rahil
 * @version Demo 2
 */

public class Pickups extends Entity {
	ActiveEntity ae;

	Image health = Toolkit.getDefaultToolkit().getImage("resources/health_up.png");
	Image damage = Toolkit.getDefaultToolkit().getImage("resources/double_damage.png");
	Image speed = Toolkit.getDefaultToolkit().getImage("resources/attack_speed.png");
	Image defence = Toolkit.getDefaultToolkit().getImage("resources/defence_up.png");

	public Pickups() {
		/**
		 * As the pickup always spawns in the middle of the room, the location will
		 * always be the same.
		 */

		super(500, 500);

		this.setName("Pickup");
		this.setTeam("");

		this.setMapX(2);
		this.setMapY(1);

		this.setVelocityX(0);
		this.setVelocityY(0);

		setWidth(38);
		setHeight(38);

	}
	// this class deals with the random powerups that spawn on every new level at
	// random times.

	public void doubleDamage() {
		/**
		 * efectively increases damage by 2 times
		 */
		super.setDamageMult(2.0);
	}

	public void healthRegen() {
		/**
		 * restores health to full
		 */
		ae.setHealth(ae.getHealthMax());
	}

	public void bonusAtackSpeed() {
		/**
		 * increases attack speed (by reducing shot Cooldown by half).
		 */
		super.setShotCooldown(15);
	}

	public void armorUp() {
		/**
		 * increases armor by one everytime a rune is activated.
		 */
		ae.setArmor(ae.getArmor() + 1);
	}

	public void randomPickup() {
		/**
		 * this method deals with calling of methods, as the pickup spawning will be
		 * random this method gives equal equal change to all the pickups.
		 */
		double rand = (int) (Math.random() * 4);
		if (rand == 0) {
			bonusAtackSpeed();
		}

		if (rand == 1) {
			// calls doubleDamage method.
			doubleDamage();
		}

		if (rand == 2) {
			healthRegen();
		}
		if (rand == 3) {
			armorUp();
		}

	}

	public void logicInterface(String inp) {
		/**
		 * this method is never invoked as there are no buttons pressed for pickups
		 */
	}

	public void movementLogic() {
		/**
		 * Pickups are not mobile.
		 */

	}

	public void attackLogic() {
		/**
		 * Pickups dont have any attack mechanic.
		 */

	}

	/**
	 * handles updates
	 */
	public void tick() {
		// randomPickup();

	}

	/**
	 * Draws the pickup to the screen
	 */
	public void render(Graphics g) {
		g.drawImage(health, getX(), getY(), 38, 38, null);
	}

	@Override
	protected void checkInteraction(Entity initiator) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void attackLogic(int direction) {
		// TODO Auto-generated method stub

	}

}
