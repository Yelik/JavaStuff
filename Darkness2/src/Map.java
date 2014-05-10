import com.mrizen.gaming.Array2D;
import com.mrizen.gaming.Screen;

public class Map {
	private Array2D dark;
	private int darkMax;
	private int darkSize;

	public Map(int width, int height) {
		dark = new Array2D(width, height);
		darkMax = 255;
	}

	public void renderTile(Screen screen, int xTile, int yTile) {
		int i = 0xFFFFFF;
		i -= dark.get(xTile, yTile) * 0xFFFFFF / darkMax;
		screen.drawColor(i, xTile * darkSize, yTile * darkSize, darkSize, darkSize);
	}

	public void render(Screen screen) {
		screen.setOffset(0, 0);
		for (int yTile = 0; yTile < dark.getHeight(); yTile++) {
			for (int xTile = 0; xTile < dark.getWidth(); xTile++) {
				renderTile(screen, xTile, yTile);
			}
		}
	}

	public void update() {
		Array2D dark2 = new Array2D(dark.getWidth(), dark.getHeight());
		for (int y = 0; y < dark.getHeight(); y++) {
			for (int x = 0; x < dark.getWidth(); x++) {
				Array2D darkSlice = dark.getSlice(x - 1, y - 1, x + 1, y + 1);
				int darkTotal = 0;
				int darkCount = 0;
				darkCount++;
				darkTotal += darkSlice.get(x, y);
				darkCount++;
				darkTotal += darkSlice.get(x + 1, y);
				darkCount++;
				darkTotal += darkSlice.get(x - 1, y - 1);
				darkCount++;
				darkTotal += darkSlice.get(x - 1, y + 1);
				dark2.set(x, y, darkTotal / darkCount);
			}
		}
		for (int y = 0; y < dark.getHeight(); y++) {
			for (int x = 0; x < dark.getWidth(); x++) {
				dark.set(x, y, dark2.get(x, y));
			}
		}
	}
}
