package com.mrizen.energy;

import com.mrizen.gaming.KeyReader;
import com.mrizen.gaming.Main;

public class Game extends Main {
	private static final long serialVersionUID = 507915546143071520L;

	private Map map;
	private int[] view;

	public Game(String title, int width, int height, double hScale, double vScale, double updatesPerSecond) {
		super(title, width, height, updatesPerSecond);
		setDimension(width, height, hScale, vScale);
		view = new int[2];
		map = new Map(16, 16);
	}

	protected void render() {
		getScreen().setOffset(-view[0], -view[1]);
		map.render(getScreen());
	}

	protected void update() {
		KeyReader keys = getKeys();
		if (keys.getLeft())
			view[0]--;
		if (keys.getRight())
			view[0]++;
		if (keys.getUp())
			view[1]--;
		if (keys.getDown())
			view[1]++;
		map.update(keys, getMouse());
	}

	public static void main(String[] args) {
		String title = "Energy";
		int width = 256;
		int height = width / 16 * 9;
		double hScale = 3;
		double vScale = 3;
		double updatesPerSecond = 60;
		Game game = new Game(title, width, height, hScale, vScale, updatesPerSecond);
		game.start();
	}
}
