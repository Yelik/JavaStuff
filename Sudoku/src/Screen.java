public class Screen {

	public int[] pixels;
	private int width;

	public Screen(int width, int height) {
		this.width = width;
		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void drawSprite(int xa, int ya, Sprite sprite) {
		for (int y = 0; y < sprite.getSize(); y++) {
			int yp = y + ya;
			for (int x = 0; x < sprite.getSize(); x++) {
				int xp = x + xa;
				int ip = yp * width + xp;
				int i = y * sprite.getSize() + x;
				if (ip >= pixels.length || ip < 0 || i < 0 || i > sprite.getPixels().length) {
					continue;
				}
				if (sprite.getPixels()[i] != Sprite.PINK)
					pixels[ip] = sprite.getPixels()[i];
			}
		}
	}
}
