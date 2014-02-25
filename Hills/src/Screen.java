public class Screen {

	private final int width;

	public int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		pixels = new int[width * height];
		clear();
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int i, int t) {
		int z = 100;
		double a = 300 / 16 * 9 / 2, b = 1d / 100d, c = 0, d = a;
		for (int i2 = 0; i2 < z; i2++) {
			c += t;
			for (int x = 0; x < width; x++) {
				int y = (int) (Math.round(a * Math.sin(b * ((x + i) + c)) + d));
				if (y * width + x >= pixels.length)
					continue;
				pixels[y * width + x] = (int) c;
			}
		}
	}
}
