import com.mrizen.cube.d2.Point2d;
import com.mrizen.cube.d2.Shape2d;

public class Screen {
	private Array2D pixels;

	public Screen(int width, int height) {
		pixels = new Array2D(width, height);
	}

	public void clear() {
		for (int i = 0; i < pixels.getLength(); i++) {
			pixels.setI(i, 0);
		}
	}

	public void drawShape2d(Shape2d shape) {
		Point2d[] points = shape.getPoints();
		drawLine2d(points[0], points[1], shape.getColor());
	}

	public void drawLine2d(Point2d pointA, Point2d pointB, int color) {
		float deltaY = pointB.getY() - pointA.getY();
		float deltaX = pointB.getX() - pointA.getX();
		float m;
		if (deltaX == 0)
			m = ;
		else
			m = deltaY / deltaX;
		float b = pointA.getY() - m * pointA.getX();
		System.out.println(m + " " + b);
	}

	public Array2D getPixels() {
		return pixels;
	}
}