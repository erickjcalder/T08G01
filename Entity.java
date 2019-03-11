import java.awt.Graphics;

public abstract class Entity {
	
	//This class should be extended by all or most entities in the game at the moment
	//Later on this class could be extended and used to create another abstract class

	protected int x, y;
	protected int velX, velY;
	protected int mapX, mapY;
	protected int health;
	protected String id;

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//Each object must have a tick method to take care of movement, speed changes, position, collision or any
	//other kinds of things that need to be updated on a constant basis
	public abstract void tick();

	//Each object must have a render method that draws the object to the screen
	public abstract void render(Graphics g);

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return this.velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return this.velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void setID(String id) {
		this.id = id;
	}

}
