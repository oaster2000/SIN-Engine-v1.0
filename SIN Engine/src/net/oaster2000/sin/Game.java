package net.oaster2000.sin;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import net.oaster2000.sin.entity.mob.Player;
import net.oaster2000.sin.graphics.Font;
import net.oaster2000.sin.graphics.Screen;
import net.oaster2000.sin.input.Keyboard;
import net.oaster2000.sin.input.Mouse;
import net.oaster2000.sin.level.Level;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int width = 360 * 3;
	private static int height = 190 * 3;
	public static int scale = 1;
	public static int tileSize = 32;
	public static String version = "v1.0";
	public static final String Title = "SIN Engine " + version;
	public static boolean debug = false;
	public static boolean released = true;
	public static boolean releasedSave = true;
	public static Game game = new Game();
	public static Font font = new Font();

	Random random = new Random();

	private boolean running = false;
	private Thread thread;
	private JFrame frame;
	private Screen screen;
	
	public static Level lvl;
	public Player player;
	public Keyboard input;
	public Mouse mouse;

	private boolean playedMusic = false;
	
	private BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
	static Dimension size = new Dimension(width * scale, height * scale);

	public Game() {
		frame = new JFrame();
		screen = new Screen(width, height);
		lvl = new Level("/textures/levels/basic.png");
		input = new Keyboard();
		mouse = new Mouse();
		player = new Player(16, 16, input);
		lvl.add(player);
		
		addKeyListener(input);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}

	public synchronized void start() {
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

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0D;
		int frames = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / 1.6666666666666666E7D;
			lastTime = now;
			while (delta >= 1.0D) {
				tick();
				delta -= 1.0D;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000L) {
				timer += 1000L;

				frame.setTitle(Title + " | " + frames + " fps");
				frames = 0;
			}
		}
		stop();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		
		int xScroll = player.x - screen.width / 2;
		int yScroll =  player.y - screen.height / 2;
		
		lvl.render(xScroll, yScroll, screen);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.GRAY);
		g.dispose();
		bs.show();
	}

	private void tick() {
		if (!playedMusic) {
			playedMusic = true;
		}
		input.tick();
		lvl.update();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Title);
		game.frame.add(game);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.pack();
		game.frame.setSize(size);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
}
