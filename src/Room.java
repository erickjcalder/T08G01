import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Random;

/**
 * Room objects are used to determine what spawns in the room that the player is
 * in on the map
 *
 * @author Erick
 * @version Demo 2
 */
public class Room {

    Random r = new Random();

	boolean north, south, east, west;
	boolean unresolvedEvent;
	int roomType;
	LinkedList<Pickups> pickupList = new LinkedList<Pickups>();
	LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
	LevelHandler levelHandler;
	Image wallTexture = Toolkit.getDefaultToolkit().getImage("resources/roomBackground.png");
	Image doorClosedNorth = Toolkit.getDefaultToolkit().getImage("resources/door_closed_north.png");
	Image doorClosedSouth = Toolkit.getDefaultToolkit().getImage("resources/door_closed_south.png");
	Image doorClosedEast = Toolkit.getDefaultToolkit().getImage("resources/door_closed_east.png");
	Image doorClosedWest = Toolkit.getDefaultToolkit().getImage("resources/door_closed_west.png");

	Image doorOpenNorth = Toolkit.getDefaultToolkit().getImage("resources/door_open_north.png");
	Image doorOpenSouth = Toolkit.getDefaultToolkit().getImage("resources/door_open_south.png");
	Image doorOpenEast = Toolkit.getDefaultToolkit().getImage("resources/door_open_east.png");
	Image doorOpenWest = Toolkit.getDefaultToolkit().getImage("resources/door_open_west.png");

    /**
     * Saves Room.
     * @param save Document to save to.
     * @return Element to save to document.
     */
	Element Save(Document save)
	{
		Element e = null;

		Element rootElement = save.createElement("Room");

		e = save.createElement("NorthSouthEastWest");
		e.appendChild(save.createTextNode(Boolean.toString(this.north) +
				" " + Boolean.toString(this.south) +
				" " + Boolean.toString(this.east) +
				" " + Boolean.toString(this.west)));
		rootElement.appendChild(e);

		e = save.createElement("unresolvedEvent");
		e.appendChild(save.createTextNode(Boolean.toString(this.unresolvedEvent)));
		rootElement.appendChild(e);

		e = save.createElement("roomType");
		e.appendChild(save.createTextNode(Integer.toString(this.roomType)));
		rootElement.appendChild(e);

		// Saves pickups.
		if(!pickupList.isEmpty())
		{
			Node pickupStack = e.appendChild(save.createElement("pickupList"));
			for(Pickups pickup : this.pickupList)
			{
				if(pickup != null)
				{
					pickupStack.appendChild(pickup.Save(save));
				}
			}
			rootElement.appendChild(pickupStack);
		}

		// Saves enemies.
		if(!enemyList.isEmpty())
		{
			Node enemyStack = e.appendChild(save.createElement("enemyList"));
			for(Enemy enemy : this.enemyList)
			{
				if(enemy != null)
				{
					enemyStack.appendChild(enemy.Save(save));
				}
			}
			rootElement.appendChild(enemyStack);
		}


		return rootElement;
	}

	// Each room has 4 booleans that represent if there is a door on that side of
	// the room
	// also have variables for if there is an unresolved event and for what kind of
	// room it is
	public Room(boolean north, boolean south, boolean east, boolean west, boolean unresolvedEvent, int roomType,
			LevelHandler levelHandler) {

	    this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
		this.unresolvedEvent = unresolvedEvent;
		this.roomType = roomType;
		this.levelHandler = levelHandler;

		int enemiesToSpawn = r.nextInt(6);

		switch(enemiesToSpawn) {
            case 1: {
                enemyList.add(new Wasp(500, 270, levelHandler));
                break;
            }

            case 2: {
                enemyList.add(new Mosquito(500, 270, levelHandler));
                break;
            }

            case 3: {
                enemyList.add(new Mosquito(400, 270, levelHandler));
                enemyList.add(new Mosquito(600, 270, levelHandler));
                break;
            }

			case 4: {
				enemyList.add(new Worm(500, 270, levelHandler));
				break;
			}

            case 5: {
                enemyList.add(new Worm(600, 270, levelHandler));
                enemyList.add(new Wasp(400, 270, levelHandler));
                break;
            }
        }

		Random rand = new Random();
		if (rand.nextInt(100) < 30) {
			pickupList.add(new Pickups(levelHandler.getHandler(), levelHandler));
		}

	}

	/**
	 * Returns the type of room of the Room object
	 *
	 * @return int the type of room, represented by an integer
	 */
	public int getRoomType() {
		return this.roomType;
	}

	/**
	 * Sets the roomType to a given value
	 *
	 * @param int
	 *            the type of room that the Room will be changed to
	 */
	public void setRoomType(int roomType) {

		this.roomType = roomType;
	}

	public void removeEnemy(Enemy enemy) {
		enemyList.remove(enemy);
	}

	public void removePickup(Pickups pickup) {
		pickupList.remove(pickup);
	}

	public void setUnresolvedEvent(boolean unresolvedEvent) {
		this.unresolvedEvent = unresolvedEvent;
	}

	public int getEnemyListSize() {
		return enemyList.size();
	}

	public boolean getUnresolvedEvent() {
		return this.unresolvedEvent;
	}

	/**
	 * Draws the current room to the screen
	 */
	public void render(Graphics g) {
		g.drawImage(wallTexture, 0, 0, null);

		g.setColor(new Color(0, 0, 0));

		// draws northern door
		if (this.north) {
			for (int i = 0; i < 30; i++) {
				g.fillRect(467 - i, 97 - i * 3, 90 + i * 2, 3);
			}
			if (unresolvedEvent) {
				g.drawImage(doorClosedNorth, 432, 7, null);
			} else {
				g.drawImage(doorOpenNorth, 392, 7, null);
			}
		}

		// draws southern door
		if (this.south) {
			for (int i = 0; i < 30; i++) {
				g.fillRect(467 - i, 639 + i * 3, 90 + i * 2, 3);
			}
			if (unresolvedEvent) {
				g.drawImage(doorClosedSouth, 432, 639, null);
			} else {
				g.drawImage(doorOpenSouth, 392, 639, null);
			}
		}

		// draws eastern door
		if (this.east) {
			for (int i = 0; i < 30; i++) {
				g.fillRect(925 + i * 3, 325 - i, 3, 90 + i * 2);
			}
			if (unresolvedEvent) {
				g.drawImage(doorClosedEast, 925, 290, null);
			} else {
				g.drawImage(doorOpenEast, 925, 250, null);
			}
		}

		// draws western door
		if (this.west) {
			for (int i = 0; i < 30; i++) {
				g.fillRect(97 - i * 3, 325 - i, 3, 90 + i * 2);
			}
			if (unresolvedEvent) {
				g.drawImage(doorClosedWest, 7, 290, null);
			} else {
				g.drawImage(doorOpenWest, 7, 250, null);
			}
		}

	}

    /**
     * Sets list of pickups.
     * @param pickupList List of pickups.
     */
	public void setPickupList(LinkedList<Pickups> pickupList)
	{
		this.pickupList = pickupList;
	}

    /**
     * Sets list of enemies.
     * @param enemyList List of enemies.
     */
	public void setEnemyList(LinkedList<Enemy> enemyList)
	{
		this.enemyList = enemyList;
	}

    /**
     * Sets levelHandler.
     * @param levelHandler Instance of levelHandler.
     */
	public void setLevelHandler(LevelHandler levelHandler)
	{
		this.levelHandler = levelHandler;
	}

}

