import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

/**
 * Controls everything that has to do with items that can be picked up
 *
 * @author Rahil
 * @version Demo 2
 */

public class Pickups extends Entity {
	ActiveEntity ae;
	Random r = new Random();
	private int pickupNumber = 0;

	Handler handler;
	LevelHandler levelHandler;

	Image health = Toolkit.getDefaultToolkit().getImage("resources/health_up.png");
	Image damage = Toolkit.getDefaultToolkit().getImage("resources/double_damage.png");
	Image speed = Toolkit.getDefaultToolkit().getImage("resources/attack_speed.png");
	Image defence = Toolkit.getDefaultToolkit().getImage("resources/defence_up.png");



	public Pickups(Handler handler, LevelHandler levelHandler) {
		/**
		 * As the pickup always spawns in the middle of the room, the location will
		 * always be the same.
		 */


		super(0,0);

		Random r = new Random();
		this.setX(r.nextInt((835 - 100) + 1) + 100);
		this.setY(r.nextInt((539 - 100) + 1) + 100);

		this.handler = handler;
		this.levelHandler = levelHandler;


		this.setName("Pickup");
		this.setTeam("");

		this.setMapX(2);
		this.setMapY(1);

		this.setVelocityX(0);
		this.setVelocityY(0);

		setWidth(38);
		setHeight(38);

	}


	public void doubleDamage() {
		/**
		 * efectively increases damage by 2 times
		 */
		super.setDamageMult(2.0);
		pickupNumber = 1;
	}

	/**
	 public void healthRegen() {
	 /**
	 * restores health to full
	 ae.setHealth(ae.getHealthMax());
	 pickupNumber = 2;
	 }
	 */
	public void bonusAtackSpeed() {
		/**
		 * increases attack speed (by reducing shot Cooldown by half).
		 */
		super.setShotCooldown(15);
		pickupNumber = 3;
	}
	/**
	 public void armorUp() {
	 /**
	 * increases armor by one everytime a rune is activated.
	 ae.setArmor(ae.getArmor() + 1);
	 pickupNumber =4;
	 }
	 */

	public void randomPickup() {
		/**
		 * this method deals with calling of methods, as the pickup spawning will be
		 * random. This method gives equal equal change to all the pickups.
		 */

		if (pickupNumber==0){
			Random rand = new Random();

			if (rand.nextInt(2) == 0) {
				bonusAtackSpeed();
			}

			if (rand.nextInt(2) == 1) {
				// calls doubleDamage method.
				doubleDamage();
			}
			//if (rand.nextInt(20) == 2) {
			//	healthRegen();
			//}
			//if (rand.nextInt(20) == 3) {
			//	armorUp();
		}

		if (pickupNumber==1){
			doubleDamage();
		}

		if (pickupNumber==3){
			bonusAtackSpeed();
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



	/**
	 * handles updates
	 */
	public void tick() {
		randomPickup();
		if (handler.checkCollision("pickup")) {
			levelHandler.removePickup(this);
		}

	}

	/**
	 * Draws the pickup to the screen
	 */
	public void render(Graphics g) {
		if (pickupNumber==1)
			g.drawImage(damage, getX(), getY(), 38, 38, null);

		if (pickupNumber==2)
			g.drawImage(health, getX(), getY(), 38, 38, null);

		if (pickupNumber==3)
			g.drawImage(speed, getX(), getY(), 38, 38, null);

		if (pickupNumber==4)
			g.drawImage(defence, getX(), getY(), 38, 38, null);
	}

	@Override
	protected void checkInteraction(Entity initiator) {
		// TODO Auto-generated method stub


	}

	@Override
	protected void attackLogic(int direction) {
		// TODO Auto-generated method stub
		/**
		 * Pickups dont have any attack mechanic.
		 */
	}

}