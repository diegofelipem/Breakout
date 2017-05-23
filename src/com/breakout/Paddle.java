package com.breakout;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle {

	private int x = 0;
	private int topY;
	public final int WIDTH = 100;
	public final int HEIGHT = 10;
	private int direction = 0;

	private Board board;

	public Paddle(Board board) {
		this.board = board;
		topY = board.getPreferredSize().height - HEIGHT;
		x = board.getPreferredSize().width / 2 - WIDTH / 2;

	}

	public void move() {
		if (x + direction >= 0 && x + direction <= board.getPreferredSize().width - WIDTH) {
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

	public boolean isMoving() {
		return direction != 0;
	}

	public int getDirection() {
		return direction;
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

	public int getX() {
		return x;
	}

	public void reset() {
		topY = board.getPreferredSize().height - HEIGHT;
		x = board.getPreferredSize().width / 2 - WIDTH / 2;
	}
}
