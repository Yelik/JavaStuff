import com.mrizen.gaming.Main;

public class Game extends Main {
	private static final long serialVersionUID = -2384178995194668215L;
	private Map map;

	public Game(String title, int width, int height, float scale, double updatesPerSecond) {
		super(title, width, height, scale, updatesPerSecond);
	}

	protected void render() {
		map.render(getScreen());
	}

	protected void update() {
		map.update();
	}

	private void load(Map map) {
		this.map = map;
	}

	public static void main(String[] args) {
		String title = "Megaman";
		int width = 256;
		int height = width / 16 * 9;
		float scale = 3.5f;
		double updatesPerSecond = 60;
		Game game = new Game(title, width, height, scale, updatesPerSecond);
		game.load(new Map(64, 64));
		game.start();
	}

}
