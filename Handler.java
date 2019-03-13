import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<Entity> object = new LinkedList<Entity>();

	// loops through every object and ticks it
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			Entity tempObject = object.get(i);
			tempObject.tick();

			if (tempObject.getID().equals("projectile") && (tempObject.getX() > 1100 || tempObject.getX() < -100
					|| tempObject.getY() > 800 || tempObject.getY() < -100)) {
				removeObject(tempObject);
			}
		}
	}

	// Loops through every object and renders it
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			Entity tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	public void addObject(Entity object) {
		this.object.add(object);
	}

	public void removeObject(Entity object) {
		this.object.remove(object);
	}

	public void clearProjectiles() {
		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Projectile) {
				removeObject(object.get(i));
				i = 0;
			}
		}
	}

}
