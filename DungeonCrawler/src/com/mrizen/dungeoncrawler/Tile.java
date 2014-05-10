package com.mrizen.dungeoncrawler;

import com.mrizen.gaming.*;

public class Tile {
	private Sprite sprite;
	private int id;

	public static final Tile nullTile = new Tile(Sprites.nullTile, 0);
	public static final Tile floor1 = new Tile(Sprites.floor1, 1);
	public static final Tile floor2 = new Tile(Sprites.floor2, 1);
	public static final Tile floor3 = new Tile(Sprites.floor3, 1);

	public Tile(Sprite sprite, int id) {
		this.sprite = sprite;
		this.id = id;
	}

	public void renderPos(Screen screen, int xPos, int yPos) {
		screen.drawSprite(sprite, xPos, yPos);
	}

	public void update(long anim) {

	}

	public void renderTile(Screen screen, int xTile, int yTile) {
		int size = getSprite().getSize();
		renderPos(screen, xTile * size, yTile * size);
	}

	public int getId() {
		return id;
	}

	public Sprite getSprite() {
		return sprite;
	}
}
