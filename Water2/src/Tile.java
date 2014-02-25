public class Tile {
	private final int x, y;
	private int water;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isFlowable() {
		return true;
	}

	public int getMaxWater() {
		return 8;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int i) {
		if (!isFlowable())
			return;
		water = i;
		if (getWater() > getMaxWater())
			setWater(getMaxWater());
	}

	public Sprite getSprite() {
		return Sprite.empty;
	}

	public void render(Screen screen) {
		int xp = x * getSprite().getWidth(), yp = y * getSprite().getHeight();
		screen.renderTile(xp, yp, getSprite());
		screen.addWater(xp, yp, getSprite().getWidth(), getSprite().getHeight(), 0x0000FF * getWater() / getMaxWater());
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void tick(Map map) {
		System.out.println(this);
	}
}
