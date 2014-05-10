package com.mrizen.dungeoncrawler;

import com.mrizen.gaming.*;

public class Game extends Main {
	private static final long serialVersionUID = -5766808531340617772L;
	private Level level;
	private long anim;

	public Game(String title, int width, int height, double scale, double updatesPerSecond) {
		super(title, width, height, scale, updatesPerSecond);
		level = new Level(16, 16);
		anim = Long.MIN_VALUE;
	}

	public void update() {
		anim++;
		level.update(getKeys(), anim);
	}

	public void render() {
		level.render(getScreen());
	}

	public static void main(String[] args) {
		String title = "Crawling";
		int width = 256;
		int height = width / 16 * 9;
		double scale = 3;
		double updatesPerSecond = 60;
		Game game = new Game(title, width, height, scale, updatesPerSecond);
		game.start();
	}
}
