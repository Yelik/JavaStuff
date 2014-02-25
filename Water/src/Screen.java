public class Screen {

	private final int width;

	public final int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		pixels = new int[width * height];
		clear();
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderTile(int xp, int yp, Tile tile) {
		for (int y = 0; y < Sprite.tileHeight; y++) {
			int ya = y + yp;
			for (int x = 0; x < Sprite.tileWidth; x++) {
				int xa = x + xp;
				if (xa + ya * width < 0 || xa + ya * width >= pixels.length) {
					continue;
				}
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * Sprite.tileWidth];
			}
		}
	}
}
