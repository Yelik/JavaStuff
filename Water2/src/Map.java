public class Map {

	private final Tile[] tiles;
	private final int width;

	public Map(int width, int height) {
		this.width = width;
		tiles = new Tile[width * height];
	}

	public void generateMap(int seed) {
		for (int y = 0; y < tiles.length / width; y++) {
			for (int x = 0; x < width; x++) {
				if (x == 0 || x == getWidth() - 1 || y == 0 || y == getHeight() - 1)
					setTile(new TileWall(x, y));
				else
					setTile(new TileFlowable(x, y));
				if (y == 1) {
					getTile(x, y).setWater(2);
				}
			}
		}
	}

	private void setTile(Tile tile) {
		tiles[tile.getX() + tile.getY() * getWidth()] = tile;
	}

	public void render(Screen screen) {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].render(screen);
		}
	}

	public void tick() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].tick(this);
		}
	}

	public int getHeight() {
		return tiles.length / width;
	}

	public int getWidth() {
		return width;
	}

	public Tile getTile(int x, int y) {
		return tiles[y * getWidth() + x];
	}
}
