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
			if (handler.object.get(i) instanceof Player) {
				Player player = (Player) handler.object.get(i);

				// Acceleration and deceleration for W key
				if (this.wPress && !this.sPress) {
					player.LogicInterface("up");
				}

				// Acceleration and deceleration for A key
				if (this.aPress && !this.dPress) {
					player.LogicInterface("left");
				}

				// Acceleration and deceleration for S key
				if (this.sPress && !this.wPress) {
					player.LogicInterface("down");
				}

				// Acceleration and deceleration for D key
				if (this.dPress && !this.aPress) {
					player.LogicInterface("right");
				}

				// Slows to 0 Y velocity if opposite key directions pressed
				if (this.wPress && this.sPress) {
					player.LogicInterface("updown");
				}

				// Slows to 0 X velocity if opposite key directions pressed
				if (this.aPress && this.dPress) {
					player.LogicInterface("leftright");
				}

				// Arrow keys to shoot

				if (this.upPress && !this.downPress && !this.leftPress && !this.rightPress) {
					player.LogicInterface("shootup");
				}

				if (this.downPress && !this.upPress && !this.leftPress && !this.rightPress) {
					player.LogicInterface("shootdown");
				}

				if (this.leftPress && !this.downPress && !this.upPress && !this.rightPress) {
					player.LogicInterface("shootleft");
				}

				if (this.rightPress && !this.downPress && !this.leftPress && !this.upPress) {
					player.LogicInterface("shootright");
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
