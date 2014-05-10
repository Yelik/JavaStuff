package com.mrizen.gaming;

public class Screen {
	private Array2D pixels;
	private int xOffset;
	private int yOffset;

	public Screen(int width, int height) {
		pixels = new Array2D(width, height);
	}

	public void clear() {
		for (int i = 0; i < pixels.getLength(); i++) {
			pixels.setI(i, 0);
		}
		setOffset(0, 0);
	}

	public void setOffset(int xOffset, int yOffset) {
		setXOffset(xOffset);
		setYOffset(yOffset);
	}

	public void drawSprite(Sprite sprite, int xPos, int yPos) {
		for (int y = 0; y < sprite.getSize(); y++) {
			int yp = y + yOffset + yPos;
			for (int x = 0; x < sprite.getSize(); x++) {
				int xp = x + xOffset + xPos;
				if (xp < 0 || yp < 0 || xp >= pixels.getWidth() || yp >= pixels.getHeight())
					continue;
				int pixel = sprite.getPixels().get(x, y);
				if (pixel != Sprite.PINK) {
					pixels.set(xp, yp, pixel);
				}
			}
		}
	}

	public void drawColor(int color, int xPos, int yPos, int width, int height) {
		for (int y = 0; y < height; y++) {
			int yp = y + yOffset + yPos;
			for (int x = 0; x < width; x++) {
				int xp = x + xOffset + xPos;
				if (xp < 0 || yp < 0 || xp >= pixels.getWidth() || yp >= pixels.getHeight())
					continue;
				int pixel = color;
				if (pixel != Sprite.PINK) {
					pixels.set(xp, yp, pixel);
				}
			}
		}
	}

	public Array2D getPixels() {
		return pixels;
	}

	public void setXOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}

}
