import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {
	private static String title = "Checkers";
	private static final long serialVersionUID = 1L;
	private static boolean running;
	private static float scale = (float) 1.5;

	public static int width = Sprite.tileWidth * 8;
	public static int height = Sprite.tileHeight * 8;

	private Thread thread;
	private JFrame frame;
	private Screen screen;
	private int[] pixels;
	private BufferedImage image;
	private Mouse mouse;
	private Board board;
	public static Random rand;

	public Main() {
		Dimension size = new Dimension((int) (width * scale), (int) (height * scale));
		setPreferredSize(size);
		frame = new JFrame();
		screen = new Screen(width, height);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		mouse = new Mouse();
		board = new Board(Board.width, Board.height);
		board.setUp();
		rand = new Random();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 1.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
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

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		board.render(screen);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	private void update() {
		int id;
		if (board.turn == Checker.red.id) {
			id = Checker.red.id;
			/*int x = mouse.getX() / Sprite.tileWidth, y = mouse.getY() / Sprite.tileHeight;
			if (mouse.getLeftButton()) {
				board.setSelected(x, y);
			}
			if (mouse.getRightButton()) {
				board.moveTo(x, y);
			}*/

		} else {
			id = Checker.blue.id;
		}
		ArrayList<Move> moves = board.think(2, id);
		if (moves.size() != 0) {
			moves.get(0).execute(board);
		} else {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.frame.setResizable(false);
		main.frame.setTitle(title);
		main.frame.add(main);
		main.frame.pack();
		main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.frame.setLocationRelativeTo(null);
		main.frame.setVisible(true);
		main.start();
	}
}