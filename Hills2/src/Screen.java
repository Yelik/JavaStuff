public class Screen {

	public final int width;

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

	public void renderDot(int x, int y) {
		if (y * width + x < pixels.length) {
			pixels[y * width + x] = 0xFF;
		}
	}
}
