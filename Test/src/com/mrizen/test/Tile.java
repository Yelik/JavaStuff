package com.mrizen.test;

public class Tile {
	public int x, y;
	public Sprite sprite;

	public static Tile grass = new TileGrass(Sprite.grass);
	public static Tile flower = new TileFlower(Sprite.flower);
	public static Tile rock = new TileRock(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidTile);

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {

	}

	public boolean solid() {
		return false;
	}
}
