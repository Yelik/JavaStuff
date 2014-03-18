public class Sprites {
	public static Sprite[] sprites;

	public static void makeSprites() {
		sprites = new Sprite[Game.waterTiles];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new Sprite(16).drawFill(0xFF / sprites.length * i);
		}
	}
}
