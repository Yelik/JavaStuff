import com.mrizen.gaming.Array2D;
import com.mrizen.gaming.KeyReader;
import com.mrizen.gaming.Point2D;
import com.mrizen.gaming.Screen;

public class Map {
	private Array2D tiles;
	private Point2D offset;
	private int lastMoveAnim;

	public Map(int width, int height) {
		tiles = new Array2D(width, height);
		offset = new Point2D(0, 0);
		generateDungeon(2, 2, 2);
	}

	private void generateDungeon(int cellsWidth, int cellsHeight, int roomChance) {
		Array2D rooms = new Array2D(cellsWidth, cellsHeight);
		for (int y = 0; y < rooms.getHeight(); y++) {
			for (int x = 0; x < rooms.getWidth(); x++) {
				if (Math.floor(Math.random() * roomChance) == 0) {
					rooms.set(x, y, 1);
				}
			}
		}

	}

	public void render(Screen screen) {
		screen.setOffset(offset.getX(), offset.getY());
		for (int yTile = 0; yTile < tiles.getHeight(); yTile++) {
			for (int xTile = 0; xTile < tiles.getWidth(); xTile++) {
				screen.drawSprite(getTile(xTile, yTile).getSprite(), xTile * Tile.SIZE, yTile * Tile.SIZE);
			}
		}
	}

	public void update(KeyReader keys, int anim) {
		if (anim - lastMoveAnim >= 10) {
			if (keys.getRight()) {
				offset.setX(offset.getX() - Tile.SIZE);
				lastMoveAnim = anim;
			}
			if (keys.getLeft()) {
				offset.setX(offset.getX() + Tile.SIZE);
				lastMoveAnim = anim;
			}
			if (keys.getUp()) {
				offset.setY(offset.getY() + Tile.SIZE);
				lastMoveAnim = anim;
			}
			if (keys.getDown()) {
				offset.setY(offset.getY() - Tile.SIZE);
				lastMoveAnim = anim;
			}
		}
	}

	protected void scrollTo(int offsetX, int offsetY, int anim) {
		offset.setX(offsetX);
		offset.setY(offsetY);
		lastMoveAnim = anim;
	}

	private Tile getTileById(int id) {
		if (id == Tile.WALL.getId()) {
			return Tile.WALL;
		} else if (id == Tile.FLOOR.getId())
			return Tile.FLOOR;
		return Tile.EMPTY;
	}

	private Tile getTile(int x, int y) {
		return getTileById(tiles.get(x, y));
	}

	public Array2D getTiles() {
		return tiles;
	}
}
