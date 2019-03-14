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

    this.setName("Pickup");
    this.setTeam("");

    this.setX(500);
    this.setY(500);

    this.setMapX(2);
    this.setMapY(1);

    this.setVelocityX(0);
    this.setVelocityY(0);

  }
  //this class deals with the random powerups that spawn on every new level at random times.

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
    if (rand == 0);{
      bonusAtackSpeed();
    }
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


  public void LogicInterface(String inp){
    /**
     * this method is never invoked as there are no buttons pressed for pickups
     */
  }


  public void MovementLogic(){
    /**
     * Pickups are not mobile.
     */

  }


  public void AttackLogic(){
    /**
     * Pickups dont have any attack mechanic.
     */

  }
  /**
   * handles updates
   */
  public void tick(){
    randomPickup();

  }
  public void render(Graphics g) {
    g.setColor(new Color(300, 300, 300));
    g.fillOval(this.getX(), this.getY(), 50, 50);
  }

  
}
