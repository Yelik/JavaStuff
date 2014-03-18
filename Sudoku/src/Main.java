import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 4648172894076113183L;

	private boolean running;
	private String title;
	private Screen screen;
	private Map map;
	private BufferedImage image;
	private int[] pixels;
	private JFrame frame;
	private Thread thread;
	private double updatesPerSecond;

	public Main(String title, int width, int height, int scale, double updatesPerSecond) {
		this.title = title;
		this.updatesPerSecond = updatesPerSecond;

		setPreferredSize(new Dimension(width * scale, height * scale));

		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		screen = new Screen(width, height);

		map = new Map(9, 9);

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		running = false;
	}

	private void update() {

	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		map.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
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
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}

	private synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	private synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String title = "Sudoku";
		int width = 300;
		int height = width / 16 * 9;
		int scale = 6;
		double updatesPerSecond = 2;

		Main main = new Main(title, width, height, scale, updatesPerSecond);
		main.map.generateMap();
		main.start();
	}
}
