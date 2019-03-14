import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends Entity{

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
		setX(getX()+(int)getVelocityX());
		setY(getY()+(int)getVelocityY());
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(185, 0, 0));
		g.fillOval(getX(), getY(), 20, 20);
		
	}

	/**
	 * Handles all AI or controls.
	 * @param input Action to be performed.
	 */
	public void LogicInterface(String input){

	}

	/**
	 * Represents the computation of the Projectile's movement.
	 */
	public void MovementLogic(){

	}

	public void AttackLogic(int direction){


	}
}
