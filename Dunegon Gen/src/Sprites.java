import com.mrizen.gaming.Sprite;

public class Sprites {
	public static final Sprite WALL = new Sprite(Tile.SIZE).drawFill(Sprite.BLACK);
	public static final Sprite FLOOR = new Sprite(Tile.SIZE).drawFill(Sprite.GRAY);
	public static final Sprite EMPTY = new Sprite(Tile.SIZE).drawFill(Sprite.BLUE);
}
