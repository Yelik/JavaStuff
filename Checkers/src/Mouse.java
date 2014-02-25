import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	private int x = -1;
	private int y = -1;
	private boolean left = false;
	private boolean right = false;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getLeftButton() {
		return left;
	}

	public boolean getRightButton() {
		return right;
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			left = true;
		}
		if (e.getButton() == 3) {
			right = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 1) {
			left = false;
		}
		if (e.getButton() == 3) {
			right = false;
		}
	}
}
