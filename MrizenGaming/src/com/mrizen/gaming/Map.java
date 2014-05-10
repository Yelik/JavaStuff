package com.mrizen.gaming;

public class Map {
	private Array2D tiles;
	private Point2D offset;

	public Map(int width, int height) {
		tiles = new Array2D(width, height);
		offset = new Point2D(0, 0);
	}

	public void render(Screen screen) {
		screen.setOffset(offset.getX(), offset.getY());
		for (int yTile = 0; yTile < tiles.getHeight(); yTile++) {
			for (int xTile = 0; xTile < tiles.getWidth(); xTile++) {
				screen.drawSprite(getTile(xTile, yTile).getSprite(), xTile * Tile.SIZE, yTile * Tile.SIZE);
			}
		}
	}

	public void update(KeyReader keys, int anim) {

	}

	protected void scrollTo(int offsetX, int offsetY) {
		offset.setX(offsetX);
		offset.setY(offsetY);
	}

	protected Tile getTileById(int id) {
		return null;
	}

	protected Tile getTile(int x, int y) {
		return getTileById(tiles.get(x, y));
	}

	protected Array2D getTiles() {
		return tiles;
	}

	public Point2D getOffset() {
		return offset;
	}

	public int getXOffset() {
		return offset.getX();
	}

	public int getYOffset() {
		return offset.getY();
	}
}