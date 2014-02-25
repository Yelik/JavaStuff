public class TileFlowable extends Tile {

	public TileFlowable(int x, int y) {
		super(x, y);
	}

	@Override
	public void tick(Map map) {
		if (getWater() > 1) {
			flow(map, 0, 1);
		}
	}

	private void flow(Map map, int x, int y) {
		Tile t = map.getTile(x, y);
		if (t == null || !t.isFlowable()) {
			return;
		}
		System.out.println(2);
		int water = t.getWater(), maxWater = t.getMaxWater();
		if (water > getWater() || water == maxWater) {
			return;
		}

		int totalWater = water + getWater(), equalWater = totalWater / 2;
		setWater(equalWater);
		t.setWater(equalWater);
	}
}
