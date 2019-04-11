import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

/**
 * Represents any entity.
 *
 * @author Justin
 * @version Demo 2
 */
abstract class Entity {
	/**
	 * Represents the name of the entity.
	 */
	private String name = "";

	/**
	 * Represents the "attack" stat of the entity.
	 */
	private int damage = 1;

	/**
	 * Represents multiplier to damage.
	 */
	private double damageMult = 1.0;

	/**
	 * Time passed since last shot, up to value of shotCooldown.
	 */
	private int shotTimer = 0;

	/**
	 * Amount of time that must pass between shots.
	 */
	private int shotCooldown = 30;

	/**
	 * Represents the X coordinate of the entity.
	 */
	private int x = 0;

	/**
	 * Represents the X coordinate of the entity.
	 */
	private int y = 0;

	/**
	 * Represents velocity of movement in X axis.
	 */
	private float velocityX = 0;

	/**
	 * Represents velocity of movement in Y axis.
	 */
	private float velocityY = 0;

	/**
	 * Represents X coordinate of room entity is in.
	 */
	private int mapX = 0;

	/**
	 * Represents Y coordinate of room entity is in.
	 */
	private int mapY = 0;

	/**
	 * Entities cannot damage other entities on the same team, nor buff other
	 * entities on other teams
	 */
	private String team = "";

	/**
	 * The width of the entity. Used for determining collision
	 */
	private int width;

	/**
	 * The height of the entity. Used for determining collision
	 */
	private int height;

	/**
	 * The frame of the current animation
	 */
	private int animFrame;

	/**
	 * The time being counted in between frames of animation
	 */
	private int animTimer;

	/**
	 * The current animation of the Entity
	 */
	private String animState;
	
	protected Handler handler;

	Entity(int x, int y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;
	}

	/**
	 * Returns the name of the entity.
	 * 
	 * @return name of the entity.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the entity.
	 * 
	 * @param name name of the entity.
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the damage of the entity.
	 * 
	 * @return damage of the entity.
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Sets the damage of the entity.
	 * 
	 * @param damage damage of the entity.
	 */
	protected void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Returns the damage multiplier of the entity.
	 * 
	 * @return damage multiplier of the entity.
	 */
	public double getDamageMult() {
		return this.damageMult;
	}

	/**
	 * Sets the damage multiplier of the entity.
	 * 
	 * @param damageMult damage multiplier of the entity.
	 */
	void setDamageMult(double damageMult) {
		this.damageMult = damageMult;
	}

	/**
	 * Returns the time that must pass between shots.
	 * 
	 * @return Time that must pass between shots
	 */
	int getShotCooldown() {
		return shotCooldown;
	}

	/**
	 * Returns the time since the last shot.
	 * 
	 * @return Time that passed since the last shot.
	 */
	protected int getShotTimer() {
		return shotTimer;
	}

	/**
	 * Returns the time since the last shot.
	 * 
	 * @return Time that passed since the last shot.
	 */
	protected void setShotTimer(int shotTimer) {
		this.shotTimer = shotTimer;
	}

	/**
	 * Sets the time that must pass between shots.
	 * 
	 * @param shotCooldown The time that must pass between shots.
	 */
	protected void setShotCooldown(int shotCooldown) {
		this.shotCooldown = shotCooldown;
	}

	/**
	 * Returns the X-value of the entity's position.
	 * 
	 * @return X-value of entity's position.
	 */
	protected int getX() {
		return this.x;
	}

	/**
	 * Returns the Y-value of the entity's position.
	 * 
	 * @return Y-value of entity position.
	 */
	protected int getY() {
		return this.y;
	}

	/**
	 * Sets the X-value of the entity's position.
	 * 
	 * @param X X-value of entity's position.
	 */
	protected void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the Y-value of the entity's position.
	 * 
	 * @param Y Y-value of entity's position.
	 */
	protected void setY(int y) {
		this.y = y;
	}

	/**
	 * Sets the width of the entity
	 * 
	 * @param int width of the entity
	 */

	protected void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Sets the height of the entity
	 * 
	 * @param int height of the entity
	 */

	protected void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Sets X-value of velocity.
	 * 
	 * @param velocityX X-value of velocity.
	 */
	protected void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	/**
	 * Sets Y-value of velocity.
	 * 
	 * @param velocityY Y-value of velocity.
	 */
	protected void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

	/**
	 * Gets X-value of velocity.
	 * 
	 * @return X-value of velocity.
	 */
	protected float getVelocityX() {
		return velocityX;
	}

	/**
	 * Gets Y-value of velocity.
	 * 
	 * @return Y-value of velocity.
	 */
	protected float getVelocityY() {
		return velocityY;
	}

	/**
	 * Returns X position on the map.
	 * 
	 * @return X position on the map.
	 */
	public int getMapX() {
		return mapX;
	}

	/**
	 * Returns Y position on the map.
	 * 
	 * @return Y position on the map.
	 */
	public int getMapY() {
		return mapY;
	}

	/**
	 * Returns the width of the entity
	 * 
	 * @return int width of the entity
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the entity
	 * 
	 * @param int height of the entity
	 */

	public int getHeight() {
		return height;
	}

	/**
	 * Sets X position on map.
	 * 
	 * @param mapX X position on map.
	 */
	protected void setMapX(int mapX) {
		this.mapX = mapX;
	}

	/**
	 * Sets Y position on map.
	 * 
	 * @param mapY Y position on map.
	 */
	protected void setMapY(int mapY) {
		this.mapY = mapY;
	}

	/**
	 * Gets the team of the entity.
	 * 
	 * @return team of the entity.
	 */
	public String getTeam() {
		return this.team;
	}

	/**
	 * Sets the team of the entity.
	 * 
	 * @param team team of the entity.
	 */
	protected void setTeam(String team) {
		this.team = team;
	}

	/**
	 * Returns the current frame of animation of the Entity
	 *
	 * @return frame of the animation.
	 */
	public int getAnimFrame() {
		return animFrame;
	}

	/**
	 * Sets the current frame of animation of the Entity
	 * 
	 * @param animFrame frame of the animation
	 */
	public void setAnimFrame(int animFrame) {
		this.animFrame = animFrame;
	}
	
	/**
	 * Returns the current timer of the animation of the Entity
	 *
	 * @return animation timer of the entity
	 */
	public int getAnimTimer() {
		return animTimer;
	}

	/**
	 * Sets the current timer of the animation of the Entity
	 * 
	 * @param animTimer animation timer of the entity.
	 */
	public void setAnimTimer(int animTimer) {
		this.animTimer = animTimer;
	}
	
	/**
	 * Returns the current state of the animation of the Entity
	 *
	 * @return state of the animation.
	 */
	public String getAnimState() {
		return new String(this.animState);
	}

	/**
	 * Sets the current state of the animation of the Entity
	 * 
	 * @param animState state of the animation.
	 */
	public void setAnimState(String animState) {
		this.animState = new String(animState);
	}

	/**
	 * Checks interaction with entity and acts based on type.
	 * 
	 * @param initiator Entity that initiates interactions.
	 */
	protected abstract void checkInteraction(Entity initiator);

	/**
	 * Handles all AI or controls.
	 * 
	 * @param input Action to be performed.
	 */
	public abstract void logicInterface(String input);

	/**
	 * Represents the computation of the entity's movement.
	 */
	protected abstract void movementLogic();

	/**
	 * Represents the computation of the entity's attacks.
	 */
	protected abstract void attackLogic(int direction);

	/**
	 * Updates the delay on the shots.
	 */
	protected void updateShotDelay() {
		if (this.shotTimer < this.shotCooldown) {
			shotTimer++;
		}
	}

	/**
	 * Code for rendering the entity.
	 * 
	 * @param g Graphics object to be drawn to.
	 */
	abstract public void render(Graphics g);

	/**
	 * Handles updates.
	 */
	protected abstract void tick();

	Element Save(Document save)
	{
		Element e = null;

		Element rootElement = save.createElement(this.getClass().getName());

		e = save.createElement("X");
		e.appendChild(save.createTextNode(Integer.toString(getX())));
		rootElement.appendChild(e);

		e = save.createElement("Y");
		e.appendChild(save.createTextNode(Integer.toString(getY())));
		rootElement.appendChild(e);

		e = save.createElement("mapX");
		e.appendChild(save.createTextNode(Integer.toString(getMapX())));
		rootElement.appendChild(e);

		e = save.createElement("mapY");
		e.appendChild(save.createTextNode(Integer.toString(getMapY())));
		rootElement.appendChild(e);

		e = save.createElement("velocityX");
		e.appendChild(save.createTextNode(Float.toString(getVelocityX())));
		rootElement.appendChild(e);

		e = save.createElement("velocityY");
		e.appendChild(save.createTextNode(Float.toString(getVelocityY())));
		rootElement.appendChild(e);

		e = save.createElement("damage");
		e.appendChild(save.createTextNode(Integer.toString(getDamage())));
		rootElement.appendChild(e);

		e = save.createElement("damageMult");
		e.appendChild(save.createTextNode(Double.toString(getDamageMult())));
		rootElement.appendChild(e);

		e = save.createElement("name");
		e.appendChild(save.createTextNode(getName()));
		rootElement.appendChild(e);

		e = save.createElement("team");
		e.appendChild(save.createTextNode(getTeam()));
		rootElement.appendChild(e);

		return rootElement;
	}
}
