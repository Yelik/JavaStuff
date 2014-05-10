import com.mrizen.gaming.Main;

public class Game extends Main {
	private static final long serialVersionUID = -5113567141173124521L;

	private Map map;

	public Game(String title, int width, int height, double scale, double updatesPerSecond) {
		super(title, width, height, scale, scale, updatesPerSecond);
		map = new Map(width / Tile.EMPTY.getSprite().getSize(), height / Tile.EMPTY.getSprite().getSize());
		showCursor(false);
		Sprites.makeSprites();
	}

	protected void render() {
		map.render(getScreen());
	}

	protected void update() {
		map.update(getMouse(), getKeys());
	}

	public static void main(String[] args) {
		String title = "A Star";
		int width = 256 * 2;
		int height = width / 16 * 9;
		float scale = 3;
		double updatesPerSecond = 60;
		Game game = new Game(title, width, height, scale, updatesPerSecond);
		game.start();
	}
}
