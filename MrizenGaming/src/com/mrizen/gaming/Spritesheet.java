package com.mrizen.gaming;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	private Array2D pixels;

	public Spritesheet(String path) {
		try {
			BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
			int width = image.getWidth();
			int height = image.getHeight();
			pixels = new Array2D(width, height);
			image.getRGB(0, 0, width, height, pixels.getArray(), 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Array2D getPixels() {
		return pixels;
	}
}
