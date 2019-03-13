import java.awt.Color;
import java.awt.Graphics;

public class LevelHandler {

	Handler handler;
	Map map;
	int width, height;

	// map is just defaulted to 7x7 right now for no particular reason
	public LevelHandler(Handler handler) {
		this.handler = handler;
		width = 7;
		height = 7;
		map = new Map(width, height);
	}

	public Map getMap() {
		return map;
	}

	public Handler getHandler()
	{
		return handler;
	}

	// renders a visual representation of the map in the top right corner
	public void renderMap(Graphics g) {
		Entity playerObject = handler.object.get(0);

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

	public void renderRoom(Graphics g) {
			this.map.roomLoc[handler.object.get(0).getMapX()][handler.object.get(0).getMapY()].render(g);
		}
	
}
