import com.mrizen.gaming.Sprite;

public class TileWall extends Tile {

	public TileWall(Sprite sprite, int id) {
		super(sprite, id);
	}

	public boolean isSolid() {
		return true;
	}
}
