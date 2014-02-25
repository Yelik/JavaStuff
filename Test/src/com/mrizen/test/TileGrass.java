package com.mrizen.test;

public class TileGrass extends Tile {

	public TileGrass(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
