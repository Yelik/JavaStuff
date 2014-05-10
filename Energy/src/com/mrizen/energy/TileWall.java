package com.mrizen.energy;

import com.mrizen.gaming.Sprite;

public class TileWall extends Tile {

	public TileWall(int id, Sprite sprite) {
		super(id, sprite);
	}

	public boolean getSolid() {
		return true;
	}
}
