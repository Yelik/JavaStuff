import com.mrizen.gaming.Main;

public class Game extends Main {
	private static final long serialVersionUID = -7803629994015778818L;

	private Map map;
	private int anim;

	public Game() {
		super("Dunegon Gen", 256, 256 / 16 * 9, 3, 3, 60);
		map = new Map(32, 32);
		anim = 0;
	}

	public void update() {
		anim++;
		map.update(getKeys(), anim);
	}

	public void render() {
		map.render(getScreen());
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
