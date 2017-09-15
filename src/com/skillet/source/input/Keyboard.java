package com.skillet.source.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	public boolean[] key = new boolean[240];
	
	public boolean checkKey(int keycode) {
		
		
		if (key[keycode])
			return true;
		else
			return false;
	}
	
	public void keyPressed(KeyEvent e) {
		key[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		key[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}

}
