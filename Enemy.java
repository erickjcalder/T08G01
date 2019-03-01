public abstract class Enemy extends ActiveEntity {

  Enemy(int baseDamage, int baseHealth) {
    healthMax = baseHealth ;
    health = healthMax ;
    damage = baseDamage ;
    name = "Enemy" ;
    team = "enemy" ;
  }

  /**
  * This method handles enemy attacks.
  * @param direction Direction of the attack.
  */
  @Override
  void AttackLogic(int direction) {
    //Enemy Attacks
  }

  /**
  * This method handles the movement of the enemies.
  */
  @Override
  void MovementLogic() {
    //Enemy Moves
  }

  /**
  * This method handles events based on the health of the enemy.
  */
  @Override
  void HealthThresholdEvents() {
    if(health <= 0) {
      //Enemy Dies
    }
  }

  /**
  * This method handles interactions with other entities.
  * @param initiator Entity that initiates interactions.
  */
  @Override
  void checkInteraction(Entity initiator) {
    //Action against player. 
  }
}
