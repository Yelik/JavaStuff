import java.util.ArrayList;

public class Level {
	public ArrayList<Entity> spawns;

	public static final Level LEVEL_ONE = new Level();

	public Level() {
		spawns = new ArrayList<Entity>();

	}

	public Level addSpawn(int time, int type) {
		return this;
	}
}
