public class Game extends Main {
	private static final long serialVersionUID = -2384178995194668215L;
	private Map map;

	public static int waterTiles;

	public Game(String title, int width, int height, int scale, double updatesPerSecond) {
		super(title, width, height, scale, updatesPerSecond);

		map = new Map(32, 32);
	}

	protected void render() {
		map.render(super.getScreen());
	}

	protected void update() {
		map.update();
	}

	public static void main(String[] args) {
		String title = "Water 3";
		int width = 300;
		int height = width / 16 * 9;
		int scale = 5;
		double updatesPerSecond = 60;
		waterTiles = 8;

		Sprites.makeSprites();
		Tile.makeTiles();

		Game game = new Game(title, width, height, scale, updatesPerSecond);
		game.start();
	}
}
