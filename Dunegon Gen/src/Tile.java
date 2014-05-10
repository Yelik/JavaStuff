import com.mrizen.gaming.Sprite;

public class Tile {

	public static final Tile WALL = new Tile(Sprites.WALL, nextId());
	public static final Tile EMPTY = new Tile(Sprites.EMPTY, nextId());
	public static final Tile FLOOR = new Tile(Sprites.FLOOR, nextId());
	public static final int SIZE = 16;
	private static int idCount = 0;

	private Sprite sprite;
	private int id;

	public Tile(Sprite sprite, int id) {
		this.sprite = sprite;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public static int nextId() {
		return idCount++;
	}
}
