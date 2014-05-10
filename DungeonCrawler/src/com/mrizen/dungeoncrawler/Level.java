package com.mrizen.dungeoncrawler;

import com.mrizen.gaming.KeyReader;
import com.mrizen.gaming.Screen;

public class Level extends Map {
	private Entity player;

	public Level(int width, int height) {
		super(width, height);
		player = new Entity(Sprites.floor2, 0, 0, this);
	}

	public void render(Screen screen) {
		screen.setOffset(-player.getXPos() + screen.getPixels().getWidth() / 2 - 8, -player.getYPos() + screen.getPixels().getHeight() / 2 - 8);
		super.render(screen);
	}

	public void update(KeyReader keys) {
		if (keys.getDown())
			player.addYPos(1);
		if (keys.getLeft())
			player.addXPos(-1);
		if (keys.getRight())
			player.addXPos(1);
		if (keys.getUp())
			player.addYPos(-1);
	}
}
