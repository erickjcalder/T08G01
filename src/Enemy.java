import java.awt.Graphics;

public abstract class Enemy extends ActiveEntity {

	LevelHandler levelHandler;

	public Enemy(int x, int y, LevelHandler levelHandler) {
		super(x, y, levelHandler.getHandler());
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

	@Override
	protected abstract void attackLogic(int direction);

	

	@Override
	protected abstract void movementLogic();
	
	@Override
	protected void healthThresholdEvents() {
		if (getHealth() <= 0) {
			handler.removeObject(this);
			levelHandler.removeEnemy(this);
		}
	}

	@Override
	protected void checkInteraction(Entity initiator) {
		if (initiator instanceof Projectile) {
			modifyHealth(initiator.getDamage());
		}
	}

	public abstract void animationHandler();

	// @Override
	public abstract void render(Graphics g);
	
	protected void checkCollision() {
		if (handler.checkEnemyCollision("projectile", this)) {
			setHealth(getHealth() - 10);
		}
	}

    @Override
    void setHealth(int health) {
        super.setHealth(health);
    }
}
