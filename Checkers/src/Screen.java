public class Screen {
	public int[] pixels;
	public final int WIDTH, HEIGHT;

	public Screen(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[width * height];
	}

	public void clear() {
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				pixels[x + y * WIDTH] = 0;
			}
		}
	}

	public void renderTile(int xPix, int yPix, Tile tile) {
		for (int y = 0; y < tile.getSprite().HEIGHT; y++) {
			int yAbs = y + yPix;
			for (int x = 0; x < tile.getSprite().WIDTH; x++) {
				int xAbs = x + xPix;
				pixels[xAbs + yAbs * WIDTH] = tile.getSprite().pixels[x + y * tile.getSprite().WIDTH];
			}
		}
	}

	public void renderChecker(int xPix, int yPix, Checker check) {
		for (int y = 0; y < check.getSprite().HEIGHT; y++) {
			int yAbs = y + yPix;
			for (int x = 0; x < check.getSprite().WIDTH; x++) {
				int xAbs = x + xPix;
				if (check.getSprite().pixels[x + y * check.getSprite().WIDTH] != 0)
					pixels[xAbs + yAbs * WIDTH] = check.getSprite().pixels[x + y * check.getSprite().WIDTH];
			}
		}
	}
}
