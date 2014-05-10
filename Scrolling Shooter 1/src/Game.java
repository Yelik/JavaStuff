import com.mrizen.gaming.Main;

public class Game extends Main {
	private Level level;

	public Game(String title, int width, int height, double hScale, double vScale, double updatesPerSecond) {
		super(title, width, height, hScale, vScale, updatesPerSecond);
		level = Level.LEVEL_ONE;
	}

	public static void main(String[] args) {
		String title = "Scrolling Shooter";
		int width = 256;
		int height = width / 16 * 9;
		double hScale = 3;
		double vScale = 3;
		double updatesPerSecond = 60;
		Game game = new Game(title, width, height, hScale, vScale, updatesPerSecond);
		game.start();
	}
}
