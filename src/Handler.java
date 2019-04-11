
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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

	Node Save(Document save)
	{
		Element e = null;

		Node rootElement = save.createElement("Handler");

		e = save.createElement("objects");

		for(Entity entity : object)
		{
			e.appendChild(entity.Save(save));
		}

		rootElement.appendChild(e);

		return rootElement;
	}
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
	
	public void clearEntities() {
		while(object.size() > 0) {
			object.removeFirst();
		}
	}
	
	public Player getPlayerInstance() {
		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Player) {
				return (Player)object.get(i);
			}
		}
		return null;
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

	public void clearEnemies() {
		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Enemy) {
				removeObject(object.get(i));
				i = 0;
			}
		}
	}

	/**
	 * Checks to see if objects are overlapping with player
	 */
	public boolean checkPlayerCollision(String collisionType) {
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

			if (object.get(i) instanceof Projectile && collisionType.equals("projectile")) {
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

	/**
	 * Checks to see if objects are overlapping with an enemy
	 */
	public boolean checkEnemyCollision(String collisionType, Enemy enemy) {
		for (int i = 0; i < object.size(); i++) {
			if (object.get(i) instanceof Projectile && collisionType.equals("projectile")) {
				if ((enemy.getWidth() + enemy.getX() >= object.get(i).getX()
						&& enemy.getX() <= object.get(i).getWidth() + object.get(i).getX())
						&& (enemy.getHeight() + enemy.getY() >= object.get(i).getY()
								&& enemy.getY() <= object.get(i).getHeight() + object.get(i).getY())) {
					addObject(new Particle(object.get(i).getX(), object.get(i).getY(), "hitparticle", this));
					removeObject(object.get(i));
					return true;
				}
			}
		}
		return false;
	}

}
