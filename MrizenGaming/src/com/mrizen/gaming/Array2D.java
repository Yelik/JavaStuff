package com.mrizen.gaming;

public class Array2D {
	int[] array;
	private int width;

	public Array2D(int width, int height) {
		this.width = width;
		array = new int[width * height];
	}

	public boolean set(int x, int y, int value) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight())
			return false;
		array[y * getWidth() + x] = value;
		return true;
	}

	public int get(int x, int y) {
		if (y < 0 || x < 0 || y >= getHeight() || x >= getWidth())
			return -1;
		return array[y * getWidth() + x];
	}

	public int getI(int i) {
		if (i >= array.length || i < 0)
			return -1;
		return array[i];
	}

	public boolean setI(int i, int value) {
		if (i >= array.length || i < 0)
			return false;
		array[i] = value;
		return true;
	}

	public boolean add(int x, int y, int value) {
		return set(x, y, get(x, y) + value);
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

	public int[] getArray() {
		return array;
	}

	public Array2D getSlice(int x0, int y0, int x1, int y1) {
		Array2D temp = new Array2D(x1 - x0, y1 - y0);
		for (int y = 0; y < temp.getHeight(); y++) {
			for (int x = 0; x < temp.getWidth(); x++) {
				if (x + x0 < 0 || y + y0 < 0)
					temp.set(x, y, -1);
				else
					temp.set(x, y, this.get(x + x0, y + y0));
			}
		}
		return temp;
	}
}
