import java.util.ArrayList;

public class Board {

	public static int red = 1;
	public static int blue = 2;

	public static int width = 8, height = 8;

	private final int WIDTH;
	private final int HEIGHT;
	private int[] tiles;
	private int[] checkers;
	private int[] selected;
	public int turn;
	private boolean selectedLocked;

	public Board(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		tiles = new int[WIDTH * HEIGHT];
		checkers = new int[tiles.length];
		selectedLocked = false;
	}

	public Board(int width, int height, int[] tiles, int[] checkers) {
		WIDTH = width;
		HEIGHT = height;
		this.tiles = new int[tiles.length];
		this.checkers = new int[checkers.length];
		for (int i = 0; i < tiles.length; i++) {
			this.tiles[i] = tiles[i];
			this.checkers[i] = checkers[i];
		}
		selectedLocked = false;
	}

	private void setTile(int x, int y, int id) {
		tiles[x + y * WIDTH] = id;
	}

	public void render(Screen screen) {
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				getTile(x, y).render(screen, x, y);
				if (hasChecker(x, y)) {
					getChecker(x, y).render(screen, x, y);
				}
			}
		}
	}

	private Checker getChecker(int x, int y) {
		if (x < 0 || x > WIDTH - 1 || y < 0 || y > HEIGHT - 1)
			return Checker.nullCheck;
		if (getCheckerId(x, y) == Checker.red.id)
			return Checker.red;
		else if (getCheckerId(x, y) == Checker.blue.id)
			return Checker.blue;
		else if (getCheckerId(x, y) == Checker.redKing.id)
			return Checker.redKing;
		else if (getCheckerId(x, y) == Checker.blueKing.id)
			return Checker.blueKing;
		else
			return Checker.nullCheck;
	}

	public int getCheckerId(int x, int y) {
		return checkers[x + y * WIDTH];
	}

	public boolean hasChecker(int x, int y) {
		return getChecker(x, y).id != Checker.nullCheck.id;
	}

	private Tile getTile(int x, int y) {
		if (getTileId(x, y) == Tile.black.id) {
			return Tile.black;
		} else if (getTileId(x, y) == Tile.white.id)
			return Tile.white;
		else if (getTileId(x, y) == Tile.yellow.id) {
			return Tile.yellow;
		} else
			return Tile.black;
	}

	private int getTileId(int x, int y) {
		return tiles[x + y * WIDTH];
	}

	public void setUp() {
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				setTile(x, y, getDefaultTile(x, y));
				if (y < 3 && getTileId(x, y) == Tile.white.id) {
					setChecker(x, y, Checker.red.id);
				} else if (y > HEIGHT - 4 && getTileId(x, y) == Tile.white.id) {
					setChecker(x, y, Checker.blue.id);
				}
			}
		}
		int[] turns = new int[2];
		turns[0] = Checker.blue.id;
		turns[1] = Checker.red.id;
		turn = turns[(int) Math.floor(Math.random() * 2)];
	}

	private void setChecker(int x, int y, int id) {
		checkers[x + y * WIDTH] = id;
	}

	public void setSelected(int x, int y) {
		if (hasChecker(x, y) && (getCheckerId(x, y) == turn || getCheckerId(x, y) == turn + 3) && !selectedLocked) {
			if (hasSelected()) {
				setTile(selected[0], selected[1], getDefaultTile(selected[0], selected[1]));
				selected[0] = x;
				selected[1] = y;
				setTile(x, y, Tile.yellow.id);
			} else {
				selected = new int[2];
				selected[0] = x;
				selected[1] = y;
				setTile(x, y, Tile.yellow.id);
			}
		}
	}

	private boolean hasSelected() {
		return selected != null;
	}

	private int getDefaultTile(int x, int y) {
		return (x + y) % 2;
	}

	public boolean canMoveTo(int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7 || hasChecker(x, y) || Math.abs(selected[0] - x) != 1 || (selected[1] - y != 1 && getCheckerId(selected[0], selected[1]) == Checker.blue.id)
				|| (selected[1] - y != -1 && getCheckerId(selected[0], selected[1]) == Checker.red.id) || getDefaultTile(selected[0], selected[1]) != getTileId(x, y) || selectedLocked)
			return false;
		return true;
	}

	public void moveTo(int x, int y) {
		if (!hasSelected()) {
			return;
		}
		if (canMoveTo(x, y)) {
			setChecker(x, y, getCheckerId(selected[0], selected[1]));
			setChecker(selected[0], selected[1], Checker.nullCheck.id);
			setTile(selected[0], selected[1], getDefaultTile(selected[0], selected[1]));
			selected = null;
			if (getCheckerId(x, y) == Checker.red.id && y == HEIGHT - 1) {
				setChecker(x, y, Checker.redKing.id);
			} else if (getCheckerId(x, y) == Checker.blue.id && y == 0) {
				setChecker(x, y, Checker.blueKing.id);
			}
			if (turn == Checker.red.id)
				turn = Checker.blue.id;
			else
				turn = Checker.red.id;
		} else if (canJumpTo(x, y)) {
			setChecker(x, y, getCheckerId(selected[0], selected[1]));
			setChecker(selected[0], selected[1], Checker.nullCheck.id);
			setChecker((selected[0] + x) / 2, (selected[1] + y) / 2, Checker.nullCheck.id);
			setTile(selected[0], selected[1], getDefaultTile(selected[0], selected[1]));
			selected = null;
			if (getCheckerId(x, y) == Checker.red.id && y == HEIGHT - 1) {
				setChecker(x, y, Checker.redKing.id);
			} else if (getCheckerId(x, y) == Checker.blue.id && y == 0) {
				setChecker(x, y, Checker.blueKing.id);
			}
			if (canJump(x, y)) {
				setSelected(x, y);
				selectedLocked = true;
			} else {
				selectedLocked = false;
				selected = null;
				if (turn == Checker.red.id)
					turn = Checker.blue.id;
				else
					turn = Checker.red.id;
			}
		}
	}

	private boolean canJump(int x, int y) {
		setSelected(x, y);
		if (!hasSelected())
			return false;
		setTile(selected[0], selected[1], getDefaultTile(selected[0], selected[1]));
		int id = getCheckerId(x, y);
		if (id == Checker.red.id && (canJumpTo(x - 2, y + 2) || canJumpTo(x + 2, y + 2))) {
			return true;
		}
		if (id == Checker.blue.id && (canJumpTo(x - 2, y - 2) || canJumpTo(x + 2, y - 2))) {
			return true;
		}
		if (id == Checker.redKing.id && (canJumpTo(x - 2, y + 2) || canJumpTo(x + 2, y + 2) || canJumpTo(x + 2, y - 2) || canJumpTo(x - 2, y - 2)))
			return true;
		if (id == Checker.blueKing.id && (canJumpTo(x - 2, y + 2) || canJumpTo(x + 2, y + 2) || canJumpTo(x + 2, y - 2) || canJumpTo(x - 2, y - 2)))
			return true;
		return false;
	}

	private boolean canJumpTo(int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7 || Math.abs(selected[0] - x) != 2 || hasChecker(x, y) || Math.abs(selected[1] - y) != 2
				|| !hasAdverseChecker((selected[0] + x) / 2, (selected[1] + y) / 2, getCheckerId(selected[0], selected[1]))
				|| (getCheckerId(selected[0], selected[1]) == Checker.red.id && selected[1] > y) || (getCheckerId(selected[0], selected[1]) == Checker.blue.id && selected[1] < y))
			return false;
		return true;
	}

	private boolean hasAdverseChecker(int x, int y, int id) {
		if (id == Checker.blue.id || id == Checker.blueKing.id) {
			if (getCheckerId(x, y) == Checker.red.id || getCheckerId(x, y) == Checker.redKing.id) {
				return true;
			}
		} else if (id == Checker.red.id || id == Checker.redKing.id) {
			if (getCheckerId(x, y) == Checker.blue.id || getCheckerId(x, y) == Checker.blueKing.id) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Move> think(int layers, int side) {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (side == Checker.blue.id) {
			for (int y = 0; y < HEIGHT; y++) {
				for (int x = 0; x < WIDTH; x++) {
					if (getCheckerId(x, y) == Checker.blue.id) {
						setSelected(x, y);
						setTile(x, y, getDefaultTile(x, y));
						if (hasSelected()) {
							if (canJumpTo(x - 2, y - 2))
								moves.add(new Move(x, y, x - 2, y - 2));
							if (canJumpTo(x + 2, y - 2))
								moves.add(new Move(x, y, x + 2, y - 2));
							if (canMoveTo(x - 1, y - 1))
								moves.add(new Move(x, y, x - 1, y - 1));
							else if (canMoveTo(x + 1, y - 1))
								moves.add(new Move(x, y, x + 1, y - 1));
						}
					} else if (getCheckerId(x, y) == Checker.blueKing.id) {
						setSelected(x, y);
						setTile(x, y, getDefaultTile(x, y));
						if (hasSelected()) {
							if (canJumpTo(x - 2, y - 2))
								moves.add(new Move(x, y, x - 2, y - 2));
							if (canJumpTo(x + 2, y - 2))
								moves.add(new Move(x, y, x + 2, y - 2));
							if (canJumpTo(x + 2, y + 2))
								moves.add(new Move(x, y, x + 2, y + 2));
							if (canJumpTo(x - 2, y + 2))
								moves.add(new Move(x, y, x - 2, y + 2));
							if (canMoveTo(x - 1, y - 1))
								moves.add(new Move(x, y, x - 1, y - 1));
							if (canMoveTo(x + 1, y - 1))
								moves.add(new Move(x, y, x + 1, y - 1));
							if (canMoveTo(x - 1, y + 1))
								moves.add(new Move(x, y, x - 1, y + 1));
							if (canMoveTo(x + 1, y + 1))
								moves.add(new Move(x, y, x + 1, y + 1));
						}
					}
				}
			}
		} else {
			for (int y = 0; y < HEIGHT; y++) {
				for (int x = 0; x < WIDTH; x++) {
					if (getCheckerId(x, y) == Checker.red.id) {
						setSelected(x, y);
						setTile(x, y, getDefaultTile(x, y));
						if (hasSelected()) {
							if (canJumpTo(x - 2, y + 2))
								moves.add(new Move(x, y, x - 2, y + 2));
							if (canJumpTo(x + 2, y + 2))
								moves.add(new Move(x, y, x + 2, y + 2));
							if (canMoveTo(x - 1, y + 1))
								moves.add(new Move(x, y, x - 1, y + 1));
							if (canMoveTo(x + 1, y + 1))
								moves.add(new Move(x, y, x + 1, y + 1));
						}
					} else if (getCheckerId(x, y) == Checker.redKing.id) {
						setSelected(x, y);
						setTile(x, y, getDefaultTile(x, y));
						if (hasSelected()) {
							if (canJumpTo(x - 2, y - 2))
								moves.add(new Move(x, y, x - 2, y - 2));
							if (canJumpTo(x + 2, y - 2))
								moves.add(new Move(x, y, x + 2, y - 2));
							if (canJumpTo(x + 2, y + 2))
								moves.add(new Move(x, y, x + 2, y + 2));
							if (canJumpTo(x - 2, y + 2))
								moves.add(new Move(x, y, x - 2, y + 2));
							if (canMoveTo(x - 1, y - 1))
								moves.add(new Move(x, y, x - 1, y - 1));
							if (canMoveTo(x + 1, y - 1))
								moves.add(new Move(x, y, x + 1, y - 1));
							if (canMoveTo(x - 1, y + 1))
								moves.add(new Move(x, y, x - 1, y + 1));
							if (canMoveTo(x + 1, y + 1))
								moves.add(new Move(x, y, x + 1, y + 1));
						}
					}
				}
			}
		}
		if (moves.size() > 0) {
			int p = moves.get(0).priority(this, side);
			for (int i = 0; i < moves.size(); i++) {
				if (moves.get(i).priority(this, side) > p) {
					p = moves.get(i).priority(this, side);
				}
			}
			ArrayList<Move> goodMoves = new ArrayList<Move>();
			if (layers > 1) {
				ArrayList<Move> othersMoves;
				int team1, team2;
				if (side == red) {
					team1 = blue;
					team2 = red;
				} else {
					team1 = red;
					team2 = blue;
				}
				int p2 = -10;
				for (int i = 0; i < moves.size(); i++) {
					Board b = copy();
					moves.get(i).execute(b);
					othersMoves = b.think(layers - 1, team1);
					for (int i2 = 0; i2 < othersMoves.size(); i2++) {
						Board b2 = b.copy();
						othersMoves.get(i2).execute(b2);
						ArrayList<Move> moves2 = b2.think(layers - 1, team2);
						for (int i3 = 0; i3 < moves2.size(); i3++) {
							int p3 = moves2.get(i3).priority(b2, team2);
							if (p3 > p2) {
								p2 = p3;
							}
						}
						for (int i3 = 0; i3 < moves2.size(); i3++) {
							int p3 = moves2.get(i3).priority(b2, team2);
							if (p3 == p2) {
								goodMoves.add(moves.get(i));
							}
						}
					}
				}
			}
			for (int i = 0; i < moves.size(); i++) {
				if (moves.get(i).priority(this, side) == p && moves.get(i) != null) {
					goodMoves.add(moves.get(i));
				}
			}
			return goodMoves;
		}
		return moves;
	}

	public Board copy() {
		Board copy = new Board(WIDTH, HEIGHT, tiles, checkers);
		copy.turn = turn;
		return copy;
	}
}