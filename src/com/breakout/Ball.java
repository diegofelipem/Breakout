package com.breakout;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

	private int x = 0;
	private int y = 15;
	private final int DIAMETER = 30;
	private int xSpeed = 1;
	private int ySpeed = 1;

	private final Board board;
	private final Paddle paddle;
	

	public Ball(Board board) {
		this.board = board;
		this.paddle = board.paddle;
		y = paddle.getTopY() - DIAMETER;
		x = board.WIDTH / 2 - DIAMETER / 2;
	}

	public void move() {

		if (x >= board.getPreferredSize().width - DIAMETER || x <= 0) {
			xSpeed = -xSpeed;
		}

		if (y < 15) {
			ySpeed = -ySpeed;
		}

		if (y + DIAMETER > paddle.getTopY() + paddle.HEIGHT) {
			board.gameOver();
		}

		if (collision()) {
			
			float paddleCenter = paddle.getX() + (paddle.WIDTH/2);
			
			float relativePos = (this.x + (DIAMETER/2) - paddleCenter) / (paddle.WIDTH/2);
			
			if((relativePos > 0 && xSpeed < 0) || (relativePos < 0 && xSpeed > 0)){
				xSpeed = -xSpeed;
			}
			
			ySpeed = -ySpeed;
			y = paddle.getTopY() - DIAMETER;
		}

		x += xSpeed;
		y += ySpeed;
	}

	public void setSpeed(int speed) {
		this.xSpeed = speed;
		this.ySpeed = speed;
	}

	public void paint(Graphics2D g2) {
		g2.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public boolean collision() {
		return paddle.getBounds().intersects(this.getBounds());
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
