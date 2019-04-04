import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Particle extends Entity {

	private Image hitparticle = Toolkit.getDefaultToolkit().getImage("resources/hit_particle.png");
	private String particleType;
	private int timer;

	Particle(int x, int y, String particleType, Handler handler) {
		super(x, y, handler);
		this.particleType = particleType;
		timer = 0;
	}

	@Override
	protected void checkInteraction(Entity initiator) {

	}

	@Override
	public void logicInterface(String input) {

	}

	@Override
	protected void movementLogic() {

	}

	@Override
	protected void attackLogic(int direction) {

	}

	@Override
	public void render(Graphics g) {
		if (particleType.equals("hitparticle")) {
			g.drawImage(hitparticle, getX(), getY(), 50, 50, null);
		}

	}

	@Override
	protected void tick() {
		if (timer >= 3) {
			handler.removeObject(this);
		}
		timer++;
	}

}
