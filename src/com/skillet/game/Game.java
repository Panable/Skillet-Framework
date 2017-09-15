package com.skillet.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import com.skillet.source.Paddle;

public class Game {

	private Handler window;
	private Paddle paddle;
	
	// set your window preferences!
	int width = 1280, height = 720;
	String title = "asd";

	public Game() {
		Dimension size = new Dimension(width, height);
		window = new Handler(size, title, this);

		// Set Your Background Color!
		window.setBackground(Color.PINK);

		// Do you want to see your frame rate?
		window.ShowFrameRate(true);
	}

	public void instantiateClasses() {
		paddle = new Paddle();
	}

	public void update() {
		paddle.update();
	}

	public void render(Graphics graphics) {
		paddle.render(graphics);
	}

	public static void main(String[] args) {
		new Game();
	}

}
