public class Sprite {

	public int[] pixels;
	public final int WIDTH, HEIGHT;
	private static int kingColor = 0xFFFF00;

	private static final int SQUARE = 1;
	private static final int CIRCLE = 2;
	private static final int KING = 3;

	public static int tileWidth = 64, tileHeight = 64;

	public static Sprite black = new Sprite(tileWidth, tileHeight, 0x0, SQUARE);
	public static Sprite white = new Sprite(tileWidth, tileHeight, 0xFFFFFF, SQUARE);
	public static Sprite yellow = new Sprite(tileWidth, tileHeight, 0xFFFF00, SQUARE);

	public static Sprite red = new Sprite(tileWidth, tileHeight, 0xFF0000, CIRCLE);
	public static Sprite blue = new Sprite(tileWidth, tileHeight, 0x0000FF, CIRCLE);
	public static Sprite empty = new Sprite(tileWidth, tileHeight, 0, SQUARE);
	public static Sprite redKing = new Sprite(tileWidth, tileHeight, 0xFF0000, KING);
	public static Sprite blueKing = new Sprite(tileWidth, tileHeight, 0x0000FF, KING);

	public Sprite(int width, int height, int color, int shape) {
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		if (shape == SQUARE) {
			for (int i = 0; i < WIDTH * HEIGHT; i++) {
				pixels[i] = color;
			}
		} else if (shape == CIRCLE) {
			for (int y = 0; y < HEIGHT; y++) {
				for (int x = 0; x < WIDTH; x++) {
					if (Math.sqrt(Math.pow(x - WIDTH / 2, 2) + Math.pow(y - HEIGHT / 2, 2)) < Math.min(WIDTH / 2 - 1, HEIGHT / 2 - 1))
						pixels[x + y * WIDTH] = color;
				}
			}
		} else if (shape == KING) {
			for (int y = 0; y < HEIGHT; y++) {
				for (int x = 0; x < WIDTH; x++) {
					if (Math.sqrt(Math.pow(x - WIDTH / 2, 2) + Math.pow(y - HEIGHT / 2, 2)) < Math.min(WIDTH / 2 - 1, HEIGHT / 2 - 1))
						if (x == tileWidth / 2)
							pixels[x + y * WIDTH] = kingColor;
						else
							pixels[x + y * WIDTH] = color;
				}
			}
		}
	}
}