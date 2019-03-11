import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;

	private boolean wPress;
	private boolean aPress;
	private boolean sPress;
	private boolean dPress;

	private boolean upPress;
	private boolean downPress;
	private boolean leftPress;
	private boolean rightPress;

	public KeyInput(Handler handler) {
		this.handler = handler;

		wPress = false;
		aPress = false;
		sPress = false;
		dPress = false;

		upPress = false;
		downPress = false;
		leftPress = false;
		rightPress = false;
	}

	public void tick() {

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID().equals("player")) {
				Player player = (Player) handler.object.get(i);

				// Acceleration and deceleration for W key
				if (this.wPress && !this.sPress && player.getVelY() > -7) {
					player.setVelY(player.getVelY() - 2);
				} else if (player.getVelY() < 0) {
					player.setVelY(player.getVelY() + 1);
				}

				// Acceleration and deceleration for A key
				if (this.aPress && !this.dPress && player.getVelX() > -7) {
					player.setVelX(player.getVelX() - 2);
				} else if (player.getVelX() < 0) {
					player.setVelX(player.getVelX() + 1);
				}

				// Acceleration and deceleration for S key
				if (this.sPress && !this.wPress && player.getVelY() < 7) {
					player.setVelY(player.getVelY() + 2);
				} else if (player.getVelY() > 0) {
					player.setVelY(player.getVelY() - 1);
				}

				// Acceleration and deceleration for D key
				if (this.dPress && !this.aPress && player.getVelX() < 7) {
					player.setVelX(player.getVelX() + 2);
				} else if (player.getVelX() > 0) {
					player.setVelX(player.getVelX() - 1);
				}

				// Slows to 0 Y velocity if opposite key directions pressed
				if (this.wPress && this.sPress) {
					if (player.getVelY() > 0) {
						player.setVelY(player.getVelY() - 1);
					} else if (player.getVelY() < 0) {
						player.setVelY(player.getVelY() + 1);
					}
				}

				// Slows to 0 X velocity if opposite key directions pressed
				if (this.aPress && this.dPress) {
					if (player.getVelX() > 0) {
						player.setVelX(player.getVelX() - 1);
					} else if (player.getVelX() < 0) {
						player.setVelX(player.getVelX() + 1);
					}
				}

				// Arrow keys to shoot

				if (this.upPress && player.checkDelay() && !this.downPress && !this.leftPress && !this.rightPress) {
					handler.addObject(new Projectile(player.getX(), player.getY(), 0, -10));
				}

				if (this.downPress && player.checkDelay() && !this.upPress && !this.leftPress && !this.rightPress) {
					handler.addObject(new Projectile(player.getX(), player.getY(), 0, 10));
				}

				if (this.leftPress && player.checkDelay() && !this.downPress && !this.upPress && !this.rightPress) {
					handler.addObject(new Projectile(player.getX(), player.getY(), -10, 0));
				}

				if (this.rightPress && player.checkDelay() && !this.downPress && !this.leftPress && !this.upPress) {
					handler.addObject(new Projectile(player.getX(), player.getY(), 10, 0));
				}

			}
		}
	}

	// Checks to see what keys are pressed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// wasd keys

		if (key == KeyEvent.VK_W) {
			this.wPress = true;
		}

		if (key == KeyEvent.VK_A) {
			this.aPress = true;
		}

		if (key == KeyEvent.VK_S) {
			this.sPress = true;
		}

		if (key == KeyEvent.VK_D) {
			this.dPress = true;
		}

		// arrow keys

		if (key == KeyEvent.VK_UP) {
			this.upPress = true;
		}

		if (key == KeyEvent.VK_DOWN) {
			this.downPress = true;
		}

		if (key == KeyEvent.VK_LEFT) {
			this.leftPress = true;
		}

		if (key == KeyEvent.VK_RIGHT) {
			this.rightPress = true;
		}

	}

	// checks to see if a key is released
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		// wasd keys

		if (key == KeyEvent.VK_W) {
			this.wPress = false;
		}

		if (key == KeyEvent.VK_A) {
			this.aPress = false;
		}

		if (key == KeyEvent.VK_S) {
			this.sPress = false;
		}

		if (key == KeyEvent.VK_D) {
			this.dPress = false;
		}

		// arrow keys

		if (key == KeyEvent.VK_UP) {
			this.upPress = false;
		}

		if (key == KeyEvent.VK_DOWN) {
			this.downPress = false;
		}

		if (key == KeyEvent.VK_LEFT) {
			this.leftPress = false;
		}

		if (key == KeyEvent.VK_RIGHT) {
			this.rightPress = false;
		}

	}
}
