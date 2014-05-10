

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
}
