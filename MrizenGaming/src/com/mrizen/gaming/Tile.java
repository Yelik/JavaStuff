package com.mrizen.gaming;

public class Tile {
	public static final int SIZE = 16;
	protected int id;
	protected Sprite sprite;

	private static int nextId = 0;

	public Tile(int id, Sprite sprite) {
		this.id = id;
		this.sprite = sprite;
	}

	public static int getNextId() {
		return nextId++;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getId() {
		return id;
	}
}
