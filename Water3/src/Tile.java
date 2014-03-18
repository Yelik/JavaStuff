public class Tile {
	private Sprite sprite;
	private int id;

	public static Tile[] tiles;

	public Tile(int id, Sprite sprite) {
		this.id = id;
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void render(Screen screen, int x, int y) {
		screen.drawSprite(x * sprite.getSize(), y * sprite.getSize(), sprite);
	}

	public Tile update(Map map, int x, int y) {
		return this;
	}

	public static void makeTiles() {
		tiles = new Tile[Game.waterTiles];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new TileFlowable(i, Sprites.sprites[i]);
		}
	}
}
