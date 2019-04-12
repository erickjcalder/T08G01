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
	Image web = Toolkit.getDefaultToolkit().getImage("resources/Web Projectile.png");

	private String type;

	public Projectile(int x, int y, int velX, int velY, Handler handler, String type) {
		super(x, y, handler);
		this.setName("projectile");
		this.setTeam("");

		this.setX(x);
		this.setY(y);

		this.setVelocityX(velX);
		this.setVelocityY(velY);

		this.type = type;
		
		if (type.equals("player")) {
			setWidth(20);
			setHeight(20);
		}
		
		if (type.equals("web")) {
			setWidth(90);
			setHeight(90);
		}
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
		if (type.equals("player")) {
			g.drawImage(projectile, getX(), getY(), null);
		}

		if (type.equals("web")) {
			g.drawImage(web, getX(), getY(), 90, 90, null);
		}

	}

	/**
	 * Handles all AI or controls.
	 * 
	 * @param input
	 *            Action to be performed.
	 */
	public void logicInterface(String input) {

	}

	/**
	 * Represents the computation of the Projectile's movement.
	 */
	public void movementLogic() {

	}

	public void attackLogic(int direction) {

	}

	@Override
	protected void checkInteraction(Entity initiator) {
		// TODO Auto-generated method stub

	}
	
	public String getType() {
		return new String(this.type);
	}
}