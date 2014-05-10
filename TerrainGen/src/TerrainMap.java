import java.util.Random;

import com.mrizen.gaming.Array2D;
import com.mrizen.gaming.KeyReader;
import com.mrizen.gaming.Map;
import com.mrizen.gaming.Tile;

public class TerrainMap extends Map {
	private int lastMoveAnim;

	public TerrainMap(int degree) {
		super((int) Math.pow(2, degree), (int) Math.pow(2, degree));
		lastMoveAnim = 0;
		generateMap(0);
	}

	public void update(KeyReader keys, int anim) {
		if (anim - lastMoveAnim > 15) {
			if (keys.getLeft()) {
				scrollTo(getXOffset() + Tile.SIZE, getYOffset());
				lastMoveAnim = anim;
			} else if (keys.getRight()) {
				scrollTo(getXOffset() - Tile.SIZE, getYOffset());
				lastMoveAnim = anim;
			} else if (keys.getUp()) {
				scrollTo(getXOffset(), getYOffset() + Tile.SIZE);
				lastMoveAnim = anim;
			} else if (keys.getDown()) {
				scrollTo(getXOffset(), getYOffset() - Tile.SIZE);
				lastMoveAnim = anim;
			}
		}
	}

	private void generateMap(int seed) {
		Random rand = new Random(seed);
		int count = exponify(getTiles().getWidth());
		Array2D first = new Array2D(2, 2);
		for (int i = 0; i < first.getLength(); i++) {
			first.setI(i, rand.nextInt(8));
		}
		Array2D working = null;
		while (count > 0) {
			working = spilt(first);
			working = wiggle(working, rand, 1);
			count--;
		}
		for (int i = 0; i < getTiles().getLength(); i++)
			getTiles().setI(i, working.getI(i));
	}

	private int exponify(int i) {
		int count = 0;
		while (i > 1) {
			count++;
			i /= 2;
		}
		return count;
	}

	private Array2D wiggle(Array2D array1, Random rand, int changeRate) {
		Array2D array2 = new Array2D(array1.getWidth(), array1.getHeight());
		for (int y = 0; y < array2.getHeight(); y++) {
			for (int x = 0; x < array2.getWidth(); x++) {
				array2.set(x, y, array1.get(x, y) + rand.nextInt(changeRate * 2) - rand.nextInt(changeRate));
			}
		}
		return array2;
	}

	private Array2D spilt(Array2D array1) {
		Array2D array2 = new Array2D(array1.getWidth() * 2, array1.getHeight() * 2);
		for (int y = 0; y < array2.getHeight(); y++) {
			for (int x = 0; x < array2.getWidth(); x++) {
				array2.set(x, y, array1.get((int) Math.floor(x / 2.0), (int) Math.floor(y / 2.0)));
			}
		}
		return array2;
	}

	public Tile getTileById(int id) {
		if (id == Tiles.EMPTY.getId())
			return Tiles.EMPTY;
		if (id == Tiles.ONE.getId())
			return Tiles.ONE;
		if (id == Tiles.TWO.getId())
			return Tiles.TWO;
		if (id == Tiles.THREE.getId())
			return Tiles.THREE;
		if (id == Tiles.FOUR.getId())
			return Tiles.FOUR;
		if (id == Tiles.FIVE.getId())
			return Tiles.FIVE;
		if (id == Tiles.SIX.getId())
			return Tiles.SIX;
		if (id == Tiles.SEVEN.getId())
			return Tiles.SEVEN;
		return Tiles.EMPTY;
	}
}
