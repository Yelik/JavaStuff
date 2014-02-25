public class Sprite {
	private int[] pixels;

	private static int width = Math.round(Main.getMainWidth() / Main.getMap().getWidth()), height = Math.round(Main.getMainHeight() / Main.getMap().getHeight());

	public static Sprite empty = new Sprite(0);
	public static Sprite wall = new Sprite(0xC0C0C0);

	public Sprite(int color) {
		pixels = new int[width * height];
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}

	public int[] getPixels() {
		return pixels;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}