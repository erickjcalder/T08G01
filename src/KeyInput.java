import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Monitors key inputs and handles them accordingly
 *
 * @author Parker
 * @version Demo 2
 */

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

	private Game game;

	/**
	 * Creates a KeyInput object. KeyInputs objects have 8 booleans that correspond
	 * to whether the W, A, S, D, Up, Left, Down, Right are currently pressed
	 * 
	 * @param handler instance of handler that is used to change velocity of Player
	 */

	public KeyInput(Handler handler, Game game) {
		this.handler = handler;

		wPress = false;
		aPress = false;
		sPress = false;
		dPress = false;

		upPress = false;
		downPress = false;
		leftPress = false;
		rightPress = false;

		this.game = game;
	}

	/**
	 * Decides what to do based on what keys are pressed. Will add velocity if a
	 * WASD key is pressed, take away velocity if a WASD key is not pressed and will
	 * create a Projectile object if an arrow key is pressed
	 */
	public void tick() {

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i) instanceof Player) {
				Player player = (Player) handler.object.get(i);

				// Acceleration and deceleration for W key
				if (this.wPress && !this.sPress && player.getVelocityY() > -7) {
					player.setVelocityY(player.getVelocityY() - 2);
					player.setAnimState("walking back");
				} else if (player.getVelocityY() < 0) {
					player.setVelocityY(player.getVelocityY() + 1);
				}

				// Acceleration and deceleration for A key
				if (this.aPress && !this.dPress && player.getVelocityX() > -7) {
					player.setVelocityX(player.getVelocityX() - 2);
					if (!this.wPress && !this.sPress) {
						player.setAnimState("walking left");
					}
				} else if (player.getVelocityX() < 0) {
					player.setVelocityX(player.getVelocityX() + 1);
				}

				// Acceleration and deceleration for S key
				if (this.sPress && !this.wPress && player.getVelocityY() < 7) {
					player.setVelocityY(player.getVelocityY() + 2);
					player.setAnimState("walking front");
				} else if (player.getVelocityY() > 0) {
					player.setVelocityY(player.getVelocityY() - 1);
				}

				// Acceleration and deceleration for D key
				if (this.dPress && !this.aPress && player.getVelocityX() < 7) {
					player.setVelocityX(player.getVelocityX() + 2);
					if (!this.wPress && !this.sPress) {
						player.setAnimState("walking right");
					}
				} else if (player.getVelocityX() > 0) {
					player.setVelocityX(player.getVelocityX() - 1);
				}

				// Slows to 0 Y velocity if opposite key directions pressed
				if (this.wPress && this.sPress) {
					if (player.getVelocityY() > 0) {
						player.setVelocityY(player.getVelocityY() - 1);
					} else if (player.getVelocityY() < 0) {
						player.setVelocityY(player.getVelocityY() + 1);
					}
				}

				// Slows to 0 X velocity if opposite key directions pressed
				if (this.aPress && this.dPress) {
					if (player.getVelocityX() > 0) {
						player.setVelocityX(player.getVelocityX() - 1);
					} else if (player.getVelocityX() < 0) {
						player.setVelocityX(player.getVelocityX() + 1);
					}
				}

				// Arrow keys to shoot

				if (this.upPress && !this.downPress && !this.leftPress && !this.rightPress) {
					player.logicInterface("shootup");
					player.setAnimState("throw back");
				}

				if (this.downPress && !this.upPress && !this.leftPress && !this.rightPress) {
					player.logicInterface("shootdown");
					player.setAnimState("throw front");
				}

				if (this.leftPress && !this.downPress && !this.upPress && !this.rightPress) {
					player.logicInterface("shootleft");
					player.setAnimState("throw left");
				}

				if (this.rightPress && !this.downPress && !this.leftPress && !this.upPress) {
					player.logicInterface("shootright");
					player.setAnimState("throw right");
				}

			}
		}
	}

	/**
	 * Checks to see what keys are pressed and changes their corresponding boolean
	 * to true if they are
	 * 
	 * @param KeyEvent used to get the code of the pressed keys
	 */
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

		if (key == KeyEvent.VK_ESCAPE) {
			if (game.getGameState().equals("game")) {
				game.setGameState("menu");
			} else {
				game.setGameState("game");
			}
		}

	}

	/**
	 * Checks to see if a key has been released changes the corresponding boolean to
	 * false if it has been
	 * 
	 * @param KeyEvent used to get the code of the released keys
	 */
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
