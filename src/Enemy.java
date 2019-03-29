import java.awt.Graphics;

public abstract class Enemy extends ActiveEntity {

	private Handler handler;
	LevelHandler levelHandler;

	/**
	 * Creates a new Enemy object by setting all default values every stat
	 * 
	 * @Param int the x position of the Enemy
	 * @param int
	 *            the y position of the Enemy
	 * @param LevelHandler
	 *            the levelHandler object
	 */
	Enemy(int x, int y, LevelHandler levelHandler) {
		super(x, y);
		this.handler = levelHandler.getHandler();
		this.levelHandler = levelHandler;
		setMapX(2);
		setMapY(1);
		setHealthMax(100);
		setHealth(100);
		setArmor(0);
		setDamage(1);
		setDamageMult(1);
		setName("Enemy");
		setTeam("enemy");

	}

	/**
	 * The underlying AI of every enemy that tells it where to attack
	 * 
	 * @Param int the direction that an attack will be sent
	 */
	@Override
	protected abstract void attackLogic(int direction);

	/**
	 * The underlying AI of every enemy that tells it where to move
	 */
	@Override
	protected abstract void movementLogic();

	/**
	 * Handles events that are triggered once an Enemy has 0 health. Mostly used to
	 * dispose of enemy from the screen and the enemyList in the room
	 */
	@Override
	protected void healthThresholdEvents() {
		if (getHealth() <= 0) {
			handler.removeObject(this);
			levelHandler.removeEnemy(this);
		}
	}

	/**
	 * Handles interactions between Enemies and projectiles shot by the player
	 */
	@Override
	protected void checkInteraction(Entity initiator) {
		if (initiator instanceof Projectile) {
			modifyHealth(initiator.getDamage());
		}
	}

	/**
	 * Handles the animation of Enemies
	 */
	public abstract void animationHandler();

	/**
	 * The method that is called by Handler to render every Enemy
	 */
	public abstract void render(Graphics g);

}
