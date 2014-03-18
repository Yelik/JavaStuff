public class Sprite {
	public static final int PINK = 0xFFFF00FF;
	public static final int WHITE = 0xFFFFFFFF;
	public static final int BLACK = 0xFF000000;

	private final int SIZE;

	private int[] pixels;

	public static Sprite one = new Sprite(16).drawNumber(1, BLACK, WHITE);
	public static Sprite two = new Sprite(16).drawNumber(2, BLACK, WHITE);
	public static Sprite three = new Sprite(16).drawNumber(3, BLACK, WHITE);
	public static Sprite four = new Sprite(16).drawNumber(4, BLACK, WHITE);
	public static Sprite five = new Sprite(16).drawNumber(5, BLACK, WHITE);
	public static Sprite six = new Sprite(16).drawNumber(6, BLACK, WHITE);
	public static Sprite seven = new Sprite(16).drawNumber(7, BLACK, WHITE);
	public static Sprite eight = new Sprite(16).drawNumber(8, BLACK, WHITE);
	public static Sprite nine = new Sprite(16).drawNumber(9, BLACK, WHITE);

	public Sprite(int SIZE) {
		this.SIZE = SIZE;
		pixels = new int[SIZE * SIZE];
	}

	public Sprite drawFill(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
		return this;
	}

	public Sprite drawNumber(int number, int textColor, int backgroundColor) {
		switch (number) {
		case 1:
			for (int y = 0; y < SIZE; y++) {
				for (int x = 0; x < SIZE; x++) {
					if (x == 7 && (y < 13 && y > 2) || x == 0 || y == 0 || x == SIZE - 1 || y == SIZE - 1) {
						pixels[y * SIZE + x] = textColor;
					} else {
						pixels[y * SIZE + x] = backgroundColor;
					}
				}
			}
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		}
		return this;
	}

	public int getSize() {
		return SIZE;
	}

	public int[] getPixels() {
		return pixels;
	}
}
