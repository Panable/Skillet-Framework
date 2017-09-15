package com.skillet.source;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
	int y = 10;

	public void update() {
		y++;
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(10, y, 12, 80);
	}

}
