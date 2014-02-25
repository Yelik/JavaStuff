public class Map {
	private final int width;
	private final int height;
	private int[] tiles;
	private int i = 0;

	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateMap(Main.random.nextInt());
	}

	public void generateMap(int xOffset) {
		for (int y = 0; y < height; y++) {
			for (int x = xOffset; x < width + xOffset; x++) {
				double a = 300 / 16 * 9 / 2, b = 1d / 100d, c = 0, d = a;
				if (y > a * Math.sin(b * (x + c)) + d || x == xOffset || x == xOffset + width - 1)
					setTile(x - xOffset, y, Tile.grass.id);
				else
					setTile(x - xOffset, y, Tile.empty.id);
			}
		}
	}

	public void render(Screen screen) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				getTile(x, y).render(screen, x, y);
			}
		}
	}

	private void setTile(int x, int y, int id) {
		tiles[x + y * width] = id;
	}

	private Tile getTile(int x, int y) {
		int id = getTileId(x, y);
		if (id == Tile.grass.id) {
			return Tile.grass;
		} else if (id == Tile.empty.id) {
			return Tile.empty;
		}
		return Tile.empty;
	}

	private int getTileId(int x, int y) {
		return tiles[x + y * width];
	}

	public void update() {
		i++;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				getTile(x, y).update(x, y);
			}
		}
	}
}
