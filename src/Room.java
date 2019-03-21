import java.awt.Color;
import java.awt.Graphics;

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
	Pickups pickups[] = new Pickups[4];
	LevelHandler levelHandler;

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

		this.pickups[0] = new Pickups();

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
	 * @param int the type of room that the Room will be changed to
	 */
	public void setRoomType(int roomType) {

		this.roomType = roomType;
	}
	
	/**
	 * Draws the current room to the screen
	 */
	public void render(Graphics g) {
		g.setColor(new Color(222, 184, 135));

		// Draws north wall of the room
		for (int i = 0; i < 100; i++) {
			g.setColor(new Color(222 - i, 184 - i, 135 - i));
			g.fillRect(i, i, 1024 - i * 2, 1);
		}

		// Draws west wall of the room
		for (int i = 0; i < 100; i++) {
			g.setColor(new Color(222 - i, 184 - i, 135 - i));
			g.fillRect(i, i + 1, 1, 738 - i * 2);
		}

		// Draws south wall of the room
		for (int i = 0; i < 100; i++) {
			g.setColor(new Color(222 - i, 184 - i, 135 - i));
			g.fillRect(i + 1, 738 - i, 1024 - i * 2, 1);
		}

		// Draws east wall of the room
		for (int i = 0; i < 100; i++) {
			g.setColor(new Color(222 - i, 184 - i, 135 - i));
			g.fillRect(1024 - i, i, 1, 738 - i * 2);
		}

		g.setColor(new Color(0, 0, 0));

		// draws northern door
		if (this.north) {
			for (int i = 0; i < 30; i++) {
				g.fillRect(467 - i, 97 - i * 3, 90 + i * 2, 3);
			}
		}

		// draws southern door
		if (this.south) {
			for (int i = 0; i < 30; i++) {
				g.fillRect(467 - i, 639 + i * 3, 90 + i * 2, 3);
			}
		}

		// draws eastern door
		if (this.east) {
			for (int i = 0; i < 30; i++) {
				g.fillRect(925 + i * 3, 325 - i, 3, 90 + i * 2);
			}
		}

		// draws western door
		if (this.west) {
			for (int i = 0; i < 30; i++) {
				g.fillRect(97 - i * 3, 325 - i, 3, 90 + i * 2);
			}
		}

	}

}
