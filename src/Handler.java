import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Handles all the entities that are on screen
 *
 * @author Parker
 * @version Demo 2
 */

public class Handler {
	LinkedList<Entity> object = new LinkedList<Entity>();

	/**
	 * Loops through every object in the LinkedList of entities and calls its tick
	 * method
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			Entity tempObject = object.get(i);
			tempObject.tick();

			if ((tempObject instanceof Projectile) && (tempObject.getX() > 1100 || tempObject.getX() < -100
					|| tempObject.getY() > 800 || tempObject.getY() < -100)) {
				removeObject(tempObject);
			}
		}
	}

	/**
	 * Loops through every object in the LinkedList of entities and calls its render
	 * method
	 */
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			Entity tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	/**
	 * Adds the object to the LinkedList of objects
	 */
	public void addObject(Entity object) {
		this.object.add(object);
	}

	/**
	 * Removes the object to the LinkedList of objects
	 */
	public void removeObject(Entity object) {
		this.object.remove(object);
	}

	/**
	 * Clears all Projectile objects from the screen
	 */
	public void clearProjectiles() {
		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Projectile) {
				removeObject(object.get(i));
				i = 0;
			}
		}
	}

	/**
	 * Clears all Pickup objects from the screen
	 */
	public void clearPickups() {
		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Pickups) {
				removeObject(object.get(i));
				i = 0;
			}
		}
	}

	/**
	 * Clears all Enemy objects from the screen
	 */
	public void clearEnemies() {
		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Enemy) {
				removeObject(object.get(i));
				i = 0;
			}
		}
	}

	/**
	 * Checks to see if a certain type of object is overlapping with the Player
	 * 
	 * @param String
	 *            the object type that collision is being checked for
	 */
	public boolean checkCollision(String collisionType) {
		Entity playerObject = object.get(0);

		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Player) {
				playerObject = object.get(i);
			}
		}

		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Pickups && collisionType.equals("pickup")) {
				if ((playerObject.getWidth() + playerObject.getX() >= object.get(i).getX()
						&& playerObject.getX() <= object.get(i).getWidth() + object.get(i).getX())
						&& (playerObject.getHeight() + playerObject.getY() >= object.get(i).getY()
								&& playerObject.getY() <= object.get(i).getHeight() + object.get(i).getY())) {
					removeObject(object.get(i));
					return true;
				}
			}

			if (object.get(i) instanceof Enemy && collisionType.equals("enemy")) {
				if ((playerObject.getWidth() + playerObject.getX() >= object.get(i).getX()
						&& playerObject.getX() <= object.get(i).getWidth() + object.get(i).getX())
						&& (playerObject.getHeight() + playerObject.getY() >= object.get(i).getY()
								&& playerObject.getY() <= object.get(i).getHeight() + object.get(i).getY())) {
					return true;
				}
			}
		}
		return false;
	}

}
