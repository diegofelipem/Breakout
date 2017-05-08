package com.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle {

	private int x = 0;
	private final int topY;
	private final int WIDTH = 100;
	private final int HEIGHT = 10;
	private int direction = 0;

	private Board board;

	public Paddle(Board board) {
		this.board = board;
		topY = board.getPreferredSize().height - 50;

	}

	public void move() {
		if (x + direction > 0 && x + direction < board.getWidth() - WIDTH) {
			x = x + direction;
		}
	}

	public void paint(Graphics2D g2) {
		g2.fillRect(x, topY, WIDTH, HEIGHT);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			direction = -5;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			direction = 5;
		}
	}

	public void keyReleased(KeyEvent e) {
		direction = 0;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, topY, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return topY;
	}
}
