import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import com.mrizen.gaming.KeyReader;
import com.mrizen.gaming.MouseReader;
import com.mrizen.gaming.Screen;
import com.mrizen.gaming.Sprite;

public class Map {
	private int width;
	private int[] tiles;
	private Point start;
	private Point end;
	private int[] paths;

	public Map(int width, int height) {
		this.width = width;
		tiles = new int[width * height];
		paths = new int[tiles.length];
		start = new Point();
		end = new Point();
		setStart(-1, -1);
		setEnd(-1, -1);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return tiles.length / getWidth();
	}

	public void render(Screen screen) {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				int path = getPathId(x, y);
				if (path == 0)
					getTile(x, y).render(screen, x, y);
				else {
					if (path > 64) {
						path -= 64;
					}
					Sprite sprite = Sprites.PATHS[Sprites.PATHS.length - path];
					screen.drawSprite(x * sprite.getSize(), y * sprite.getSize(), sprite);
				}

			}
		}
		if (hasStart())
			screen.drawSprite(start.x * Sprites.GREEN.getSize(), start.y * Sprites.GREEN.getSize(), Sprites.GREEN);
		if (hasEnd())
			screen.drawSprite(end.x * Sprites.RED.getSize(), end.y * Sprites.RED.getSize(), Sprites.RED);
	}

	private int getPathId(int x, int y) {
		int i = y * getWidth() + x;
		if (i < 0 || i >= paths.length)
			return -1;
		return paths[y * getWidth() + x];
	}

	private void pathfind() {
		paths = new int[tiles.length];
		ArrayList<Node> opens = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		opens.add(new Node(end, null, 0));
		while (!(contains(closed, new Node(start, null, 0))) && opens.size() > 0) {
			sortNodeList(opens);
			Node n = opens.get(0);
			for (int y = -1; y < 2; y++) {
				Node n2 = new Node(n.getPosX(), y + n.getPosY(), n.getPos(), n.getCount() + 1);
				if (n2.getPosX() >= 0 && n2.getPosX() < getWidth() && n2.getPosY() >= 0 && n2.getPosY() < getHeight() && !contains(opens, n2) && !contains(closed, n2)
						&& !getTile(n2.getPosX(), n2.getPosY()).isSolid())
					opens.add(n2);
			}
			for (int x = -1; x < 2; x++) {
				Node n2 = new Node(x + n.getPosX(), n.getPosY(), n.getPos(), n.getCount() + 1);
				if (n2.getPosX() >= 0 && n2.getPosX() < getWidth() && n2.getPosY() >= 0 && n2.getPosY() < getHeight() && !contains(opens, n2) && !contains(closed, n2)
						&& !getTile(n2.getPosX(), n2.getPosY()).isSolid())
					opens.add(n2);
			}
			closed.add(n);
			opens.remove(0);
		}
		Node[] nodes = new Node[getWidth() * getHeight()];
		for (int i = 0; i < closed.size(); i++) {
			Node n = closed.get(i);
			int i2 = n.getPosY() * getWidth() + n.getPosX();
			if (i2 >= 0 && i2 < nodes.length)
				nodes[i2] = n;
		}
		Node n = nodes[start.y * getWidth() + start.x];
		if (n != null)
			while (n.getFrom() != null) {
				int i = n.getPosY() * getWidth() + n.getPosX(), i2 = n.getFromY() * getWidth() + n.getFromX();
				if (i < 0 || i >= tiles.length || i2 < 0 || i2 >= tiles.length) {
					break;
				}
				paths[i] = n.getCount();
				n = nodes[n.getFromY() * getWidth() + n.getFromX()];
			}
	}

	private boolean contains(ArrayList<Node> nodes, Node node) {
		for (int i = 0; i < nodes.size(); i++) {
			if (node.equals(nodes.get(i)))
				return true;
		}
		return false;
	}

	private void sortNodeList(ArrayList<Node> nodes) {
		ArrayList<Node> temp = (ArrayList<Node>) nodes.clone();
		nodes.clear();
		nodes.add(temp.get(0));
		boolean end = false;
		for (int i = 1; i < temp.size(); i++) {
			for (int i2 = 0; i2 < nodes.size(); i2++) {
				if (temp.get(i).getCount() < nodes.get(i2).getCount()) {
					nodes.add(i2, temp.get(i));
					end = false;
					break;
				}
				end = true;
			}
			if (end) {
				nodes.add(temp.get(i));
			}
		}
	}

	private boolean hasEnd() {
		return end.x != -1 && end.y != -1;
	}

	private boolean hasStart() {
		return start.x != -1 && start.y != -1;
	}

	private Tile getTile(int x, int y) {
		int id = getTileId(x, y);
		if (id == Tile.EMPTY.getId())
			return Tile.EMPTY;
		if (id == Tile.WALL.getId())
			return Tile.WALL;
		return Tile.WALL;
	}

	private int getTileId(int x, int y) {
		int i = y * width + x;
		if (i < 0 || i >= tiles.length)
			return Tile.EMPTY.getId();
		return tiles[y * getWidth() + x];
	}

	public void update(MouseReader mouse, KeyReader keys) {
		Point tile = new Point();
		tile.setLocation(Math.floor(mouse.getScaledX() / Tile.EMPTY.getSprite().getSize()), Math.floor(mouse.getScaledY() / Tile.EMPTY.getSprite().getSize()));
		boolean leftMouse = mouse.getButton() == MouseEvent.BUTTON1;
		boolean rightMouse = mouse.getButton() == MouseEvent.BUTTON3;
		boolean space = keys.getSpace();
		boolean repath = false;
		if (leftMouse) {
			setTile(tile.x, tile.y, Tile.WALL.getId());
			repath = true;
		} else if (rightMouse) {
			setTile(tile.x, tile.y, Tile.EMPTY.getId());
			repath = true;
		}
		if (space) {
			setStart(tile.x, tile.y);
			repath = true;
		}
		if (end.x != tile.x || end.y != tile.y) {
			setEnd(tile.x, tile.y);
			repath = true;
		}
		if (repath && hasStart() && hasEnd() && start.x != end.x && start.y != end.y) {
			pathfind();
		}
	}

	private void setTile(int x, int y, int id) {
		int i = y * getWidth() + x;
		if (i >= 0 && i < tiles.length)
			tiles[y * getWidth() + x] = id;
	}

	private void setEnd(int x, int y) {
		end.x = x;
		end.y = y;
	}

	private void setStart(int x, int y) {
		start.x = x;
		start.y = y;
	}

	public void generate(int seed) {
		Random rand = new Random(seed);
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = rand.nextInt(2);
		}
		setStart(-1, -1);
		setEnd(-1, -1);
	}
}
