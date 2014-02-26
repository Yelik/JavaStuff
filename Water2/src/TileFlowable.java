public class TileFlowable extends Tile {

	public TileFlowable(int x, int y) {
		super(x, y);
	}

	@Override
	public void tick(Map map) {
		if (getX() == 4)
			setWater(getMaxWater());
		flow(map, 0, 1);
		flow(map, -1, -1);
		flow(map, 1, -1);
	}

	private void flow(Map map, int x, int y) {
		Tile t = map.getTile(x + getX(), y + getY());
		if (t == null || !t.isFlowable()) {
			return;
		}
		int water = t.getWater(), maxWater = t.getMaxWater();
		if (water >= getWater() || water == maxWater) {
			return;
		}

		int totalWater = water + getWater(), equalWater = totalWater / 2;
		if (totalWater % 2 == 1)
			setWater(equalWater + 1);
		else
			setWater(equalWater);
		t.setWater(equalWater);
	}
}
