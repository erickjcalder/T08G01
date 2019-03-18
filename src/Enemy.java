import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

class enemyOne extends ActiveEntity {

  private Map map ;
  private Handler handler ;
  private int animFrame ;
  private int animTimer ;
  public String animState ;
  private Image left[] = new Image[5] ;
  private Image right[] = new Image[5] ;
  private Image back[] = new Image[5] ;
  private Image front[] = new Image[5] ;
  private LevelHandler levelHandler ;

  enemyOne(int X, int Y, LevelHandler levelHandler) {
    super(X,Y) ;
    this.map = levelHandler.getMap() ;
    this.handler = levelHandler.getHandler() ;
    this.levelHandler = levelHandler ;
    setMapX(2) ;
    setMapY(1) ;
    setHealthMax(100) ;
    setHealth(100) ;
    setArmor(0) ;
    setDamage(1) ;
    setDamageMult(1) ;
    setName("Enemy") ;
    setTeam("enemy") ;

    setWidth(40) ;
    setHeight(40) ;

  //  animState = ("walking front") ;
/**
    left[0] = Toolkit.getDefaultToolkit().getImage("");
    left[1] = Toolkit.getDefaultToolkit().getImage("");
    left[2] = Toolkit.getDefaultToolkit().getImage("");
    left[3] = Toolkit.getDefaultToolkit().getImage("");
    left[4] = Toolkit.getDefaultToolkit().getImage("");

    right[0] = Toolkit.getDefaultToolkit().getImage("");
    right[1] = Toolkit.getDefaultToolkit().getImage("");
    right[2] = Toolkit.getDefaultToolkit().getImage("");
    right[3] = Toolkit.getDefaultToolkit().getImage("");
    right[4] = Toolkit.getDefaultToolkit().getImage("");

    back[0] = Toolkit.getDefaultToolkit().getImage("");
    back[1] = Toolkit.getDefaultToolkit().getImage("");
    back[2] = Toolkit.getDefaultToolkit().getImage("");
    back[3] = Toolkit.getDefaultToolkit().getImage("");
    back[4] = Toolkit.getDefaultToolkit().getImage("");
*/
    front[0] = Toolkit.getDefaultToolkit().getImage("resources/Raid Front.png");
  //  front[1] = Toolkit.getDefaultToolkit().getImage("");
  //  front[2] = Toolkit.getDefaultToolkit().getImage("");
  //  front[3] = Toolkit.getDefaultToolkit().getImage("");
  //  front[4] = Toolkit.getDefaultToolkit().getImage("");
  }

  @Override
  protected void AttackLogic(int direction) {
    switch(direction) {
      case 270 :
        handler.addObject(new Projectile(getX() + 10, getY() -25, 0, -10)) ;
        break ;

      case 90:
        handler.addObject(new Projectile(getX() + 10, getY() + 45, 0, 10));
        break;

      case 180:
        handler.addObject(new Projectile(getX() - 25, getY() + 10, -10, 0));
        break;

      case 0:
        handler.addObject(new Projectile(getX() + 45, getY() + 10, 10, 0));
        break;
    }
  }

  @Override
  protected void MovementLogic() {
    //enemy movement
  }

  @Override
  protected void HealthThresholdEvents() {
    if(getHealth() <= 0) {
      //Enemy dies and disappears
    }
  }

  @Override
  protected void checkInteraction(Entity initiator) {
    if(initiator instanceof Projectile) {
      ModifyHealth(initiator.getDamage()) ;
    }
  }

  public void animationHandler() {
    setAnimTimer(getAnimTimer() + 1) ;
  }

  @Override
  public void Render(Graphics g) {
    switch(getAnimState()) {
      //cases 
    }
  }
}
