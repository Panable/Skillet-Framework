package com.skillet.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.skillet.source.input.Keyboard;
import com.skillet.source.input.Mouse;

public class Handler extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public Game game;
	
	private int width, height;
	private String title;

	public static boolean running = false;
	private Thread thread;
	public static JFrame frame;

	boolean show_framerate = false;

	public Keyboard key;
	public Mouse mouse;
	
	
	public void initWindow(String title) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public Handler(Dimension size, String title, Game game) {
		if (size.width == 0 || size.height == 0) {
			System.err.println("Specify the width and height of the window!");
			System.exit(0);
		}
		
		this.game = game;
		this.width = size.width;
		this.height = size.height;
		this.title = title;

		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		

		initWindow(title);
		
		key = new Keyboard();
		mouse = new Mouse();
		
		addKeyListener(key);
		addMouseListener(mouse);
		
		game.instantiateClasses();
		
		start();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			System.err.println("Thread failed to join!");
		}
	}

	public void run() {
		this.requestFocus();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;

		int frames = 0;
		int updates = 0;

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				
				if (show_framerate)
					frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				
				frames = 0;
				updates = 0;
			}
		}
		stop();

	}

	public void update() {
		game.update();
	}

	static Color bgc = Color.BLACK;
	public void render() {
		BufferStrategy buffer = getBufferStrategy();
		
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		
		g.setColor(bgc);
		g.fillRect(0, 0, width, height);
		
		game.render(g);
		
		g.dispose();
		buffer.show();
	}

	public static void setBackgroundColor(Color color) {
		bgc = color;
	}

	public void ShowFrameRate(boolean bool) {
		show_framerate = bool;
	}

	

}
