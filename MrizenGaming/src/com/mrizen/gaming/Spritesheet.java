package com.mrizen.gaming;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	public int[] pixels;
	private int width;

	public Spritesheet(String path) {
		try {
			BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
			width = image.getWidth();
			int height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return pixels.length / getWidth();
	}
}
