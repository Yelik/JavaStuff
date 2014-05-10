package com.mrizen.cube.d2;

public class Shape2d {
	private Point2d[] points;
	private int color;

	public Shape2d(Point2d[] points, int color) {
		this.points = points;
		this.color = color;
	}

	public Point2d[] getPoints() {
		return points;
	}

	public int getColor() {
		return color;
	}
}
