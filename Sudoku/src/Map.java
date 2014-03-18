public class Map {
	private int[] tiles;
	private Sprite[] sprites;
	private int width;

	public Map(int width, int height) {
		this.width = width;
		tiles = new int[width * height];
		sprites = new Sprite[10];
		sprites[1] = Sprite.one;
		sprites[2] = Sprite.two;
		sprites[3] = Sprite.three;
		sprites[4] = Sprite.four;
		sprites[5] = Sprite.five;
		sprites[6] = Sprite.six;
		sprites[7] = Sprite.seven;
		sprites[8] = Sprite.eight;
		sprites[9] = Sprite.nine;
	}

	public void generateMap() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = 1;
		}
	}

	public void render(Screen screen) {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				Sprite sprite = sprites[tiles[y * getWidth() + x]];
				int size = sprite.getSize();
				screen.drawSprite(x * size, y * size, sprite);
			}
		}
	}

	private int getWidth() {
		return width;
	}

	private int getHeight() {
		return tiles.length / getWidth();
	}
}
