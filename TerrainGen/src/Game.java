import com.mrizen.gaming.Main;

public class Game extends Main {
	private static final long serialVersionUID = -7803629994015778818L;
	private TerrainMap map;

	public Game() {
		super("Dunegon Gen", 256, 256 / 16 * 9, 3, 3, 60);
		map = new TerrainMap(5);
	}

	public void update() {
		map.update(getKeys(), getAnim());
	}

	public void render() {
		map.render(getScreen());
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
