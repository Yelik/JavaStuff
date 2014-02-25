public class Move {
	private final int startX;
	private final int startY;
	private final int endX;
	private final int endY;

	public Move(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}

	public void execute(Board board) {
		board.setSelected(startX, startY);
		board.moveTo(endX, endY);
	}

	public int priority(Board board, int side) {
		if (side == Checker.blue.id) {
			if (Math.abs(startX - endX) == 2)
				return 2;
			if (board.getCheckerId(startX, startY) == Checker.blue.id && endY == 0)
				return 1;
		} else {
			if (Math.abs(startX - endX) == 2)
				return 2;
			if (board.getCheckerId(startX, startY) == Checker.red.id && endY == 7)
				return 1;
		}
		return 0;
	}

	public boolean equals(Move move) {
		if (startX == move.startX && startY == move.startY && endX == move.endX && endY == move.endY) {
			return true;
		}
		return false;
	}
}