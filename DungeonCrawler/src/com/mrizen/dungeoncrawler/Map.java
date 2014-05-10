package com.mrizen.dungeoncrawler;

import java.util.ArrayList;

import com.mrizen.gaming.*;

public class Map {
	private Array2D tiles;
	private ArrayList<Entity> entities;

	public Map(int width, int height) {
		tiles = new Array2D(width, height);
		entities = new ArrayList<Entity>();
	}

	public void render(Screen screen) {
		for (int y = 0; y < tiles.getHeight(); y++) {
			for (int x = 0; x < tiles.getWidth(); x++) {
				getTile(x, y).renderTile(screen, x, y);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}

	protected Tile getTile(int x, int y) {
		return getTileById(tiles.get(x, y));
	}

	protected Tile getTileById(int id) {
		if (id == Tile.floor1.getId())
			return Tile.floor1;
		if (id == Tile.floor2.getId())
			return Tile.floor2;
		if (id == Tile.floor3.getId())
			return Tile.floor3;
		return Tile.nullTile;
	}

	public void update(KeyReader keyReader, long anim) {
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	protected Array2D getTiles() {
		return tiles;
	}
}
