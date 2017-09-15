package com.skillet.source.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
	boolean pressed;
	int mouseX, mouseY;
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		pressed = true;
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	
	
}
