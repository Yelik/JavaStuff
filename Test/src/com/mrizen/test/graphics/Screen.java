package com.mrizen.test.graphics;

import java.util.Random;

public class Screen {
	private int width, height;
	public int[] pixels;

	private Random rand = new Random();

	public int[] tiles = new int[64 * 64];

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < 64 * 64; i++) {
			tiles[i] = rand.nextInt(0xFFFFFFF);
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			int yy = y;
			for (int x = 0; x < width; x++) {
				int xx = x;
				int tileIndex = ((xx >> 4) & 63) + ((yy >> 4) & 63) * 64;
				pixels[x + y * width] = tiles[tileIndex];
			}
		}
	}
}
