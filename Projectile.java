import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends Entity{

	public Projectile(int x, int y, int velX, int velY) {
		super(x, y);
		
		this.id = "projectile";
		
		this.velX = velX;
		this.velY = velY;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(185, 0, 0));
		g.fillOval(x, y, 20, 20);
		
	}

}
