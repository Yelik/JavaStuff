package com.mrizen.dungeoncrawler;

import com.mrizen.gaming.Sprite;
import com.mrizen.gaming.Spritesheet;

public class Sprites {
	public static final Spritesheet tiles = new Spritesheet("/tiles.png");

	public static final Sprite floor1 = new Sprite(16).drawLoad(0, 0, tiles);
	public static final Sprite floor2 = new Sprite(16).drawLoad(0, 1, tiles);
	public static final Sprite floor3 = new Sprite(16).drawLoad(0, 2, tiles);
	public static final Sprite nullTile = new Sprite(16).drawFill(0xFF0066FF);
}
