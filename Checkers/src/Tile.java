public class Tile {
	private Sprite sprite;

	public static Tile black = new Tile(Sprite.black, 0);
	public static Tile white = new Tile(Sprite.white, 1);
	public static Tile yellow = new Tile(Sprite.yellow, 2);

	public final int id;

	public Tile(Sprite sprite, int num) {
		this.sprite = sprite;
		this.id = num;
	}

	public void render(Screen screen, int x, int y) {
		screen.renderTile(x * sprite.WIDTH, y * sprite.HEIGHT, this);
	}

	public Sprite getSprite() {
		return sprite;
	}
}