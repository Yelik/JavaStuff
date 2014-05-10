package com.mrizen.energy;

import com.mrizen.gaming.Screen;
import com.mrizen.gaming.Sprite;

public class Tile {
	public static final Tile FLAT = new Tile(0, Sprites.FLAT);
	public static final Tile WALL = new TileWall(1, Sprites.WALL);
	public static final Tile NODE = new TileNode(2);

	private int id;
	private Sprite sprite;

	public Tile(int id, Sprite sprite) {
		this.id = id;
		this.sprite = sprite;

	}

	public boolean getSolid() {
		return false;
	}

	public int getId() {
		return id;
	}

	public void render(Screen screen, int xTile, int yTile) {
		int size = sprite.getSize();
		screen.drawSprite(sprite, xTile * size, yTile * size);
	}
}
