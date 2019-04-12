import java.awt.Color;
import java.awt.Graphics;

/**
 * Determines and handles how each level is handled within the game
 *
 * @author Parker
 * @version Demo 2
 */
public class LevelHandler {

	Handler handler;
	Map map;
	int width, height, currentRoomX, currentRoomY;
	String gameState = "";

	/**
	 * Creates a new LevelHandler object and also creates a new Map
	 */
	public LevelHandler(Handler handler) {
		this.handler = handler;
		width = 7;
		height = 7;
		map = new Map(width, height, this);
		currentRoomX = map.getStartX();
		currentRoomY = map.getStartY();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				map.roomLoc[i][j].setUnresolvedEvent(true);
			}
		}

		map.roomLoc[currentRoomX][currentRoomY].setUnresolvedEvent(false);
	}

	/**
	 * Returns the current Map object belonging to the LevelHandler
	 * 
	 * @return Map the instance of Map that belongs to LevelHandler
	 */

	public Map getMap() {
		return map;
	}

	/**
	 * Returns the current Handler object belonging to the LevelHandler
	 * 
	 * @return Handler the instance of Handler that belongs to LevelHandler
	 */

	public Handler getHandler() {
		return handler;
	}

	public void removeEnemy(Enemy enemy) {
		this.map.roomLoc[this.currentRoomX][this.currentRoomY].removeEnemy(enemy);
		System.out.println(currentRoomX);
	}

	public void removePickup(Pickups pickup) {
		this.map.roomLoc[this.currentRoomX][this.currentRoomY].removePickup(pickup);
		System.out.println(currentRoomX);
	}

	/**
	 * Adds the pickups in the current room to the object list in Handler
	 */

	public void addPickups(int mapX, int mapY) {
		if (this.map.roomLoc[mapX][mapY].pickupList.size() > 0) {
			for (int i = 0; i < this.map.roomLoc[mapX][mapY].pickupList.size(); i++) {
				handler.addObject(this.map.roomLoc[mapX][mapY].pickupList.get(i));
			}
		}
	}

	public void addEnemies(int mapX, int mapY) {
		if (this.map.roomLoc[mapX][mapY].enemyList.size() > 0) {
			for (int i = 0; i < this.map.roomLoc[mapX][mapY].enemyList.size(); i++) {
				handler.addObject(this.map.roomLoc[mapX][mapY].enemyList.get(i));
			}
		}
	}
	
	public void addPickupToRoom(Pickups pickup) {
		this.map.roomLoc[currentRoomX][currentRoomY].pickupList.add(pickup);
	}

	public void setCurrentRoomX(int currentRoomX) {
		this.currentRoomX = currentRoomX;
	}

	public void setCurrentRoomY(int currentRoomY) {
		this.currentRoomY = currentRoomY;
	}

	/**
	 * Draws the minimap in the top right corner
	 */

	public void renderMap(Graphics g) {
		Entity playerObject = handler.object.get(0);

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i) instanceof Player) {
				playerObject = handler.object.get(i);
			}
		}

		if (playerObject instanceof Player) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (map.roomLoc[i][j].getRoomType() != 0) {

						g.setColor(new Color(200, 200, 200));
						g.fillRect(1022 - (width * 25) + (i * 20), 23 + (j * 20), 12, 12);

						g.setColor(new Color(100, 100, 100));

						if (i == ((Player) playerObject).getMapX() && j == ((Player) playerObject).getMapY()) {
							g.setColor(new Color(255, 100, 100));
						}

						g.fillRect(1024 - (width * 25) + (i * 20), 25 + (j * 20), 10, 10);
						if (map.roomLoc[i][j].east) {
							g.setColor(new Color(0, 0, 0));
							g.fillRect(1024 - (width * 25) + (i * 20) + 10, 25 + (j * 20) + 4, 10, 2);
						}

						if (map.roomLoc[i][j].north) {
							g.setColor(new Color(0, 0, 0));
							g.fillRect(1024 - (width * 25) + (i * 20) + 4, 25 + (j * 20) - 10, 2, 10);
						}

					}
				}
			}
		}
	}

	/**
	 * Renders the room that the Player is currently in
	 */

	public void renderRoom(Graphics g) {
		Entity playerObject = handler.object.get(0);

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i) instanceof Player) {
				playerObject = handler.object.get(i);
			}
		}
		this.map.roomLoc[playerObject.getMapX()][playerObject.getMapY()].render(g);
	}

	public void checkEnemiesDefeated() {
		if (this.map.roomLoc[this.currentRoomX][this.currentRoomY].getEnemyListSize() == 0) {
			this.map.roomLoc[this.currentRoomX][this.currentRoomY].setUnresolvedEvent(false);
		}
	}

	public boolean canLeaveRoom() {
		if (this.map.roomLoc[this.currentRoomX][this.currentRoomY].getUnresolvedEvent()) {
			return false;
		}
		return true;
	}

	public void tick() {
		checkEnemiesDefeated();
	}

	/**
	 * Sets map.
	 * @param newMap Instance of map.
	 */
	public void setMap(Map newMap)
	{
		this.map = newMap;
	}

	/**
	 * Sets handler.
	 * @param handler Instance of handler.
	 */
	public void setHandler(Handler handler)
	{
		this.handler = handler;
	}
}
