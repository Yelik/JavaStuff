public class Map {
	private int width;
	private int[] ids;

	public Map(int width, int height) {
		this.width = width;
		ids = new int[width * height];
	}

	public void generateMap() {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				if ((y + x) % 2 == 0) {
					ids[y * getWidth() + x] = Tile.grass.id;
				}
			}
		}
	}

	public void render(Screen screen, int xOffset, int yOffset) {
		for (int y = 0; y < getHeight(); y++) {
			int yp = yOffset + y;
			for (int x = 0; x < getWidth(); x++) {
				int xp = xOffset + x, ip = yp * getWidth() + xp;
				if (ip < 0 || ip >= ids.length)
					continue;
				getTile(x, y).render(screen, xp, yp);
			}
		}
	}

	private Tile getTile(int x, int y) {
		int id = getTileId(x, y);
		if (id == Tile.grass.id) {
			return Tile.grass;
		} else
			return Tile.empty;
	}

	private int getTileId(int x, int y) {
		return ids[y * getWidth() + x];
	}

	private int getWidth() {
		return width;
	}

	private int getHeight() {
		return ids.length / width;
	}

	public void update() {

	}
}