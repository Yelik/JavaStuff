public class Map {
	public int[] ids;

	private int width;

	public Map(int width, int height) {
		this.width = width;
		ids = new int[width * height];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = Game.waterTiles - 1;
		}
	}

	public void update() {
		System.out.println(ids[0]);
		for (int y0 = 0; y0 < getHeight(); y0++) {
			for (int x0 = 0; x0 < getWidth(); x0++) {
				Tile t = getTile(x0, y0);
				double[] waters = t.update(this, x0, y0).getWater();
				for (int y1 = 0; y1 < 3; y1++) {
					int yp = y0 + y1;
					for (int x1 = 0; x1 < 3; x1++) {
						int xp = x0 + x1;
						int ip = yp * getWidth() + xp;
						int i = y1 * 3 + x1;
						if (ip < 0 || ip >= ids.length || i < 0 || i >= waters.length) {
							continue;
						}
					}
				}
			}
		}
	}

	public void render(Screen screen) {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				int i = y * getWidth() + x;
				if (i < 0 || i >= ids.length) {
					continue;
				}
				getTile(x, y).render(screen, x, y);
			}
		}
	}

	private Tile getTile(int x, int y) {
		return Tile.tiles[getTileId(x, y)];
	}

	private int getTileId(int x, int y) {
		int i = y * getWidth() + x;
		if (i < 0 || i >= ids.length)
			return -1;
		return ids[i];
	}

	public int getHeight() {
		return ids.length / getWidth();
	}

	public int getWidth() {
		return width;
	}
}
