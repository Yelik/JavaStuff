package com.mrizen.test;

public class Sprite {
	private int x, y;
	private SpriteSheet sheet;

	public final int SIZE;
	public int[] pixels;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);

	public static Sprite voidTile = new Sprite(16, 0x0000FF);

	public static Sprite playerUp = new Sprite(32, 0, 1, SpriteSheet.tiles);
	public static Sprite playerRight = new Sprite(32, 1, 1, SpriteSheet.tiles);
	public static Sprite playerDown = new Sprite(32, 2, 1, SpriteSheet.tiles);

	public static Sprite playerUp1 = new Sprite(32, 0, 2, SpriteSheet.tiles);
	public static Sprite playerUp2 = new Sprite(32, 0, 3, SpriteSheet.tiles);

	public static Sprite playerRight1 = new Sprite(32, 1, 2, SpriteSheet.tiles);
	public static Sprite playerRight2 = new Sprite(32, 1, 3, SpriteSheet.tiles);

	public static Sprite playerDown1 = new Sprite(32, 2, 2, SpriteSheet.tiles);
	public static Sprite playerDown2 = new Sprite(32, 2, 3, SpriteSheet.tiles);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[x + this.x + (y + this.y) * sheet.SIZE];
			}
		}
	}

	private void setColor(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
}
