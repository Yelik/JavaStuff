public class Tile {
	public final Sprite sprite;
	public final int id;

	private boolean flowable;
	private int water;

	public final static Tile grass = new TileWall(Sprite.grass, 1).setFlowable(false);
	public final static Tile empty = new TileFlowable(Sprite.black, 0).setFlowable(true);

	public Tile(Sprite sprite, int id) {
		this.sprite = sprite;
		this.id = id;
		flowable = false;
		water = 0;
	}

	public Tile setFlowable(boolean b) {
		flowable = b;
		return this;
	}

	public Tile setWater(int i, int x, int y) {
		water = i;
		return this;
	}

	public int getWater() {
		return water;
	}

	public boolean getFlowable() {
		return flowable;
	}

	public void update(int x, int y) {

	}

	public void render(Screen screen, int x, int y) {
		screen.renderTile(x, y, this);
	}
}
