package com.mrizen.gaming;

public class Sprite {
	public static final int PINK = 0xFFFF00FF;
	public static final int WHITE = 0xFFFFFFFF;
	public static final int BLACK = 0xFF000000;
	public static final int GREEN = 0xFF00FF00;
	public static final int RED = 0xFFFF0000;
	public static final int BLUE = 0xFF0000FF;
	public static final int GRAY = 0xC0C0C0;

	private Array2D pixels;

	public Sprite(int size) {
		pixels = new Array2D(size, size);
	}

	public Sprite drawFill(int color) {
		for (int i = 0; i < pixels.getLength(); i++) {
			pixels.setI(i, color);
		}
		return this;
	}

	public Sprite drawLoad(int xa, int ya, Spritesheet sheet) {
		for (int y = 0; y < getSize(); y++) {
			int yp = ya * getSize() + y;
			for (int x = 0; x < getSize(); x++) {
				int xp = xa * getSize() + x;
				if (xp < 0 || yp < 0 || xp >= sheet.getPixels().getWidth() || yp >= sheet.getPixels().getHeight()) {
					continue;
				}
				pixels.set(x, y, sheet.getPixels().get(xp, yp));
			}
		}
		return this;
	}

	public int getSize() {
		return pixels.getWidth();
	}

	public Array2D getPixels() {
		return pixels;
	}
}