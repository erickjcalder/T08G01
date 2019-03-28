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

		enemyList.add(new Wasp(500, 200, levelHandler));

		Random rand = new Random();
		if (rand.nextInt(100) < 25) {
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
			// g.drawImage(doorClosedNorth, 432, 7, null);
			g.drawImage(doorOpenNorth, 392, 7, null);
		}

		// draws southern door
		if (this.south) {
			for (int i = 0; i < 30; i++) {
				g.fillRect(467 - i, 639 + i * 3, 90 + i * 2, 3);
			}
			// g.drawImage(doorClosedSouth, 432, 639, null);
			g.drawImage(doorOpenSouth, 392, 639, null);
		}

		// draws eastern door
		if (this.east) {
			for (int i = 0; i < 30; i++) {
				g.fillRect(925 + i * 3, 325 - i, 3, 90 + i * 2);
			}
			// g.drawImage(doorClosedEast, 925, 290, null);
			g.drawImage(doorOpenEast, 925, 250, null);
		}

		// draws western door
		if (this.west) {
			for (int i = 0; i < 30; i++) {
				g.fillRect(97 - i * 3, 325 - i, 3, 90 + i * 2);
			}
			// g.drawImage(doorClosedWest, 7, 290, null);
			g.drawImage(doorOpenWest, 7, 250, null);
		}

	}

}
