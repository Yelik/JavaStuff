public class Map {

	public void render(Screen screen, int i) {
		for (int x = 0; x < screen.width; x++) {

			screen.renderDot(x, getY(x, i));
		}
	}

	private int getY(int x, int i) {
		return ;
	}

}
