public class Checker {

	public static Checker red = new Checker(Sprite.red, 1);
	public static Checker blue = new Checker(Sprite.blue, 2);
	public static Checker nullCheck = new Checker(Sprite.empty, 0);
	public static Checker redKing = new Checker(Sprite.redKing, 4);
	public static Checker blueKing = new Checker(Sprite.blueKing, 5);;
	public int id;
	private Sprite sprite;

	public Checker(Sprite sprite, int id) {
		this.id = id;
		this.sprite = sprite;
	}

	public void render(Screen screen, int x, int y) {
		screen.renderChecker(x * Sprite.tileWidth, y * Sprite.tileHeight, this);
	}

	public Sprite getSprite() {
		return sprite;
	}
}