import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Projectiles are any object that is shot by an Enemy or Player
 *
 * @author Vlad
 * @version Demo 2
 */

public class Projectile extends Entity {

	Image projectile = Toolkit.getDefaultToolkit().getImage("resources/Projectile.png");

	public Projectile(int x, int y, int velX, int velY) {
		super(x, y);
		this.setName("projectile");
		this.setTeam("");

		this.setX(x);
		this.setY(y);

		this.setVelocityX(velX);
		this.setVelocityY(velY);
	}

	@Override
	public void tick() {
		setX(getX() + (int) getVelocityX());
		setY(getY() + (int) getVelocityY());
	}

	/**
	 * Draws the Projectile to the screen
	 * 
	 * @param Graphics
	 *            the graphics object that is used to draw to the screen
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(projectile, getX(), getY(), null);

	}

	/**
	 * Handles all AI or controls.
	 * 
	 * @param input
	 *            Action to be performed.
	 */
	public void LogicInterface(String input) {

	}

	/**
	 * Represents the computation of the Projectile's movement.
	 */
	public void MovementLogic() {

	}

	public void AttackLogic(int direction) {

	}

	@Override
	protected void checkInteraction(Entity initiator) {
		// TODO Auto-generated method stub

	}
}