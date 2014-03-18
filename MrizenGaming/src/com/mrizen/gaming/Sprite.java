package com.mrizen.gaming;

public class Sprite {
	public static final int PINK = 0xFFFF00FF;
	public static final int WHITE = 0xFFFFFFFF;
	public static final int BLACK = 0xFF000000;
	public static final int GREEN = 0xFF00FF00;
	public static final int RED = 0xFFFF0000;
	public static final int BLUE = 0xFF0000FF;
	public static final int GRAY = 0xC0C0C0;

	private final int SIZE;

	private int[] pixels;

	public Sprite(int SIZE) {
		this.SIZE = SIZE;
		pixels = new int[SIZE * SIZE];
	}

	public Sprite drawFill(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
		return this;
	}

	public Sprite drawLoad(int xa, int ya, Spritesheet sheet) {
		for (int y = 0; y < SIZE; y++) {
			int yp = ya * SIZE + y;
			for (int x = 0; x < SIZE; x++) {
				int xp = xa * SIZE + x;
				int ip = yp * sheet.getWidth() + xp, i = y * SIZE + x;
				if (ip < 0 || ip >= sheet.pixels.length || i < 0 || i >= SIZE) {
					continue;
				}
				pixels[i] = sheet.pixels[ip];
			}
		}
		return this;
	}

	public int getSize() {
		return SIZE;
	}

	public int[] getPixels() {
		return pixels;
	}
}
