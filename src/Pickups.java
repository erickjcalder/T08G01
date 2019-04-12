import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

/**
 * Controls everything that has to do with items that can be picked up
 *
 * @author Rahil
 * @version Final
 */

public class Pickups extends Entity {
	Random r = new Random();
	private int pickupNumber = 0;

	Handler handler;
	LevelHandler levelHandler;

	private Player player;

	Image health = Toolkit.getDefaultToolkit().getImage("resources/health_up.png");
	Image damage = Toolkit.getDefaultToolkit().getImage("resources/double_damage.png");
	Image speed = Toolkit.getDefaultToolkit().getImage("resources/attack_speed.png");
	Image defence = Toolkit.getDefaultToolkit().getImage("resources/defence_up.png");
	Image win = Toolkit.getDefaultToolkit().getImage("resources/Trophy.png");


	/**This contructor is called when randomly rolling for normal power pickup
	 *There are 4 types of pickups
	 *each helps the player to get advantage.
	 */
	public Pickups(Handler handler, LevelHandler levelHandler) {

		super(0,0, handler);

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

	/**This contructor is called creating the final pickup when you have defeated final
	 * boss. when you pick this pickup the game ends resulting in your win.
	 *
	 */
	public Pickups(Handler handler, LevelHandler levelHandler, int number, int x, int y) {

		super(0,0, handler);

		this.setX(x);
		this.setY(y);

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

		this.pickupNumber = number;


	}

	/**
	 * efectively increases damage by 2 times
	 */
	public void doubleDamage() {

		handler.getPlayerInstance().setDamage(100);
	}

	/**
	 * restores health to full
	 */
	public void healthRegen() {

		handler.getPlayerInstance().setHealth(100);

	}

	/**
	 * increases attack speed (by reducing shot Cooldown by half).
	 */
	public void bonusAtackSpeed() {

		handler.getPlayerInstance().setShotCooldown(15);

	}

	/**
	 * increases armor by ten everytime a rune is activated.
	 */
	public void armorUp() {

		handler.getPlayerInstance().setArmor(handler.getPlayerInstance().getArmor()+10);

	}

	public void endGame(){
		levelHandler.gameState="won";
	}


	/**
	 * this method deals with calling of methods, as the pickup spawning will be
	 * random. This method gives equal equal change to all the pickups.
	 */
	public void randomPickup() {

		if (pickupNumber == 0) {
			Random rand = new Random();

			if (rand.nextInt(4) == 0) {
				//bonusAtackSpeed();
				pickupNumber = 3;
			}

			if (rand.nextInt(4) == 1) {
				// calls doubleDamage method.
				pickupNumber = 1;
			}
			if (rand.nextInt(4) == 2) {
				//	healthRegen();
				pickupNumber = 2;
			}
			if (rand.nextInt(4) == 3) {
				//armorUp();
				pickupNumber = 4;
			}
		}
	}

	/**
	 * this method is never invoked as there are no buttons pressed for pickups
	 */
	public void logicInterface(String inp) {

	}

	/**
	 * Pickups are not mobile.
	 */

	public void movementLogic() {

	}

	/**
	 * handles updates
	 */
	public void tick() {

		randomPickup();
		if (handler.checkPlayerCollision("pickup")) {
			if (pickupNumber==1){
				doubleDamage();
			}

			if (pickupNumber==2){
				healthRegen();
			}

			if (pickupNumber==3){
				bonusAtackSpeed();
			}

			if (pickupNumber==4){
				armorUp();
			}

			if (pickupNumber==66){
				endGame();
			}

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

		if (pickupNumber==66)
			g.drawImage(win, getX(), getY(), 60, 60, null);
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