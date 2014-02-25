public class Sprite {
	public final int[] pixels;
	public static final int tileWidth = 16, tileHeight = 16;
	public static final Sprite grass = new Sprite(0xFF00FF00);
	public static final Sprite black = new Sprite(0xFF000000);

	public Sprite(int color) {
		pixels = new int[tileWidth * tileHeight];
		for (int y = 0; y < tileHeight; y++) {
			for (int x = 0; x < tileWidth; x++) {
				pixels[x + y * tileWidth] = color;
			}
		}
	}
}
