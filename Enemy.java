public abstract class Enemy extends ActiveEntity {

  Enemy(int baseDamage, int baseHealth) {
    healthMax = baseHealth ;
    health = healthMax ;
    damage = baseDamage ;
    name = "Enemy" ;
    team = "enemy" ;
  }

  @Override
  void AttackLogic(int direction) {
    //Enemy Attacks
  }

  @Override
  void HealthThresholdEvents() {
    if(health <= 0) {
      //Enemy Dies
    }
  }

  @Override
  void checkInteraction(Entity initiator) {
    //Action
  }

}
