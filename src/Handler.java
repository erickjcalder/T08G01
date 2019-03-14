import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<Entity> object = new LinkedList<Entity>();

	// loops through every object and ticks it
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			Entity tempObject = object.get(i);
			tempObject.tick();

			if ((tempObject instanceof Projectile) && (tempObject.getX() > 1100 || tempObject.getX() < -100
					|| tempObject.getY() > 800 || tempObject.getY() < -100)) {
				removeObject(tempObject);
			}
		}
		checkCollision();
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
	
	public void addPickups() {
		
	}

	public void clearProjectiles() {
		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Projectile) {
				removeObject(object.get(i));
				i = 0;
			}
		}
	}
	
	public void clearPickups() {
		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Pickups) {
				removeObject(object.get(i));
				i = 0;
			}
		}
	}

	public void checkCollision() {
		Entity playerObject = object.get(0);

		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Player) {
				playerObject = object.get(i);
			}
		}
		
		for (int i = 0; i < object.size(); i++) {
			if (!object.get(i).equals(playerObject)) {
			if ((playerObject.getWidth() + playerObject.getX() >= object.get(i).getX()
					&& playerObject.getX() <= object.get(i).getWidth() + object.get(i).getX())
					&& (playerObject.getHeight() + playerObject.getY() >= object.get(i).getY()
							&& playerObject.getY() <= object.get(i).getHeight() + object.get(i).getY())) {
				removeObject(object.get(i));
			}
		}
		}
	}

}
