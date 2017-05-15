package com.breakout;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

	private int x = 0;
	private int y = 0;
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

		if (x > board.getPreferredSize().width - DIAMETER || x < 0) {
			xSpeed = -xSpeed;

		} else if (y < 0) {
			ySpeed = -ySpeed;

		} else if (y + DIAMETER > paddle.getTopY() + paddle.HEIGHT) {
			board.gameOver();

		} else if (paddleCollision()) {

			float paddleCenter = paddle.getX() + (paddle.WIDTH / 2);
			float paddleRelativePos = (this.x + (DIAMETER / 2) - paddleCenter) / (paddle.WIDTH / 2);

			if ((paddleRelativePos > 0 && xSpeed < 0) || (paddleRelativePos < 0 && xSpeed > 0)) {
				xSpeed = -xSpeed;
			}

			ySpeed = -ySpeed;
			y = paddle.getTopY() - DIAMETER;
		}

		for (Brick brick : board.wallBricks) {

			if (brickCollision(brick) && !brick.hasBroken()) {

				float brickCenter = brick.getX() + (brick.WIDTH / 2);
				float brickRelativePos = (this.x + (DIAMETER / 2) - brickCenter) / (brick.WIDTH / 2);

				if ((brickRelativePos > 0 && xSpeed < 0) || (brickRelativePos < 0 && xSpeed > 0)) {
					xSpeed = -xSpeed;
				}

				brick.setState(true);
				board.wallBricks.remove(brick);
				ySpeed = -ySpeed;
				break;
			}
		}

		x += xSpeed;
		y += ySpeed;
	}

	public void paint(Graphics2D g2) {
		g2.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public void increaseSpeed() {
		xSpeed = Math.abs(xSpeed) + 1;
		ySpeed = Math.abs(ySpeed) + 1;
	}

	public boolean paddleCollision() {
		return paddle.getBounds().intersects(this.getBounds());
	}

	public boolean brickCollision(Brick b) {
		return this.getBounds().intersects(b.getBounds());
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

	public void reset() {
		x = board.WIDTH / 2 - DIAMETER / 2;
		y = paddle.getTopY() - DIAMETER;
	}
}
