public class Screen {
	public final int[] pixels;
	private final int width;

	public Screen(int width, int height) {
		this.width = width;
		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderTile(int xp, int yp, Sprite sprite) {
		int width = sprite.getWidth(), height = sprite.getHeight();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int i = y * width + x, ip = (yp + y) * this.width + (xp + x);
				if (ip >= pixels.length || ip < 0) {
					continue;
				}
				pixels[ip] = sprite.getPixels()[i];
			}
		}
	}

	public void addWater(int xp, int yp, int width, int height, int color) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int i = y * width + x, ip = (yp + y) * this.width + (xp + x);
				if (ip >= pixels.length || ip < 0) {
					continue;
				}
				pixels[ip] = color + pixels[ip];
			}
		}
	}
}
