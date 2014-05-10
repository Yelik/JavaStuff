package com.mrizen.darkness;

import com.mrizen.gaming.Main;

public class Game extends Main {
	private static final long serialVersionUID = -7301747131889422012L;

	private Map map;

	public Game(String title, int width, int height, double hScale, double vScale, double updatesPerSecond) {
		super(title, width, height, hScale, vScale, updatesPerSecond);
		map = new Map(64, 64);
	}

	protected void render() {
		map.render(getScreen());
	}

	protected void update() {
		map.update();
	}

	public static void main(String[] args) {
		String title = "Darkness";
		int width = 256;
		int height = width / 16 * 9;
		double updatesPerSecond = 60;
		double hScale = 3;
		double vScale = 3;

		Game game = new Game(title, width, height, hScale, vScale, updatesPerSecond);
		game.start();
	}
}
