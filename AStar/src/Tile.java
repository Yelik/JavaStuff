import com.mrizen.gaming.Screen;
import com.mrizen.gaming.Sprite;

public class Tile {
	private Sprite sprite;
	private int id;

	public final static Tile EMPTY = new Tile(Sprites.WHITE, 0);
	public final static Tile WALL = new TileWall(Sprites.BLACK, 1);

	public Tile(Sprite sprite, int id) {
		this.sprite = sprite;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void render(Screen screen, int x, int y) {
		screen.drawSprite(x * sprite.getSize(), y * sprite.getSize(), sprite);
	}

	public Sprite getSprite() {
		return sprite;
	}
	
	public boolean isSolid(){
		return false;
	}
}