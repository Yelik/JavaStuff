

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyReader implements KeyListener {
	private boolean[] keys;

	public KeyReader() {
		keys = new boolean[120];
	}

	public boolean getUp() {
		return keys[KeyEvent.VK_UP];
	}

	public boolean getDown() {
		return keys[KeyEvent.VK_DOWN];
	}

	public boolean getLeft() {
		return keys[KeyEvent.VK_LEFT];
	}

	public boolean getRight() {
		return keys[KeyEvent.VK_RIGHT];
	}

	public boolean getSpace() {
		return keys[KeyEvent.VK_SPACE];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
