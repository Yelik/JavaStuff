package com.mrizen.test;

public class TileFlower extends Tile {

	public TileFlower(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
