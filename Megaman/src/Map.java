import com.mrizen.gaming.Screen;

public class Map {
	private int[] tiles;
	private int width;

	public Map(int width, int height) {
		this.width = width;
		tiles = new int[width * height];
	}

	public void render(Screen screen) {
		for (int y = 0; y < getHeight(); y++) {

		}
	}

	private int getHeight() {
		return tiles.length / getWidth();
	}

	private int getWidth() {
		return width;
	}

	public void update() {

	}
}