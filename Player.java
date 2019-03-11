import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity {

	LevelHandler levelhandler;
	Map map;
	int shotDelay;
	int shotTimer;

	public Player(int x, int y, LevelHandler levelhandler) {
		super(x, y);

		this.levelhandler = levelhandler;
		this.map = levelhandler.getMap();

		this.id = "player";

		this.x = 500;
		this.y = 350;

		this.mapX = 2;
		this.mapY = 1;

		this.velX = 0;
		this.velY = 0;

		this.shotTimer = 0;
		this.shotDelay = 30;
	}

	// Very crude collision detection to make sure player stays inside walls
	public void tick() {

		if (y + velY > 599 && !map.roomCheck(mapX, mapY + 1)) {
			y = 599;
			velY = 0;
		} else if (y + velY > 599 && map.roomCheck(mapX, mapY + 1) && (x > 557 || x < 467)) {
			y = 599;
			velY = 0;
		}

		if (y + velY < 100 && !map.roomCheck(mapX, mapY - 1)) {
			y = 100;
			velY = 0;
		} else if (y + velY < 100 && map.roomCheck(mapX, mapY - 1) && (x > 557 || x < 467)) {
			y = 100;
			velY = 0;
		}

		if (x + velX > 885 && !map.roomCheck(mapX + 1, mapY)) {
			x = 885;
			velX = 0;
		} else if (x + velX > 885 && map.roomCheck(mapX + 1, mapY) && (y > 375 || y < 325)) {
			x = 885;
			velX = 0;
		}

		if (x + velX < 100 && !map.roomCheck(mapX - 1, mapY)) {
			x = 100;
			velX = 0;
		} else if (x + velX < 100 && map.roomCheck(mapX - 1, mapY) && (y > 375 || y < 325)) {
			x = 100;
			velX = 0;
		}

		x += velX;
		y += velY;

		if (x > 940 && map.roomCheck(mapX + 1, mapY)) {
			x = 40;
			mapX++;
		}

		if (x < 40 && map.roomCheck(mapX - 1, mapY)) {
			x = 940;
			mapX--;
		}

		if (y < 40 && map.roomCheck(mapX, mapY - 1)) {
			y = 600;
			mapY--;
		}

		if (y > 640 && map.roomCheck(mapX, mapY + 1)) {
			y = 40;
			mapY++;
		}
		
		shotTimer++;
	}

	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 255));
		g.fillRect(x, y, 40, 40);
	}

	public boolean checkDelay() {
		if (shotTimer < shotDelay) {
			shotTimer++;
			return false;
		}
		
		shotTimer = 0;
		return true;

	}

}
