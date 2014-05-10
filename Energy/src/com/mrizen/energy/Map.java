package com.mrizen.energy;

import com.mrizen.gaming.Array2D;
import com.mrizen.gaming.KeyReader;
import com.mrizen.gaming.MouseReader;
import com.mrizen.gaming.Screen;

public class Map {
	private Array2D tiles;

	public Map(int width, int height) {
		tiles = new Array2D(width, height);
		generateMap();
	}

	public void render(Screen screen) {
		for (int yTile = 0; yTile < tiles.getHeight(); yTile++) {
			for (int xTile = 0; xTile < tiles.getWidth(); xTile++) {
				getTile(xTile, yTile).render(screen, xTile, yTile);
			}
		}
	}

	public void update(KeyReader keys, MouseReader mouse) {
		for (int y = 0; y < tiles.getHeight(); y++) {
			for (int x = 0; x < tiles.getWidth(); x++) {

			}
		}
	}

	public void generateMap() {
		for (int y = 0; y < tiles.getHeight(); y++) {
			for (int x = 0; x < tiles.getWidth(); x++) {
				if (x % 3 == 1 && y % 3 == 1)
					tiles.set(x, y, Tile.NODE.getId());
				else
					tiles.set(x, y, Tile.FLAT.getId());
			}
		}
	}

	public Tile getTile(int x, int y) {
		return getTile(tiles.get(x, y));
	}

	public Tile getTile(int id) {
		if (id == Tile.FLAT.getId())
			return Tile.FLAT;
		else if (id == Tile.WALL.getId())
			return Tile.WALL;
		else if (id == Tile.NODE.getId())
			return Tile.NODE;
		return Tile.FLAT;
	}
}
