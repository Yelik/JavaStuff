package com.mrizen.gaming;

import java.awt.Color;

public class Array2DColor {
	Color[] array;
	private int width;

	public Array2DColor(int width, int height) {
		this.width = width;
		array = new Color[width * height];
	}

	public boolean set(int x, int y, Color value) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight())
			return false;
		array[y * getWidth() + x] = value;
		return true;
	}

	public Color get(int x, int y) {
		if (y < 0 || x < 0 || y >= getHeight() || x >= getWidth())
			return null;
		return array[y * getWidth() + x];
	}

	public Color getI(int i) {
		if (i >= array.length || i < 0)
			return null;
		return array[i];
	}

	public boolean setI(int i, Color value) {
		if (i >= array.length || i < 0)
			return false;
		array[i] = value;
		return true;
	}

	public boolean add(int x, int y, Color value) {
		return set(x, y, new Color(get(x, y).getRGB() + value.getRGB()));
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return array.length / getWidth();
	}

	public int getLength() {
		return array.length;
	}

	public Color[] getArray() {
		return array;
	}
}
