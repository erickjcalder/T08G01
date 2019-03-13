import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;

abstract class Pickups extends Entity {
  LevelHandler levelhandler;
  Map map;
  ActiveEntity ae;

  public Pickups(int x, int y, LevelHandler levelhandler) {
    /**
     * As the pickup always spawns in the middle of the room, the location will always
     * be the same.
     */
    super(x, y);

    this.levelhandler = levelhandler;
    this.map = levelhandler.getMap();

    this.id = "pickup";

    this.x = 500;
    this.y = 500;

    this.mapX = 2;
    this.mapY = 1;

    this.velX = 0;
    this.velY = 0;

  }
  //this class deals with the randon powerups that spawn on every new level at random times.

  public void doubleDamage(){
    /**
     * efectively increases damage by 2 times
     */
    super.setDamageMult(2.0);
  }

  public void healthRegen(){
    /**
     * restores health to full
     */
    ae.setHealth(ae.getHealthMax());
  }

  public void bonusAtackSpeed(){
    /**
     * increases attack speed (by reducing shot Cooldown by half).
     */
    super.setShotCooldown(15);
  }


  public void armorUp(){
    /**
     * increases armor by one everytime a rune is activated.
     */
    ae.setArmor(ae.getArmor()+1);
  }

  public void randomPickup(){
    /**
     * this method deals with calling of methods, as the pickup spawning will be random this method gives equal
     * equal change to all the pickups.
     */
    double rand = (int)(Math.random()*4);
    if (rand == 1);{
      //calls doubleDamage method.
        doubleDamage();
    }

    if (rand == 2);{
      healthRegen();
    }
    if (rand == 3);{
      armorUp();
    }
  }

  public void render(Graphics g) {
    g.setColor(new Color(300, 300, 300));
    g.fillOval(x, y, 50, 50);
  }

  
}
