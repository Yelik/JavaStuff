public class TileWall extends Tile {

	public TileWall(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isFlowable() {
		return false;
	}

	@Override
	public Sprite getSprite() {
		return Sprite.wall;
	}
}
