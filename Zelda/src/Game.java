public class Game extends Main {
	private static final long serialVersionUID = -2384178995194668215L;
	private Map map;
	private Entity player;

	public Game(String title, int width, int height, int scale, double updatesPerSecond) {
		super(title, width, height, scale, updatesPerSecond);

		map = new Map(16, 16);
		map.generateMap();
	}

	protected void render() {
		map.render(getScreen(), player.getX(), player.getY());
	}

	protected void update() {
		map.update();
	}

	public static void main(String[] args) {
		String title = "Zelda";
		int width = 256;
		int height = width / 16 * 9;
		int scale = 6;
		double updatesPerSecond = 60;

		Game game = new Game(title, width, height, scale, updatesPerSecond);
		game.start();
	}
}