import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = -5461790717007009799L;
	private static Main main;

	private final String title;
	private JFrame frame;
	private Thread thread;
	private int width;
	private int height;
	private int scale;
	private boolean running;
	private Screen screen;
	private Map map;
	private int[] pixels;
	private BufferedImage image;

	public static Random random;

	public Main() {

		int mapWidth = 32, mapHeight = 32;
		scale = 6;
		title = "Rain";

		width = 300;
		height = width / 16 * 9;
		setPreferredSize(new Dimension(width * scale, height * scale));

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		frame = new JFrame();
		screen = new Screen(width, height);
		map = new Map(mapWidth, mapHeight);
	}

	private synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta > 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + ticks + " TPS " + frames + " FPS");
				ticks = 0;
				frames = 0;
			}
		}
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

	private void tick() {
		map.tick();
	}

	public static int getMainWidth() {
		return main.width;
	}

	public static int getMainHeight() {
		return main.height;
	}

	public static Map getMap() {
		return main.map;
	}

	public static void main(String[] args) {
		random = new Random();
		main = new Main();

		main.frame.setResizable(false);
		main.frame.setTitle(main.title);
		main.frame.add(main);
		main.frame.pack();
		main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.frame.setLocationRelativeTo(null);
		main.frame.setVisible(true);

		main.map.generateMap(0);

		main.start();
	}
}
