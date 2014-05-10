package com.mrizen.darkness;

import java.util.ArrayList;
import java.util.Random;

import com.mrizen.gaming.Array2D;
import com.mrizen.gaming.Screen;

public class Map {
	private Array2D darkness;
	private Array2D terrain;
	private int darknessSize;
	private int darknessMax;

	public Map(int width, int height) {
		darknessSize = 1;
		darknessMax = 0xFF;
		darkness = new Array2D(width, height);
		terrain = new Array2D(width, height);
	}

	public void renderTile(Screen screen, int xTile, int yTile) {
		int i = 0xFFFFFF;
		i -= darkness.get(xTile, yTile) * 0xFFFFFF / darknessMax;
		screen.drawColor(i, xTile * darknessSize, yTile * darknessSize, darknessSize, darknessSize);
	}

	public void render(Screen screen) {
		screen.setOffset(0, 0);
		for (int yTile = 0; yTile < terrain.getHeight(); yTile++) {
			for (int xTile = 0; xTile < terrain.getWidth(); xTile++) {
				renderTile(screen, xTile, yTile);
			}
		}
	}

	public void update() {
		Random rand = new Random();
		int addin = 5;
		/*for (int y = 0; y < darkness.getHeight(); y++) {
			for (int x = 0; x < darkness.getWidth(); x++) {
				if (rand.nextInt(10) == 0)
					if (darkness.get(x, y) < darknessMax)
						darkness.add(x, y, addin);
			}
		}*/
		darkness.set(0, 0, darknessMax);
		//darkness.set(rand.nextInt(darkness.getWidth()), rand.nextInt(darkness.getHeight()), darknessMax);
		Array2D darkness2 = new Array2D(darkness.getWidth(), darkness.getHeight());
		for (int y = 0; y < darkness.getHeight(); y++) {
			for (int x = 0; x < darkness.getWidth(); x++) {
				ArrayList<Integer> darks = new ArrayList<Integer>();
				darks.add(darkness.get(x, y));
				if (darkness.get(x - 1, y) != -1)
					darks.add(darkness.get(x - 1, y));
				if (darkness.get(x + 1, y) != -1)
					darks.add(darkness.get(x + 1, y));
				if (darkness.get(x, y + 1) != -1)
					darks.add(darkness.get(x, y + 1));
				if (darkness.get(x, y - 1) != -1)
					darks.add(darkness.get(x, y - 1));
				float darkTotal = 0;
				for (int i = 0; i < darks.size(); i++) {
					darkTotal += darks.get(i);
				}
				if (darkTotal > darks.size())
					darkness2.set(x, y, (int) (darkTotal / darks.size()));
				else if (darkTotal != 0) {
					darkness2.set(x, y, 1);
				}
			}
		}
		for (int y = 0; y < darkness.getHeight(); y++) {
			for (int x = 0; x < darkness.getWidth(); x++) {
				darkness.set(x, y, darkness2.get(x, y));
			}
		}
	}
}
