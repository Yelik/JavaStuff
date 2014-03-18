import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import com.mrizen.gaming.KeyReader;
import com.mrizen.gaming.MouseReader;
import com.mrizen.gaming.Screen;

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
				if (getPathId(x, y) == 0)
					getTile(x, y).render(screen, x, y);
				else
					screen.drawSprite(x * Sprites.GRAY.getSize(), y * Sprites.GRAY.getSize(), Sprites.GRAY);
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
		ArrayList<Node> opens = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		opens.add(new Node(end, null, 0));
		while (!closed.contains(new Node(start, null, 0)) && opens.size() > 0) {
			Node n = opens.get(0);
			for (int y = -1; y < 2; y++) {
				int yp = y + n.getPosY();
				for (int x = -1; x < 2; x++) {
					int xp = x + n.getPosX();
					
				}
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
		if (repath) {
			pathfind();
		}
	}

	private void setTile(int x, int y, int id) {
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
