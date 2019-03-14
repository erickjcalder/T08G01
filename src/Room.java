import java.awt.Color;
import java.awt.Graphics;

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

	public int getRoomType() {
		return this.roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

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
