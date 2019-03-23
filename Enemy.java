import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public abstract class Enemy extends ActiveEntity {

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

  Enemy(int X, int Y, LevelHandler levelHandler) {
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

    animState = ("walking front") ;
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
  protected void attackLogic(int direction) {
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
  protected void movementLogic() {
    if (getY() + getHeight() + getVelocityY() > 640 && !this.map.roomCheck(getMapX(), getMapY() + 1)) {
			setY(640 - getHeight());
			setVelocityY(0);

		} else if (getY() + getHeight() + getVelocityY() > 640 && this.map.roomCheck(getMapX(), getMapY() + 1)
				&& (getX() > 515 || getX() < 452)) {
			setY(640 - getHeight());
			setVelocityY(0);
		}

		if (getY() + getVelocityY() < 55 && !this.map.roomCheck(getMapX(), getMapY() - 1)) {
			setY(55);
			setVelocityY(0);

		} else if (getY() + getVelocityY() < 55 && this.map.roomCheck(getMapX(), getMapY() - 1)
				&& (getX() > 515 || getX() < 452)) {

			setY(55);
			setVelocityY(0);

		}

		if (getX() + getWidth() + getVelocityX() > 936 && !this.map.roomCheck(getMapX() + 1, getMapY())) {
			setX(936 - getWidth());
			setVelocityX(0);

		} else if (getX() + getWidth() + getVelocityX() > 936 && this.map.roomCheck(getMapX() + 1, getMapY())
				&& (getY() > 335 || getY() < 265)) {
			setX(936 - getWidth());
			setVelocityX(0);
		}

		if (getX() + getVelocityX() < 90 && !this.map.roomCheck(getMapX() - 1, getMapY())) {
			setX(90);
			setVelocityX(0);

		} else if (getX() + getVelocityX() < 90 && this.map.roomCheck(getMapX() - 1, getMapY())
				&& (getY() > 335 || getY() < 265)) {
			setX(90);
			setVelocityX(0);
		}

    setX(getX() + (int) getVelocityX());
    setY(getY() + (int) getVelocityY());
  }

  @Override
  protected void healthThresholdEvents() {
    if(getHealth() <= 0) {
      //Enemy dies and disappears
    }
  }

  @Override
  protected void checkInteraction(Entity initiator) {
    if(initiator instanceof Projectile) {
      modifyHealth(initiator.getDamage()) ;
    }
  }

  public void animationHandler() {
    setAnimTimer(getAnimTimer() + 1) ;
  }

  //@Override
  public void Render(Graphics g) {
    switch(getAnimState()) {
      //cases
    }
  }
}
