public class Tile {
	public static final Tile empty = new Tile(0, Sprites.empty);
	public static final Tile grass = new Tile(1, Sprites.grass);

	private static final int WIDTH = 16;
	private static final int HEIGHT = 16;

	public final int id;
	private Sprite sprite;

	public Tile(int id, Sprite sprite) {
		this.id = id;
		this.sprite = sprite;
	}

	public void render(Screen screen, int x, int y) {
		screen.drawSprite(x * WIDTH, y * HEIGHT, sprite);
	}
}
