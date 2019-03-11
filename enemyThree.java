import javafx.geometry.Point2D ;

public class enemyThree extends Enemy {

  enemyThree() {
    setHealthMax(100) ;
    setHealth(100) ;
    setArmor(2) ;
    setDamage(3) ;
    setDamageMult(1) ;
    setName("Enemy Three") ;
    setTeam("enemy") ;
  }

  /**
  * Attacking
  *@param direction Direction of attack
  */
  @Override
  protected void AttackLogic(int direction) {
    //attack
  }
  /**
  * Movement of the enemy.
  */
  @Override
  void MovementLogic() {
    setLocation(new Point2D(getLocation().getX() + getVelocityX(), getLocation().getY() + getVelocityY())) ;
  }

  /**
  * Health based events.
  */
  @Override
  void HealthThresholdEvents() {
    if(health <= 0) {
      //enemyThree Dies
    }
  }

  /**
  * Interactions with other entities.
  * @param initiator Entity that initiates interactions.
  */
  @Override
  protected void checkInteraction(Entity initiator) {
    if(initiator instanceof player) {
      ModifyHealth(initiator.getDamage()) ;
    }
  }
}
