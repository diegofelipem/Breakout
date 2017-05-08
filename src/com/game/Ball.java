package com.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

	private int x = 0;
	private int y = 15;
	private final int DIAMETER = 30;
	private int xSpeed = 1;
	private int ySpeed = 1;

	private Board board;

	public Ball(Board board) {
		this.board = board;
	}

	public void move() {

		if (x > board.getWidth() - DIAMETER || x < 0) {
			xSpeed = -xSpeed;
		}

		if (y < 15) {
			ySpeed = -ySpeed;
		}

		if (y > board.getHeight() - DIAMETER) {
			board.gameOver();
		}

		if (collision()) {
			ySpeed = -ySpeed;
			y = board.racquet.getTopY() - DIAMETER;
		}

		x += xSpeed;
		y += ySpeed;
	}

	public void paint(Graphics2D g2) {
		g2.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public boolean collision() {
		return board.racquet.getBounds().intersects(this.getBounds());
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
