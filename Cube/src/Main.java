import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.mrizen.cube.d2.Point2d;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 4648172894076113183L;

	private boolean running;
	private String title;
	private Screen screen;
	private BufferedImage image;
	private int[] pixels;
	private JFrame frame;
	private Thread thread;
	private double updatesPerSecond;
	private KeyReader keys;
	private MouseReader mouse;

	public Main(String title, int width, int height, double scale, double updatesPerSecond) {
		this.title = title;
		this.updatesPerSecond = updatesPerSecond;

		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(this);
		setDimension(width, height, scale);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		screen = new Screen(width, height);

		keys = new KeyReader();
		addKeyListener(keys);

		mouse = new MouseReader(scale);
		addMouseMotionListener(mouse);
		addMouseListener(mouse);

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		running = false;
	}

	public KeyReader getKeys() {
		return keys;
	}

	public MouseReader getMouse() {
		return mouse;
	}

	protected void setDimension(int width, int height, double scale) {
		setPreferredSize(new Dimension((int) (width * scale), (int) (height * scale)));
		frame.pack();
	}

	protected void update() {

	}

	protected void render() {
		screen.drawLine2d(new Point2d(1, 1), new Point2d(1, 1), 0);
	}

	private void renderPrivate() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		render();
		for (int i = 0; i < screen.getPixels().getLength(); i++) {
			pixels[i] = screen.getPixels().getI(i);
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / updatesPerSecond;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta > 1) {
				update();
				updates++;
				delta--;
			}
			renderPrivate();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}

	protected synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	protected synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String title = "Test";
		int width = 300;
		int height = width / 16 * 9;
		int scale = 6;
		double updatesPerSecond = 60;

		Main main = new Main(title, width, height, scale, updatesPerSecond);
		main.start();
	}

	public Screen getScreen() {
		return screen;
	}

	protected void showCursor(boolean show) {
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);
	}
}
