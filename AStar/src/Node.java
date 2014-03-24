import java.awt.Point;

public class Node {
	private Point pos;
	private Point from;
	private int count;

	public Node(Point pos, Point from, int count) {
		this.pos = pos;
		this.from = from;
		this.count = count;
	}

	public Node(int x, int y, Point from, int count) {
		this.pos = new Point(x, y);
		this.from = from;
		this.count = count;
	}

	public int getPosX() {
		return pos.x;
	}

	public int getPosY() {
		return pos.y;
	}

	public int getFromX() {
		return from.x;
	}

	public int getFromY() {
		return from.y;
	}

	public int getCount() {
		return count;
	}

	public Point getFrom() {
		return from;
	}

	public Point getPos() {
		return pos;
	}

	public boolean equals(Node node) {
		if (getPosX() == node.getPosX() && getPosY() == node.getPosY())
			return true;
		return false;
	}
}
