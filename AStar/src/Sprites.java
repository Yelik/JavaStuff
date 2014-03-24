import com.mrizen.gaming.Sprite;

public class Sprites {
	public final static Sprite BLACK = new Sprite(16).drawFill(Sprite.BLACK);
	public final static Sprite WHITE = new Sprite(16).drawFill(Sprite.WHITE);
	public final static Sprite RED = new Sprite(16).drawFill(Sprite.RED);
	public final static Sprite GREEN = new Sprite(16).drawFill(Sprite.GREEN);
	public final static Sprite GRAY = new Sprite(16).drawFill(Sprite.GRAY);
	public final static Sprite[] PATHS = new Sprite[128];

	public static void makeSprites() {
		for (int i = 0; i < PATHS.length; i++) {
			PATHS[i] = new Sprite(16).drawFill((int) (0xFFFF / PATHS.length * i));
		}
	}
}
